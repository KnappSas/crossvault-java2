package Regression;

import IO.Input;
import GUI.TestGUI;
import Oberflaeche.Integral;

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

    static void removeAnomaly(PointMatrix points)
    {
        double delta = 0.5;
        ArrayList<Point3D> tmpPoints = new ArrayList<Point3D>();
        int j = 1;
        double sum = 0;
        for(int i = 0; i < points.stepsInXDirection(); i++)
        {

            Point3D currPoint = points.getPoint(i, 0);
            tmpPoints.add(currPoint);
            if(currPoint.getX() <= i*delta)
            {
                tmpPoints.add(currPoint);
                sum += currPoint.getX();
                j++;
            }
            else
            {
                double mid = sum / j;
                for(Point3D p : tmpPoints)
                {
                    p.setX(mid);
                }
                tmpPoints.clear();
                j = 0;
                sum = 0;
            }
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

        TestGUI test = new TestGUI();
        test.gui();

    }
    
    public static void update(File file, double genauigkeit, TestGUI testgui) {
        ArrayList<ArrayList<Point3D>> points;
        ArrayList<Point3D> src = null;
        long start = System.currentTimeMillis();

        try
        {
            src = Input.unOrderedReadFromFile(file.getAbsolutePath().replace("\\", "/"));
        }
        catch (IOException e)
        {

        }

        points = orderPoints(src);

        AppData data = new AppData();
        data.points = points;
        data.biggestY = ListHelper.findBiggestY(data.points);
        data.biggestX = ListHelper.findBiggestX(data.points);

        PointMatrix pointMatrix = new PointMatrix();
        pointMatrix.setPointList(points);
        data.pm = pointMatrix;

        removeAnomaly(data.pm);

        PolynomialPool pool = new PolynomialPool(data);
        pool.approximate(points);
        Integral i = new Integral();
        Double flaeche = i.calcSurfaceArea(data.xPolynomials, data.yPolynomials);
        testgui.update(pointMatrix ,genauigkeit, flaeche);

        long end = System.currentTimeMillis();
        long time = end - start;
//        System.out.println("Time spent for calculation: " + time);

    }
}
