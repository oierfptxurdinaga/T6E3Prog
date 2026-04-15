package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import Modeloa.ErabiltzaileMota;

public class ErabiltzaileakDao {

	public ArrayList<ErabiltzaileMota> lortuErabiltzaileakODB() {
        ArrayList<ErabiltzaileMota> listaErabiltzaileak = new ArrayList<>();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("erabiltzaileak.odb");
        
        // Cachea garbitu beti datu berrienak (erreak) lortzeko datu-basetik
        emf.getCache().evictAll(); 
        
        EntityManager em = emf.createEntityManager();

        try {
            TypedQuery<ErabiltzaileMota> query = em.createQuery("SELECT em FROM ErabiltzaileMota em", ErabiltzaileMota.class);
            List<ErabiltzaileMota> resultados = query.getResultList();
            listaErabiltzaileak.addAll(resultados);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null) em.close();
            if (emf != null) emf.close();
        }

        return listaErabiltzaileak;
    }

    public void gehituErabiltzailea(ErabiltzaileMota erabiltzailea) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("erabiltzaileak.odb");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(erabiltzailea);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    // BERRIA: Funtzio hau aldatu dugu JPA Query bat erabiltzeko. Modu honetan %100ean gordeko ditu aldaketak.
    public void aldatuErabiltzailea(String nan, String izenBerria, String abizenBerria, String erabBerria, String pasahitzBerria) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("erabiltzaileak.odb");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            
            // Zuzenean datu-baseari agindua bidali dena aldatzeko, cache-a edo "find" metodoak saihestuz
            Query query = em.createQuery(
                "UPDATE ErabiltzaileMota e SET e.Izena = :izen, e.Abizena = :abizen, e.Erabiltzailea = :erab, e.Pasahitza = :pasa WHERE e.NAN = :nan"
            );
            
            query.setParameter("izen", izenBerria);
            query.setParameter("abizen", abizenBerria);
            query.setParameter("erab", erabBerria);
            query.setParameter("pasa", pasahitzBerria);
            query.setParameter("nan", nan.trim()); // trim() erabili zuriuneak kentzeko badaezpada
            
            int eguneratuak = query.executeUpdate(); // Zenbat lerro aldatu diren itzultzen du
            
            em.getTransaction().commit();
            
            if (eguneratuak == 0) {
                System.out.println("KONTUZ: Ez da inor eguneratu. Ziurtatu NAN-a ondo dagoela: " + nan);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    public void ezabatuErabiltzailea(String nan) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("erabiltzaileak.odb");
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            ErabiltzaileMota erab = em.find(ErabiltzaileMota.class, nan.trim());
            if (erab != null) {
                em.remove(erab);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}