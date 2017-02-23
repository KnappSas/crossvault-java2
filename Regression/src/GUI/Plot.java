package GUI;

import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.JFrame;

import org.math.plot.Plot3DPanel;

import Regression.Polynome;

public class Plot {

	 private static ArrayList<Polynome> funcX;
	 private static ArrayList<Polynome> funcY;
	 private static double genauigkeit = 0.01; // user input
	 private static double[] fx_x;
	 private static double[] fy_y;
	 
	 public static void gui(ArrayList<Polynome> funcX2, ArrayList<Polynome> funcY2, double genauigkeit2) {
		 
			funcX = funcX2;
			funcY = funcY2;
			genauigkeit = genauigkeit2;

	         // define your data
		 	double[] x = increment(0, 1.0, funcX.size()); // x = 0.0:0.1:1.0
		 	double[] y = increment(0, 1.0, funcY.size());// y = 0.0:0.05:1.0

	         double[][] zx = fx(y);
	         double[][] zy = fy(x);

	         // create your PlotPanel (you can use it as a JPanel) with a legend at SOUTH
	         Plot3DPanel plot = new Plot3DPanel("SOUTH");

	         // add grid plot to the PlotPanel
	         plot.addGridPlot("Funktionen nach x", x, y, zx);
	         plot.addGridPlot("Funktionen nach y", x, y, zy);

	         // put the PlotPanel in a JFrame like a JPanel
	         JFrame frame = new JFrame("a plot panel");
	         frame.setSize(600, 600);
	         frame.setContentPane(plot);
	         frame.setVisible(true);

		 }
	 
	 public static double fx(Double[] f, double y) {
		 double z = 0;
		 double exp;
		 
		 for(int i = 0; i < f.length; i++)
		 {
			 exp = f.length - i - 1;
			 z += f[i] * Math.pow(y, exp);
		 }
		 
		 return z;
	 }
	 
	 public static double fy(Double[] f, double x) {
		 double z = 0;
		 double exp;
		 
		 for(int i = 0; i < f.length; i++)
		 {
			 exp = f.length - i - 1;
			 z += f[i] * Math.pow(x, exp);
		 }
		 
		 return z;
	 }

	public static double[][] fx(double[] y) {
		double[][] z = new double[funcX.size()][funcX.size()];
		for (int i = 0; i < funcX.size(); i++) {

			for (int j = 0; j < funcX.size(); j++) {
				z[i][j] = funcX.get(i).function(y[j]);
			}
		}
		return z;
	}

	public static double[][] fy(double[] x) {
		double[][] z = new double[funcY.size()][funcY.size()];
		for (int i = 0; i < funcY.size(); i++) {
			for (int j = 0; j < funcY.size(); j++) {
				z[j][i] = funcY.get(i).function(x[j]);
			}
		}
		return z;
	}
	 
	public static double[] increment(double start, double step, double end) {
	      double range = end - start;
	      int steps = (int)(range / step);
	      double[] rv = new double[steps];
	      for (int i = 0; i<steps; i++) {
	         rv[i] = start + (step * i);
	      }
	      return rv;
	   }
}
