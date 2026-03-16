package Kontrolatzailea;


import java.util.ArrayList;
import javax.persistence.*;

import Modeloa.Administradorea;
import Modeloa.ErabiltzaileNormala;
import Modeloa.Presidentea;

public class ErabiltzaileakKargatu {

    public static void main(String[] args) {
        
        // 1. Datu-basearen fitxategiaren izena definitu
        String artxiboa = "erabiltzaileak.odb";

        // 2. Datuen egitura orokorra sortu (ArrayList erabiliz)
        ArrayList<Object> gordeBeharrekoDatuak = new ArrayList<>();

        // 3. Datuak "hardcodeatu" 
        Administradorea admin = new Administradorea("Eder", "Bilbao", "11111111A", "admin", "12345");
        Presidentea presi = new Presidentea("Ekaitz", "Alonso", "22222222B", "presi", "12345");
        ErabiltzaileNormala arrunta = new ErabiltzaileNormala("Oier", "Garcia", "33333333C", "arrunta", "12345");

        // Objektuak zerrendara gehitu
        gordeBeharrekoDatuak.add(admin);
        gordeBeharrekoDatuak.add(presi);
        gordeBeharrekoDatuak.add(arrunta);

        // =========================================================================
        // 4. OBJECTDB KONEXIOA ETA GORDETZE PROZESUA
        // =========================================================================
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(artxiboa);
        EntityManager em = emf.createEntityManager();

        try {
            // Transakzioa hasi
            em.getTransaction().begin();
            
            // Zerrendako objektu guztiak datu-basean sartu
            for (Object obj : gordeBeharrekoDatuak) {
                em.persist(obj);
            }
            
            // Aldaketak baieztatu eta fitxategian gorde
            em.getTransaction().commit();

        } catch (Exception e) {
            // Errore bat gertatzen bada, egindako aldaketak desegin
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Konexioak beti itxi
            em.close();
            emf.close();
        }
    }
}