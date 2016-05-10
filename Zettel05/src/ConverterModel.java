public class ConverterModel{
    private int celsius;

    public ConverterModel(){
        celsius = 0;
    }

    public int getCelsius(){
        return celsius;
    }
    public int getFahrenheit(){
        return celsiusToFahrenheit(celsius);
    }

    public void setFahrenheit(int new_fahrenheit){
        celsius = fahrenheitToCelsius(new_fahrenheit);
    }
    public void setCelsius(int new_celsius){
        celsius = new_celsius;
    }

    private int celsiusToFahrenheit(int fahrenheit){
        return (int) ((fahrenheit-32)/1.8);
    }
    private int fahrenheitToCelsius(int celsius){
        return (int) ((celsius*1.8)+32);
    }
}
