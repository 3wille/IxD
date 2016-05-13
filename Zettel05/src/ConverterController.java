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
}
