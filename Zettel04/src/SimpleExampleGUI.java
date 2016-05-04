import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;

class SimpleExampleGUI extends JFrame
{
    private JButton nutzer_hinzufuegen_btn = new JButton("Benutzer hinzufügen");
    private JButton buch_hinzufuegen_btn = new JButton("Buch aufnehmen");
    private JButton buch_ausleihen_btn = new JButton("Buch ausleihen");
    private JButton buch_zurueckgeben_btn = new JButton("Buch zurückgeben");

    // Names for the labels inside the dialogs
    String[] BenutzerLabels = {"Name:", "ID:", "Adresse:", "Geburtsdatum:", "Aufnahmedatum:"};
    String[] BuchLabels = {"Titel",  "ISBN", "Entleiher", "Schlagworte", "Verleihstatus"};
    //create a new Dialog
    Dialog nutzerDialog = new Dialog(BenutzerLabels, createComponents(0), "Nutzer hinzufügen");
    Dialog buchDialog = new Dialog(BuchLabels, createComponents(1), "Buch aufnehmen");
    
    String[] columnNames = {"Buchtitel", "ISBN", "Entliehen an"};
    Object[][] exampleData = {{"Human-Computer Interaction", "9780130461094", "Tina Müller"}};
    JScrollPane scrollPane = new JScrollPane();
    JTable datenbank = new JTable(exampleData, columnNames);
    
    SimpleExampleGUI()
    {
        buildMenu();
        setButtonTextSize();
        
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, 1));
        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout());
        scrollPane.setViewportView(datenbank);
        content.add(scrollPane);
        content.add(buttons);
        buttons.add(nutzer_hinzufuegen_btn);
        buttons.add(buch_hinzufuegen_btn);
        buttons.add(buch_ausleihen_btn);
        buttons.add(buch_zurueckgeben_btn);

        this.setContentPane(content);
        this.pack();

        nutzer_hinzufuegen_btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                nutzerDialog.launch();
            }
        });

        buch_hinzufuegen_btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                buchDialog.launch();
            }
        });

    }

    private void buildMenu()
    {
        JMenuBar menuBar;

        menuBar = new JMenuBar();

        JMenuItem database_new = new JMenuItem("Neue Datenbank");
        JMenuItem database_open = new JMenuItem("Datenbank öffnen");
        JMenuItem database_change = new JMenuItem("Datenbank wechseln");
        JMenuItem database_save = new JMenuItem("Datenbank speichern");
        JMenuItem database_close = new JMenuItem("Datenbank schließen");
        JMenuItem window_close = new JMenuItem("Beenden");
        JMenuItem user_add = new JMenuItem("Benutzer hinzufügen");
        JMenuItem book_add = new JMenuItem("Buch aufnehmen");
        JMenuItem book_borrow = new JMenuItem("Buch ausleihen");
        JMenuItem book_back = new JMenuItem("Buch zurückgeben");
        JMenuItem window_new = new JMenuItem("Neues Fenster");
        JMenuItem window_user = new JMenuItem("Benutzer anzeigen");
        JMenuItem window_book = new JMenuItem("Buch anzeigen");
        JMenuItem helpfile = new JMenuItem("Hilfedatei anzeigen");
        JMenuItem about = new JMenuItem("Über...");
        
        window_close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                PACT.window.dispose();
                }
        });
        
        user_add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                nutzerDialog.launch();
            }
        });
        
        book_add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                buchDialog.launch();
            }
        });
        
        ArrayList <JMenu> menus = new ArrayList<JMenu>();
        menus.add(new JMenu("Datei"));
        menus.add(new JMenu("Datenbank"));
        menus.add(new JMenu("Fenster"));
        menus.add(new JMenu("Hilfe"));
        menus.forEach((menu) -> menuBar.add(menu));
        menuBar.getMenu(0).add(database_new);
        menuBar.getMenu(0).add(new JSeparator());
        menuBar.getMenu(0).add(database_open);
        menuBar.getMenu(0).add(database_change);
        menuBar.getMenu(0).add(database_save);
        menuBar.getMenu(0).add(database_close);
        menuBar.getMenu(0).add(new JSeparator());
        menuBar.getMenu(0).add(window_close);
        menuBar.getMenu(1).add(user_add);
        menuBar.getMenu(1).add(new JSeparator());
        menuBar.getMenu(1).add(book_add);
        menuBar.getMenu(1).add(book_borrow);
        menuBar.getMenu(1).add(book_back);
        menuBar.getMenu(2).add(window_new);
        menuBar.getMenu(2).add(new JSeparator());
        menuBar.getMenu(2).add(window_user);
        menuBar.getMenu(2).add(window_book);
        menuBar.getMenu(3).add(helpfile);
        menuBar.getMenu(3).add(new JSeparator());
        menuBar.getMenu(3).add(about);
        setJMenuBar(menuBar);
    }
    
    private void setButtonTextSize()
    {
        float size = (float) (nutzer_hinzufuegen_btn.getFont().getSize() * 1.3);
        Font btnFont = nutzer_hinzufuegen_btn.getFont().deriveFont(size);
        nutzer_hinzufuegen_btn.setFont(btnFont);
        buch_hinzufuegen_btn.setFont(btnFont);
        buch_ausleihen_btn.setFont(btnFont);
        buch_zurueckgeben_btn.setFont(btnFont);
    }
    
    /**
     * Creates the TextFields, Numberfields and Radio Buttons for the dialogs to use.
     * @param BenutzerOrBuch determines, which kind of dialog to create the components for.
     * @return ArrayList<Component> components the List of components
     */
    private ArrayList<Component> createComponents(int BenutzerOrBuch)
    {
        ArrayList<Component> components = new ArrayList<Component>();
        NumberFormat numberFormat = NumberFormat.getIntegerInstance();
        numberFormat.setGroupingUsed(false);
        
        if(BenutzerOrBuch == 0)
        {
            components.add(new JTextField());
            // accept numbers only
            JFormattedTextField idFeld = new JFormattedTextField(numberFormat);
            idFeld.setColumns(10);
            idFeld.addKeyListener(new KeyAdapter()
            {
                public void keyTyped(KeyEvent e)
                {
                    char ch = e.getKeyChar();

                    if (ch < '0' || ch > '9')
                    {
                        e.consume();
                    }
                }
            });
            components.add(idFeld);
            // Empty TextFields 
            components.add(new JTextField());
            components.add(new JTextField());
            components.add(new JTextField());
            // Radio Buttons
            JRadioButton maennlich = new JRadioButton("Männlich");
            maennlich.setSelected(true);
            JRadioButton weiblich = new JRadioButton("Weiblich");
            ButtonGroup groupSex = new ButtonGroup();
            groupSex.add(maennlich);
            groupSex.add(weiblich);
            components.add(maennlich);
            components.add(weiblich);
        }
        else
        {
            components.add(new JTextField());
            //Accept Numbers only
            JFormattedTextField idFeld = new JFormattedTextField(numberFormat);
            idFeld.setColumns(10);
            idFeld.addKeyListener(new KeyAdapter()
            {
                public void keyTyped(KeyEvent e)
                {
                    char ch = e.getKeyChar();

                    if (ch < '0' || ch > '9')
                    {
                        e.consume();
                    }
                }
            });
            components.add(idFeld);
            // Empty TextFields
            components.add(new JTextField());
            // Drop down menu
            String[] schlagworte = {"eins", "zwei", "drei"};
            components.add(new JComboBox<String>(schlagworte));
            // RadioButtons
            JRadioButton verfuegbar = new JRadioButton("verfügbar");
            verfuegbar.setSelected(true);
            JRadioButton entliehen = new JRadioButton("entliehen");
            ButtonGroup groupStatus = new ButtonGroup();
            groupStatus.add(verfuegbar);
            groupStatus.add(entliehen);
            components.add(verfuegbar);
            components.add(entliehen);
        }
        return components;
    }
    
}