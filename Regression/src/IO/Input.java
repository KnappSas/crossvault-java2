package IO;

import Regression.Point3D;
import java.io.*;
import java.util.ArrayList;

public class Input {
	public static ArrayList<ArrayList<Point3D>> readFromFile(String file) throws IOException
	{
		FileReader fr = new FileReader(file);
	    BufferedReader br = new BufferedReader(fr);

	    String zeile = "";
	    int zeilennummer = 0;
	    ArrayList<ArrayList<Point3D>> ergebnis = new ArrayList<ArrayList<Point3D>>();
	    ArrayList<Point3D> liste;
	    Point3D p;
	    while( (zeile = br.readLine()) != null )
	    {
	    	if(zeile.isEmpty())
	    		continue;
	    	String[] split = zeile.split("\\s+");
	    	liste = new ArrayList<Point3D>();
	    	for(int i = 0; i < split.length; i+=3)
	    	{
	    		
	    		p = new Point3D(Double.parseDouble(split[i]), Double.parseDouble(split[i+1]), Double.parseDouble(split[i+2]));
	    		//p.getPunkt();
	    		liste.add(p);
	    	}
	    	ergebnis.add(zeilennummer, liste);
	    	zeilennummer++;
	    }

	   
	    br.close();
		
	    System.out.println("File '" + file + "' wurde erfolgreich eingelesen.");
	    
		return ergebnis;
	}
}
