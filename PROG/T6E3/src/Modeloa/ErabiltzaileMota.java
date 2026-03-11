package Modeloa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Seme guztiak taula/egitura bakarrean gordetzeko estrategia
public abstract class ErabiltzaileMota {

    protected String Izena;
    protected String Abizena;
    
    @Id // Etiketa honek NAN-a gako nagusi (primary key) gisa markatzen du
    protected String NAN;
    
    protected String Erabiltzailea;
    protected String Pasahitza;

    // Eraikitzaile hutsa, derrigorrezkoa ObjectDB / JPA erabiltzeko
    protected ErabiltzaileMota() {
    }

    public ErabiltzaileMota(String izena, String abizena, String NAN, String erabiltzailea, String pasahitza) {
        this.Izena = izena;
        this.Abizena = abizena;
        this.NAN = NAN;
        this.Erabiltzailea = erabiltzailea;
        this.Pasahitza = pasahitza;
    }

    // Metodo abstraktua (Seme guztiek inplementatu beharrekoa)
    public abstract String baimenak();

    // ==========================================================
    // GETTER-ak ETA SETTER-ak (Seme guztiek heredatuko dituzte)
    // ==========================================================
    public String getIzena() {
        return Izena;
    }

    public void setIzena(String izena) {
        Izena = izena;
    }

    public String getAbizena() {
        return Abizena;
    }

    public void setAbizena(String abizena) {
        Abizena = abizena;
    }

    public String getNAN() {
        return NAN;
    }

    public void setNAN(String NAN) {
        this.NAN = NAN;
    }

    public String getErabiltzailea() {
        return Erabiltzailea;
    }

    public void setErabiltzailea(String erabiltzailea) {
        Erabiltzailea = erabiltzailea;
    }

    public String getPasahitza() {
        return Pasahitza;
    }

    public void setPasahitza(String pasahitza) {
        Pasahitza = pasahitza;
    }
}