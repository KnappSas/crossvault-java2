package GUI;

/**
 * Created by SKnapp on 31/03/17.
 */
public class Utility
{
    public static double[] increment(double start, double step, double end) {
        double range = end - start;
        int steps = (int)(range / step);
        double[] rv = new double[steps];
        for (int i = 0; i<steps; i++) {
            rv[i] = start + (step * i);
        }
        return rv;
    }
}
