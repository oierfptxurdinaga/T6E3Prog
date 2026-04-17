package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modeloa.Partidua;

class Partidahasieraztekotesta {

	private Partidua partiduaDataOrduarekin;
	private Partidua partiduaIdArekin;

	@BeforeEach
	void setUp() throws Exception {
		partiduaDataOrduarekin = new Partidua("Baskonia", "Bilbao Basket", 85, 70, LocalDate.of(2024, 10, 15), "18:00");
		partiduaIdArekin = new Partidua(1, "Real Madrid", "Barcelona", 90, 85);
	}

	@AfterEach
	void tearDown() throws Exception {
		partiduaDataOrduarekin = null;
		partiduaIdArekin = null;
	}

	@Test
	void testKonstruktoreaDataOrduarekin() {
		assertEquals("Baskonia", partiduaDataOrduarekin.getTaldeLokala());
		assertEquals("Bilbao Basket", partiduaDataOrduarekin.getTaldeBisitari());
		assertEquals(85, partiduaDataOrduarekin.getResultLokala());
		assertEquals(70, partiduaDataOrduarekin.getResulBisitari());
		assertEquals(LocalDate.of(2024, 10, 15), partiduaDataOrduarekin.getData());
		assertEquals("18:00", partiduaDataOrduarekin.getOrdua());
	}

	@Test
	void testKonstruktoreaIdArekin() {
		assertEquals(1, partiduaIdArekin.getIdPar());
		assertEquals("Real Madrid", partiduaIdArekin.getTaldeLokala());
		assertEquals("Barcelona", partiduaIdArekin.getTaldeBisitari());
		assertEquals(90, partiduaIdArekin.getResultLokala());
		assertEquals(85, partiduaIdArekin.getResulBisitari());
	}

	@Test
	void testSetters() {
		partiduaDataOrduarekin.setIdPar(10);
		assertEquals(10, partiduaDataOrduarekin.getIdPar());
		partiduaDataOrduarekin.setTaldeLokala("Gipuzkoa");
		assertEquals("Gipuzkoa", partiduaDataOrduarekin.getTaldeLokala());
		partiduaDataOrduarekin.setTaldeBisitari("Joventut");
		assertEquals("Joventut", partiduaDataOrduarekin.getTaldeBisitari());
		partiduaDataOrduarekin.setResultLokala(100);
		assertEquals(100, partiduaDataOrduarekin.getResultLokala());
		partiduaDataOrduarekin.setResulBisitari(99);
		assertEquals(99, partiduaDataOrduarekin.getResulBisitari());
		partiduaDataOrduarekin.setData(LocalDate.of(2025, 1, 1));
		assertEquals(LocalDate.of(2025, 1, 1), partiduaDataOrduarekin.getData());
		partiduaDataOrduarekin.setOrdua("20:30");
		assertEquals("20:30", partiduaDataOrduarekin.getOrdua());
	}
}