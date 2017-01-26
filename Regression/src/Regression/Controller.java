package Regression;


import GUI.Plot;
import IO.Input;

import java.io.IOException;
import java.util.ArrayList;

public class Controller
{
    static Double[] x = {0.0, 2.0, 4.0, 6.0, 8.0, 9.0, 1.0, 2.0, 4.0, 6.0, 8.0, 9.0, 2.0, 4.0, 6.0, 8.0, 9.0, 3.0, 4.0, 6.0, 8.0, 9.0, 4.0, 6.0, 8.0, 9.0, 5.0, 6.0, 8.0, 6.0, 8.0, 7.0};
    static Double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 3.0, 3.0, 4.0, 4.0, 4.0, 4.0, 5.0, 5.0, 5.0, 6.0, 6.0, 7.0};
    static Double[] z = {150.0, 146.0, 134.0, 114.0, 86.0, 69.0, 148.0, 145.0, 133.0, 113.0, 85.0, 68.0, 142.0, 130.0, 110.0, 82.0, 65.0, 132.0, 125.0, 105.0, 77.0, 60.0, 118.0, 98.0, 70.0, 53.0, 100.0, 89.0, 61.0, 78.0, 50.0, 52.0};

//    static Double[] x = {-1d, 0d, 1d, 2d, 3d, 5d, 7d, 9d};
//    static Double[] y = {-1d, 3d, 2.5, 5d, 4d, 2d, 5d, 4d};

    public static void main(String[] args)
    {
        ArrayList<ArrayList<Point3D>> p = null;
        try
        {
            p = Input.readFromFile("/home/killer/Documents/Kreuzgewölbe-Projekt/Regression/test/input.txt");
        }
        catch (IOException e)
        {

        }

        PolynomePool pool = new PolynomePool();
        pool.approximate(p);
        Plot.gui(pool.getXPolynomes(), pool.getYPolynomes(), 1);

//        ArrayList<Point2D> p = new ArrayList<Point2D>();
//        for(int i = 0; i < x.length; i++)
//        {
//            p.add(new Point2D(x[i], y[i]));
//        }
//
//        Regression r = new Regression();
//        r.approximate(p, 8);
    }
}
