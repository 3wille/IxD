import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PACT
{
	static final JFrame window = new SimpleExampleGUI();
	
	public static void main(String[] args)
    {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Simple GUI");
        window.setSize(800, 600);
        window.setVisible(true);
    }
}

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
    Object[][] exampleData = {{"Human-Computer Interaction", "9780130461094", "Tina Müller"}
    };
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
    
 // Create Textfields
    private ArrayList<Component> createComponents(int BenutzerOrBuch)
    {
        ArrayList<Component> components = new ArrayList<Component>();
        
        if(BenutzerOrBuch == 0)
        {
            components.add(new JTextField());
            //accept numbers only
            JFormattedTextField idFeld = new JFormattedTextField(NumberFormat.getNumberInstance());
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
            
            components.add(new JTextField());
            components.add(new JTextField());
            components.add(new JTextField());
            
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
            JFormattedTextField idFeld = new JFormattedTextField(NumberFormat.getNumberInstance());
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
            
            components.add(new JTextField());
            //Drop down menu
            String[] schlagworte = {"eins", "zwei", "drei"};
            components.add(new JComboBox<String>(schlagworte));
            
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
/**
 * A Dialog.
 * @author macbook
 *
 */
class Dialog
{
    private JDialog nutzerHinzu;
    private JOptionPane option;
    private JPanel panel;
    private ArrayList<Component> componentsList;
    private ArrayList<JLabel> labelList;

    public void nameLabels(String[] names)
    {
        int i = 0;
        for(JLabel label: labelList)
        {
            label.setText(names[i]);
            i += 1;
        }
    }
    
    public void nameFields(String[] names)
    {
        int i = 0;
        for(Component field: componentsList)
        {
            ((JTextField) field).setText(names[i]);
            i += 1;
        }
    }
    
    public void launch()
    {
        nutzerHinzu.setVisible(true);
    }
    
    public void addComponents(ArrayList<Component> components)
    {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        
        // Add Labels and Fields along with their layout constraints
        c.fill = GridBagConstraints.HORIZONTAL;
        for(int i = 0; i < labelList.size(); i++)
        {
            c.gridx = 0;
            c.gridy = i;
            layout.setConstraints(labelList.get(i), c);
            panel.add(labelList.get(i), c);
            c.gridx = 1;
            c.gridy = i;
            layout.setConstraints(components.get(i), c);
            panel.add(components.get(i), c);
        }
        for(int i = labelList.size(); i < components.size(); i++)
        {
            c.gridx = 1;
            c.gridy = i;
            layout.setConstraints(components.get(i), c);
            panel.add(components.get(i), c);
        }

        
     // Pack and return dialog.
        panel.setLayout(layout);
    }
    
    private ArrayList<JLabel> createLabels (String[] labelNamen)
    {
        ArrayList<JLabel> labels = new ArrayList<JLabel>();
        for(String name: labelNamen)
        {
            labels.add(new JLabel(name));
        }
        return labels;
    }

    /**
     * Baut das Dialogfenster zum Hinzufügen von Benutzern.
     * @param labelNamen names for the labels
     * @param components Textfields to add.
     */
    public Dialog(String[] labelNamen, ArrayList<Component> components, String dialogName)
    {
        componentsList = components;
        labelList = createLabels(labelNamen);
        
        
        //Dialogfenster
        nutzerHinzu = new JDialog();
        nutzerHinzu.setMinimumSize(new Dimension(300, 300));
        nutzerHinzu.setTitle(dialogName);
        
        nameLabels(labelNamen);
        //nameFields(labelNamen);

        
        

        // Optionpane inside the Dialog
        option = new JOptionPane(null);
        JButton abbrechen_btn = new JButton("Abbrechen");
        JButton aufnehmen_btn = new JButton("Aufnehmen");
        Object[] options = {abbrechen_btn, aufnehmen_btn};
        
        abbrechen_btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                nutzerHinzu.dispose();
            }
        });
        
        aufnehmen_btn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                nutzerHinzu.dispose();
            }
        });
        // rename the buttons
        option.setOptions(options);

        // Panel inside the Optionpane to prevent Textfields being positioned under the buttons. Dunno if necessary.
        panel = new JPanel();
        option.add(panel, 0);
        nutzerHinzu.setContentPane(option);

        addComponents(components);

        
        nutzerHinzu.pack();

    }
}