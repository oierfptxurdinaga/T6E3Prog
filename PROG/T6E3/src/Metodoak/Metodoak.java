package Metodoak;

import java.time.LocalDate;
import java.util.ArrayList;

import DAO.ErabiltzaileakDao;
import Modeloa.ErabiltzaileMota;
import Modeloa.Jokalaria;
import Modeloa.Partidua;
import Modeloa.Taldea;

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

	public static ArrayList<Taldea> klasifikasioaKalkulatu(ArrayList<Partidua> partiduakEmaitzekin){
		
		ArrayList<Taldea> klasifikasioa = new ArrayList<>();
		
		for(Partidua p : partiduakEmaitzekin) {
			if(p.getResulBisitari() != null && p.getResultLokala() != null) {
				Taldea talL = new Taldea(p.getTaldeBisitari(), LocalDate.now(), "", 0);
				if(!klasifikasioa.contains(talL)) {
					talL.setPuntuakF(p.getResulBisitari());
					talL.setPuntuakC(p.getResultLokala());
					if(p.getResulBisitari()>p.getResultLokala()) {
						talL.setPuntuTotalak(3);
						talL.setIrabazitakoak(1);
					} else if(p.getResulBisitari()==p.getResultLokala()) {
						talL.setPuntuTotalak(1);
					} else {
						talL.setGaldutakoak(1);
					}
									
					klasifikasioa.add(talL);
				} else {
					for(Taldea kla : klasifikasioa) {
							if(kla.equals(talL)) {
								kla.setPuntuakF(kla.getPuntuakF()+p.getResultLokala());
								kla.setPuntuakC(kla.getPuntuakC()+p.getResulBisitari());
								if(p.getResulBisitari()>p.getResultLokala()) {
									kla.setPuntuTotalak(3+kla.getPuntuTotalak());
									kla.setIrabazitakoak(1+kla.getIrabazitakoak());
								} else if(p.getResulBisitari()==p.getResultLokala()) {
									kla.setPuntuTotalak(1+kla.getPuntuTotalak());
								} else {
									kla.setGaldutakoak(1+kla.getGaldutakoak());
								}
							}
					}
				}
				
				Taldea talB = new Taldea(p.getTaldeLokala(), LocalDate.now(), "", 0);
				if(!klasifikasioa.contains(talB)) {
					talB.setPuntuakF(p.getResultLokala());
					talB.setPuntuakC(p.getResulBisitari());
					if(p.getResultLokala()>p.getResulBisitari()) {
						talB.setPuntuTotalak(3);
						talB.setIrabazitakoak(1);
					} else if(p.getResulBisitari()==p.getResultLokala()) {
						talB.setPuntuTotalak(1);
					} else {
						talB.setGaldutakoak(1);
					}
									
					klasifikasioa.add(talB);
				} else {
					for(Taldea kla : klasifikasioa) {
							if(kla.equals(talB)) {
								kla.setPuntuakF(kla.getPuntuakF()+p.getResulBisitari());
								kla.setPuntuakC(kla.getPuntuakC()+p.getResultLokala());
								if(p.getResultLokala()>p.getResulBisitari()) {
									kla.setPuntuTotalak(3+kla.getPuntuTotalak());
									kla.setIrabazitakoak(1+kla.getIrabazitakoak());
								} else if(p.getResulBisitari()==p.getResultLokala()) {
									kla.setPuntuTotalak(1+kla.getPuntuTotalak());
								} else {
									kla.setGaldutakoak(1+kla.getGaldutakoak());
								}
							}
						}
				}
			}
		}
		
		return klasifikasioa;
	}
}
