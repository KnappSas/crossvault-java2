package Regression;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by SKnapp on 06/03/17.
 */
public class PolynomialTest {
    @Test
    public void function() throws Exception {
        Polynomial p = new Polynomial(3);
        for(int i = 0; i < 4; i++)
        {
            p.setCoefficient(i, 1.0);
        }

        assertTrue(p.function(3.0) == 40.0);
    }

    @Test
    public void derivation() throws Exception {
        Polynomial p = new Polynomial(3);
        for(int i = 0; i < 4; i++)
        {
            p.setCoefficient(i, 1.0);
        }

        Double value = p.derivation(3.0);
        assertTrue(value == 34.0);
    }

}