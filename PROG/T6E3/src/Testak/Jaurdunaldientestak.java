package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modeloa.Jaurdunaldia;
import Modeloa.Partidua;
import Modeloa.Puntuazioa;
import Modeloa.Taldea;

class Jaurdunaldientestak {
	private ArrayList<Partidua> partidos;
	private Jaurdunaldia jaurdunaldia;
	private ArrayList<Taldea> taldeak;
	private Puntuazioa puntuazioa;


	@BeforeEach
	void setUp() throws Exception {
			partidos = new ArrayList<>();
			jaurdunaldia = new Jaurdunaldia(partidos);
			taldeak = new ArrayList<>();
			puntuazioa = new Puntuazioa(taldeak);
	}

	@AfterEach
	void tearDown() throws Exception {
		partidos = null;
		jaurdunaldia = null;
		taldeak = null;
		puntuazioa = null;
	}
	// ==========================================
		// JAURDUNALDIA TESTAK
		// ==========================================

		@Test
		void testJaKonstruktoreaEtaGetters() {
		assertEquals(partidos, jaurdunaldia.getJaurdunaldia());
		}

		@Test
		void testSetJaurdunaldia() {
				
		ArrayList<Partidua> zerrendaBerria = new ArrayList<>();
		jaurdunaldia.setJaurdunaldia(zerrendaBerria);		
		assertEquals(zerrendaBerria, jaurdunaldia.getJaurdunaldia());
		}


	@Test
	void test() {
		fail("Not yet implemented");

	}

}
