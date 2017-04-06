package Regression;

import java.util.ArrayList;

/**
 * Created by killer on 1/25/17.
 */
public interface IApproximation
{
	PointMatrix approximate(ArrayList<ArrayList<Point3D>> points);
}
