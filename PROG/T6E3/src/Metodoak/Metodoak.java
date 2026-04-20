package Metodoak;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;

// XML eta DAO Importak
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import DAO.ErabiltzaileakDao;
import DAO.PartiduaDAO;
import DAO.TaldeakDAO;
import DAO.JokalariakDAO;
import Modeloa.ErabiltzaileMota;
import Modeloa.Jokalaria;
import Modeloa.Partidua;
import Modeloa.Taldea;

public class Metodoak {

    public static ErabiltzaileakDao edao = new ErabiltzaileakDao();
    
    /**
     * Erabiltzaile baten login-a egiaztatzen du datu-basean.
     * * @param erabiltzailea Erabiltzailearen izena.
     * @param pasahitza Erabiltzailearen pasahitza.
     * @return Erabiltzailearen baimen-rola ("Admin", "Presidentea", "Arrunta") edo null okerra bada.
     */
    public static String login (String erabiltzailea, String pasahitza) {
        ArrayList<ErabiltzaileMota> erabiltzaileaklist = edao.lortuErabiltzaileakODB();
        
        for (ErabiltzaileMota e : erabiltzaileaklist) {
            if (e.getErabiltzailea().equals(erabiltzailea) && e.getPasahitza().equals(pasahitza)) {
                idatziLog(e.getIzena() + " " + e.getAbizena(), erabiltzailea, e.baimenak());
                return e.baimenak();
            }
        }
        return null;
    }
    
    /**
     * Erabiltzaileen saio-hasierak (login) testu fitxategi batean erregistratzen ditu.
     * * @param izenAbizenak Erabiltzailearen izen-abizenak.
     * @param erabiltzailea Erabiltzailearen nick-a.
     * @param rola Erabiltzailearen baimen mota.
     */
    public static void idatziLog(String izenAbizenak, String erabiltzailea, String rola) {
        try {
            File fitxategia = new File("Login_Erregistroa.log");
            FileWriter fw = new FileWriter(fitxategia, true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            LocalDateTime orain = LocalDateTime.now();
            DateTimeFormatter formatua = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String dataFormateatua = orain.format(formatua);
            
            String logMezua = String.format("[%s] Erabiltzailea: %-12s | Izena: %-20s | Rola: %s", 
                                            dataFormateatua, erabiltzailea, izenAbizenak, rola);
            
            bw.write(logMezua);
            bw.newLine();
            bw.close();
            fw.close();
        } catch (IOException e) {
            System.out.println("Errorea log fitxategia idaztean: " + e.getMessage());
        }
    }

    /**
     * Ligako datu guztiak XML fitxategi batean esportatzen ditu.
     */
    public static void sortuXMLFitxategia() {
        try {
            TaldeakDAO tdao = new TaldeakDAO();
            JokalariakDAO jdao = new JokalariakDAO();
            PartiduaDAO pdao = new PartiduaDAO();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("SaskibaloiLiga");
            doc.appendChild(root);

            Element taldeakNode = doc.createElement("Taldeak");
            root.appendChild(taldeakNode);
            for (Taldea t : tdao.lortuTaldeak()) {
                Element taldea = doc.createElement("Taldea");
                taldea.setAttribute("Izena", t.getIzena());
                taldea.appendChild(doc.createElement("Lehendakaria")).appendChild(doc.createTextNode(t.getLehendakari()));
                taldea.appendChild(doc.createElement("Bazkideak")).appendChild(doc.createTextNode(String.valueOf(t.getN_Bazkideak())));
                taldeakNode.appendChild(taldea);
            }

            Element jokalariakNode = doc.createElement("Jokalariak");
            root.appendChild(jokalariakNode);
            for (Jokalaria j : jdao.lortuJokalariak()) {
                Element jokalaria = doc.createElement("Jokalaria");
                jokalaria.setAttribute("NAN", j.getNAN());
                jokalaria.appendChild(doc.createElement("Izena")).appendChild(doc.createTextNode(j.getIzena() + " " + j.getAbizena()));
                jokalaria.appendChild(doc.createElement("Taldea")).appendChild(doc.createTextNode(j.getTaldea()));
                jokalaria.appendChild(doc.createElement("Prezioa")).appendChild(doc.createTextNode(String.valueOf(j.getPrezioa())));
                jokalariakNode.appendChild(jokalaria);
            }

            Element partiduakNode = doc.createElement("Partiduak");
            root.appendChild(partiduakNode);
            for (Partidua p : pdao.lortuPartiduGuztiak()) {
                Element partidua = doc.createElement("Partidua");
                partidua.appendChild(doc.createElement("Lokala")).appendChild(doc.createTextNode(p.getTaldeLokala()));
                partidua.appendChild(doc.createElement("Bisitaria")).appendChild(doc.createTextNode(p.getTaldeBisitari()));
                String emaitza = (p.getResultLokala() != null ? p.getResultLokala() : "0") + " - " + 
                                 (p.getResulBisitari() != null ? p.getResulBisitari() : "0");
                partidua.appendChild(doc.createElement("Emaitza")).appendChild(doc.createTextNode(emaitza));
                partiduakNode.appendChild(partidua);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("LigaDatuak.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            System.err.println("Errorea XML sortzean: " + e.getMessage());
        }
    }
    
    /**
     * Partidu baten emaitzak baliozkoak direla egiaztatzen du.
     * * @param txtLok Talde lokalaren golak.
     * @param txtBis Talde bisitariaren golak.
     * @return Errore mezua edo null dena ondo badago.
     */
    public static String balioztatuEmaitzak(String txtLok, String txtBis) {
        String mezua = null; 
        if ((txtLok.isEmpty() && !txtBis.isEmpty()) || (!txtLok.isEmpty() && txtBis.isEmpty())) {
            mezua = "Bi kutxak hutsik edo biak beteta egon behar dute.";
        } else if (!txtLok.isEmpty() && !txtBis.isEmpty()) {
            try {
                int golesLocal = Integer.parseInt(txtLok);
                int golesVisitante = Integer.parseInt(txtBis);
                if (golesLocal < 0 || golesVisitante < 0) {
                    mezua = "Ezin dira gol negatiboak sartu.";
                }
            } catch (NumberFormatException ex) {
                mezua = "Zenbakiak soilik onartzen dira.";
            }
        }
        return mezua; 
    }

    /**
     * Partiduen emaitzetan oinarrituta ligako sailkapen orokorra kalkulatzen du.
     * * @param partiduakEmaitzekin Jokatutako partiduen zerrenda.
     * @return Sailkapeneko taldeen zerrenda, puntuen arabera eguneratuta.
     */
    public static ArrayList<Taldea> klasifikasioaKalkulatu(ArrayList<Partidua> partiduakEmaitzekin) {
        ArrayList<Taldea> klasifikasioa = new ArrayList<>();
        
        for(Partidua p : partiduakEmaitzekin) {
            if(p.getResulBisitari() != null && p.getResultLokala() != null) {
                
                Taldea talL = new Taldea(p.getTaldeBisitari(), LocalDate.now(), "", 0);
                if(!klasifikasioa.contains(talL)) {
                    talL.setPuntuakF(p.getResulBisitari());
                    talL.setPuntuakC(p.getResultLokala());
                    if(p.getResulBisitari() > p.getResultLokala()) {
                        talL.setPuntuTotalak(3);
                        talL.setIrabazitakoak(1);
                    } else if(p.getResulBisitari() == p.getResultLokala()) {
                        talL.setPuntuTotalak(1);
                    } else {
                        talL.setGaldutakoak(1);
                    }
                    klasifikasioa.add(talL);
                } else {
                    for(Taldea kla : klasifikasioa) {
                        if(kla.equals(talL)) {
                            kla.setPuntuakF(kla.getPuntuakF() + p.getResulBisitari());
                            kla.setPuntuakC(kla.getPuntuakC() + p.getResultLokala());
                            if(p.getResulBisitari() > p.getResultLokala()) {
                                kla.setPuntuTotalak(3 + kla.getPuntuTotalak());
                                kla.setIrabazitakoak(1 + kla.getIrabazitakoak());
                            } else if(p.getResulBisitari() == p.getResultLokala()) {
                                kla.setPuntuTotalak(1 + kla.getPuntuTotalak());
                            } else {
                                kla.setGaldutakoak(1 + kla.getGaldutakoak());
                            }
                        }
                    }
                }
                
                Taldea talB = new Taldea(p.getTaldeLokala(), LocalDate.now(), "", 0);
                if(!klasifikasioa.contains(talB)) {
                    talB.setPuntuakF(p.getResultLokala());
                    talB.setPuntuakC(p.getResulBisitari());
                    if(p.getResultLokala() > p.getResulBisitari()) {
                        talB.setPuntuTotalak(3);
                        talB.setIrabazitakoak(1);
                    } else if(p.getResulBisitari() == p.getResultLokala()) {
                        talB.setPuntuTotalak(1);
                    } else {
                        talB.setGaldutakoak(1);
                    }
                    klasifikasioa.add(talB);
                } else {
                    for(Taldea kla : klasifikasioa) {
                        if(kla.equals(talB)) {
                            kla.setPuntuakF(kla.getPuntuakF() + p.getResultLokala());
                            kla.setPuntuakC(kla.getPuntuakC() + p.getResulBisitari());
                            if(p.getResultLokala() > p.getResulBisitari()) {
                                kla.setPuntuTotalak(3 + kla.getPuntuTotalak());
                                kla.setIrabazitakoak(1 + kla.getIrabazitakoak());
                            } else if(p.getResulBisitari() == p.getResultLokala()) {
                                kla.setPuntuTotalak(1 + kla.getPuntuTotalak());
                            } else {
                                kla.setGaldutakoak(1 + kla.getGaldutakoak());
                            }
                        }
                    }
                }
            }
        }
        return klasifikasioa;
    }
}