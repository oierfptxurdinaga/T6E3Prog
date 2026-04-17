package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import DAO.JaurdunaldiaDAO;
import DB.ConexionDB;

class JaurdunaldiaDAOTesta {

    private JaurdunaldiaDAO dao;
    private ConexionDB conexionDB;
    
    // Número inventado para la jornada del test (muy alto para no pisar las reales)
    private final int TEST_ZENBAKIA = 9999; 
    
    // ID de una temporada que exista en tu base de datos (normalmente el 1 existe)
    private final int TEST_DENBORALDIA_ID = 1;

    @BeforeEach
    void setUp() throws Exception {
        dao = new JaurdunaldiaDAO();
        conexionDB = new ConexionDB();
        
        // 1. Limpiamos por si quedó basura de un test anterior
        ezabatuTestJaurdunaldia();
        
        // 2. Creamos la jornada en la base de datos
        Connection conn = conexionDB.conectar();
        if (conn != null) {
            try {
                // AHORA: Insertamos Id_Jaurdu, Zenbakia Y el Id_Denbo
                String sql = "INSERT INTO jaurdunaldia (Id_Jaurdu, Zenbakia, Id_Denbo) VALUES (?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, TEST_ZENBAKIA); 
                pstmt.setInt(2, TEST_ZENBAKIA);
                pstmt.setInt(3, TEST_DENBORALDIA_ID); // Le asignamos la temporada 1
                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.desconectar();
            }
        }
    }

    @AfterEach
    void tearDown() throws Exception {
        // Al terminar (falle o pase), borramos la jornada de test
        ezabatuTestJaurdunaldia();
    }

    /**
     * Método auxiliar para borrar la jornada de prueba de la BD.
     */
    private void ezabatuTestJaurdunaldia() {
        Connection conn = conexionDB.conectar();
        if (conn != null) {
            try {
                String sql = "DELETE FROM jaurdunaldia WHERE Id_Jaurdu = ?";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, TEST_ZENBAKIA);
                pstmt.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionDB.desconectar();
            }
        }
    }

    // ==========================================
    // 1. LORTU JAURDUNALDIAK TESTA
    // ==========================================
    @Test
    void testLortuJaurdunaldienZenbakiak() {
        ArrayList<Integer> zenbakiak = dao.lortuJaurdunaldienZenbakiak();
        
        assertNotNull(zenbakiak, "La lista no debería ser null");
        assertTrue(zenbakiak.size() > 0, "La lista debería tener al menos un elemento");
        
        assertTrue(zenbakiak.contains(TEST_ZENBAKIA), "La lista debe contener la jornada inventada (9999)");
        
        boolean ondoOrdenatuta = true;
        for (int i = 0; i < zenbakiak.size() - 1; i++) {
            if (zenbakiak.get(i) > zenbakiak.get(i + 1)) {
                ondoOrdenatuta = false;
                break;
            }
        }
        assertTrue(ondoOrdenatuta, "La consulta SQL debe ordenar los datos de forma ascendente (ASC)");
    }
}