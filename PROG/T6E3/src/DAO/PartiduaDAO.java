package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import DB.ConexionDB;
import Modeloa.Partidua;

public class PartiduaDAO {

    private ConexionDB conexionDB = new ConexionDB();

    // -------------------------------------------------------------------
    // Partiduak lortzeko metodoa, jornadako zenbakia erabiliz
    // -------------------------------------------------------------------
    public ArrayList<Partidua> lortuJaurdunaldikoPartiduak(int jornadaZenbakia) {
    	
        ArrayList<Partidua> listaPartiduak = new ArrayList<>();
        Connection conn = conexionDB.conectar();

        if (conn == null) return listaPartiduak;

        
        String sql = "SELECT p.* FROM partiduak p " +
                     "JOIN jaurdunaldia j ON p.Id_Jaurdu = j.Id_Jaurdu " +
                     "WHERE j.Zenbakia = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, jornadaZenbakia); 
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int idPar = rs.getInt("Id_Par");
                String taldeLok = rs.getString("Talde_Lok");
                String taldeBis = rs.getString("Talde_Bis");
                
                
                int resLokDB = rs.getInt("Result_Lok");
                Integer resultLok = rs.wasNull() ? null : resLokDB;

                int resBisDB = rs.getInt("Result_Bis");
                Integer resultBis = rs.wasNull() ? null : resBisDB;

                
                Partidua partidua = new Partidua(idPar, taldeLok, taldeBis, resultLok, resultBis);
                listaPartiduak.add(partidua);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionDB.desconectar();
        }

        return listaPartiduak;
    }

 // -------------------------------------------------------------------
    // Partiduaren emaitzak eguneratzeko metodoa (aldatutako errenkadak bueltatzen ditu)
    // -------------------------------------------------------------------
    public int eguneratuEmaitza(int idPar, Integer resultLok, Integer resultBis) {
        int filasModificadas = 0; 
        Connection conn = conexionDB.conectar();

        if (conn == null) return -1; 

        String sql = "UPDATE partiduak SET Result_Lok = ?, Result_Bis = ? WHERE Id_Par = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            
            if (resultLok == null) {
                pstmt.setNull(1, Types.INTEGER);
            } else {
                pstmt.setInt(1, resultLok);
            }

            
            if (resultBis == null) {
                pstmt.setNull(2, Types.INTEGER);
            } else {
                pstmt.setInt(2, resultBis);
            }

            pstmt.setInt(3, idPar); 

            
            filasModificadas = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexionDB.desconectar();
        }

        return filasModificadas; 
    }
}