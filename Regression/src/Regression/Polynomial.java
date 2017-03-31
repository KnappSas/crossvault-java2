package Regression;
/**
 * Created by killer on 12/9/16.
 */
public class Polynomial
{
    private Double[] coefficients;
    private double roomCoordinate = 0.0;


    public Polynomial(int degree)
    {
        coefficients = new Double[degree + 1];
    }

    public Double function(Double x)
    {
        Double y = 0.0;
        for(int i = 0; i < coefficients.length;i++)
        {
            y += coefficients[i]*Math.pow(x, i);
        }

        return y;
    }

    public Double derivation(Double x)
    {
        Double y = 0.0;
        for(int i = 1; i < coefficients.length;i++)
        {
            y += i*coefficients[i]*Math.pow(x, i-1);
        }

        return y;
    }

    public void setRoomCoordinate(double roomCoordinate)
    {
        this.roomCoordinate = roomCoordinate;
    }

    public double getRoomCoordinate() {
        return roomCoordinate;
    }

    public void setCoefficient(int idx, Double value)
    {
        coefficients[idx] = value;
    }

    public Double[] getCoefficients(){ return coefficients; }
}
