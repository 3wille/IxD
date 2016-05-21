import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Observable;

@SuppressWarnings("serial")
public class ConverterView extends JFrame implements java.util.Observer{
    private JPanel centerPanel;
    private ConverterController controller;
    private JFormattedTextField celsiusField;
    private JFormattedTextField fahrenField;
    private JSlider celsiusSlider;
    private JSlider fahrSlider;
    private boolean changeAlreadyHandled;

    public ConverterView(ConverterController c){
        buildUI();
        controller = c;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Simple GUI");
        setSize(900, 400);
        setVisible(true);
    }

    private void buildUI(){
        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        celsiusSlider = buildSlider(-40, 80, 10, 2, 0);
        celsiusSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(!changeAlreadyHandled){
                    controller.celSliderChanged(e);
                }

            }
        });
        fahrSlider = buildSlider(-40, 176, 10, 5, 32);
        fahrSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // only update if a change hasnt already been handled.
                if(!changeAlreadyHandled) {
                    controller.fahrSliderChanged(e);
                }
            }
        });
        content.add(celsiusSlider, BorderLayout.WEST);
        content.add(fahrSlider, BorderLayout.EAST);

        content.add(buildCenter(), BorderLayout.CENTER);

        this.setContentPane(content);
        this.pack();
    }

    private void buildCelsiusCenter(JPanel center){
        JLabel celsiusLabel = new JLabel("Celsius");
        celsiusField = new JFormattedTextField(NumberFormat.getNumberInstance());
        celsiusField.setValue(new Integer(0));
        celsiusField.setColumns(10);
        celsiusField.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();

                if (ch < '0' || ch > '9') {
                    e.consume();
                }}}
        );
        celsiusField.setMaximumSize(new Dimension(60, 40));
        JButton convert2Fahrenheit = new JButton("Convert2Fahrenheit ->");
        convert2Fahrenheit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float value = Float.parseFloat(celsiusField.getValue().toString());
                controller.acknowledgeCelsius(value);
            }
        });

        center.add(celsiusLabel);
        center.add(celsiusField);
        center.add(convert2Fahrenheit);
    }


    private void buildFahrenCenter(JPanel center){
        JButton convert2Celsius = new JButton("<- Convert2Celsius");
        fahrenField = new JFormattedTextField(NumberFormat.getNumberInstance());
        fahrenField.setValue(new Integer(32));
        fahrenField.setColumns(10);
        fahrenField.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();

                if (ch < '0' || ch > '9') {
                    e.consume();
                }}}
        );
        fahrenField.setMaximumSize(new Dimension(60, 40));
        JLabel fahrenheitLabel = new JLabel("Fahrenheit");
        convert2Celsius.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float value = Float.parseFloat(fahrenField.getValue().toString());
                controller.acknowledgeFahrenheit(value);
            }
        });
        center.add(fahrenheitLabel);
        center.add(fahrenField);
        center.add(convert2Celsius);
    }

    private JPanel buildCenter(){
        JPanel centerPane = new JPanel();
        centerPane.setLayout(new GridBagLayout());

        JPanel center = new JPanel();
        centerPane.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        center.setMinimumSize(new Dimension(500,500));
        centerPanel = center;
        // center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
        center.setLayout(new GridLayout(2, 3));
        System.out.println(center.getAlignmentY());

        buildCelsiusCenter(center);
        buildFahrenCenter(center);

        centerPane.add(center);
        return centerPane;
    }

    private void addComponentToCenter(JComponent component){
        centerPanel.add(component);
        centerPanel.add(Box.createRigidArea(new Dimension(5,0)));
    }

    private JSlider buildSlider(int min, int max, int majorSpacing, int minorSpacing, int init){
        JSlider slider = new JSlider(JSlider.VERTICAL, min, max, init);
        slider.setMajorTickSpacing(majorSpacing);
        slider.setMinorTickSpacing(minorSpacing);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }


    @Override
    public void update(Observable o, Object arg) {
        ConverterModel model = (ConverterModel) o;
        celsiusField.setValue(model.getCelsius());
        fahrenField.setValue(model.getFahrenheit());
        // Set the values of the sliders according to the model. changeAlreadyHandled == true avoids infinite loops since
        // setValue notifies listeners of the Sliders again.
        int val = (int) model.getFahrenheit();
        changeAlreadyHandled = true;
        celsiusSlider.setValue((int) model.getCelsius());
        fahrSlider.setValue(val);
        changeAlreadyHandled = false;
    }
}
