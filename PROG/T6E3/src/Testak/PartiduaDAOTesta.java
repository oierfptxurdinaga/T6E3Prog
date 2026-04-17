package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DAO.PartiduaDAO;
import DB.ConexionDB;
import Modeloa.Partidua;

class PartiduaDAOTesta {

    private PartiduaDAO dao;
    private ConexionDB conexionDB;
    
    // IDs y nombres cortos para evitar errores de truncado o duplicado
    private final int TEST_PARTIDUA_ID = 9999;
    private final int TEST_JAURDUNALDIA_ID = 9999;
    private final int TEST_DENBORALDIA_ID = 1; 
    
    private final String TEST_LOKALA = "TLok";
    private final String TEST_BISITARIA = "TBis";
    private final String TEST_LEH_LOK = "1111A"; // DNI corto y único
    private final String TEST_LEH_BIS = "2222B"; // DNI corto y único

    @BeforeEach
    void setUp() throws Exception {
        dao = new PartiduaDAO();
        conexionDB = new ConexionDB();
        
        // Limpiar antes de empezar
        ezabatuTestDatuak();
        
        Connection conn = conexionDB.conectar();
        if (conn != null) {
            try {
                // 1. Insertar Taldeak (con datos mínimos para cumplir restricciones)
                String sqlT = "INSERT INTO taldeak (Talde_Izena, Sorrera_Data, Lehendakari_Izena, N_Bazkideak, Lehendakari_NAN) VALUES (?, '2000-01-01', 'Presi', 100, ?)";
                PreparedStatement pstmtT = conn.prepareStatement(sqlT);
                
                pstmtT.setString(1, TEST_LOKALA);
                pstmtT.setString(2, TEST_LEH_LOK);
                pstmtT.executeUpdate();
                
                pstmtT.setString(1, TEST_BISITARIA);
                pstmtT.setString(2, TEST_LEH_BIS);
                pstmtT.executeUpdate();

                // 2. Insertar Jaurdunaldia
                String sqlJ = "INSERT INTO jaurdunaldia (Id_Jaurdu, Zenbakia, Id_Denbo) VALUES (?, 1, ?)";
                PreparedStatement pstmtJ = conn.prepareStatement(sqlJ);
                pstmtJ.setInt(1, TEST_JAURDUNALDIA_ID);
                pstmtJ.setInt(2, TEST_DENBORALDIA_ID);
                pstmtJ.executeUpdate();
                
                // 3. Insertar Partidua
                String sqlP = "INSERT INTO partiduak (Id_Par, Talde_Lok, Talde_Bis, Id_Jaurdu, ordutegia) VALUES (?, ?, ?, ?, '2024-01-01 10:00:00')";
                PreparedStatement pstmtP = conn.prepareStatement(sqlP);
                pstmtP.setInt(1, TEST_PARTIDUA_ID);
                pstmtP.setString(2, TEST_LOKALA);
                pstmtP.setString(3, TEST_BISITARIA);
                pstmtP.setInt(4, TEST_JAURDUNALDIA_ID);
                pstmtP.executeUpdate();
                
            } catch (Exception e) {
                System.err.println("Error preparando el entorno de test: " + e.getMessage());
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
                // El orden inverso es CRUCIAL por las Foreign Keys
                conn.prepareStatement("DELETE FROM partiduak WHERE Id_Par = " + TEST_PARTIDUA_ID).executeUpdate();
                conn.prepareStatement("DELETE FROM jaurdunaldia WHERE Id_Jaurdu = " + TEST_JAURDUNALDIA_ID).executeUpdate();
                conn.prepareStatement("DELETE FROM taldeak WHERE Talde_Izena IN ('" + TEST_LOKALA + "', '" + TEST_BISITARIA + "')").executeUpdate();
            } catch (Exception e) {
                // Silenciamos el error en el borrado para no ensuciar el resultado del test
            } finally {
                conexionDB.desconectar();
            }
        }
    }

    // --- MÉTODOS DE TEST ---

    @Test
    void testLortuPartiduGuztiak() {
        ArrayList<Partidua> list = dao.lortuPartiduGuztiak();
        assertNotNull(list);
        // Verificamos que al menos el que hemos insertado aparece
        boolean encontrado = list.stream().anyMatch(p -> p.getIdPar() == TEST_PARTIDUA_ID);
        assertTrue(encontrado, "El partido de prueba debería existir en la lista.");
    }

    @Test
    void testEguneratuEmaitza() {
        // Probamos a actualizar el partido que sabemos que existe
        int filas = dao.eguneratuEmaitza(TEST_PARTIDUA_ID, 3, 0);
        assertEquals(1, filas, "Debería haberse actualizado una fila.");
    }
}