public class ConverterModel extends java.util.Observable{
    private float celsius;
    private ConverterView view;

    public ConverterModel(ConverterView ConvView){
        celsius = 0;
        view = ConvView;
    }

    public float getCelsius(){
        return celsius;
    }
    public float getFahrenheit(){
        return celsiusToFahrenheit(celsius);
    }

    public void setFahrenheit(float new_fahrenheit){
        celsius = fahrenheitToCelsius(new_fahrenheit);
        view.update(this, 0);

    }
    public void setCelsius(float new_celsius){
        celsius = new_celsius;
        view.update(this, 0);

    }

    private float celsiusToFahrenheit(float celsius){
        float result = (float) ((celsius*1.8)+32);
        return result;
    }
    private float fahrenheitToCelsius(float fahrenheit){
        float result = (float) ((fahrenheit-32)/1.8);
        return result;
    }
}
