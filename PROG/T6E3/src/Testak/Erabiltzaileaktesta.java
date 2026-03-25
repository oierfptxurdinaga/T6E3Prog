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
			//	 Hiru erabiltzaile motak hasieratzen ditugu test bakoitzaren aurretik
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
	void testAdmKonstruktoreaEtaGetters() {
		assertEquals("Aratz", admin.getIzena());
		assertEquals("Elexpe", admin.getAbizena());
		assertEquals("12345678A", admin.getNAN());
		assertEquals("aelexpe", admin.getErabiltzailea());
		assertEquals("12345", admin.getPasahitza());
	}

	@Test
	void testSetIzenaAdm() {
		admin.setIzena("Carlos");
		assertEquals("Carlos", admin.getIzena());
	}

	@Test
	void testSetAbizenaAdm() {
		admin.setAbizena("Gomez");
		assertEquals("Gomez", admin.getAbizena());
	}

	@Test
	void testSetErabiltzaileaAdm() {
		admin.setErabiltzailea("carlosg");
		assertEquals("carlosg", admin.getErabiltzailea());
	}

	@Test
	void testSetPasahitzaAdm() {
		admin.setPasahitza("abcd");
		assertEquals("abcd", admin.getPasahitza());
	}

	@Test
	void testBaimenakAdm() {
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
			assertEquals("kargui", presidente.getErabiltzailea());
			assertEquals("54321", presidente.getPasahitza());
		}

		@Test
		void testSetIzenaPr() {
			presidente.setIzena("Karlos Berria");
			assertEquals("Karlos Berria", presidente.getIzena());
		}

		@Test
		void testSetAbizenaPr() {
			presidente.setAbizena("Zubizarreta");
			assertEquals("Zubizarreta", presidente.getAbizena());
		}

		@Test
		void testSetErabiltzaileaPr() {
			presidente.setErabiltzailea("kzubiza");
			assertEquals("kzubiza", presidente.getErabiltzailea());
		}

		@Test
		void testSetPasahitzaPr() {
			presidente.setPasahitza("berria123");
			assertEquals("berria123", presidente.getPasahitza());
		}

		@Test
		void testBaimenakPr() {
			assertEquals("Presidentea", presidente.baimenak());
		}

		// ==========================================
		// ERABILTZAILE NORMALA (ARRUNTA) TESTAK
		// ==========================================

		@Test
		void testENKonstruktoreaEtaGetters() {
			assertEquals("Jon", erabiltzaile.getIzena());
			assertEquals("Igartua", erabiltzaile.getAbizena());
			assertEquals("11223344C", erabiltzaile.getNAN());
			assertEquals("jigar", erabiltzaile.getErabiltzailea());
			assertEquals("11111", erabiltzaile.getPasahitza());
		}

		@Test
		void testSetIzenaEN() {
			erabiltzaile.setIzena("Mikel");
			assertEquals("Mikel", erabiltzaile.getIzena());
		}

		@Test
		void testSetAbizenaEN() {
			erabiltzaile.setAbizena("Landa");
			assertEquals("Landa", erabiltzaile.getAbizena());
		} 

		@Test
		void testSetErabiltzaileaEN() {
			erabiltzaile.setErabiltzailea("mlanda");
			assertEquals("mlanda", erabiltzaile.getErabiltzailea());
		}

		@Test
		void testSetPasahitzaEN() {
			erabiltzaile.setPasahitza("bizikleta");
			assertEquals("bizikleta", erabiltzaile.getPasahitza());
		}

		@Test
		void testBaimenakEN() {
			assertEquals("Arrunta", erabiltzaile.baimenak());
}	
}
