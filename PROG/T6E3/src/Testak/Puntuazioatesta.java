package Testak;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Modeloa.Puntuazioa;
import Modeloa.Taldea;

class Puntuazioatesta {
	
	private ArrayList<Taldea> taldeak;
	private Puntuazioa puntuazioa;

	@BeforeEach
	void setUp() throws Exception {
		taldeak = new ArrayList<>();
		puntuazioa = new Puntuazioa(taldeak);
	}

	@AfterEach
	void tearDown() throws Exception {
		taldeak = null;
		puntuazioa = null;
	}

	@Test
	void testKonstruktoreaEtaGetter() {
		assertEquals(taldeak, puntuazioa.getPuntuazioa());
	}

	@Test
	void testSetPuntuazioa() {
		ArrayList<Taldea> taldeBerriak = new ArrayList<>();
		puntuazioa.setPuntuazioa(taldeBerriak);
		assertEquals(taldeBerriak, puntuazioa.getPuntuazioa());
	}
}