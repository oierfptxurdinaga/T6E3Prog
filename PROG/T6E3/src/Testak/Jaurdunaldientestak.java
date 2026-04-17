package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modeloa.Jaurdunaldia;
import Modeloa.Partidua;

class Jaurdunaldientestak {
	private ArrayList<Partidua> partidos;
	private Jaurdunaldia jaurdunaldia;

	@BeforeEach
	void setUp() throws Exception {
		partidos = new ArrayList<>();
		jaurdunaldia = new Jaurdunaldia(partidos);
	}

	@AfterEach
	void tearDown() throws Exception {
		partidos = null;
		jaurdunaldia = null;
	}

	@Test
	void testJaKonstruktoreaEtaGetters() {
		assertEquals(partidos, jaurdunaldia.getJaurdunaldia());
	}
	
	@Test
	void testSetJaurdunaldia() {
		ArrayList<Partidua> nuevosPartidos = new ArrayList<>();
		nuevosPartidos.add(new Partidua(1, "A", "B", 80, 80));
		jaurdunaldia.setJaurdunaldia(nuevosPartidos);
		assertEquals(nuevosPartidos, jaurdunaldia.getJaurdunaldia());
	}
}