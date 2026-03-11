package Modeloa;

import javax.persistence.Entity;

@Entity
public class ErabiltzaileNormala extends ErabiltzaileMota {

    // Eraikitzaile hutsa, derrigorrezkoa ObjectDB / JPA erabiltzeko
    protected ErabiltzaileNormala() {
    }

    public ErabiltzaileNormala(String izena, String abizena, String NAN, String erabiltzailea, String pasahitza) {
        super(izena, abizena, NAN, erabiltzailea, pasahitza);
    }

    @Override
    public String baimenak() {
        return "Normala";
    }
}