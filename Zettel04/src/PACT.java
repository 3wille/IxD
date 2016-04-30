import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PACT {
    public static void main(String[] args) {
        JFrame window = new SimpleExampleGUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Simple GUI");
        window.setSize(800, 600);
        window.setVisible(true);
    }
}

class SimpleExampleGUI extends JFrame {
    private JButton nutzer_hinzufuegen_btn = new JButton("Nutzer hinzufügen");
    private JButton buch_hinzufuegen_btn = new JButton("Buch aufnehmen");
    private JButton buch_ausleihen_btn = new JButton("Buch ausleihen");
    private JButton buch_zurueckgeben_btn = new JButton("Buch zurückgeben");

    SimpleExampleGUI() {
        buildMenu();
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(nutzer_hinzufuegen_btn);
        content.add(buch_hinzufuegen_btn);
        content.add(buch_ausleihen_btn);
        content.add(buch_zurueckgeben_btn);
        JDialog nutzerDialog = buildDialog();
        JDialog buchDialog = buildDialog();
        
        this.setContentPane(content);
        this.pack();

        nutzer_hinzufuegen_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               nutzerDialog.setVisible(true);
            }
        });
        
        buch_hinzufuegen_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               buchDialog.setVisible(true);
            }
        });
        
    }
    
    /**
     * Baut das Dialogfenster zum Hinzufügen von Benutzern.
     */
    private JDialog buildDialog()
    {
        //Dialogfenster
        JDialog nutzerHinzu = new JDialog();
        nutzerHinzu.setMinimumSize(new Dimension(300,300));
        nutzerHinzu.setTitle("Nutzer hinzufügen");

        // Create Lables
        JLabel name = new JLabel("Name:");
        JLabel id = new JLabel("ID:");
        JLabel adresse = new JLabel("Adresse:");
        JLabel geburt = new JLabel("Geburtsdatum:");
        JLabel aufnahme = new JLabel("Aufnahmedatum:");
        
        // Create Textfields
        JTextField nameFeld = new JTextField("Name");
        JTextField adressFeld = new JTextField("Adresse");
        JTextField geburtsFeld = new JTextField("Geburtsdatum");
        JTextField aufnahmeFeld = new JTextField("Aufnahmedatum");
        
        //Numberfield for IDs
        JFormattedTextField idFeld = new JFormattedTextField(NumberFormat.getNumberInstance());
        idFeld.setValue(new Integer(30));
        idFeld.setColumns(10);
        idFeld.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                
                if (ch < '0' || ch > '9') {
                    e.consume();
                }}}
                );
        
        // Radio buttons for sex
        JRadioButton maennlich = new JRadioButton("Männlich");
        JRadioButton weiblich = new JRadioButton("Weiblich");
        ButtonGroup group = new ButtonGroup();
        group.add(maennlich);
        group.add(weiblich);

        // Optionpane inside the Dialog
        JOptionPane option = new JOptionPane(null);
        Object[] options = {"Abbrechen", "Aufnehmen"};
        // rename the buttons
        option.setOptions(options);
        
        // Panel inside the Optionpane to prevent Textfields being positioned under the buttons. Dunno if necessary.
        JPanel panel = new JPanel();
        option.add(panel, 0);
        nutzerHinzu.add(option);

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        
        //Set Constraints for Lables and corresponding Textfields and add them to the Panel
        c.gridx = 0;
        c.gridy = 0;
        c.fill = c.HORIZONTAL;
        layout.setConstraints(name, c);
        panel.add(name, c);
        c.gridx = 1;
        c.gridy = 0;
        layout.setConstraints(nameFeld, c);
        panel.add(nameFeld, c);
        
        c.gridx = 0;
        c.gridy = 1;
        layout.setConstraints(id, c);
        panel.add(id, c);
        c.gridx = 1;
        c.gridy = 1;
        layout.setConstraints(idFeld, c);
        panel.add(idFeld, c);

        
        c.gridx = 0;
        c.gridy = 2;
        layout.setConstraints(adresse, c);
        panel.add(adresse, c);
        c.gridx = 1;
        c.gridy = 2;
        layout.setConstraints(adressFeld, c);
        panel.add(adressFeld, c);

        
        c.gridx = 0;
        c.gridy = 3;
        layout.setConstraints(geburt, c);
        panel.add(geburt, c);
        c.gridx = 1;
        c.gridy = 3;
        layout.setConstraints(geburtsFeld, c);
        panel.add(geburtsFeld, c);

        
        c.gridx = 0;
        c.gridy = 4;
        layout.setConstraints(aufnahme, c);
        panel.add(aufnahme, c);
        c.gridx = 1;
        c.gridy = 4;
        layout.setConstraints(aufnahmeFeld, c);
        panel.add(aufnahmeFeld, c);
        
        c.gridx = 0;
        c.gridy = 5;
        layout.setConstraints(maennlich, c);
        panel.add(maennlich, c);
        
        c.gridx = 1;
        c.gridy = 5;
        layout.setConstraints(weiblich, c);
        panel.add(weiblich, c);

        // Pack and return dialog.
        panel.setLayout(layout);
        nutzerHinzu.pack();
        return nutzerHinzu;

    }
   
    private void buildMenu() {
        JMenuBar menuBar;
        JRadioButtonMenuItem rbMenuItem;
        JCheckBoxMenuItem cbMenuItem;

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
            public void actionPerformed(ActionEvent e) {
            	window.dispose();
            	};
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
}
