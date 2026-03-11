package Kontrolatzailea;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ErronkaBisuala extends JFrame implements ActionListener, WindowListener {

    // ==========================================================
    // 1. Atributuak (Pribatuak)
    // ==========================================================
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private Font titleFont;

    // --- Panelen Osagaiak ---
    private JPanel LoginPanela, HasierakoPanela, KlasifikazioaPanela, EmaitzaPanela, TaldeakPanela, JokalariakPanela;
    
    // Login
    private JLabel logoaImg1, erabiltzaileak, pasahitza;
    private JTextField textErabiltzaile;
    private JPasswordField textPasahitza;
    private JButton sartu, atera1;

    // Hasiera
    private JLabel logoaImg2;
    private JButton atzerantz, atera2, klasifikazioaIkusi, sartuEmaitza, taldeakIkusi, jokalariakAldatu;

    // Klasifikazioa
    private JButton atzerantzKlasif, ateraKlasif;
    private JTable tablaKlasif;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollTabla;

    // Taldeak Ikusi
    private JComboBox<String> comboBox;
    private JTable tablaPequena, tablaGrande;
    private JButton atzerantzTaldeak, ateraTaldeak;
    
    // Emaitza
    private JTable tablaEmaitzak;
    private DefaultTableModel modeloEmaitzak;
    private JButton atzerantzEmaitza, ateraEmaitza, gordeEmaitza;

    // Jokalariak
    private JComboBox<String> comboIzquierda, comboDerecha;
    private JTable tablaIzquierda, tablaDerecha;
    private JButton btnAldatu, btnIrten, btnAtera;


    // ==========================================================
    // 2. Eraikitzailea (Constructor)
    // ==========================================================
    public ErronkaBisuala() {
        setTitle("FNFS Liga Kudeatzailea");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        setSize(802, 604); // TAMAÑO COMPACTADO A 800x600
        setLocationRelativeTo(null);
        titleFont = new Font("Verdana", Font.BOLD, 24);

        this.addWindowListener(this);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        setContentPane(contentPanel);

        // 1. Panelak eta osagaiak sortu
        inizializatuPanelak();
        
        // 2. Irudiak kargatu JLabelek sortuta daudenean
        konfiguratuOsagaiBisualak();

        contentPanel.add(LoginPanela, "Login");
        contentPanel.add(HasierakoPanela, "Hasiera");
        contentPanel.add(KlasifikazioaPanela, "Klasifikazioa");
        contentPanel.add(EmaitzaPanela, "Emaitzak");
        contentPanel.add(TaldeakPanela, "Taldeak");
        contentPanel.add(JokalariakPanela, "Jokalariak");

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
        this.getRootPane().setDefaultButton(sartu); // Enter sakatzean exekutatzeko
        
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
        
        taldeakIkusi = new JButton("Taldeak ikusi"); 
        taldeakIkusi.setBounds(150, 420, 200, 40);
        
        jokalariakAldatu = new JButton("Jokalariak Aldatu"); 
        jokalariakAldatu.setBounds(450, 420, 200, 40);

        HasierakoPanela.add(logoaImg2); 
        HasierakoPanela.add(atzerantz); HasierakoPanela.add(atera2);
        HasierakoPanela.add(klasifikazioaIkusi); HasierakoPanela.add(sartuEmaitza);
        HasierakoPanela.add(taldeakIkusi); HasierakoPanela.add(jokalariakAldatu);

        // --- KLASIFIKAZIOA PANELA  ---
        KlasifikazioaPanela = new JPanel(null);
        JLabel titleKlasif = new JLabel("LIGAKO KLASIFIKAZIOA", JLabel.CENTER);
        titleKlasif.setBounds(0, 20, 800, 30);
        titleKlasif.setFont(titleFont);

        String[] zutabeTituluak = {"Taldea", "P. Totalak", "Irabazi", "Galdu", "Aldeko", "Aurkako"};
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
        comboIzquierda.setBounds(50, 60, 200, 25);
        JokalariakPanela.add(comboIzquierda);
     
        comboDerecha = new JComboBox<>();
        comboDerecha.setBounds(430, 60, 200, 25);
        JokalariakPanela.add(comboDerecha);

        String[] columnasJok = {"Izena", "Abizena", "DNI", "Taldea"};
        DefaultTableModel modeloIzquierda = new DefaultTableModel(columnasJok, 0);
        tablaIzquierda = new JTable(modeloIzquierda);
        JScrollPane scrollIzquierda = new JScrollPane(tablaIzquierda);
        scrollIzquierda.setBounds(50, 100, 300, 340);
        JokalariakPanela.add(scrollIzquierda);

        DefaultTableModel modeloDerecha = new DefaultTableModel(columnasJok, 0);        
        tablaDerecha = new JTable(modeloDerecha);
        JScrollPane scrollDerecha = new JScrollPane(tablaDerecha);
        scrollDerecha.setBounds(430, 100, 300, 340);
        JokalariakPanela.add(scrollDerecha);

        btnAldatu = new JButton("Aldatu");
        btnAldatu.setBounds(360, 240, 60, 40);
        JokalariakPanela.add(btnAldatu);

        btnIrten = new JButton("Irten");
        btnIrten.setBounds(50, 470, 100, 30);
        JokalariakPanela.add(btnIrten);

        btnAtera = new JButton("Atera");
        btnAtera.setBounds(630, 470, 100, 30);
        JokalariakPanela.add(btnAtera);

        // --- TALDEAK PANELA ---
        TaldeakPanela = new JPanel(null);

        comboBox = new JComboBox<>();
        comboBox.setBounds(300, 20, 200, 25);
        TaldeakPanela.add(comboBox);

        String[] columnasPequena = {"SorreraUrtea", "Lehendakari", "N_Bazkideak"};
        DefaultTableModel modeloPequena = new DefaultTableModel(columnasPequena,0);
        tablaPequena = new JTable(modeloPequena);
        JScrollPane scrollPequena = new JScrollPane(tablaPequena);
        scrollPequena.setBounds(50, 60, 680, 40);
        TaldeakPanela.add(scrollPequena);

        String[] columnasGrande = {"Izena","Abizena","JaiotzeData","NAN","Taldea","Prezioa","JokalariarenPuntuak"};
        DefaultTableModel modeloGrande = new DefaultTableModel(columnasGrande,0);
        tablaGrande = new JTable(modeloGrande);
        JScrollPane scrollGrande = new JScrollPane(tablaGrande);
        scrollGrande.setBounds(50, 120, 680, 320);
        TaldeakPanela.add(scrollGrande);

        atzerantzTaldeak = new JButton("Atzerantz");
        atzerantzTaldeak.setBounds(50, 470, 100, 30);

        ateraTaldeak = new JButton("Atera");
        ateraTaldeak.setBounds(630, 470, 100, 30);

        TaldeakPanela.add(atzerantzTaldeak);
        TaldeakPanela.add(ateraTaldeak);

        // --- EMAITZA PANELA ---
        EmaitzaPanela = new JPanel(null);
        JLabel titleEmaitza = new JLabel("LIGAKO EMAITZAK", JLabel.CENTER);
        titleEmaitza.setBounds(0, 20, 800, 30);
        titleEmaitza.setFont(titleFont);

        modeloEmaitzak = new DefaultTableModel(new String[]{"Jornada / Partidua", "Puntuak", "vs", "Puntuak", "Kanpoko Taldea"}, 0);
        tablaEmaitzak = new JTable(modeloEmaitzak);
        JScrollPane scrollEmaitzak = new JScrollPane(tablaEmaitzak);
        scrollEmaitzak.setBounds(50, 80, 680, 360);

        atzerantzEmaitza = new JButton("Atzerantz"); 
        atzerantzEmaitza.setBounds(50, 470, 100, 30);

        gordeEmaitza = new JButton("Gorde Emaitzak");
        gordeEmaitza.setBounds(300, 470, 200, 30);

        ateraEmaitza = new JButton("Atera"); 
        ateraEmaitza.setBounds(630, 470, 100, 30);

        EmaitzaPanela.add(titleEmaitza);
        EmaitzaPanela.add(scrollEmaitzak);
        EmaitzaPanela.add(atzerantzEmaitza);
        EmaitzaPanela.add(gordeEmaitza);
        EmaitzaPanela.add(ateraEmaitza);


        // ==========================================================
        // --- THE LISTENERS (Botoien eta osagaien ekintzak) ---
        // ==========================================================
        
        // Login listeners
        sartu.addActionListener(this);
        atera1.addActionListener(this);

        // Hasiera listeners
        atzerantz.addActionListener(this);
        atera2.addActionListener(this);
        klasifikazioaIkusi.addActionListener(this);
        sartuEmaitza.addActionListener(this);
        taldeakIkusi.addActionListener(this);
        jokalariakAldatu.addActionListener(this);

        // Klasifikazioa listeners
        atzerantzKlasif.addActionListener(this);
        ateraKlasif.addActionListener(this);

        // Jokalariak listeners
        comboIzquierda.addActionListener(this);
        comboDerecha.addActionListener(this);
        btnAldatu.addActionListener(this);
        btnIrten.addActionListener(this);
        btnAtera.addActionListener(this);

        // Taldeak listeners
        comboBox.addActionListener(this);
        atzerantzTaldeak.addActionListener(this);
        ateraTaldeak.addActionListener(this);

        // Emaitzak listeners
        atzerantzEmaitza.addActionListener(this);
        gordeEmaitza.addActionListener(this);
        ateraEmaitza.addActionListener(this);
    }


    // ==========================================================
    // 4. Ekintzen Kudeaketa (ActionListener)
    // ==========================================================
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        // --- LOGIN ---
        if (source == sartu) {
            // DAO Login
            cardLayout.show(contentPanel, "Hasiera");
            
        // --- ATERA / IRTEN (Guztiak) ---
        } else if (source == atera1 || source == atera2 || source == ateraKlasif || 
                   source == btnAtera || source == ateraTaldeak || source == ateraEmaitza) {
            atera();
            
        // --- HASIERA NAVEGAZIOA ---
        } else if (source == klasifikazioaIkusi) {
            cardLayout.show(contentPanel, "Klasifikazioa");
        } else if (source == sartuEmaitza) {
            cardLayout.show(contentPanel, "Emaitzak");
        } else if (source == taldeakIkusi) {
            cardLayout.show(contentPanel, "Taldeak");
        } else if (source == jokalariakAldatu) {
            cardLayout.show(contentPanel, "Jokalariak");

        // --- ATZERANTZ / BUELTATU (Guztiak) ---
        } else if (source == atzerantzKlasif || source == atzerantzEmaitza || 
                   source == btnIrten || source == atzerantzTaldeak) {
            cardLayout.show(contentPanel, "Hasiera");

        } else if (source == atzerantz) {
            cardLayout.show(contentPanel, "Login");

        // --- BESTELAKO EKINTZAK (COMBOBOX ETA ALDATU) ---
        } else if (source == comboIzquierda) {
            // DAO logika
        } else if (source == comboDerecha) {
            // DAO logika
        } else if (source == comboBox) {
            // DAO logika
        } else if (source == btnAldatu) {
            // DAO logika
        } else if (source == gordeEmaitza) {
            // DAO logika
        }
    }

    // ==========================================================
    // 5. Irudien Kudeaketa
    // ==========================================================
    /**
     * Aplikazioko osagai bisualak (irudiak) kargatzen eta esleitzen ditu.
     */
    private void konfiguratuOsagaiBisualak() {
        // img1 eta img2 ezabatu dira hemendik
        kargatuIrudia(logoaImg1, 200, 150, "/Multimedia/logoa.png");
        kargatuIrudia(logoaImg2, 200, 150, "/Multimedia/logoa.png");
    }

    /**
     * Irudi bat baliabideetatik kargatu eta JLabel batean ezartzen du.
     *
     * @param label irudia bistaratuko duen JLabel-a
     * @param w irudiaren zabalera
     * @param h irudiaren altuera
     * @param path irudiaren fitxategiaren bidea
     */
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
    // 6. WindowListener Kudeaketa
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
    // 7. Irteera Metodoa
    // ==========================================================
    public static void atera() {
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Atera baino lehen, gorde nahi duzu?", "Berrespena", JOptionPane.YES_NO_CANCEL_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            gordeDatuak();
            System.exit(0);
        } else if (respuesta == JOptionPane.NO_OPTION) {
            System.exit(0);
        }
    }
    
    private static void gordeDatuak() {
        // Zure DAO-ko gordeDatuak deituko da hemen
    }

    // ==========================================================
    // 8. Main (Abiarazlea)
    // ==========================================================
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new ErronkaBisuala());
    }
}