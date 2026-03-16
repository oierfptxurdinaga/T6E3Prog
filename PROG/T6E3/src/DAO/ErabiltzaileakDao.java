package DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Modeloa.ErabiltzaileMota;



public class ErabiltzaileakDao {

	public ArrayList<ErabiltzaileMota> lortuErabiltzaileakODB() {
        // 1. ArrayList-a sortu erabiltzaileak gordetzeko
        ArrayList<ErabiltzaileMota> listaErabiltzaileak = new ArrayList<>();
        
        // 2. EntityManagerFactory eta EntityManager sortu ObjectDB-rekin konektatzeko
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("erabiltzaileak.odb");
        EntityManager em = emf.createEntityManager();

        try {
            // 3. Kontsulta prestatu (Aitaren klaseari deituz, seme guztiak lortuko ditugu)
            TypedQuery<ErabiltzaileMota> query = em.createQuery("SELECT em FROM ErabiltzaileMota em", ErabiltzaileMota.class);
            
            // 4. Emaitzak lortu
            List<ErabiltzaileMota> resultados = query.getResultList();
            
            // 5. List-etik ArrayList-era pasa
            listaErabiltzaileak.addAll(resultados);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. Beti itxi EntityManager eta EntityManagerFactory
            if (em != null) em.close();
            if (emf != null) emf.close();
        }

        return listaErabiltzaileak;
    }
}
