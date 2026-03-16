package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modeloa.*;



class PojoenTestak {

	private Administradorea admin;

	private Denboraldia denboraldia;

	private ArrayList<Jaurdunaldia> jaurdunaldiak;

	private ArrayList<Puntuazioa> puntuazioak;

	private ErabiltzaileMota erabiltzailea;

	private ErabiltzaileNormala erabiltzaile;

	private Jaurdunaldia jaurdunaldia;

	private ArrayList<Partidua> partidos;

	private Jokalaria jokalaria;

	private Partidua partidua;

	private Presidentea presidente;

	private Puntuazioa puntuazioa;

	private ArrayList<Taldea> taldeak;

	private Taldea taldea;

	private ArrayList<Jokalaria> jokalariak;
	
	
	@BeforeEach
	void setUp() throws Exception {
		// Hiru erabiltzaile motak hasieratzen ditugu test bakoitzaren aurretik
		admin = new Administradorea("Aratz", "Elexpe", "12345678A", "aelexpe", "12345");
		presidente = new Presidentea("Karlos", "Arguiñano", "87654321B", "kargui", "54321");
		erabiltzaile = new ErabiltzaileNormala("Jon", "Igartua", "11223344C", "jigar", "11111");

		// --- Denboraldiaren hasieratzea ---
		jaurdunaldiak = new ArrayList<>();
		puntuazioak = new ArrayList<>();
		LocalDate hasieraData = LocalDate.of(2024, 9, 1);
		
		denboraldia = new Denboraldia(hasieraData, jaurdunaldiak, puntuazioak);
		
		// --- Jaurdunaldiaren hasieratzea ---		
		partidos = new ArrayList<>();
		jaurdunaldia = new Jaurdunaldia(partidos);
		taldeak = new ArrayList<>();
		puntuazioa = new Puntuazioa(taldeak);
		
		// --- Partiduaren hasieratzea ---
		LocalDate partidaData = LocalDate.of(2024, 10, 15);
		partidua = new Partidua("Bilbao Basket", "Baskonia", 80, 75,partidaData, "20:00");
		jokalariak = new ArrayList<>();
		LocalDate sorreraData = LocalDate.of(1898, 1, 1);
		taldea = new Taldea("Athletic", sorreraData, "Jon Uriarte", 43000, 45, 30, 60, 18, 5, jokalariak);
		
		// --- Jokalariaren hasieratzea ---
		LocalDate jaiotzeData = LocalDate.of(1999, 2, 28);
		jokalaria = new Jokalaria("Luka", "Doncic", jaiotzeData, "12345678X", "Real Madrid", 5000000, 250);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		// Test bakoitzaren ondoren objektuak garbitzen ditugu memoria askatzeko
		admin = null;
		presidente = null;
		erabiltzaile = null;
		
		denboraldia = null;
		jaurdunaldiak = null;
		puntuazioak = null;
		
		jaurdunaldia = null;
		partidos = null;
				
		puntuazioa = null;
		taldeak = null;		
		
		partidua = null;
		taldea = null;
		jokalariak = null;
		
		
		jokalaria = null;
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
	// ==========================================
	// DENBORALDIA TESTAK
	// ==========================================

	@Test
	void testDenKonstruktoreaEtaGetters() {
		
	assertEquals(LocalDate.of(2024, 9, 1), denboraldia.getData());
	assertEquals(jaurdunaldiak, denboraldia.getDenboraldia());
	assertEquals(puntuazioak, denboraldia.getDenboraldiaP());
	}
	@Test
	void testSetDataDen() {
		
	LocalDate dataBerria = LocalDate.of(2025, 8, 15);
	denboraldia.setData(dataBerria);
	assertEquals(dataBerria, denboraldia.getData());
	}


	@Test
	void testSetDenboraldia() {
		
	ArrayList<Jaurdunaldia> zerrendaBerria = new ArrayList<>();
	denboraldia.setDenboraldia(zerrendaBerria);
	assertEquals(zerrendaBerria, denboraldia.getDenboraldia());
	}

	@Test
	void testSetDenboraldiaP() {
			
	ArrayList<Puntuazioa> zerrendaBerriaP = new ArrayList<>();
	denboraldia.setDenboraldiaP(zerrendaBerriaP);
	assertEquals(zerrendaBerriaP, denboraldia.getDenboraldiaP());
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

	// ==========================================
	// PUNTUAZIOA TESTAK
	// ==========================================

	@Test
	void testPuKonstruktoreaEtaGetters() {
	
	assertEquals(taldeak, puntuazioa.getPuntuazioa());
	}

	@Test
	void testSetPuntuazioa() {
			
	ArrayList<Taldea> zerrendaBerria = new ArrayList<>();
	puntuazioa.setPuntuazioa(zerrendaBerria);
			
			
	assertEquals(zerrendaBerria, puntuazioa.getPuntuazioa());
	}
	
	// ==========================================
	// PARTIDUA TESTAK
	// ==========================================

	@Test
	void testPaKonstruktoreaEtaGetters() {
			
	assertEquals("Bilbao Basket", partidua.getTaldeLokala());
	assertEquals("Baskonia", partidua.getTaldeBisitari());
	assertEquals(80, partidua.getResultLokala());
	assertEquals(75, partidua.getResulBisitari());
	assertEquals(LocalDate.of(2024, 10, 15), partidua.getData());
	assertEquals("20:00", partidua.getOrdua());
	}

	@Test
	void testSetTaldeLokala() {
	partidua.setTaldeLokala("Gipuzkoa Basket");
	assertEquals("Gipuzkoa Basket", partidua.getTaldeLokala());
	}

	@Test
	void testSetTaldeBisitari() {
	partidua.setTaldeBisitari("Real Madrid");
	assertEquals("Real Madrid", partidua.getTaldeBisitari());
	}

	@Test
	void testSetResultLokala() {
	partidua.setResultLokala(85);
	assertEquals(85, partidua.getResultLokala());
	}

	@Test
	void testSetResulBisitari() {
	partidua.setResulBisitari(82);
	assertEquals(82, partidua.getResulBisitari());
	}

	@Test
	void testSetDataPa() {
	LocalDate dataBerria = LocalDate.of(2024, 11, 20);
	partidua.setData(dataBerria);
	assertEquals(dataBerria, partidua.getData());
		}

	@Test
	void testSetOrduaPa() {
	partidua.setOrdua("18:30");
	assertEquals("18:30", partidua.getOrdua());
	}

	// ==========================================
	// TALDEA TESTAK
	// ==========================================

	@Test
	void testTaKonstruktoreaEtaGetters() {
			
	assertEquals("Athletic", taldea.getIzena());
	assertEquals(LocalDate.of(1898, 1, 1), taldea.getSorreraUrtea());
	assertEquals("Jon Uriarte", taldea.getLehendakari());
	assertEquals(43000, taldea.getN_Bazkideak());
	assertEquals(45, taldea.getPuntuakF());
	assertEquals(30, taldea.getPuntuakC());
	assertEquals(60, taldea.getPuntuTotalak());
	assertEquals(18, taldea.getIrabazitakoak());
	assertEquals(5, taldea.getGaldutakoak());
	assertEquals(jokalariak, taldea.getJokalariak());
	}

	@Test
	void testSetIzenaTa() {
	taldea.setIzena("Real Sociedad");
	assertEquals("Real Sociedad", taldea.getIzena());
	}

	@Test
	void testSetLehendakariTa() {
	taldea.setLehendakari("Jokin Aperribay");
	assertEquals("Jokin Aperribay", taldea.getLehendakari());
	}

	@Test
	void testSetNBazkideakTa() {
	taldea.setN_Bazkideak(35000);
	assertEquals(35000, taldea.getN_Bazkideak());
	}

	@Test
	void testSetPuntuakFTa() {
	taldea.setPuntuakF(50);
	assertEquals(50, taldea.getPuntuakF());
	}

	@Test
	void testSetPuntuakCTa() {
	taldea.setPuntuakC(25);
	assertEquals(25, taldea.getPuntuakC());
	}

	@Test
	void testSetPuntuTotalakTa() {
	taldea.setPuntuTotalak(65);
	assertEquals(65, taldea.getPuntuTotalak());
	}

	@Test
	void testSetIrabazitakoakTa() {
	taldea.setIrabazitakoak(20);
	assertEquals(20, taldea.getIrabazitakoak());
	}

	@Test
	void testSetGaldutakoakTa() {
	taldea.setGaldutakoak(3);
	assertEquals(3, taldea.getGaldutakoak());
	}

	@Test
	void testSetJokalariakTa() {
	ArrayList<Jokalaria> jokalariBerriak = new ArrayList<>();
	taldea.setJokalariak(jokalariBerriak);
	assertEquals(jokalariBerriak, taldea.getJokalariak());
	}
	
	// ==========================================
	// JOKALARIA TESTAK
	// ==========================================

	@Test
	void testJoKonstruktoreaEtaGetters() {
			
	assertEquals("Luka", jokalaria.getIzena());
	assertEquals("Doncic", jokalaria.getAbizena());
	assertEquals(LocalDate.of(1999, 2, 28), jokalaria.getJaiotzeData());
	assertEquals("12345678X", jokalaria.getNAN());
	assertEquals("Real Madrid", jokalaria.getTaldea());
	assertEquals(5000000, jokalaria.getPrezioa());
	assertEquals(250, jokalaria.getJokalarienPuntuak());
	}

	@Test
	void testSetIzenaJo() {
	jokalaria.setIzena("Facundo");
	assertEquals("Facundo", jokalaria.getIzena());
	}

	@Test
	void testSetAbizenaJo() {
	jokalaria.setAbizena("Campazzo");
	assertEquals("Campazzo", jokalaria.getAbizena());
	}

	@Test
	void testSetJaiotzeDataJo() {
	LocalDate jaiotzeDataBerria = LocalDate.of(1991, 3, 23);
	jokalaria.setJaiotzeData(jaiotzeDataBerria);
	assertEquals(jaiotzeDataBerria, jokalaria.getJaiotzeData());
	}

		@Test
		void testSetNANJo() {
			jokalaria.setNAN("87654321Z");
			assertEquals("87654321Z", jokalaria.getNAN());
		}

		@Test
		void testSetTaldeaJo() {
			jokalaria.setTaldea("Baskonia");
			assertEquals("Baskonia", jokalaria.getTaldea());
		}

		@Test
		void testSetPrezioaJo() {
			jokalaria.setPrezioa(3000000);
			assertEquals(3000000, jokalaria.getPrezioa());
		}

		@Test
		void testSetJokalarienPuntuakJo() {
			jokalaria.setJokalarienPuntuak(300);
			assertEquals(300, jokalaria.getJokalarienPuntuak());
		}

		@Test
		void testKopiaKonstruktoreaJo() {
			// Kopia konstruktoreak (beste jokalari bat jasotzen duenak) ondo funtzionatzen duela egiaztatu
			Jokalaria kopia = new Jokalaria(jokalaria);
			
			assertEquals(jokalaria.getIzena(), kopia.getIzena());
			assertEquals(jokalaria.getAbizena(), kopia.getAbizena());
			assertEquals(jokalaria.getNAN(), kopia.getNAN());
		}
}
