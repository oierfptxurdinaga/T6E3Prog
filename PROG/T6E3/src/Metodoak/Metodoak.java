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

}
