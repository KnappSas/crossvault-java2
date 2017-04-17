package Regression;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by killer on 1/25/17.
 */
public class PolynomialPool implements IApproximation
{
    private static double kDelta = 0.1;
    private static final int kDegree = 2;

    AppData data = null;

    public PolynomialPool(AppData data)
    {
        this.data = data;
        data.xPolynomials = new ArrayList<Polynomial>();
        data.yPolynomials = new ArrayList<Polynomial>();
    }

    public PolynomialPool(AppData data, double delta)
    {
    	this.kDelta = delta;
        this.data = data;
        data.xPolynomials = new ArrayList<Polynomial>();
        data.yPolynomials = new ArrayList<Polynomial>();
    }

    private ArrayList<Point2D> extractPointsInLine_XDirectrion(PointMatrix pm, int x)
    {
        ArrayList<Point2D> tmpPoints = new ArrayList<>();
        for(int i = 0; i < pm.stepsInYDirection(); i++)
        {
            Point3D p = pm.getPoint(x, i);
            tmpPoints.add(new Point2D(p.getY(), p.getZ()));
        }

        return tmpPoints;
    }

    private ArrayList<Point2D> extractPointsInLine_YDirectrion(int k)
    {
        ArrayList<Point2D> tmpPoints = new ArrayList<>();
        for(int i = 0; i < data.pm.stepsInXDirection(); i++)
        {
            Point3D currPoint = data.points.get(i).get(k);
            tmpPoints.add(new Point2D(currPoint.getX(), currPoint.getZ()));
        }

        return tmpPoints;
    }

    private void approximateYPolynomials(PointMatrix points) //wenn noch keine StÃ¼tzpunkte vorhanden sind
    {
        for(int j = 0; j < points.stepsInYDirection(); j++)
        {
            Regression r = new Regression();
            ArrayList<Point2D> p = extractPointsInLine_YDirectrion(j);
            Polynomial polynomial = r.approximate(p, kDegree);
            polynomial.setRoomCoordinate(data.pm.getPoint(0, j).getY());
            data.yPolynomials.add(polynomial);
        }
    }

    private void approximateXPolynomials(PointMatrix points) //wenn noch keine StÃ¼tzpunkte vorhanden sind
    {
        for(int i = 0; i < points.stepsInXDirection(); i++)
        {
            Regression r = new Regression();
            ArrayList<Point2D> p = extractPointsInLine_XDirectrion(points, i);
            Polynomial polynomial = r.approximate(p, kDegree);
            polynomial.setRoomCoordinate(data.pm.getPoint(i, 0).getX());
            data.xPolynomials.add(polynomial);
        }
    }

    public void approximate(PointMatrix p)
    {
        data.pm = p;
        approximateYPolynomials(p);
        approximateXPolynomials(p);
        //approximateMoreX();
        //approximateMoreY();
    }

    public Polynomial approxiamtePolynomialInX(Double x)
    {
        ArrayList<Point2D> pointsToInterpolate = new ArrayList<>();
        for(int k = 0; k < data.yPolynomials.size(); k++) //alle stützpunkte durchgehen
        {
            Polynomial polynomial = data.yPolynomials.get(k);
            Double y = polynomial.getRoomCoordinate();
            Double z = data.yPolynomials.get(k).function(x);
            Point2D currPoint = new Point2D(y,z);
            pointsToInterpolate.add(currPoint);
        }

        Regression regression = new Regression();
        Polynomial polynomial = regression.approximate(pointsToInterpolate, 2);
        polynomial.setRoomCoordinate(x);
        data.xPolynomials.add(polynomial);
        return polynomial;
    }

    public void approximateMoreX()
    {
        Double delta = kDelta;
        int i = 0;
        Double x = 0.0;
        int xi = 0;
        while(x < data.biggestX)
        {
            x = xi * delta;
            if(Math.abs(x-data.pm.getPoint(i, 0).getX()) <= 0.0001)
            {
                xi++;
                continue;
            }

            if(x >= data.pm.getPoint(i, 0).getX())
                i++;

            Polynomial p = approxiamtePolynomialInX(x);
            int max = (int)(data.biggestY/delta);
            for(int k = 0; k <= max; k++)
            {
                double y = k * delta;
                data.pm.addPoint(x, y, p.function(y));
            }
            xi++;
        }

        Collections.sort(data.xPolynomials, (a, b)-> a.getRoomCoordinate() < b.getRoomCoordinate() ? -1 : a.getRoomCoordinate() == b.getRoomCoordinate() ? 0 : 1);
    }

    public Polynomial approximatePolynomialInY (Double y)
    {
        ArrayList<Point2D> pointsToInterpolate = new ArrayList<>();
        for(int k = 0; k < data.xPolynomials.size(); k++) //alle stützpunkte durchgehen
        {
            Polynomial polynomial = data.xPolynomials.get(k);
            Double x = polynomial.getRoomCoordinate();
            Double z = data.xPolynomials.get(k).function(y);
            Point2D currPoint = new Point2D(x,z);
            pointsToInterpolate.add(currPoint);
        }

        Regression regression = new Regression();
        Polynomial polynomial = regression.approximate(pointsToInterpolate, 2);
        polynomial.setRoomCoordinate(y);
        data.yPolynomials.add(polynomial);
        return polynomial;
    }

    public void approximateMoreY() //TODO: UNITTESTS
    {
        Double delta = kDelta;
        int i = 0;
        Double y = 0.0;
        int yi = 0;
        while(y < data.biggestY)
        {
            y = yi * delta;
            if(Math.abs(y-data.pm.getPoint(0, i).getY()) <= 0.0001)
            {
                yi++;
                continue;
            }
            if(y >= data.pm.getPoint(0, i).getY())
                i++;
            Polynomial p = approximatePolynomialInY(y);
            int max = (int)(data.biggestX/delta);
            for(int xk = 0; xk <= max; xk++)
            {
                double x = xk*delta;
                data.pm.addPoint(x, y, p.function(x));
            }

            yi++;
        }

        Collections.sort(data.yPolynomials, (a, b)-> a.getRoomCoordinate() < b.getRoomCoordinate() ? -1 : a.getRoomCoordinate() == b.getRoomCoordinate() ? 0 : 1);
    }

    public ArrayList<Polynomial> getXPolynomes()
    {
        return data.xPolynomials;
    }

    public ArrayList<Polynomial> getYPolynomes()
    {
        return data.yPolynomials;
    }

    @Override
    public PointMatrix approximate(ArrayList<ArrayList<Point3D>> points) {
        PointMatrix pointMatrix = new PointMatrix();
        pointMatrix.setPointList(points);
        approximate(pointMatrix);
        return pointMatrix;
    }
}
