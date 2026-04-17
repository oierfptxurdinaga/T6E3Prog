package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DAO.JokalariakDAO;
import DB.ConexionDB;
import Modeloa.Jokalaria;

class JokalariakDAOTesta {

    private JokalariakDAO dao;
    private ConexionDB conexionDB;

    // Datos de prueba (usamos un NAN y Equipo que no estén en tu script para no chocar)
    private final String TEST_NAN = "88888888Z";
    private final String TEST_TALDEA = "TestTeam"; 
    private final String TEST_IZENA = "Jon";
    private final String TEST_ABIZENA = "Doe";

    @BeforeEach
    void setUp() throws Exception {
        dao = new JokalariakDAO();
        conexionDB = new ConexionDB();

        // Limpieza previa
        ezabatuTestDatuak();

        Connection conn = conexionDB.conectar();
        if (conn != null) {
            try {
                // 1. Insertar Equipo (Siguiendo tu esquema: Talde_Izena, Sorrera_Data, Lehendakari_Izena, Lehendakari_NAN, N_Bazkideak)
                String sqlTaldea = "INSERT INTO taldeak (Talde_Izena, Sorrera_Data, Lehendakari_Izena, Lehendakari_NAN, N_Bazkideak) " +
                                   "VALUES (?, '2026-01-01', 'Presi Test', '00000000T', 100)";
                PreparedStatement pstmtT = conn.prepareStatement(sqlTaldea);
                pstmtT.setString(1, TEST_TALDEA);
                pstmtT.executeUpdate();

                // 2. Insertar Jugador (Usando tus nombres reales: Jok_Izena, Jok_Abizena, Jaio_Data, Merka_Prezioa, Talde_Izena, Posizioa)
                String sqlJokalaria = "INSERT INTO jokalariak (NAN, Jok_Izena, Jok_Abizena, Jaio_Data, Merka_Prezioa, Talde_Izena, Posizioa) " +
                                      "VALUES (?, ?, ?, '2000-01-01', 50000.00, ?, 'Ala')";
                PreparedStatement pstmtJ = conn.prepareStatement(sqlJokalaria);
                pstmtJ.setString(1, TEST_NAN);
                pstmtJ.setString(2, TEST_IZENA);
                pstmtJ.setString(3, TEST_ABIZENA);
                pstmtJ.setString(4, TEST_TALDEA);
                pstmtJ.executeUpdate();

            } catch (Exception e) {
                System.err.println("Error preparando datos: " + e.getMessage());
            } finally {
                conexionDB.desconectar();
            }
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        ezabatuTestDatuak();
    }

    private void ezabatuTestDatuak() {
        Connection conn = conexionDB.conectar();
        if (conn != null) {
            try {
                // Borrar primero el jugador y luego el equipo
                conn.prepareStatement("DELETE FROM jokalariak WHERE NAN = '" + TEST_NAN + "'").executeUpdate();
                conn.prepareStatement("DELETE FROM taldeak WHERE Talde_Izena = '" + TEST_TALDEA + "'").executeUpdate();
            } catch (Exception e) {
                // Silencioso
            } finally {
                conexionDB.desconectar();
            }
        }
    }

    @Test
    void testLortuJokalariak() {
        ArrayList<Jokalaria> jokalariak = dao.lortuJokalariak();
        
        assertNotNull(jokalariak, "La lista no debe ser null");
        
        boolean encontrado = false;
        for (Jokalaria j : jokalariak) {
            if (TEST_NAN.equals(j.getNAN())) {
                encontrado = true;
                // Verificamos que los datos se mapean bien al objeto Modeloa.Jokalaria
                assertEquals(TEST_IZENA, j.getIzena());
                assertEquals(TEST_ABIZENA, j.getAbizena());
                break;
            }
        }
        assertTrue(encontrado, "Debería encontrar el jugador de prueba insertado.");
    }
}