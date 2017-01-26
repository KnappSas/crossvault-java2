package Regression;
/**
 * Created by killer on 12/9/16.
 */
public class Polynome
{
    private Double[] coefficients;

    public Polynome(int degree)
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

    public void setCoefficient(int idx, Double value)
    {
        coefficients[idx] = value;
    }

    public Double[] getCoefficients(){ return coefficients; }
}
