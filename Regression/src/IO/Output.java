package IO;

import java.io.*;
import java.util.ArrayList;

import Regression.Polynomial;

public class Output {

	public static void writeToFile(String file, double genauigkeit, ArrayList<Polynomial> funcX, ArrayList<Polynomial> funcY) throws IOException
	{
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		int length;

		bw.write("Genauigkeit: ");
		bw.write(String.valueOf(genauigkeit));
		bw.newLine();
		bw.newLine();
		bw.write("Funktionen in x-Richtung: ");
		bw.newLine();

		length = funcX.size();
		int size;
		for(int i = 0; i < length; i++)
		{
			size = funcX.get(i).getCoefficients().length;
			for(int j = 0; j < size; j++)
			{
				bw.write(String.valueOf(funcX.get(i).getCoefficients()[j]));
				if(i != 0)
				{
					bw.write("+ x^" + i);
				}
			}
			bw.newLine();
		}

		bw.newLine();
		bw.write("Funktionen in y-Richtung: ");
		bw.newLine();

		length = funcY.size();
		for(int i = 0; i < length; i++)
		{
			size = funcY.get(i).getCoefficients().length;
			for(int j = 0; j < size; j++)
			{
				bw.write(String.valueOf(funcY.get(i).getCoefficients()[j]));
				if(j != 0)
				{
					bw.write("+ x^" + j);
				}
			}
			bw.newLine();
		}
		bw.close();

		System.out.println("File '" + file + "' wurde erfolgreich gespeichert.");
	}

	public static void writeInputFile(String file, double[] _x, double[] _y, double[][] z) throws IOException
	{
		double[] x = _x.clone();
		double[] y = _y.clone();
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		for(int i = 0; i < x.length; i++)
		{
			for(int j = 0; j < y.length; j++)
			{
				if(i == 0 && j % 3 == 0)
					bw.write(x[i] + " " + (y[j] + -0.5) + " " + z[i][j] + " ");
				else
				if(i == 3 && j % 3 == 0)
					bw.write(x[i] + " " + (y[j]  + 0.5) + " " + z[i][j] + " ");
				else
				{
					System.out.print(x[i] + " " + y[j] + " " + z[i][j] + " ");
					bw.write(x[i] + " " + y[j] + " " + z[i][j] + " ");
				}

			}
			System.out.println();
			bw.newLine();
		}
		bw.close();
		System.out.println("File '" + file + "' wurde erfolgreich gespeichert.");
	}
}
