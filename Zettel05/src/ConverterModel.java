public class ConverterModel extends java.util.Observable{
    private float celsius;

    public ConverterModel(){
        celsius = 0;

    }

    public float getCelsius(){
        return celsius;
    }
    public float getFahrenheit(){
        return celsiusToFahrenheit(celsius);
    }

    public void setFahrenheit(float new_fahrenheit){
        celsius = fahrenheitToCelsius(new_fahrenheit);
        setChanged();
        notifyObservers(true);

    }
    public void setCelsius(float new_celsius){
        celsius = new_celsius;
        setChanged();
        notifyObservers(false);
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
