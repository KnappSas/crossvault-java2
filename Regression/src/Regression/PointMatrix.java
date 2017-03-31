package Regression;

import java.util.ArrayList;

/**
 * Created by SKnapp on 24/03/17.
 */
public class PointMatrix
{
    ArrayList<ArrayList<Point3D>> p;

    public void setPointList(ArrayList<ArrayList<Point3D>> p)
    {
        this.p = p;
    }

    public Point3D getPoint(int x, int y)
    {
        return p.get(x).get(y);
    }

    private boolean compare(double a, double b)
    {
        return Math.abs(a-b) <= 0.00001;
    }

    private void putIntoSubList(int i, Point3D point)
    {
        if(point.getY() < getPoint(i, 0).getY())
        {
            p.get(i).add(0, point);
            return;
        }
        else if(point.getY() > getPoint(i, p.get(i).size()-1).getY())
        {
            p.get(i).add(point);
            return;
        }
        else
        {
            for(int j = 1; j < p.get(i).size(); j++)
            {
                if(compare(point.getY(), getPoint(i, j).getY()) || compare(point.getY(), getPoint(i, j-1).getY()))
                    return;
                if(point.getY() < getPoint(i,j).getY() && point.getY() > getPoint(i, j-1).getY())
                {
                    p.get(i).add(j, point);
                    break;
                }
            }
        }
    }

    private void putBetweenValues(int i, Point3D point)
    {
        p.add(i, new ArrayList<>());
        p.get(i).add(point);
    }

    private boolean isBetweenValues(int i, double x)
    {
        return x < p.get(i).get(0).getX() && x > p.get(i-1).get(0).getX();
    }

    private boolean isEqualToValue(int i, double x)
    {
        return x == p.get(i).get(0).getX();
    }

    public void addPoint(double x, double y, double z)
    {
        for(int i = 1; i < p.size(); i++)
        {
            if(isBetweenValues(i,x))
            {
                putBetweenValues(i, new Point3D(x,y,z));
                break;
            }
            else if(isEqualToValue(i-1, x))
            {
                putIntoSubList(i-1, new Point3D(x,y,z));
                break;
            }
            else if(isEqualToValue(i, x))
            {
                putIntoSubList(i, new Point3D(x,y,z));
                break;
            }
        }
    }

    public int stepsInXDirection()
    {
        return p.size();
    }

    public int stepsInYDirection()
    {
        return p.get(0).size(); //TODO: es muss sichergestellt werden, dass alle Unterarrays(y-Arrays) die gleiche Größe haben.
    }

    public int stepsInYDirection(int i)
    {
        return p.get(i).size();
    }
}
