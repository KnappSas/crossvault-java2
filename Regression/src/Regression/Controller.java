package Regression;


import IO.Input;
import IO.Output;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import GUI.Plot;
import GUI.TestGUI;

public class Controller
{
    static Double[] x = {0.0, 2.0, 4.0, 6.0, 8.0, 9.0, 1.0, 2.0, 4.0, 6.0, 8.0, 9.0, 2.0, 4.0, 6.0, 8.0, 9.0, 3.0, 4.0, 6.0, 8.0, 9.0, 4.0, 6.0, 8.0, 9.0, 5.0, 6.0, 8.0, 6.0, 8.0, 7.0};
    static Double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 3.0, 3.0, 4.0, 4.0, 4.0, 4.0, 5.0, 5.0, 5.0, 6.0, 6.0, 7.0};
    static Double[] z = {150.0, 146.0, 134.0, 114.0, 86.0, 69.0, 148.0, 145.0, 133.0, 113.0, 85.0, 68.0, 142.0, 130.0, 110.0, 82.0, 65.0, 132.0, 125.0, 105.0, 77.0, 60.0, 118.0, 98.0, 70.0, 53.0, 100.0, 89.0, 61.0, 78.0, 50.0, 52.0};
    private static double genauigkeit = 0.01;
    
    public static void main(String[] args) throws IOException
    {

        TestGUI test = new TestGUI();
        test.gui();

        
    }
    
    public static void update(File file, double genauigkeit2, TestGUI testgui) {
    	genauigkeit = genauigkeit2;
    	
    	ArrayList<ArrayList<Point3D>> p = null;
        try
        {
            p = Input.readFromFile(file.getAbsolutePath());
        }
        catch (IOException e)
        {

        }

        PolynomePool pool = new PolynomePool();
        pool.approximate(p);
        
        testgui.update(pool.getXPolynomes(), pool.getYPolynomes(), genauigkeit);
    }
}
