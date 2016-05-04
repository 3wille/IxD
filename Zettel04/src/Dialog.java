import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A Dialog for adding users or books.
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
    /**
     * Create the Labels for the dialogs to use.
     * @param labelNamen
     * @return List of JLabels
     */
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
     * Initializes the dialogs.
     * @param labelNamen names for the labels
     * @param components components to add.
     * @param dialogNamen the name of the dialog
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