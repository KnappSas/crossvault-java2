package Regression;
public class Vector
{
    private double x[] = new double[3];
    private int size = 3;

    public Vector()
    {
        x[0] = 0.0;
        x[1] = 0.0;
        x[2] = 0.0;
    }

    public Vector(int size)
    {
        x = new double[size];
        this.size = size;
    }

    public Vector(Vector v)
    {
        x = new double[v.size];
        this.size = v.size;
        this.x[0] = v.x[0];
        this.x[1] = v.x[1];
        this.x[2] = v.x[2];
    }


    public Vector(double x, double y, double z)
    {
        this.x[0] = x;
        this.x[1] = y;
        this.x[2] = z;
    }

    public double x()
    {
        return x[0];
    }

    public void x(double val)
    {
        x[0] = val;
    }

    public double y()
    {
        return x[1];
    }

    public void y(double val)
    {
        x[1] = val;
    }


    public double z()
    {
        return x[2];
    }

    public void z(double val)
    {
        x[2] = val;
    }


    public Double sum()
    {
        Double sum = 0.0;
        for(Double xValue : x)
        {
            sum += xValue*xValue;
        }

        return Math.sqrt(sum);
    }

    public double get(int i)
    {
        return x[i];
    }

    public void set(int i, double val)
    {
        x[i] = val;
    }

    public void print()
    {
        if (size == 3)
            System.out.print("vec[" + x[0] + " , " + x[1] + " , " + x[2] + " ] ");
        else
        {
            System.out.print("[");
            for (int i = 0; i < size; i++)
            {
                System.out.print("" + x[i] + " ");
            }
            System.out.println("]");
        }
    }


    public int getSize()
    {
        return size;
    }

}

