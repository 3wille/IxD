import java.awt.*;
import javax.swing.*;
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
    private JSlider fahrenSlider;

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

        JSlider celsiusSlider = buildSlider(-40, 120, 0);
        JSlider fahrSlider = buildSlider(-40, 80, 0);
        content.add(celsiusSlider, BorderLayout.WEST);
        content.add(fahrSlider, BorderLayout.EAST);

        content.add(buildCenter(), BorderLayout.CENTER);

        this.setContentPane(content);
        this.pack();
    }

    private void buildCelsiusCenter(JPanel center){
        JLabel celsiusLabel = new JLabel("Celsius");
        celsiusField = new JFormattedTextField(NumberFormat.getNumberInstance());
        celsiusField.setValue(new Integer(30));
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
        JButton convert2Celsius = new JButton("<- Convert2Fahreheit");
        fahrenField = new JFormattedTextField(NumberFormat.getNumberInstance());
        fahrenField.setValue(new Integer(30));
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

    private JSlider buildSlider(int min, int max, int init){
        JSlider slider = new JSlider(JSlider.VERTICAL, min, max, init);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        return slider;
    }


    @Override
    public void update(Observable o, Object arg) {
        ConverterModel model = (ConverterModel) o;
        celsiusField.setValue(model.getCelsius());
        fahrenField.setValue(model.getFahrenheit());
    }
}
