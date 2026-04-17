package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modeloa.Denboraldia;
import Modeloa.Jaurdunaldia;
import Modeloa.Puntuazioa;

class Denboraldiatestak {
	private Denboraldia denboraldia;
	private ArrayList<Jaurdunaldia> jaurdunaldiak;
	private ArrayList<Puntuazioa> puntuazioak;
	
	@BeforeEach
	void setUp() throws Exception {
		jaurdunaldiak = new ArrayList<>();
		puntuazioak = new ArrayList<>();
		LocalDate hasieraData = LocalDate.of(2024, 9, 1);
		
		denboraldia = new Denboraldia(hasieraData, jaurdunaldiak, puntuazioak);
	}

	@AfterEach
	void tearDown() throws Exception {
		jaurdunaldiak = null;
		puntuazioak = null;
		denboraldia = null;
	}

	@Test
	void testDenKonstruktoreaEtaGetters() {
		assertEquals(LocalDate.of(2024, 9, 1), denboraldia.getData());
		assertEquals(jaurdunaldiak, denboraldia.getDenboraldia());
		assertEquals(puntuazioak, denboraldia.getDenboraldiaP());
	}
	
	@Test
	void testSetters() {
		LocalDate dataBerria = LocalDate.of(2025, 8, 15);
		denboraldia.setData(dataBerria);
		assertEquals(dataBerria, denboraldia.getData());
		
		ArrayList<Jaurdunaldia> jBerria = new ArrayList<>();
		denboraldia.setDenboraldia(jBerria);
		assertEquals(jBerria, denboraldia.getDenboraldia());
		
		ArrayList<Puntuazioa> pBerria = new ArrayList<>();
		denboraldia.setDenboraldiaP(pBerria);
		assertEquals(pBerria, denboraldia.getDenboraldiaP());
	}
}