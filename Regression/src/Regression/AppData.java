package Regression;

import java.util.ArrayList;

/**
 * Created by Sascha on 15.03.2017.
 */
public class AppData
{
    PointMatrix pm;
    ArrayList<ArrayList<Point3D>> points;
    ArrayList<Polynomial> xPolynomials;
    ArrayList<Polynomial> yPolynomials;
    public Double biggestY = 0.0;
    public Double biggestX = 0.0;

}
