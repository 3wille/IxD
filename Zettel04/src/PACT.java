import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.math.*;
import java.text.NumberFormat;
import java.util.ArrayList;

public class PACT
{
    public static void main(String[] args)
    {
        JFrame window = new SimpleExampleGUI();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Simple GUI");
        window.setSize(800, 600);
        window.setVisible(true);
    }
}

class SimpleExampleGUI extends JFrame
{
    private JButton nutzer_hinzufuegen_btn = new JButton("Nutzer hinzufügen");
    private JButton buch_hinzufuegen_btn = new JButton("Buch aufnehmen");

    SimpleExampleGUI()
    {
        buildMenu();
        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(nutzer_hinzufuegen_btn);
        content.add(buch_hinzufuegen_btn);
        // Names for the labels inside the dialogs
        String[] BenutzerLabels = {"Name:", "Adresse:", "ID:", "geburt", "aufnahme"};
        String[] BuchLabels = {"Titel", "Verleihstatus", "Entleiher", "ISBN", "Schlagworte"};
        //create a new Dialog
        Dialog nutzerDialog = new Dialog(BenutzerLabels, createComponents(0));
        Dialog buchDialog = new Dialog(BuchLabels, createComponents(1));

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

        ArrayList<JMenu> menus = new ArrayList<JMenu>();
        menus.add(new JMenu("File"));
        menus.add(new JMenu("Datenbank"));
        menus.add(new JMenu("Fenster"));
        menus.add(new JMenu("Hilfe"));
        menus.forEach((menu) -> menuBar.add(menu));

        setJMenuBar(menuBar);
    }
    
 // Create Textfields
    private ArrayList<Component> createComponents(int BenutzerOrBuch)
    {
        ArrayList<Component> components = new ArrayList<Component>();
        
        if(BenutzerOrBuch == 0)
        {
            components.add(new JTextField("Name"));
            JFormattedTextField idFeld = new JFormattedTextField(NumberFormat.getNumberInstance());
            idFeld.setValue(new Integer(30));
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
            components.add(new JTextField("Adresse"));
            components.add(new JTextField("Geburtsdatum"));
            components.add(new JTextField("Aufnahmedatum"));
        }
        else
        {
            components.add(new JTextField("Name"));
            JFormattedTextField idFeld = new JFormattedTextField(NumberFormat.getNumberInstance());
            idFeld.setValue(new Integer(30));
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
            components.add(new JTextField("Adresse"));
            components.add(new JTextField("Geburtsdatum"));
            components.add(new JTextField("Aufnahmedatum"));
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
    
    // Create Lables
    private JLabel name = new JLabel("Name:");
    private JLabel id = new JLabel("ID:");
    private JLabel adresse = new JLabel("Adresse:");
    private JLabel geburt = new JLabel("Geburtsdatum:");
    private JLabel aufnahme = new JLabel("Aufnahmedatum:");
    
    private JLabel[] labelArray = {name, id, adresse, geburt, aufnahme};

    public void nameLabels(String[] names)
    {
        int i = 0;
        for(JLabel label: labelArray)
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
        
        // Radio buttons for sex
        JRadioButton maennlich = new JRadioButton("Männlich");
        JRadioButton weiblich = new JRadioButton("Weiblich");
        ButtonGroup group = new ButtonGroup();
        group.add(maennlich);
        group.add(weiblich);
        
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
        layout.setConstraints(components.get(0), c);
        panel.add(components.get(0), c);

        c.gridx = 0;
        c.gridy = 1;
        layout.setConstraints(id, c);
        panel.add(id, c);
        c.gridx = 1;
        c.gridy = 1;
        layout.setConstraints(components.get(1), c);
        panel.add(components.get(1), c);

        c.gridx = 0;
        c.gridy = 2;
        layout.setConstraints(adresse, c);
        panel.add(adresse, c);
        c.gridx = 1;
        c.gridy = 2;
        layout.setConstraints(components.get(2), c);
        panel.add(components.get(2), c);

        c.gridx = 0;
        c.gridy = 3;
        layout.setConstraints(geburt, c);
        panel.add(geburt, c);
        c.gridx = 1;
        c.gridy = 3;
        layout.setConstraints(components.get(3), c);
        panel.add(components.get(3), c);

        c.gridx = 0;
        c.gridy = 4;
        layout.setConstraints(aufnahme, c);
        panel.add(aufnahme, c);
        c.gridx = 1;
        c.gridy = 4;
        layout.setConstraints(components.get(4), c);
        panel.add(components.get(4), c);

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
    }

    /**
     * Baut das Dialogfenster zum Hinzufügen von Benutzern.
     * @param labelNamen names for the labels
     * @param components Textfields to add.
     */
    public Dialog(String[] labelNamen, ArrayList<Component> components)
    {
        componentsList = components;
        
        
        //Dialogfenster
        nutzerHinzu = new JDialog();
        nutzerHinzu.setMinimumSize(new Dimension(300, 300));
        nutzerHinzu.setTitle("Nutzer hinzufügen");
        
        nameLabels(labelNamen);
        nameFields(labelNamen);

        
        

        // Optionpane inside the Dialog
        option = new JOptionPane(null);
        Object[] options = {"Abbrechen", "Aufnehmen"};
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
