package Regression;

import IO.Input;
import GUI.TestGUI;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Controller
{
    static Double[] x = {0.0, 2.0, 4.0, 6.0, 8.0, 9.0, 1.0, 2.0, 4.0, 6.0, 8.0, 9.0, 2.0, 4.0, 6.0, 8.0, 9.0, 3.0, 4.0, 6.0, 8.0, 9.0, 4.0, 6.0, 8.0, 9.0, 5.0, 6.0, 8.0, 6.0, 8.0, 7.0};
    static Double[] y = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 2.0, 2.0, 2.0, 2.0, 3.0, 3.0, 3.0, 3.0, 3.0, 4.0, 4.0, 4.0, 4.0, 5.0, 5.0, 5.0, 6.0, 6.0, 7.0};
    static Double[] z = {150.0, 146.0, 134.0, 114.0, 86.0, 69.0, 148.0, 145.0, 133.0, 113.0, 85.0, 68.0, 142.0, 130.0, 110.0, 82.0, 65.0, 132.0, 125.0, 105.0, 77.0, 60.0, 118.0, 98.0, 70.0, 53.0, 100.0, 89.0, 61.0, 78.0, 50.0, 52.0};

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
        ArrayList<ArrayList<Point3D>> p;
        ArrayList<Point3D> src = null;

        try
        {
            src = Input.unOrderedReadFromFile("test\\crossvault_points.txt");
        }
        catch (IOException e)
        {

        }

        p = orderPoints(src);
        removeDistortionInX(p);
        removeDistortionInY(p);
        PolynomePool pool = new PolynomePool();
        pool.approximate(p);
        //Plot.gui(pool.getXPolynomes(), pool.getYPolynomes(),genauigkeit);
        testgui.update(pool.getXPolynomes(), pool.getYPolynomes(),genauigkeit);
    }
}
