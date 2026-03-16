package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import DB.ConexionDB;
import Modeloa.Jokalaria;
import Modeloa.Taldea;

public class JokalariakDAO {

	private ConexionDB conexionDB = new ConexionDB();
	
	public ArrayList<Jokalaria> lortuJokalariak() {
        ArrayList<Jokalaria> listaJokalariak = new ArrayList<>();
        
        Connection conn = conexionDB.conectar();

        if (conn == null) return listaJokalariak;

        String sql = "SELECT * FROM jokalariak"; 

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String Izena = rs.getString("Jok_Izena");
                String Abizena = rs.getString("Jok_Abizena");
                LocalDate JaiotzeData = rs.getDate("Jaio_Data").toLocalDate();
                String NAN = rs.getString("NAN");
                String Taldea = rs.getString("Talde_Izena");
                int Prezioa = rs.getInt("Merka_Prezioa"); 
                int JokalarienPuntuak = 0; 
                
                
                Jokalaria jokalaria = new Jokalaria(Izena, Abizena, JaiotzeData, NAN, Taldea, Prezioa, JokalarienPuntuak);
                
                listaJokalariak.add(jokalaria);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            conexionDB.desconectar();
        }
        
        return listaJokalariak;
    }
}

