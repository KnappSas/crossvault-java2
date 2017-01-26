package Regression;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by killer on 1/25/17.
 */
public class PolynomePool implements IApproximation
{
    private static final int kMinPointCount = 2;
    private ArrayList<Polynome> xPolynomes;
    private ArrayList<Polynome> yPolynomes;
    private ArrayList<ArrayList<Point3D>> points;

    public PolynomePool()
    {
        xPolynomes = new ArrayList<Polynome>();
        yPolynomes = new ArrayList<Polynome>();
    }

    private ArrayList<Point2D> extractPointsInLineXDirectrion(ArrayList<Point3D> fx)
    {
        ArrayList<Point2D> tmpPoints = new ArrayList<Point2D>();
        for(Point3D p : fx)
        {
            tmpPoints.add(new Point2D(p.getY(), p.getZ()));
        }

        return tmpPoints;
    }

    private ArrayList<Point2D> extractPointsInLineYDirectrion(int j)
    {
        ArrayList<Point2D> tmpPoints = new ArrayList<Point2D>();
        for(int i = 0; i < points.size(); i++)
        {
            if(j >= points.get(i).size())
                continue;
            Point3D currPoint = points.get(i).get(j);
            tmpPoints.add(new Point2D(currPoint.getX(), currPoint.getZ()));
        }

        return tmpPoints;
    }


    private int findBiggestList(ArrayList<ArrayList<Point3D>> points)
    {
        int size = 0;
        for(ArrayList<Point3D> list : points)
        {
            if(list.size() > size)
                size = list.size();
        }

        return size;
    }

    @Override
    public void approximate(ArrayList<ArrayList<Point3D>> points)
    {
        this.points = points;
        for(int i = 0; i < points.size(); i++)
        {
            Regression r = new Regression();
            ArrayList<Point2D> p = extractPointsInLineXDirectrion(points.get(i));
            if(p.size() <= 2)
                continue;
            xPolynomes.add(r.approximate(p, 2));
        }

        for(int j = 0; j < findBiggestList(points); j++)
        {
            Regression r = new Regression();
            ArrayList<Point2D> p = extractPointsInLineYDirectrion(j);
            if(p.size() <= 2)
                continue;
            yPolynomes.add(r.approximate(p, kMinPointCount));
        }
    }

    public ArrayList<Polynome> getXPolynomes()
    {
        return xPolynomes;
    }

    public ArrayList<Polynome> getYPolynomes()
    {
        return yPolynomes;
    }
}
