package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.ConexionDB;

/**
 * JaurdunaldiaDAO klasea datu-baseko jaurdunaldiak kudeatzeko erabiltzen da.
 */
public class JaurdunaldiaDAO {

    private ConexionDB conexionDB = new ConexionDB();
    
    /**
     * Datu-basean erregistratuta dauden jaurdunaldien zenbaki guztiak lortzen ditu, 
     * orden gorakorrean (ASC).
     * * @return Jaurdunaldien zenbakien zerrenda.
     */
    public ArrayList<Integer> lortuJaurdunaldienZenbakiak() {
        ArrayList<Integer> listaZenbakiak = new ArrayList<>();
        
        Connection conn = conexionDB.conectar();
        
        if (conn == null) return listaZenbakiak;

        String sql = "SELECT Zenbakia FROM jaurdunaldia ORDER BY Zenbakia ASC"; 

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                
                int zenbakia = rs.getInt("Zenbakia");
                
                listaZenbakiak.add(zenbakia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            conexionDB.desconectar();
        }
        
        return listaZenbakiak;
    }
}