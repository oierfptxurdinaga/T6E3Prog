package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modeloa.Jokalaria;
import Modeloa.Taldea;

class Taldeaktesta {

	private Taldea taldeaOsoa;
	private Taldea taldeaMotza;
	private LocalDate data;

	@BeforeEach
	void setUp() throws Exception {
		data = LocalDate.of(1959, 1, 1);
		ArrayList<Jokalaria> jokalariak = new ArrayList<>();
		
		// 1. Konstruktore osoa (10 parametro)
		taldeaOsoa = new Taldea("Baskonia", data, "Josean Querejeta", 10000, 80, 70, 10, 5, 2, jokalariak);
		
		// 2. Konstruktore motza (4 parametro)
		taldeaMotza = new Taldea("Bilbao Basket", LocalDate.of(2000, 1, 1), "Isabel Iturbe", 8000);
	}

	@AfterEach
	void tearDown() throws Exception {
		taldeaOsoa = null;
		taldeaMotza = null;
		data = null;
	}

	// ==========================================
	// KONSTRUKTOREAK ETA GETTERS
	// ==========================================
	@Test
	void testKonstruktoreakEtaGetters() {
		// Konstruktore osoaren datuak egiaztatu
		assertEquals("Baskonia", taldeaOsoa.getIzena());
		assertEquals(data, taldeaOsoa.getSorreraUrtea());
		assertEquals("Josean Querejeta", taldeaOsoa.getLehendakari());
		assertEquals(10000, taldeaOsoa.getN_Bazkideak());
		assertEquals(80, taldeaOsoa.getPuntuakF());
		assertEquals(70, taldeaOsoa.getPuntuakC());
		assertEquals(10, taldeaOsoa.getPuntuTotalak());
		assertEquals(5, taldeaOsoa.getIrabazitakoak());
		assertEquals(2, taldeaOsoa.getGaldutakoak());
		assertNotNull(taldeaOsoa.getJokalariak());

		// Konstruktore motzaren hasieratze lehenetsia egiaztatu (dena 0 edo hutsik egoten da)
		assertEquals("Bilbao Basket", taldeaMotza.getIzena());
		assertEquals(0, taldeaMotza.getPuntuakF());
		assertEquals(0, taldeaMotza.getPuntuTotalak());
		assertNotNull(taldeaMotza.getJokalariak());
		assertEquals(0, taldeaMotza.getJokalariak().size());
	}

	// ==========================================
	// SETTERS
	// ==========================================
	@Test
	void testSetters() {
		taldeaMotza.setIzena("Gipuzkoa Basket");
		taldeaMotza.setLehendakari("Nacho Nuñez");
		taldeaMotza.setN_Bazkideak(5000);
		taldeaMotza.setPuntuakF(100);
		taldeaMotza.setPuntuakC(90);
		taldeaMotza.setPuntuTotalak(20);
		taldeaMotza.setIrabazitakoak(10);
		taldeaMotza.setGaldutakoak(5);
		
		ArrayList<Jokalaria> jBerriak = new ArrayList<>();
		taldeaMotza.setJokalariak(jBerriak);

		assertEquals("Gipuzkoa Basket", taldeaMotza.getIzena());
		assertEquals("Nacho Nuñez", taldeaMotza.getLehendakari());
		assertEquals(5000, taldeaMotza.getN_Bazkideak());
		assertEquals(100, taldeaMotza.getPuntuakF());
		assertEquals(90, taldeaMotza.getPuntuakC());
		assertEquals(20, taldeaMotza.getPuntuTotalak());
		assertEquals(10, taldeaMotza.getIrabazitakoak());
		assertEquals(5, taldeaMotza.getGaldutakoak());
		assertEquals(jBerriak, taldeaMotza.getJokalariak());
	}

	// ==========================================
	// TO STRING (Begiizta / Bucle barne hartzeko)
	// ==========================================
	@Test
	void testToString() {
		// Jokalari bat gehitzen diogu for begiztatik pasa dadin (Coverage 100%)
		Jokalaria j = new Jokalaria("Jon", "Arregi", LocalDate.of(1995, 1, 1), "12345678Z", "Baskonia", 500);
		taldeaOsoa.getJokalariak().add(j);
		
		String emaitza = taldeaOsoa.toString();
		
		assertTrue(emaitza.contains("Baskonia"));
		assertTrue(emaitza.contains("Josean Querejeta"));
		assertTrue(emaitza.contains("10000"));// Jokalaria inprimatu den ziurtatzen du
	}

	// ==========================================
	// EQUALS (Adar guztiak: null, class mota, izena null)
	// ==========================================
	@Test
	void testEquals() {
		Taldea taldeaBerdina = new Taldea("Baskonia", data, "Josean Querejeta", 10000);
		Taldea taldeaEzberdina = new Taldea("Zaragoza", data, "Presi", 5000);
		
		// 1. Objektu bera
		assertTrue(taldeaOsoa.equals(taldeaOsoa));
		
		// 2. Null edo beste klase batekoa
		assertFalse(taldeaOsoa.equals(null));
		assertFalse(taldeaOsoa.equals(new Object()));
		
		// 3. Izen desberdinak
		assertFalse(taldeaOsoa.equals(taldeaEzberdina));
		
		// 4. Izen berdinak
		assertTrue(taldeaOsoa.equals(taldeaBerdina));
		
		// 5. Izena null denean (Coverage igotzeko adar hau sartzen da equals-en barruan)
		Taldea taldeaIzenaNull1 = new Taldea(null, data, "L1", 100);
		Taldea taldeaIzenaNull2 = new Taldea(null, data, "L2", 200);
		assertTrue(taldeaIzenaNull1.equals(taldeaIzenaNull2)); // biak null
		assertFalse(taldeaIzenaNull1.equals(taldeaOsoa));      // bat null, bestea ez
	}

	// ==========================================
	// COMPARE TO (Irabazitakoak, PuntuakF, PuntuakC...)
	// ==========================================
	@Test
	void testCompareTo() {
		Taldea t1 = new Taldea("T1", data, "L1", 1000);
		Taldea t2 = new Taldea("T2", data, "L2", 1000);

		// Dena berdina denean (0 itzuli behar du)
		t1.setPuntuTotalak(10); t1.setIrabazitakoak(5); t1.setPuntuakF(80); t1.setPuntuakC(70);
		t2.setPuntuTotalak(10); t2.setIrabazitakoak(5); t2.setPuntuakF(80); t2.setPuntuakC(70);
		assertEquals(0, t1.compareTo(t2));

		// 1. PuntuTotalak ezberdinak direnean (T2-k puntu gehiago, beraz T2 aurretik doa)
		t2.setPuntuTotalak(15);
		assertTrue(t1.compareTo(t2) > 0); 
		
		// 2. PuntuTotalak berdinak, baina Irabazitakoak ezberdinak
		t2.setPuntuTotalak(10); // Berdindu puntuak
		t2.setIrabazitakoak(8); // T2-k partida gehiago irabazi ditu
		assertTrue(t1.compareTo(t2) > 0);

		// 3. Puntuak eta Irabazitakoak berdinak, baina PuntuakF ezberdinak
		t2.setIrabazitakoak(5); // Berdindu irabazitakoak
		t2.setPuntuakF(90);     // T2-k aldeko gol/puntu gehiago
		assertTrue(t1.compareTo(t2) > 0);

		// 4. Dena berdina PuntuakC izan ezik (Aurkako puntuak Gutxiago -> Hobea)
		t2.setPuntuakF(80); // Berdindu PuntuakF
		t2.setPuntuakC(50); // T2-k aurkako gol/puntu GUTXIAGO (hobeto defendatzen du)
		// compareTo honela dago eginda código-an: Integer.compare(this.PuntuakC, o.PuntuakC) -> 70 vs 50 -> Positiboa
		assertTrue(t1.compareTo(t2) > 0);
	}
}