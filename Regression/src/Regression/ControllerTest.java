package Regression;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
/**
 * Created by Sascha on 22.02.2017.
 */
public class ControllerTest {

 /*   @Test
    public void orderPoints() throws Exception {
        Point3D[] src = {new Point3D(-4.5, -3d, 4d),new Point3D(-5d,-5d,5d), new Point3D(-4.5, -3d, 3d),new Point3D(-5d,-5d,5d)};
        ArrayList<Point3D> points = new ArrayList<>();
        Collections.addAll(points, src);

        ArrayList<ArrayList<Point3D>> orderedPoints = Controller.orderPoints(points);
        boolean rightOrder = false;
        for(int i = 0; i < orderedPoints.size();i++)
        {
            for(int j = 0; j < orderedPoints.get(i).size(); j++)
                rightOrder = orderedPoints.get(i).get(0).getX() == src[i].getX() ? true : false;
        }

        assertTrue(rightOrder);
    }
*/
    @Test
    public void removeDistortion() throws Exception {
        /*Point3D[] src = {new Point3D(0d,-5d,5d), new Point3D(0.5, -3d, 4d), new Point3D(0.5, -3d, 3d)};
        ArrayList<Point3D> points = new ArrayList<>();
        Collections.addAll(points, src);

        ArrayList<ArrayList<Point3D>> orderedPoints = Controller.orderPoints(points);

        Controller.removeAnomaly(orderedPoints);*/
    }

}