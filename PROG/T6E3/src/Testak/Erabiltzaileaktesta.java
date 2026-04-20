package Testak;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modeloa.Administradorea;
import Modeloa.ErabiltzaileNormala;
import Modeloa.Presidentea;

class Erabiltzaileaktesta {
	
	private Presidentea presidente;
	private Administradorea admin;
	private ErabiltzaileNormala erabiltzaile;
	
	@BeforeEach
	void setUp() throws Exception {
		admin = new Administradorea("Aratz", "Elexpe", "12345678A", "aelexpe", "12345");
		presidente = new Presidentea("Karlos", "Arguiñano", "87654321B", "kargui", "54321");
		erabiltzaile = new ErabiltzaileNormala("Jon", "Igartua", "11223344C", "jigar", "11111");
	}

	@AfterEach
	void tearDown() throws Exception {
		admin = null;
		presidente = null;
		erabiltzaile = null;
	}

	// ==========================================
	// ADMINISTRADOREA TESTAK
	// ==========================================
	@Test
	void testAdminKonstruktoreaEtaGetters() {
		assertEquals("Aratz", admin.getIzena());
		assertEquals("Elexpe", admin.getAbizena());
		assertEquals("12345678A", admin.getNAN());
		assertEquals("aelexpe", admin.getErabiltzailea());
		assertEquals("12345", admin.getPasahitza());
	}

	@Test
	void testSettersAdmin() {
		admin.setIzena("Iker");
		assertEquals("Iker", admin.getIzena());
		admin.setAbizena("Muniain");
		assertEquals("Muniain", admin.getAbizena());
		admin.setNAN("99887766Z");
		assertEquals("99887766Z", admin.getNAN());
		admin.setErabiltzailea("imuniain");
		assertEquals("imuniain", admin.getErabiltzailea());
		admin.setPasahitza("passBerria");
		assertEquals("passBerria", admin.getPasahitza());
	}

	@Test
	void testBaimenakAdmin() {
		assertEquals("Admin", admin.baimenak());
	}

	// ==========================================
	// PRESIDENTEA TESTAK
	// ==========================================
	@Test
	void testPrKonstruktoreaEtaGetters() {
		assertEquals("Karlos", presidente.getIzena());
		assertEquals("Arguiñano", presidente.getAbizena());
		assertEquals("87654321B", presidente.getNAN());
	}
	
	@Test
	void testBaimenakPr() {
		assertEquals("Presidentea", presidente.baimenak());
	}

	// ==========================================
	// ERABILTZAILE NORMALA TESTAK
	// ==========================================
	@Test
	void testENKonstruktoreaEtaGetters() {
		assertEquals("Jon", erabiltzaile.getIzena());
		assertEquals("11111", erabiltzaile.getPasahitza());
	}

	@Test
	void testBaimenakEN() {
		assertEquals("Arrunta", erabiltzaile.baimenak());
	}
}