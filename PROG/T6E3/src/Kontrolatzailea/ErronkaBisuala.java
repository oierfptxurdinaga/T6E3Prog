package Kontrolatzailea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import DAO.ErabiltzaileakDao;
import DAO.JokalariakDAO;
import DAO.TaldeakDAO;
import DAO.JaurdunaldiaDAO;
import DAO.PartiduaDAO;
import Modeloa.Partidua;

import Metodoak.Metodoak;
import Modeloa.ErabiltzaileMota;
import Modeloa.Jokalaria;
import Modeloa.Taldea;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Collections;

public class ErronkaBisuala extends JFrame implements ActionListener, WindowListener {

    private static final long serialVersionUID = 1L;
    
    // ==========================================================
    // 1. Atributuak (Pribatuak)
    // ==========================================================
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Font titleFont;

    // --- Panelen Osagaiak ---
    private JPanel LoginPanela, HasierakoPanela, KlasifikazioaPanela, EmaitzaPanela, TaldeakJokalariakPanela, JokalariakPanela, ErabiltzaileKudeaketaPanela;
    
    // Login
    private JLabel logoaImg1, erabiltzaileak, pasahitza;
    private JTextField textErabiltzaile;
    private JPasswordField textPasahitza; 
    private JButton sartu, atera1;

    // Hasiera
    private JLabel logoaImg2;
    private JButton atzerantz, atera2, klasifikazioaIkusi, sartuEmaitza, taldeakjokalariakIkusi, jokalariakAldatu, kudeaketaBotoia;

    // Klasifikazioa
    private JButton atzerantzKlasif, ateraKlasif;
    private JTable tablaKlasif;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollTabla;

    // Taldeak eta Jokalariak Ikusi
    private JComboBox<String> comboBox;
    private JTable tablaPequena, tablaGrande;
    private DefaultTableModel modeloPequena, modeloGrande;
    private JButton atzerantzTaldeak, ateraTaldeak;
    
    // Emaitza 
    private JComboBox<String> cmbJaurdunaldia;
    private JLabel lblLokala1, lblBisitaria1, lblLokala2, lblBisitaria2, lblLokala3, lblBisitaria3;
    private JTextField txtPuntuakLokala1, txtPuntuakBisitaria1, txtPuntuakLokala2, txtPuntuakBisitaria2, txtPuntuakLokala3, txtPuntuakBisitaria3;
    private JButton atzerantzEmaitza, ateraEmaitza, gordeEmaitza;

    // Jokalariak
    private JComboBox<String> comboIzquierda, comboDerecha;
    private JTable tablaIzquierda;
    private DefaultTableModel modeloIzquierda;
    private DefaultTableModel modeloDerecha;
    private JButton btnAldatu, atzerantzjokalariak, btnAtera;
    
    // Erabiltzaileen Kudeaketaren Osagaiak
    private JTable tablaErabiltzaileak;
    private DefaultTableModel modeloErabiltzaileak;
    
    private JTextField txtIzenKud, txtAbizenKud, txtNanKud, txtErabKud, txtPasahitzKud; 
    private JComboBox<String> comboMotaKud;
    private JButton btnGehituKud, btnAldatuKud, btnEzabatuKud, btnAtzerantzKud, btnAteraKud;
    
    // DAO-ak instantziatu
    private TaldeakDAO tdao = new TaldeakDAO();
    private JokalariakDAO jdao = new JokalariakDAO();
    private JaurdunaldiaDAO jaurdunaldiaDAO = new JaurdunaldiaDAO();
    private PartiduaDAO partiduaDAO = new PartiduaDAO();
    private ErabiltzaileakDao edao = new ErabiltzaileakDao(); 
    
    private ArrayList<Partidua> partiduGordeak = new ArrayList<>(); 
    private ArrayList<Taldea> taldeak;
    private ArrayList<Jokalaria> jokalariak;
    
    // Erabiltzaileen zerrenda memorian gordetzeko
    private ArrayList<ErabiltzaileMota> erabiltzaileenZerrenda = new ArrayList<>();

    // ==========================================================
    // 2. Eraikitzailea (Constructor)
    // ==========================================================
    public ErronkaBisuala() {
        setTitle("FNFS Liga Kudeatzailea");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        setSize(802, 604); 
        setLocationRelativeTo(null);
        titleFont = new Font("Verdana", Font.BOLD, 24);

        this.addWindowListener(this);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        setContentPane(contentPanel);

        inizializatuPanelak();
        konfiguratuOsagaiBisualak();

        contentPanel.add(LoginPanela, "Login");
        contentPanel.add(HasierakoPanela, "Hasiera");
        contentPanel.add(KlasifikazioaPanela, "Klasifikazioa");
        contentPanel.add(EmaitzaPanela, "Emaitzak");
        contentPanel.add(TaldeakJokalariakPanela, "Taldeak");
        contentPanel.add(JokalariakPanela, "Jokalariak");
        contentPanel.add(ErabiltzaileKudeaketaPanela, "Kudeaketa");
        
        JLabel lblTaldeBerria = new JLabel("Talde Berria");
        lblTaldeBerria.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTaldeBerria.setBounds(476, 205, 150, 25);
        JokalariakPanela.add(lblTaldeBerria);

        cardLayout.show(contentPanel, "Login");
        setVisible(true);
    }

    // ==========================================================
    // 3. Panelen Inizializazioa
    // ==========================================================
    private void inizializatuPanelak() {
        // --- LOGIN PANELA ---
        LoginPanela = new JPanel(null);
        JLabel titleLogin = new JLabel("", JLabel.CENTER);
        titleLogin.setFont(titleFont);
        titleLogin.setBounds(0, 20, 800, 30);
        
        logoaImg1 = new JLabel("LOGOA IMAGEN", JLabel.CENTER);
        logoaImg1.setBounds(320, 88, 200, 150);
        
        erabiltzaileak = new JLabel("Erabiltzailea:", JLabel.RIGHT);
        erabiltzaileak.setBounds(200, 280, 110, 30);
        textErabiltzaile = new JTextField();
        textErabiltzaile.setBounds(320, 280, 200, 30);
        
        pasahitza = new JLabel("Pasahitza:", JLabel.RIGHT);
        pasahitza.setBounds(200, 330, 110, 30);
        textPasahitza = new JPasswordField(); 
        textPasahitza.setBounds(320, 330, 200, 30);
        
        sartu = new JButton("Sartu");
        sartu.setBounds(374, 390, 100, 30);
        this.getRootPane().setDefaultButton(sartu); 
        
        atera1 = new JButton("Atera");
        atera1.setBounds(675, 148, 80, 30);

        LoginPanela.add(titleLogin); LoginPanela.add(logoaImg1); LoginPanela.add(erabiltzaileak);
        LoginPanela.add(textErabiltzaile); LoginPanela.add(pasahitza); LoginPanela.add(textPasahitza);
        LoginPanela.add(sartu); LoginPanela.add(atera1);

        // --- HASIERA PANELA ---
        HasierakoPanela = new JPanel(null);
        
        logoaImg2 = new JLabel("LOGOA", JLabel.CENTER); 
        logoaImg2.setBounds(280, 80, 240, 211);
        
        atzerantz = new JButton("Atzerantz"); 
        atzerantz.setBounds(570, 30, 100, 30);
        
        atera2 = new JButton("Atera"); 
        atera2.setBounds(680, 30, 80, 30);
        
        klasifikazioaIkusi = new JButton("Klasifikazioa ikusi"); 
        klasifikazioaIkusi.setBounds(150, 350, 200, 40);
        
        sartuEmaitza = new JButton("Sartu Emaitza"); 
        sartuEmaitza.setBounds(450, 350, 200, 40);
        
        taldeakjokalariakIkusi = new JButton("Taldeak eta Jokalariak ikusi"); 
        taldeakjokalariakIkusi.setBounds(150, 420, 200, 40);
        
        jokalariakAldatu = new JButton("Jokalariak Aldatu"); 
        jokalariakAldatu.setBounds(450, 420, 200, 40);

        kudeaketaBotoia = new JButton("Erabiltzaileak Kudeatu");
        kudeaketaBotoia.setBounds(300, 490, 200, 40);
        kudeaketaBotoia.setVisible(false);

        HasierakoPanela.add(logoaImg2); 
        HasierakoPanela.add(atzerantz); HasierakoPanela.add(atera2);
        HasierakoPanela.add(klasifikazioaIkusi); HasierakoPanela.add(sartuEmaitza);
        HasierakoPanela.add(taldeakjokalariakIkusi); HasierakoPanela.add(jokalariakAldatu);
        HasierakoPanela.add(kudeaketaBotoia);

        // --- KLASIFIKAZIOA PANELA ---
        KlasifikazioaPanela = new JPanel(null);
        JLabel titleKlasif = new JLabel("LIGAKO KLASIFIKAZIOA", JLabel.CENTER);
        titleKlasif.setBounds(0, 20, 800, 30);
        titleKlasif.setFont(titleFont);

        String[] zutabeTituluak = {"Posisioa", "Taldea", "P. Totalak", "Irabazi", "Galdu", "Aldeko Golak", "Aurkako Golak"};
        modeloTabla = new DefaultTableModel(zutabeTituluak, 0);
        tablaKlasif = new JTable(modeloTabla);
        JScrollPane scrollTabla = new JScrollPane(tablaKlasif);
        scrollTabla.setBounds(50, 80, 680, 360);

        atzerantzKlasif = new JButton("Atzerantz"); 
        atzerantzKlasif.setBounds(50, 470, 100, 30);
        
        ateraKlasif = new JButton("Atera"); 
        ateraKlasif.setBounds(630, 470, 100, 30);

        KlasifikazioaPanela.add(titleKlasif); KlasifikazioaPanela.add(scrollTabla);
        KlasifikazioaPanela.add(atzerantzKlasif); KlasifikazioaPanela.add(ateraKlasif);

        // --- JOKALARIAK ALDATU PANELA ---
        JokalariakPanela = new JPanel(null);
        JLabel titleJokalariak = new JLabel("JOKALARIAK ALDATU", JLabel.CENTER);
        titleJokalariak.setFont(titleFont);
        titleJokalariak.setBounds(0, 20, 800, 30);
        JokalariakPanela.add(titleJokalariak);

        comboIzquierda = new JComboBox<>();
        comboIzquierda.setBounds(30, 89, 200, 25);
        JokalariakPanela.add(comboIzquierda);
     
        comboDerecha = new JComboBox<>();
        comboDerecha.setBounds(476, 240, 254, 40);
        JokalariakPanela.add(comboDerecha);

        String[] columnasJok = {"Izena", "Abizena", "Jaiotse-Data", "NAN"};
        modeloIzquierda = new DefaultTableModel(columnasJok, 0);
        tablaIzquierda = new JTable(modeloIzquierda);
        JScrollPane scrollIzquierda = new JScrollPane(tablaIzquierda);
        scrollIzquierda.setBounds(30, 124, 312, 306);
        JokalariakPanela.add(scrollIzquierda);
        
        btnAldatu = new JButton("Aldatu");
        btnAldatu.setBounds(360, 240, 92, 40);
        JokalariakPanela.add(btnAldatu);

        atzerantzjokalariak = new JButton("Atzerantz");
        atzerantzjokalariak.setHorizontalAlignment(SwingConstants.RIGHT);
        atzerantzjokalariak.setBounds(50, 470, 100, 30);
        JokalariakPanela.add(atzerantzjokalariak);

        btnAtera = new JButton("Atera");
        btnAtera.setBounds(630, 470, 100, 30);
        JokalariakPanela.add(btnAtera);

        // --- TALDEAK eta JOKALARIAK PANELA ---
        TaldeakJokalariakPanela = new JPanel(null);

        comboBox = new JComboBox<>();
        comboBox.setBounds(300, 20, 200, 25);
        TaldeakJokalariakPanela.add(comboBox);

        String[] columnasPequena = {"SorreraUrtea", "Lehendakari", "N_Bazkideak"};
        modeloPequena = new DefaultTableModel(columnasPequena,0);
        tablaPequena = new JTable(modeloPequena);
        JScrollPane scrollPequena = new JScrollPane(tablaPequena);
        scrollPequena.setBounds(50, 60, 680, 40);
        TaldeakJokalariakPanela.add(scrollPequena);

        String[] columnasGrande = {"Izena","Abizena","JaiotzeData","NAN","Taldea","Prezioa"};
        modeloGrande = new DefaultTableModel(columnasGrande,0);
        tablaGrande = new JTable(modeloGrande);
        JScrollPane scrollGrande = new JScrollPane(tablaGrande);
        scrollGrande.setBounds(50, 120, 680, 320);
        TaldeakJokalariakPanela.add(scrollGrande);
        
        taldeak = tdao.lortuTaldeak();
        for (Taldea taldea : taldeak) {
            comboBox.addItem(taldea.getIzena());
            comboIzquierda.addItem(taldea.getIzena());
            comboDerecha.addItem(taldea.getIzena());
        }
        
        jokalariak = jdao.lortuJokalariak();

        if (comboBox.getItemCount() > 0) {
            eguneratuTaulak((String) comboBox.getItemAt(0));
        }
        
        if (comboIzquierda.getItemCount() > 0) {
            eguneratuTaulaJokalariakAldatu((String) comboIzquierda.getItemAt(0));
        }
        
        atzerantzTaldeak = new JButton("Atzerantz");
        atzerantzTaldeak.setBounds(50, 470, 100, 30);

        ateraTaldeak = new JButton("Atera");
        ateraTaldeak.setBounds(630, 470, 100, 30);

        TaldeakJokalariakPanela.add(atzerantzTaldeak);
        TaldeakJokalariakPanela.add(ateraTaldeak);

        // --- EMAITZA PANELA ---
        EmaitzaPanela = new JPanel(null);
        JLabel titleEmaitza = new JLabel("LIGAKO EMAITZAK", JLabel.CENTER);
        titleEmaitza.setBounds(0, 20, 800, 30);
        titleEmaitza.setFont(titleFont);
        EmaitzaPanela.add(titleEmaitza);

        JLabel lblAukeratuJaurdunaldia = new JLabel("Aukeratu Jaurdunaldia:");
        lblAukeratuJaurdunaldia.setBounds(250, 80, 150, 25);
        EmaitzaPanela.add(lblAukeratuJaurdunaldia);

        cmbJaurdunaldia = new JComboBox<>();
        cmbJaurdunaldia.setBounds(400, 80, 150, 25);
        
        ArrayList<Integer> zenbakiak = jaurdunaldiaDAO.lortuJaurdunaldienZenbakiak();
        for (Integer zenbakia : zenbakiak) {
            cmbJaurdunaldia.addItem(zenbakia + ". Jaurdunaldia");
        }
        
        EmaitzaPanela.add(cmbJaurdunaldia);

        // 1. PARTIDUA
        JPanel partidua1Panela = new JPanel(null);
        partidua1Panela.setBounds(100, 150, 600, 50);
        EmaitzaPanela.add(partidua1Panela);

        lblLokala1 = new JLabel("1. Talde Lokala", SwingConstants.RIGHT);
        lblLokala1.setBounds(0, 10, 200, 30);
        partidua1Panela.add(lblLokala1);

        txtPuntuakLokala1 = new JTextField();
        txtPuntuakLokala1.setBounds(220, 10, 50, 30);
        partidua1Panela.add(txtPuntuakLokala1);

        JLabel banatzailea1 = new JLabel("-", SwingConstants.CENTER);
        banatzailea1.setBounds(280, 10, 40, 30);
        partidua1Panela.add(banatzailea1);

        txtPuntuakBisitaria1 = new JTextField();
        txtPuntuakBisitaria1.setBounds(330, 10, 50, 30);
        partidua1Panela.add(txtPuntuakBisitaria1);

        lblBisitaria1 = new JLabel("1. Talde Bisitaria", SwingConstants.LEFT);
        lblBisitaria1.setBounds(400, 10, 200, 30);
        partidua1Panela.add(lblBisitaria1);

        // 2. PARTIDUA
        JPanel partidua2Panela = new JPanel(null);
        partidua2Panela.setBounds(100, 220, 600, 50);
        EmaitzaPanela.add(partidua2Panela);

        lblLokala2 = new JLabel("2. Talde Lokala", SwingConstants.RIGHT);
        lblLokala2.setBounds(0, 10, 200, 30);
        partidua2Panela.add(lblLokala2);

        txtPuntuakLokala2 = new JTextField();
        txtPuntuakLokala2.setBounds(220, 10, 50, 30);
        partidua2Panela.add(txtPuntuakLokala2);

        JLabel banatzailea2 = new JLabel("-", SwingConstants.CENTER);
        banatzailea2.setBounds(280, 10, 40, 30);
        partidua2Panela.add(banatzailea2);

        txtPuntuakBisitaria2 = new JTextField();
        txtPuntuakBisitaria2.setBounds(330, 10, 50, 30);
        partidua2Panela.add(txtPuntuakBisitaria2);

        lblBisitaria2 = new JLabel("2. Talde Bisitaria", SwingConstants.LEFT);
        lblBisitaria2.setBounds(400, 10, 200, 30);
        partidua2Panela.add(lblBisitaria2);

        // 3. PARTIDUA
        JPanel partidua3Panela = new JPanel(null);
        partidua3Panela.setBounds(100, 290, 600, 50);
        EmaitzaPanela.add(partidua3Panela);

        lblLokala3 = new JLabel("3. Talde Lokala", SwingConstants.RIGHT);
        lblLokala3.setBounds(0, 10, 200, 30);
        partidua3Panela.add(lblLokala3);

        txtPuntuakLokala3 = new JTextField();
        txtPuntuakLokala3.setBounds(220, 10, 50, 30);
        partidua3Panela.add(txtPuntuakLokala3);

        JLabel banatzailea3 = new JLabel("-", SwingConstants.CENTER);
        banatzailea3.setBounds(280, 10, 40, 30);
        partidua3Panela.add(banatzailea3);

        txtPuntuakBisitaria3 = new JTextField();
        txtPuntuakBisitaria3.setBounds(330, 10, 50, 30);
        partidua3Panela.add(txtPuntuakBisitaria3);

        lblBisitaria3 = new JLabel("3. Talde Bisitaria", SwingConstants.LEFT);
        lblBisitaria3.setBounds(400, 10, 200, 30);
        partidua3Panela.add(lblBisitaria3);

        atzerantzEmaitza = new JButton("Atzerantz"); 
        atzerantzEmaitza.setBounds(50, 470, 100, 30);

        gordeEmaitza = new JButton("Gorde Emaitzak");
        gordeEmaitza.setBounds(300, 470, 200, 30);

        ateraEmaitza = new JButton("Atera"); 
        ateraEmaitza.setBounds(630, 470, 100, 30);

        EmaitzaPanela.add(atzerantzEmaitza);
        EmaitzaPanela.add(gordeEmaitza);
        EmaitzaPanela.add(ateraEmaitza);

        if (cmbJaurdunaldia.getItemCount() > 0) {
            String aukera = (String) cmbJaurdunaldia.getItemAt(0);
            int jaurdunaldiZenbakia = Integer.parseInt(aukera.split("\\.")[0]);
            kargatuPartiduak(jaurdunaldiZenbakia);
        }

        // --- ERABILTZAILEAK KUDEATU PANELA ---
        ErabiltzaileKudeaketaPanela = new JPanel(null);
        JLabel titleKudeaketa = new JLabel("ERABILTZAILEEN KUDEAKETA", JLabel.CENTER);
        titleKudeaketa.setBounds(0, 20, 800, 30);
        titleKudeaketa.setFont(titleFont);
        ErabiltzaileKudeaketaPanela.add(titleKudeaketa);

        String[] zutabeErab = {"Izena", "Abizena", "NAN", "Erabiltzailea", "Mota"};
        modeloErabiltzaileak = new DefaultTableModel(zutabeErab, 0);
        tablaErabiltzaileak = new JTable(modeloErabiltzaileak);
        JScrollPane scrollErab = new JScrollPane(tablaErabiltzaileak);
        scrollErab.setBounds(50, 70, 680, 200);
        ErabiltzaileKudeaketaPanela.add(scrollErab);

        // BERRIA: Funtzionamendu seguruagoa, hutsuneak ('trim') onartzen ditu
        tablaErabiltzaileak.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaErabiltzaileak.getSelectedRow() != -1) {
                int row = tablaErabiltzaileak.getSelectedRow();
                
                txtIzenKud.setText((String) modeloErabiltzaileak.getValueAt(row, 0));
                txtAbizenKud.setText((String) modeloErabiltzaileak.getValueAt(row, 1));
                txtNanKud.setText((String) modeloErabiltzaileak.getValueAt(row, 2));
                txtErabKud.setText((String) modeloErabiltzaileak.getValueAt(row, 3));
                comboMotaKud.setSelectedItem((String) modeloErabiltzaileak.getValueAt(row, 4));

                // BERRIA: Pasahitza garbitu lehenbizi aurreko balioa (adib. 12345) ez geratzeko
                txtPasahitzKud.setText(""); 

                String nanAukeratua = (String) modeloErabiltzaileak.getValueAt(row, 2);
                if(nanAukeratua != null) {
                    nanAukeratua = nanAukeratua.trim();
                    for (ErabiltzaileMota erab : erabiltzaileenZerrenda) {
                        if (erab.getNAN() != null && erab.getNAN().trim().equals(nanAukeratua)) {
                            txtPasahitzKud.setText(erab.getPasahitza());
                            break;
                        }
                    }
                }
            }
        });

        int yForm = 290;
        ErabiltzaileKudeaketaPanela.add(new JLabel("Izena:")).setBounds(50, yForm, 60, 25);
        txtIzenKud = new JTextField(); txtIzenKud.setBounds(110, yForm, 100, 25); ErabiltzaileKudeaketaPanela.add(txtIzenKud);
        
        ErabiltzaileKudeaketaPanela.add(new JLabel("Abizena:")).setBounds(220, yForm, 60, 25);
        txtAbizenKud = new JTextField(); txtAbizenKud.setBounds(280, yForm, 100, 25); ErabiltzaileKudeaketaPanela.add(txtAbizenKud);
        
        ErabiltzaileKudeaketaPanela.add(new JLabel("NAN:")).setBounds(400, yForm, 40, 25);
        txtNanKud = new JTextField(); txtNanKud.setBounds(440, yForm, 100, 25); ErabiltzaileKudeaketaPanela.add(txtNanKud);

        yForm += 40;
        ErabiltzaileKudeaketaPanela.add(new JLabel("Erab:")).setBounds(50, yForm, 60, 25);
        txtErabKud = new JTextField(); txtErabKud.setBounds(110, yForm, 100, 25); ErabiltzaileKudeaketaPanela.add(txtErabKud);
        
        ErabiltzaileKudeaketaPanela.add(new JLabel("Pasahitza:")).setBounds(220, yForm, 70, 25);
        txtPasahitzKud = new JTextField(); 
        txtPasahitzKud.setBounds(290, yForm, 100, 25); 
        ErabiltzaileKudeaketaPanela.add(txtPasahitzKud);

        ErabiltzaileKudeaketaPanela.add(new JLabel("Mota:")).setBounds(410, yForm, 40, 25);
        comboMotaKud = new JComboBox<>(new String[]{"Admin", "Presidentea", "Arrunta"});
        comboMotaKud.setBounds(450, yForm, 100, 25);
        ErabiltzaileKudeaketaPanela.add(comboMotaKud);

        yForm += 50;
        btnGehituKud = new JButton("Gehitu"); btnGehituKud.setBounds(150, yForm, 100, 30); ErabiltzaileKudeaketaPanela.add(btnGehituKud);
        btnAldatuKud = new JButton("Aldatu"); btnAldatuKud.setBounds(300, yForm, 100, 30); ErabiltzaileKudeaketaPanela.add(btnAldatuKud);
        btnEzabatuKud = new JButton("Ezabatu"); btnEzabatuKud.setBounds(450, yForm, 100, 30); ErabiltzaileKudeaketaPanela.add(btnEzabatuKud);

        btnAtzerantzKud = new JButton("Atzerantz"); btnAtzerantzKud.setBounds(50, 470, 100, 30); ErabiltzaileKudeaketaPanela.add(btnAtzerantzKud);
        btnAteraKud = new JButton("Atera"); btnAteraKud.setBounds(630, 470, 100, 30); ErabiltzaileKudeaketaPanela.add(btnAteraKud);

        // ==========================================================
        // --- THE LISTENERS (Botoien eta osagaien ekintzak) ---
        // ==========================================================
        sartu.addActionListener(this);
        atera1.addActionListener(this);
        atzerantz.addActionListener(this);
        atera2.addActionListener(this);
        klasifikazioaIkusi.addActionListener(this);
        sartuEmaitza.addActionListener(this);
        taldeakjokalariakIkusi.addActionListener(this);
        jokalariakAldatu.addActionListener(this);
        atzerantzKlasif.addActionListener(this);
        ateraKlasif.addActionListener(this);
        comboIzquierda.addActionListener(this);
        comboDerecha.addActionListener(this);
        btnAldatu.addActionListener(this);
        atzerantzjokalariak.addActionListener(this);
        btnAtera.addActionListener(this);
        comboBox.addActionListener(this);
        atzerantzTaldeak.addActionListener(this);
        ateraTaldeak.addActionListener(this);
        cmbJaurdunaldia.addActionListener(this); 
        atzerantzEmaitza.addActionListener(this);
        gordeEmaitza.addActionListener(this);
        ateraEmaitza.addActionListener(this);
        
        kudeaketaBotoia.addActionListener(this);
        btnGehituKud.addActionListener(this);
        btnAldatuKud.addActionListener(this);
        btnEzabatuKud.addActionListener(this);
        btnAtzerantzKud.addActionListener(this);
        btnAteraKud.addActionListener(this);
    }
    
    // ==========================================================
    // 4. WindowListener Kudeaketa
    // ==========================================================
    @Override
    public void windowClosing(WindowEvent e) {
        atera();
    }
    @Override public void windowOpened(WindowEvent e) {}
    @Override public void windowClosed(WindowEvent e) {}
    @Override public void windowIconified(WindowEvent e) {}
    @Override public void windowDeiconified(WindowEvent e) {}
    @Override public void windowActivated(WindowEvent e) {}
    @Override public void windowDeactivated(WindowEvent e) {}

    // ==========================================================
    // 5. Ekintzen Kudeaketa (ActionListener)
    // ==========================================================
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == sartu) {
            String username = textErabiltzaile.getText();
            String password = new String(textPasahitza.getPassword()); 
            
            if(username.trim().isEmpty() || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mesedez, bete eremu guztiak.", "Errorea", JOptionPane.ERROR_MESSAGE);
                return;
            }
           
            String baimenak = Metodoak.login(username, password);
            
            if (baimenak != null) {
                textErabiltzaile.setText(null);
                textPasahitza.setText(null);
                erakutsiPanelak(baimenak);
                cardLayout.show(contentPanel, "Hasiera");
            } else {
                JOptionPane.showMessageDialog(null, "Erabiltzaile edo Pasahitz okerra", "Errorea", JOptionPane.ERROR_MESSAGE);
            }
            
        } else if (source == atera1 || source == atera2 || source == ateraKlasif || 
                   source == btnAtera || source == ateraTaldeak || source == ateraEmaitza || source == btnAteraKud) {
            atera();
            
        } else if (source == klasifikazioaIkusi) {
            cardLayout.show(contentPanel, "Klasifikazioa");
            ArrayList<Partidua> partidakKlas = partiduaDAO.lortuPartiduGuztiak();
            ArrayList<Taldea> klasifikasioa = Metodoak.klasifikasioaKalkulatu(partidakKlas);
            modeloTabla.setRowCount(0);
            Collections.sort(klasifikasioa);
            int i = 0;
            for (Taldea t : klasifikasioa) {
            		i++;
                    modeloTabla.addRow(new Object[]{
                    i, t.getIzena(), t.getPuntuTotalak(), t.getIrabazitakoak(),
                    t.getGaldutakoak(), t.getPuntuakF(), t.getPuntuakC()});
            }
        } else if (source == sartuEmaitza) {
            cardLayout.show(contentPanel, "Emaitzak");
        } else if (source == taldeakjokalariakIkusi) {
            cardLayout.show(contentPanel, "Taldeak");
        } else if (source == jokalariakAldatu) {
            cardLayout.show(contentPanel, "Jokalariak");
            
        } else if (source == kudeaketaBotoia) {
            cardLayout.show(contentPanel, "Kudeaketa");
            eguneratuErabiltzaileTaula();

        } else if (source == atzerantzKlasif || source == atzerantzEmaitza || 
                   source == atzerantzTaldeak|| source == atzerantzjokalariak || source == btnAtzerantzKud) {
            cardLayout.show(contentPanel, "Hasiera");

        } else if (source == atzerantz) {
            cardLayout.show(contentPanel, "Login");

        } else if (source == comboBox) {
            String taldeaAukeratua = (String) comboBox.getSelectedItem();
            if (taldeaAukeratua != null) {
                eguneratuTaulak(taldeaAukeratua);
            }
        } else if (source == comboIzquierda) {
            String taldeaAukeratua = (String) comboIzquierda.getSelectedItem();
            if (taldeaAukeratua != null) {
            	eguneratuTaulaJokalariakAldatu(taldeaAukeratua);
            }
        } else if (source == cmbJaurdunaldia) {
            if (cmbJaurdunaldia.getSelectedItem() != null) {
                String aukera = (String) cmbJaurdunaldia.getSelectedItem();
                int jaurdunaldiZenbakia = Integer.parseInt(aukera.split("\\.")[0]);
                kargatuPartiduak(jaurdunaldiZenbakia);
            }
        } else if (source == gordeEmaitza) {
            if (partiduGordeak.size() < 3) return;
            JTextField[] txtLokalak = {txtPuntuakLokala1, txtPuntuakLokala2, txtPuntuakLokala3};
            JTextField[] txtBisitariak = {txtPuntuakBisitaria1, txtPuntuakBisitaria2, txtPuntuakBisitaria3};

            for (int i = 0; i < 3; i++) {
                Partidua p = partiduGordeak.get(i);
                String txtLok = txtLokalak[i].getText().trim();
                String txtBis = txtBisitariak[i].getText().trim();
                String erroreMezua = Metodoak.balioztatuEmaitzak(txtLok, txtBis);
                if (erroreMezua != null) {
                    JOptionPane.showMessageDialog(this, "Errorea (" + p.getTaldeLokala() + " vs " + p.getTaldeBisitari() + "): \n" + erroreMezua, "Balidazio Errorea", JOptionPane.ERROR_MESSAGE);
                    return; 
                }
                Integer golesLocal = txtLok.isEmpty() ? null : Integer.parseInt(txtLok);
                Integer golesVisitante = txtBis.isEmpty() ? null : Integer.parseInt(txtBis);
                partiduaDAO.eguneratuEmaitza(p.getIdPar(), golesLocal, golesVisitante);
            }
            JOptionPane.showMessageDialog(this, "Emaitzak ondo gorde dira datu-basean!", "Eguneratua", JOptionPane.INFORMATION_MESSAGE);
            
        } else if (source == btnAldatu) {
        	try {
        		if(comboIzquierda.getSelectedItem() != (String)comboDerecha.getSelectedItem()) {
                	jdao.aldatu_jokalriak(jokalariak.get(tablaIzquierda.getSelectedRow()).getNAN(), (String)comboDerecha.getSelectedItem());
                	jokalariak.get(tablaIzquierda.getSelectedRow()).setTaldea((String)comboDerecha.getSelectedItem());
                	if (comboBox.getItemCount() > 0) {
                        eguneratuTaulak((String) comboBox.getSelectedItem());
                    }
                    if (comboIzquierda.getItemCount() > 0) {
                        eguneratuTaulaJokalariakAldatu((String) comboIzquierda.getSelectedItem());
                    }
            	} else {
                    JOptionPane.showMessageDialog(this, "Errorea", "Jokalaria talde berdin horrtan dago", JOptionPane.ERROR_MESSAGE);
            	}
        	} catch(Exception er) {
        		JOptionPane.showMessageDialog(this, "Errorea", "Zerabait Txarto Atera da", JOptionPane.ERROR_MESSAGE);
        	}
        	
        // ==========================================================
        // ERABILTZAILE KUDEAKETA METODOAK (CRUD)
        // ==========================================================
        } else if (source == btnGehituKud) {
            String izena = txtIzenKud.getText();
            String abizena = txtAbizenKud.getText();
            String nan = txtNanKud.getText();
            String erab = txtErabKud.getText();
            String pas = txtPasahitzKud.getText(); 
            String mota = (String) comboMotaKud.getSelectedItem();

            if (nan.isEmpty() || izena.isEmpty() || erab.isEmpty() || pas.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Bete datu guztiak mesedez.", "Errorea", JOptionPane.ERROR_MESSAGE);
                return;
            }

            ErabiltzaileMota berria = null;
            if (mota.equals("Admin")) {
                berria = new Modeloa.Administradorea(izena, abizena, nan, erab, pas);
            } else if (mota.equals("Presidentea")) {
                berria = new Modeloa.Presidentea(izena, abizena, nan, erab, pas);
            } else {
                berria = new Modeloa.ErabiltzaileNormala(izena, abizena, nan, erab, pas);
            }

            edao.gehituErabiltzailea(berria);
            eguneratuErabiltzaileTaula();
            garbituKudeaketaEremuak();

        } else if (source == btnAldatuKud) {
            int row = tablaErabiltzaileak.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Aukeratu erabiltzaile bat taulan.", "Errorea", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nan = (String) modeloErabiltzaileak.getValueAt(row, 2);
            String izenBerria = txtIzenKud.getText();
            String abizenBerria = txtAbizenKud.getText(); 
            String erabBerria = txtErabKud.getText();     
            String pasahitzBerria = txtPasahitzKud.getText();

            if (izenBerria.isEmpty() || pasahitzBerria.isEmpty() || abizenBerria.isEmpty() || erabBerria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ezin dituzu datuak hutsik utzi.", "Errorea", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            edao.aldatuErabiltzailea(nan, izenBerria, abizenBerria, erabBerria, pasahitzBerria);
            eguneratuErabiltzaileTaula();
            garbituKudeaketaEremuak();

        } else if (source == btnEzabatuKud) {
            int row = tablaErabiltzaileak.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Aukeratu erabiltzaile bat taulan ezabatzeko.", "Errorea", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String nan = (String) modeloErabiltzaileak.getValueAt(row, 2);
            edao.ezabatuErabiltzailea(nan);
            eguneratuErabiltzaileTaula();
            garbituKudeaketaEremuak();
        }
    }
    
    // ==========================================================
    // 6. Tauleen eta combobox-aren metodoa
    // ==========================================================
    private void eguneratuTaulak(String aukeratutakoTaldea) {
        modeloPequena.setRowCount(0); 
        modeloGrande.setRowCount(0);
        for (Taldea taldea : taldeak) {
            if (taldea.getIzena().equals(aukeratutakoTaldea)) {
                modeloPequena.addRow(new Object[]{taldea.getSorreraUrtea(), taldea.getLehendakari(), taldea.getN_Bazkideak()});
                break;
            }
        }
        for (Jokalaria jokalaria : jokalariak) {
            if (jokalaria.getTaldea().equals(aukeratutakoTaldea)) {
                modeloGrande.addRow(new Object[]{
                    jokalaria.getIzena(), jokalaria.getAbizena(), jokalaria.getJaiotzeData(), jokalaria.getNAN(),                    
                });
            }
        }
    }
    
    private void eguneratuTaulaJokalariakAldatu(String aukeratutakoTaldea) {
        modeloIzquierda.setRowCount(0);
        for (Jokalaria jokalaria : jokalariak) {
            if (jokalaria.getTaldea().equals(aukeratutakoTaldea)) {
                modeloIzquierda.addRow(new Object[]{
                    jokalaria.getIzena(), jokalaria.getAbizena(), jokalaria.getJaiotzeData(),
                    jokalaria.getNAN(), jokalaria.getTaldea(), jokalaria.getPrezioa(),
                });
            }
        }
    }

    private void eguneratuErabiltzaileTaula() {
        modeloErabiltzaileak.setRowCount(0);
        erabiltzaileenZerrenda = edao.lortuErabiltzaileakODB(); 
        
        for (ErabiltzaileMota e : erabiltzaileenZerrenda) {
            modeloErabiltzaileak.addRow(new Object[]{
                e.getIzena(), e.getAbizena(), e.getNAN(), e.getErabiltzailea(), e.baimenak()
            });
        }
    }

    private void garbituKudeaketaEremuak() {
        txtIzenKud.setText(""); txtAbizenKud.setText(""); txtNanKud.setText("");
        txtErabKud.setText(""); txtPasahitzKud.setText("");
        tablaErabiltzaileak.clearSelection(); 
    }

    // ==========================================================
    // 7. Irteera Metodoa
    // ==========================================================
    public static void atera() {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Seguru atera nahi duzula?", "Agur", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
    // ==========================================================
    // 8. Panelak erakusteko metodoa
    // ==========================================================
    private void erakutsiPanelak(String baimenak) {
        klasifikazioaIkusi.setVisible(true);
        taldeakjokalariakIkusi.setVisible(true);
        sartuEmaitza.setVisible(baimenak.equals("Admin"));
        jokalariakAldatu.setVisible(baimenak.equals("Presidentea"));
        kudeaketaBotoia.setVisible(baimenak.equals("Admin"));
    }
    
    // ==========================================================
    // 9. Irudien Kudeaketa
    // ==========================================================
    private void konfiguratuOsagaiBisualak() {
        kargatuIrudia(logoaImg1, 200, 150, "/Multimedia/logoa.png");
        kargatuIrudia(logoaImg2, 200, 150, "/Multimedia/logoa.png");
    }

    private void kargatuIrudia(JLabel label, int w, int h, String path) {
        try {
            java.net.URL url = getClass().getResource(path);
            if (url != null) {
                Image img = new ImageIcon(url).getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(img));
                label.setText("");
            }
        } catch (Exception e) { 
            label.setText("Error Imagen"); 
        }
    }
    
    // ==========================================================
    // 10. EMAITZAK KUDEATZEKO METODOA
    // ==========================================================
    private void kargatuPartiduak(int jaurdunaldiZenbakia) {
        partiduGordeak = partiduaDAO.lortuJaurdunaldikoPartiduak(jaurdunaldiZenbakia);
        
        if (partiduGordeak.size() == 3) {
            Partidua p1 = partiduGordeak.get(0);
            lblLokala1.setText(p1.getTaldeLokala());
            lblBisitaria1.setText(p1.getTaldeBisitari());
            txtPuntuakLokala1.setText(p1.getResultLokala() != null ? String.valueOf(p1.getResultLokala()) : "");
            txtPuntuakBisitaria1.setText(p1.getResulBisitari() != null ? String.valueOf(p1.getResulBisitari()) : "");

            Partidua p2 = partiduGordeak.get(1);
            lblLokala2.setText(p2.getTaldeLokala());
            lblBisitaria2.setText(p2.getTaldeBisitari());
            txtPuntuakLokala2.setText(p2.getResultLokala() != null ? String.valueOf(p2.getResultLokala()) : "");
            txtPuntuakBisitaria2.setText(p2.getResulBisitari() != null ? String.valueOf(p2.getResulBisitari()) : "");

            Partidua p3 = partiduGordeak.get(2);
            lblLokala3.setText(p3.getTaldeLokala());
            lblBisitaria3.setText(p3.getTaldeBisitari());
            txtPuntuakLokala3.setText(p3.getResultLokala() != null ? String.valueOf(p3.getResultLokala()) : "");
            txtPuntuakBisitaria3.setText(p3.getResulBisitari() != null ? String.valueOf(p3.getResulBisitari()) : "");
        }
    }

    // ==========================================================
    // 11. Main (Abiarazlea)
    // ==========================================================
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new ErronkaBisuala());
    }
}