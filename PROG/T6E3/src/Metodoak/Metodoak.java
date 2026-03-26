package Metodoak;

import java.util.ArrayList;

import DAO.ErabiltzaileakDao;
import Modeloa.ErabiltzaileMota;


public class Metodoak {

	// ErabiltzaileakDao objektua datu-baseko erabiltzaileak kudeatzeko
	public static ErabiltzaileakDao edao = new ErabiltzaileakDao();
	
	// Erabiltzaileak login metodoa erabiltzailearen autentifikazioa egiteko
	public static String login (String erabiltzailea, String pasahitza) {
		ArrayList<ErabiltzaileMota> erabiltzaileaklist = edao.lortuErabiltzaileakODB();
		
		for (ErabiltzaileMota e : erabiltzaileaklist) {
			if (e.getErabiltzailea().equals(erabiltzailea) && e.getPasahitza().equals(pasahitza)) {
				return e.baimenak();
			}
		}
		return null;
	}
	
    // Emaitzak balioztatzeko metodoa
public static String balioztatuEmaitzak(String txtLok, String txtBis) {
        
        String mezua = null; // Berez, ez dago errorerik (null)

        // 1. BALIDAZIOA: "Todo o nada"
        if ((txtLok.isEmpty() && !txtBis.isEmpty()) || (!txtLok.isEmpty() && txtBis.isEmpty())) {
            mezua = "Bi kutxak hutsik edo biak beteta egon behar dute.";
        } 
        // 2. BALIDAZIOA: Zenbakiak izatea (Bakarrik biak beteta badaude sartuko da hona)
        else if (!txtLok.isEmpty() && !txtBis.isEmpty()) {
            try {
                int golesLocal = Integer.parseInt(txtLok);
                int golesVisitante = Integer.parseInt(txtBis);

                // 3. BALIDAZIOA: Positiboak izatea
                if (golesLocal < 0 || golesVisitante < 0) {
                    mezua = "Ezin dira gol negatiboak sartu.";
                }
            } catch (NumberFormatException ex) {
                mezua = "Zenbakiak soilik onartzen dira (adib: 3).";
            }
        }
        
        // Amaieran mezu bakarra itzultzen dugu (errorea badago testua, bestela null)
        return mezua; 
    }

}
