package Modeloa;

import javax.persistence.Entity;

@Entity
public class Presidentea extends ErabiltzaileMota {

    // Eraikitzaile hutsa, derrigorrezkoa ObjectDB / JPA erabiltzeko
    protected Presidentea() {
    }

    public Presidentea(String izena, String abizena, String NAN, String erabiltzailea, String pasahitza) {
        super(izena, abizena, NAN, erabiltzailea, pasahitza);
    }

    @Override
    public String baimenak() {
        return "Presidentea";
    }
}