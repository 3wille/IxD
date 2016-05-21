import javax.swing.*;
import javax.swing.event.ChangeEvent;

public class ConverterController{
    ConverterModel model;
    ConverterView view;

    public ConverterController(){
        view = new ConverterView(this);
        model = new ConverterModel();
        model.addObserver(view);
    }

    public void acknowledgeCelsius(float celsius)
    {
        model.setCelsius(celsius);
    }

    public void acknowledgeFahrenheit(float fahrenheit)
    {
        model.setFahrenheit(fahrenheit);
    }

    public void celSliderChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        float val = slider.getValue();
        model.setCelsius(val);
    }

    public void fahrSliderChanged(ChangeEvent e) {
        JSlider slider = (JSlider) e.getSource();
        float val = slider.getValue();
        model.setFahrenheit(val);
    }
}
