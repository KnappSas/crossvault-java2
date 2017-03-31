package GUI;

import IO.Output;

import static java.lang.Math.*;

import java.io.IOException;
 

public class GenerateTest {
        public static void main(String[] args) throws IOException {
                double[] x = Utility.increment(-5.0, 0.5, 6.0); // x = 0.0:0.1:1.0
                double[] y = Utility.increment(-5.0, 0.5, 6.0);// y = 0.0:0.05:1.0
                double[][] z1 = f1(x, y);

                Output.writeInputFile("C:\\Users\\Sascha\\Downloads\\Kreuzgewölbe\\Kreuzgewölbe\\Regression\\test\\input__comma.txt", x, y, z1);
        }
 
        public static double f1(double x, double y) {
                double z = 100 - x*x - y*y;
                return z;
        }
 
        public static double[][] f1(double[] x, double[] y) {
                double[][] z = new double[y.length][x.length];
                for (int i = 0; i < x.length; i++)
                        for (int j = 0; j < y.length; j++)
                        {
                            z[j][i] = f1(x[i], y[j]);
                        }
                return z;
        }
}