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
        for(ArrayList<Point3D> list : points)
        {
            if(list.size() > size)
                size = list.size();
        }

        return size;
    }
}
