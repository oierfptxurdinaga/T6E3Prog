package Testak;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import Modeloa.Jokalaria;
import java.time.LocalDate;

class jokalarientesta {

    private Jokalaria jokalaria;
    private LocalDate fecha;

    @BeforeEach
    void setUp() {
        fecha = LocalDate.of(1995, 5, 20);
        jokalaria = new Jokalaria("Jon", "Arregi", fecha, "12345678Z", "Bilbao Basket", 50000);
    }

    @Test
    void testConstructorYGetters() {
        assertEquals("Jon", jokalaria.getIzena());
        assertEquals("Arregi", jokalaria.getAbizena());
        assertEquals(fecha, jokalaria.getJaiotzeData());
        assertEquals("12345678Z", jokalaria.getNAN());
        assertEquals("Bilbao Basket", jokalaria.getTaldea());
        assertEquals(50000, jokalaria.getPrezioa());
    }

    @Test
    void testSetters() {
        jokalaria.setIzena("Mikel");
        jokalaria.setAbizena("Urrutia");
        jokalaria.setPrezioa(60000);
        
        assertEquals("Mikel", jokalaria.getIzena());
        assertEquals("Urrutia", jokalaria.getAbizena());
        assertEquals(60000, jokalaria.getPrezioa());
    }

    @Test
    void testConstructorCopia() {
        Jokalaria copia = new Jokalaria(jokalaria);
        assertEquals(jokalaria.getIzena(), copia.getIzena());
        assertEquals(jokalaria.getNAN(), copia.getNAN());
        assertEquals(jokalaria.getAbizena(), copia.getAbizena());
    }

    @Test
    void testCompareTo() {
        // Misma persona (o mismos datos) -> compareTo debería ser 0
        Jokalaria igual = new Jokalaria("Jon", "Arregi", fecha, "12345678Z", "Bilbao Basket", 50000);
        assertEquals(0, jokalaria.compareTo(igual), "Debería ser 0 si los apellidos y nombres son iguales");

        // Diferente apellido (Zabaleta > Arregi)
        Jokalaria distintoApellido = new Jokalaria("Aitor", "Zabaleta", fecha, "87654321X", "Baskonia", 40000);
        assertTrue(jokalaria.compareTo(distintoApellido) < 0);
        assertTrue(distintoApellido.compareTo(jokalaria) > 0);

        // Mismo apellido, diferente nombre (Jon > Ander)
        Jokalaria mismoApellido = new Jokalaria("Ander", "Arregi", fecha, "11223344M", "Gipuzkoa", 30000);
        assertTrue(jokalaria.compareTo(mismoApellido) > 0);
    }
}