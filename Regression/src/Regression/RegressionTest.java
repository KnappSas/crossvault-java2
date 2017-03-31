package Regression;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by killer on 1/25/17.
 */
public class RegressionTest
{
    static Double[] x = {-1.0, 0.0, 1.0, 2.0, 3.0,5.0,7.0,9.0};
    static Double[] y = {-1.0, 3.0, 2.5, 5.0, 4.0, 2.0,5.0,4.0};
    @Test
    public void approximate() throws Exception
    {
        int n = x.length;
        ArrayList<Point2D> points = new ArrayList<Point2D>();
        for(int i = 0; i < n; i++)
        {
            points.add(new Point2D(x[i], y[i]));
        }

        Regression r = new Regression();
        Polynomial p = r.approximate(points, 4);

        Double f30 = p.function(3.0);

        //TODO: something to assert
    }

}