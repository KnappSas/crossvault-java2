package Oberflaeche;

import Regression.Point3D;
import Regression.Polynomial;
import Regression.Vector;

import java.util.ArrayList;

/**
 * Created by SKnapp on 06/03/17.
 */
public class Integral {

    public boolean isInRange(int size, int i)
    {
        return i < size-1;
    }

    public double calcSurfaceArea(ArrayList<ArrayList<Point3D>> points)
    {
        Double result = 0.0;
        for(int i = 0; i < points.size()-1; i++)
        {
            for(int j = 0; j < points.get(i).size()-1; j++)
            {
                if(!isInRange(points.size(), i))
                    continue;
                if(!isInRange(points.get(i).size(),j) || !isInRange(points.get(i+1).size(),j))
                    continue;
                Point3D px1 = points.get(i).get(j);
                Point3D px2 = points.get(i+1).get(j);
                Point3D py1 = px1;
                Point3D py2 = points.get(i).get(j+1);

                Double xDelta = px2.getX()-px1.getX();
                Double yDelta = py2.getY()-py1.getY();

                Vector rx = new Vector(px2.getX()-px1.getX(), px2.getY()-px1.getY(), px2.getZ()-px1.getZ());
                Vector ry = new Vector(py2.getX()-py1.getX(), py2.getY()-py1.getY(), py2.getZ()-py1.getZ());

                Vector crossProduct = new Vector(rx.y() * ry.z()-rx.z()*ry.y(),rx.z()*ry.x()-rx.x()*ry.z(), rx.x()*ry.y()-rx.y()*ry.x());
                Double sum = crossProduct.sum(); //*xDelta*yDelta;
                result += sum;
            }
        }
        System.out.println("Oberfläche: " + result + " FE");
        return result;
    }

    public double calcSurfaceArea(ArrayList<Polynomial> funcX, ArrayList<Polynomial> funcY)
    {
        Double result = 0.0;
        for(int i = 0; i < funcX.size()-1; i++)
        {
            for(int j = 0; j < funcY.size()-1; j++)
            {
                Polynomial currFuncX = funcX.get(i);
                Polynomial nextFuncX = funcX.get(i+1);
                Polynomial currFuncY = funcY.get(j);
                Polynomial nextFuncY = funcY.get(j+1);
                Point3D px1 = new Point3D(currFuncX.getRoomCoordinate(), currFuncY.getRoomCoordinate(), currFuncX.derivation(currFuncY.getRoomCoordinate()));
                Point3D py1 = new Point3D(currFuncX.getRoomCoordinate(), currFuncY.getRoomCoordinate(), currFuncY.derivation(currFuncX.getRoomCoordinate()));
                Point3D px2 = new Point3D(nextFuncX.getRoomCoordinate(), currFuncY.getRoomCoordinate(), nextFuncX.derivation(currFuncY.getRoomCoordinate()));
                Point3D py2 = new Point3D(currFuncX.getRoomCoordinate(), nextFuncY.getRoomCoordinate(), nextFuncY.derivation(currFuncX.getRoomCoordinate()));

                Double xDelta = px2.getX()-px1.getX();
                Double yDelta = py2.getY()-py1.getY();

                Vector rx = new Vector(px2.getX()-px1.getX(), px2.getY()-px1.getY(), px2.getZ()-px1.getZ());
                Vector ry = new Vector(py2.getX()-py1.getX(), py2.getY()-py1.getY(), py2.getZ()-py1.getZ());

                Vector crossProduct = new Vector(rx.y() * ry.z()-rx.z()*ry.y(),rx.z()*ry.x()-rx.x()*ry.z(), rx.x()*ry.y()-rx.y()*ry.x());
                Double sum = crossProduct.sum()*xDelta*yDelta;
                result += sum;
            }
        }
        System.out.println("Oberfläche: " + result + " FE");
        return result;
    }
}
