package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DB.ConexionDB;
import Modeloa.Taldea;

/**
 * TaldeakDAO klasea datu-baseko taldeak kudeatzeko erabiltzen da.
 */
public class TaldeakDAO {

	private ConexionDB conexionDB = new ConexionDB();
	
	/**
	 * Datu-basean dauden talde guztiak eta haien informazioa lortzen ditu.
	 * * @return Taldeen zerrenda.
	 */
	public ArrayList<Taldea> lortuTaldeak() {
        ArrayList<Taldea> listaTaldeak = new ArrayList<>();
        
        Connection conn = conexionDB.conectar();

        if (conn == null) return listaTaldeak;

        String sql = "SELECT * FROM taldeak"; 

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                
                String taldeIzena = rs.getString("Talde_Izena");
                LocalDate sorreraUrtea = rs.getDate("Sorrera_Data").toLocalDate(); 
                String lehendakariIzena = rs.getString("Lehendakari_Izena");
                int nBazkideak = rs.getInt("N_Bazkideak");
                Taldea taldea = new Taldea(taldeIzena, sorreraUrtea, lehendakariIzena, nBazkideak); 
                
                listaTaldeak.add(taldea);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            conexionDB.desconectar();
        }
        
        return listaTaldeak;
    }
}