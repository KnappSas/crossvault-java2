package Regression;

import IO.Input;
import GUI.TestGUI;
import Oberfläche.Integral;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Controller
{
    static ArrayList<ArrayList<Point3D>> orderPoints(ArrayList<Point3D> points)
    {
        ArrayList<ArrayList<Point3D>> result = new ArrayList<>();
        for(Point3D p : points)
        {
            boolean alreadyInList = false;
            for(ArrayList<Point3D> pList : result)
            {
                if((double)pList.get(0).getX() == p.getX()) //TODO: why is this not working without double cast
                {
                    pList.add(p);
                    alreadyInList = true;
                }
            }

            if(alreadyInList)
                continue;

            ArrayList<Point3D> subList = new ArrayList<>();
            subList.add(p);
            Collections.sort(subList, (a,b)-> a.getY() < b.getY() ? -1 : a.getY() == b.getY() ? 0 : 1);
            result.add(subList);
        }

        Collections.sort(result, (a,b)-> a.get(0).getX() < b.get(0).getX() ? -1 : a.get(0).getX() == b.get(0).getX() ? 0 : 1);
        return result;
    }

    static void removeDistortionInX(ArrayList<ArrayList<Point3D>> points)
    {
        double distortion;
        double prevXValue;
        double currentXValue;
        for(int i = 1; i < points.size(); i++)
        {
            prevXValue = points.get(i-1).get(0).getX();
            currentXValue = points.get(i).get(0).getX();
            distortion = Math.abs(currentXValue-prevXValue);
            if(distortion > 0.5)
                continue;

            for(Point3D p : points.get(i-1))
            {
                p.setX(currentXValue);
                points.get(i).add(p);
            }

            points.remove(i-1);
        }
    }
/*
    static void removeDistortionInXWithRegression(AppData data)
    {
        Double delta = 1/data.biggestX;

        for(int i = 0; i < data.biggestX.intValue(); i++)
        {
            Double currX = delta * i;
            ArrayList<Point2D> pointsToInterpolate = new ArrayList<>();
            for(int k = 0; k < data.points.size(); i++)
            {
                Point3D currPoint = data.points.get(i).get(0);
                if(currPoint.getX() <= currX)
                {
                    for(Point3D point3D : data.points.get(i))
                    {
                        pointsToInterpolate.add(new Point2D(point3D.getX(), point3D.getY());
                    }
                }
            }
        }
    }
*/
    static void removeDistortionInY(ArrayList<ArrayList<Point3D>> points)
    {
        double distortion;
        double prevYValue;
        double currentYValue;
        for(int i = 0; i < ListHelper.findBiggestList(points); i++)
        {
            for(int j = 1; j < points.size(); j++)
            {
                if(i >= points.get(j-1).size() || i >= points.get(j).size())
                    continue;
                prevYValue = points.get(j-1).get(i).getY();
                currentYValue = points.get(j).get(i).getY();
                distortion = Math.abs(currentYValue-prevYValue);
                if(distortion > 0.5 && distortion <= 0)
                    continue;

                if(prevYValue > currentYValue)
                    points.get(j-1).get(i).setY(currentYValue);
                else
                    points.get(j).get(i).setY(prevYValue);
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        long start = System.currentTimeMillis();
        TestGUI test = new TestGUI();
        test.gui();
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("Time spent for calculation: " + time);
    }
    
    public static void update(File file, double genauigkeit, TestGUI testgui) {
        ArrayList<ArrayList<Point3D>> points;
        ArrayList<Point3D> src = null;

        try
        {
            src = Input.unOrderedReadFromFile("/Users/SKnapp/Documents/Java-Programming/crossvault-java2/Regression/test/crossvault_points_original.txt");
        }
        catch (IOException e)
        {

        }

        points = orderPoints(src);
//        removeDistortionInX(points);
//        removeDistortionInY(points);
        AppData data = new AppData();
        data.points = points;
        data.biggestY = ListHelper.findBiggestY(data.points);
        data.biggestX = ListHelper.findBiggestX(data.points);
        PolynomialPool pool = new PolynomialPool(data);
        pool.approximate(points);
        Integral i = new Integral();
        Double fläche = i.calcSurfaceArea(data.xPolynomials, data.yPolynomials);
        testgui.update(pool.getXPolynomes(), pool.getYPolynomes(),genauigkeit, data);
    }
}
