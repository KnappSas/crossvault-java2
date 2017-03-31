package Regression;
import java.util.ArrayList;

/**
 * Created by killer on 1/24/17.
 */
public class Regression
{

    public Polynomial approximate(ArrayList<Point2D> points, int degree)
    {
        if(points.size() <= 1)
        {
            //TODO: depends on the degree!!!
            System.out.println("A points count of" + degree +  "or less is unvalid!");
            return null;
        }
        int n = degree + 1;
        Matrix mat = new Matrix(n,n);
        Vector vec = new Vector(n);
        for(int row = 0; row < mat.anzahlZeilen(); row++)
        {
            for(int col = 0; col < mat.anzahlSpalten(); col++)
            {
                for(int i = 0; i < points.size(); i++)
                {
                    Double x = points.get(i).getX();
                    mat.x(row,col, mat.x(row,col) + Math.pow(x, row+col));
                }
            }

            for(int i = 0; i < points.size(); i++)
            {
                Double y = points.get(i).getY();
                vec.set(row, vec.get(row) + Math.pow(points.get(i).getX(), row)*y);
            }
        }

        Vector result = mat.solveDLG(mat,vec);
        return createPolynome(result, degree);
    }

    private Polynomial createPolynome(Vector result, int degree)
    {
        Polynomial p = new Polynomial(degree);
        for(int i = 0; i < degree+1; i++)
        {
            p.setCoefficient(i, result.get(i));
        }

        return p;
    }
}
