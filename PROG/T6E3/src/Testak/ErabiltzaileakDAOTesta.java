package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DAO.ErabiltzaileakDao;
import Modeloa.ErabiltzaileMota;
import Modeloa.ErabiltzaileNormala;

class ErabiltzaileakDaoTesta {

    private ErabiltzaileakDao dao;
    private ErabiltzaileNormala testErabiltzailea;
    
    private final String TEST_NAN = "99999999T"; 

    @BeforeEach
    void setUp() throws Exception {
        dao = new ErabiltzaileakDao();
        
       testErabiltzailea = new ErabiltzaileNormala("TestIzena", "TestAbizena", TEST_NAN, "testErab", "testPass");
        
         dao.ezabatuErabiltzailea(TEST_NAN);
    }

    @AfterEach
    void tearDown() throws Exception {
        dao.ezabatuErabiltzailea(TEST_NAN);
    }

    @Test
    void testGehituEtaLortuErabiltzaileak() {
        dao.gehituErabiltzailea(testErabiltzailea);

        ArrayList<ErabiltzaileMota> lista = dao.lortuErabiltzaileakODB();

        assertNotNull(lista);
        assertTrue(lista.size() > 0);

        boolean aurkitua = false;
        for (ErabiltzaileMota e : lista) {
            if (e.getNAN().equals(TEST_NAN)) {
                aurkitua = true;
                assertEquals("TestIzena", e.getIzena());
                assertEquals("TestAbizena", e.getAbizena());
                assertEquals("testErab", e.getErabiltzailea());
                assertEquals("testPass", e.getPasahitza());
                break;
            }
        }
        assertTrue(aurkitua, "Gehitutako erabiltzailea datu-basean egon beharko litzateke.");
    }

    @Test
    void testAldatuErabiltzailea() {
        dao.gehituErabiltzailea(testErabiltzailea);

        dao.aldatuErabiltzailea(TEST_NAN, "IzenBerria", "AbizenBerria", "erabBerria", "passBerria");

        ArrayList<ErabiltzaileMota> lista = dao.lortuErabiltzaileakODB();
        
        boolean aurkitua = false;
        for (ErabiltzaileMota e : lista) {
            if (e.getNAN().equals(TEST_NAN)) {
                aurkitua = true;
                assertEquals("IzenBerria", e.getIzena());
                assertEquals("AbizenBerria", e.getAbizena());
                assertEquals("erabBerria", e.getErabiltzailea());
                assertEquals("passBerria", e.getPasahitza());
                break;
            }
        }
        assertTrue(aurkitua, "Erabiltzailea datu-basean egon beharko litzateke aldaketak egiaztatzeko.");
    }

    @Test
    void testEzabatuErabiltzailea() {
        dao.gehituErabiltzailea(testErabiltzailea);

        dao.ezabatuErabiltzailea(TEST_NAN);

        ArrayList<ErabiltzaileMota> lista = dao.lortuErabiltzaileakODB();
        
        boolean aurkitua = false;
        for (ErabiltzaileMota e : lista) {
            if (e.getNAN().equals(TEST_NAN)) {
                aurkitua = true;
                break;
            }
        }
        assertFalse(aurkitua, "Ezabatu ondoren, erabiltzailea ez litzateke datu-basean agertu behar.");
    }
}