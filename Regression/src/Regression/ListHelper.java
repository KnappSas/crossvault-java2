package Regression;

import java.util.ArrayList;

/**
 * Created by Sascha on 22.02.2017.
 */
public class ListHelper
{
    public static int findBiggestList(ArrayList<ArrayList<Point3D>> points)
    {
        int size = 0;
        int i;
        for(i = 0; i < points.size(); i++)
        {
            if(points.get(i).size() > size)
                size = points.get(i).size();
        }

        return i;
    }

    public static  Double findBiggestX(ArrayList<ArrayList<Point3D>> points)
    {
        Double x = 0.0;
        for(ArrayList<Point3D> list : points)
        {
            Double currX = list.get(0).getX();
            if(x < currX)
                x = currX;
        }
        return x;
    }

    public static  Double findBiggestY(ArrayList<ArrayList<Point3D>> points)
    {
        Double y = 0.0;
        for(ArrayList<Point3D> list : points)
        {
            for(Point3D p : list)
            {
                if(p.getY() > y)
                    y = p.getY();
            }
        }
        return y;
    }
}
