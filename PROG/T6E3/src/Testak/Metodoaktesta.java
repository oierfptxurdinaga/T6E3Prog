package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Metodoak.Metodoak;
import Modeloa.Partidua;
import Modeloa.Taldea;

class Metodoaktesta {

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testBalioztatuEmaitzak() {
        assertNull(Metodoak.balioztatuEmaitzak("", ""));
        
        assertEquals("Bi kutxak hutsik edo biak beteta egon behar dute.", Metodoak.balioztatuEmaitzak("1", ""));
        assertEquals("Bi kutxak hutsik edo biak beteta egon behar dute.", Metodoak.balioztatuEmaitzak("", "2"));
        
        assertEquals("Ezin dira gol negatiboak sartu.", Metodoak.balioztatuEmaitzak("-1", "2"));
        assertEquals("Ezin dira gol negatiboak sartu.", Metodoak.balioztatuEmaitzak("1", "-2"));
        
        assertEquals("Zenbakiak soilik onartzen dira.", Metodoak.balioztatuEmaitzak("a", "2"));
        assertEquals("Zenbakiak soilik onartzen dira.", Metodoak.balioztatuEmaitzak("1", "b"));
        
        assertNull(Metodoak.balioztatuEmaitzak("2", "1"));
        assertNull(Metodoak.balioztatuEmaitzak("0", "0"));
    }

    @Test
    void testKlasifikasioaKalkulatu() {
        ArrayList<Partidua> partiduak = new ArrayList<>();
        
        partiduak.add(new Partidua(1, "Talde1", "Talde2", 80, 70));
        
        partiduak.add(new Partidua(2, "Talde1", "Talde3", 60, 90));
        
        partiduak.add(new Partidua(3, "Talde2", "Talde3", 75, 75));
        
        partiduak.add(new Partidua(4, "Talde4", "Talde5", null, null));

        ArrayList<Taldea> klasifikazioa = Metodoak.klasifikasioaKalkulatu(partiduak);

        assertNotNull(klasifikazioa);
        assertEquals(3, klasifikazioa.size(), "Jokatu ez duten taldeak ez dira klasifikazioan sartzen");

        boolean t1Aurkitua = false;
        for (Taldea t : klasifikazioa) {
            if (t.getIzena().equals("Talde1")) {
                assertEquals(3, t.getPuntuTotalak());
                assertEquals(1, t.getIrabazitakoak());
                assertEquals(1, t.getGaldutakoak());
                assertEquals(140, t.getPuntuakF());
                assertEquals(160, t.getPuntuakC());
                t1Aurkitua = true;
            } else if (t.getIzena().equals("Talde2")) {
                assertEquals(1, t.getPuntuTotalak());
                assertEquals(0, t.getIrabazitakoak());
                assertEquals(1, t.getGaldutakoak());
            } else if (t.getIzena().equals("Talde3")) {
                assertEquals(4, t.getPuntuTotalak());
                assertEquals(1, t.getIrabazitakoak());
                assertEquals(0, t.getGaldutakoak());
            }
        }
        assertTrue(t1Aurkitua);
    }

    @Test
    void testIdatziLog() {
        File logFitxategia = new File("Login_Erregistroa.log");
        if (logFitxategia.exists()) {
            logFitxategia.delete(); // Test garbia izateko
        }
        
        Metodoak.idatziLog("Test Izena", "testErabiltzailea", "Admin");
        
        assertTrue(logFitxategia.exists(), "Login_Erregistroa.log fitxategia sortu behar da.");
    }

    @Test
    void testSortuXMLFitxategia() {
        File xmlFitxategia = new File("LigaDatuak.xml");
        if (xmlFitxategia.exists()) {
            xmlFitxategia.delete();
        }
        
        Metodoak.sortuXMLFitxategia();
        
        assertTrue(xmlFitxategia.exists(), "LigaDatuak.xml fitxategia sortu behar da exekutatu ondoren.");
    }

    @Test
    void testLogin() {
        String baimena = Metodoak.login("erabiltzaileAsmatuaXX", "pasahitzAsmatuaXX");
        assertNull(baimena, "Datu-basean existitzen ez den erabiltzaile bat sartzean null itzuli behar du.");
    }
}