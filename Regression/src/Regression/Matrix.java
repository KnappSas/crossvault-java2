package Regression;
class Matrix
{

    protected int AnzahlZeilen = 3;
    protected int AnzahlSpalten = 3;
    protected double x[][] = new double[AnzahlZeilen][AnzahlSpalten];


    public static final String ST_STRIKTDIAGONALDOMINAT = "Strikt diagonaldominante Matrix";
    public static final String ST_SCHWACHDIAGONALDOMINAT = "Schwach diagonaldominante Matrix";
    public static final String ST_KEINEDIAGONALDOMINAT = "Keine diagonaldominante Matrix";

    public static final int STRIKTDIAGONALDOMINAT = 2;
    public static final int SCHWACHDIAGONALDOMINAT = 1;
    public static final int KEINEDIAGONALDOMINAT = 0;
    public Vector detZW;
    public double detZWA = 0.0;


    public static final int ZUFALLSMATRIX = 2;
    public static final int EINHEITSMATRIX = 1;
    public static final int NULLMATRIX = 0;


    public Matrix()
    {
        for (int i = 0; i < AnzahlZeilen; i++)
            for (int j = 0; j < AnzahlSpalten; j++)
                x[i][j] = 0;
    }


    public Matrix(Matrix m)
    {
        this.x = new double[m.AnzahlZeilen][m.AnzahlSpalten];
        this.AnzahlZeilen = m.AnzahlZeilen;
        this.AnzahlSpalten = m.AnzahlSpalten;

        for (int i = 0; i < AnzahlZeilen; i++)
            for (int j = 0; j < AnzahlSpalten; j++)
                x[i][j] = m.x[i][j];
    }


    public Matrix(Vector x1, Vector x2, Vector x3)
    {
        x[0][0] = x1.x();
        x[0][1] = x1.y();
        x[0][2] = x1.z();

        x[1][0] = x2.x();
        x[1][1] = x2.y();
        x[1][2] = x2.z();

        x[2][0] = x3.x();
        x[2][1] = x3.y();
        x[2][2] = x3.z();
        this.AnzahlZeilen = 3;
        this.AnzahlSpalten = 3;
    }


    public Matrix(int Zeilen, int Spalten)
    {
        if ((Zeilen <= 0) || (Spalten <= 0))
        {
            AnzahlZeilen = 0;
            AnzahlSpalten = 0;
            return;
        }

        AnzahlZeilen = Zeilen;
        AnzahlSpalten = Spalten;
        x = new double[Spalten][Zeilen];
        for (int i = 0; i < Zeilen; i++)
        {
            // feld mit dem Wert Null vorbesetzen.
            for (int j = 0; j < Spalten; j++)
                x[i][j] = 0;
        }
    }


    public void initMatrix(int typ)
    {
        if (typ == ZUFALLSMATRIX)
        {
            for (int i = 0; i < AnzahlZeilen; i++)
            {
                for (int j = 0; j < AnzahlSpalten; j++)
                {
                    x[i][i] = Math.random() * 20;
                } // end of for
            } // end of if
        } // end of if

        if (typ == EINHEITSMATRIX)
        {
            for (int i = 0; i < AnzahlZeilen; i++)
            {
                x[i][i] = 1;
            } // end of if
        } // end of if
    }

    public int anzahlSpalten()
    {
        return this.AnzahlSpalten;
    }

    public int anzahlZeilen()
    {
        return this.AnzahlZeilen;
    }

    public double x(int i, int j)
    {
        return x[i][j];
    }

    public void x(int i, int j, double val)
    {
        x[i][j] = val;
    }

    public void Zufall()
    {
        for (int i = 0; i < AnzahlZeilen; i++)
        {
            for (int j = 0; j < AnzahlSpalten; j++)
                x[i][j] = Math.round(Math.random() * 10);
        }

    }

    // Multiplikation von Rechts
    public Vector mult(Vector val)
    {
        double x1 = this.x(0, 0) * val.x() + this.x(1, 0) * val.y() + this.x(2, 0) * val.z();
        double x2 = this.x(0, 1) * val.x() + this.x(1, 1) * val.y() + this.x(2, 1) * val.z();
        double x3 = this.x(0, 2) * val.x() + this.x(1, 2) * val.y() + this.x(2, 2) * val.z();
        return new Vector(x1, x2, x3);
    }

    // Multiplikation von Links
    public Vector lmult(Vector val)
    {
        double x1 = this.x(0, 0) * val.x() + this.x(0, 1) * val.y() + this.x(0, 2) * val.z();
        double x2 = this.x(1, 0) * val.x() + this.x(1, 1) * val.y() + this.x(1, 2) * val.z();
        double x3 = this.x(2, 0) * val.x() + this.x(2, 1) * val.y() + this.x(2, 2) * val.z();
        return new Vector(x1, x2, x3);
    }


    public void print()
    {
        for (int i = 0; i < AnzahlZeilen; i++)
        {

            System.out.print("(");

            for (int j = 0; j < AnzahlSpalten; j++)
                System.out.print(" " + x[i][j]);

            System.out.println(")");
        }
    }

    public Matrix Addition(Matrix mat)
    {
        // überprüfung der Dimensionen
        if ((mat.AnzahlZeilen != this.AnzahlZeilen) ||
                (mat.AnzahlSpalten != this.AnzahlSpalten))
            return null;

        Matrix Ergebnis = new Matrix(AnzahlZeilen, AnzahlSpalten);
        for (int i = 0; i < AnzahlZeilen; i++)
        {
            for (int j = 0; j < AnzahlSpalten; j++)
            {
                Ergebnis.x[i][j] = this.x[i][j] + mat.x[i][j];
            }

        }
        return Ergebnis;
    }


    public Matrix Subtraktion(Matrix mat)
    {
        // überprüfung der Dimensionen
        if ((mat.AnzahlZeilen != this.AnzahlZeilen) ||
                (mat.AnzahlSpalten != this.AnzahlSpalten))
            return null;

        Matrix Ergebnis = new Matrix(AnzahlZeilen, AnzahlSpalten);
        for (int i = 0; i < AnzahlZeilen; i++)
        {
            for (int j = 0; j < AnzahlSpalten; j++)
            {
                Ergebnis.x[i][j] = this.x[i][j] - mat.x[i][j];
            }

        }
        return Ergebnis;
    }

    public Matrix Multiplikation(Matrix mat)
    {
        // überprüfung der Dimensionen
        if ((mat.AnzahlZeilen != this.AnzahlSpalten) ||
                (mat.AnzahlSpalten != this.AnzahlZeilen))
            return null;

        Matrix Ergebnis = new Matrix(AnzahlZeilen, mat.AnzahlSpalten);
        for (int i = 0; i < AnzahlZeilen; i++)
        {
            for (int j = 0; j < AnzahlSpalten; j++)
            {

                double value = 0;
                // Wert für die Spalte / Zeile berechnen
                for (int k = 0; k < AnzahlSpalten; k++)
                    value = value + this.x[i][k] * mat.x[k][j];
                //
                Ergebnis.x[i][j] = value;
            }

        }
        return Ergebnis;


    }

    public Matrix matrixMitB(Matrix mat, Vector B, int spalte)
    {
        int spalten = mat.AnzahlZeilen;
        int zeilen = mat.AnzahlSpalten;
        Matrix retMat = new Matrix(zeilen, spalten);
        int k = 0;
        int l = 0;
        for (int i = 0; i < zeilen; i++)
        {
            for (int j = 0; j < spalten; j++)
            {
                retMat.x[i][j] = mat.x[i][j];


            }
        }
        for (int i = 0; i < zeilen; i++)
        {
            retMat.x[i][spalte] = B.get(i);
        } // end of for

        return retMat;
    }


    public Matrix teilmatrix(Matrix mat, int zeile, int spalte)
    {
        int spalten = mat.AnzahlZeilen;
        int zeilen = mat.AnzahlSpalten;
        Matrix retMat = new Matrix(zeilen - 1, spalten - 1);
        int k = 0;
        int l = 0;
        for (int i = 0; i < zeilen - 1; i++)
        {
            if (i == spalte)
                k++;
            l = 0;

            for (int j = 0; j < spalten - 1; j++)
            {
                if (j == zeile)
                    l++;

                retMat.x[i][j] = mat.x[k][l];
                l++;
            }
            k++;
        }
        return retMat;
    }


    public double determinante()
    {
        double value = 0;
        double vorzeichen = 1;

        if (this.AnzahlZeilen == 2)
            return x[0][0] * x[1][1] - x[0][1] * x[1][0];

        for (int i = 0; i < this.AnzahlZeilen; i++)
        {
            value += vorzeichen * x[0][i] * teilmatrix(this, i, 0).determinante();

            vorzeichen *= (-1);
        }

        return value;
    }

    public Vector solveDLG(Matrix a, Vector b)
    {
        int size = b.getSize();
        Vector result = new Vector(size);
        detZW = new Vector(size);
        double result_a = a.determinante();
        this.detZWA = result_a;
        for (int i = 0; i < size; i++)
        {
            Matrix mat_i = a.matrixMitB(a, b, i);

            double det_b = mat_i.determinante();
            result.set(i, det_b / result_a);
            detZW.set(i, det_b);
        }

        return result;
    }

    public void printDetZW()
    {
        for (int i = 0; i < this.detZW.getSize(); i++)
        {
            System.out.print("" + detZW.get(i) + " / " + detZWA + "  ");
        }
    }


    private void swap(Matrix a, Vector b, int zeile1, int zeile2)
    {
        for (int i = 0; i < a.AnzahlSpalten; i++)
        {
            double temp = a.x[zeile1][i];
            a.x[zeile1][i] = a.x[zeile2][i];
            a.x[zeile2][i] = temp;
        } // end of for

        double temp = b.get(zeile1);
        b.set(zeile1, b.get(zeile2));
        b.set(zeile2, temp);
    }


    private int searchNewPivot(Matrix a, int spalte, int k)
    {
        for (int i = k + 1; i < a.AnzahlZeilen; i++)
        {
            if (a.x[i][spalte] != 0)
            {
                return i;
            } // end of if
        } // end of for
        return -1;
    }

    public Vector solveGaussDLG(Matrix a, Vector b)
    {
        int size = b.getSize();
        Vector result = new Vector(b);
        Matrix mat = new Matrix(a);

        // Matrix auf obere Dreiecksform bringen

        int n = size;

        // für alle Spalten - 1
        for (int k = 0; k < n; k++)
        {
            // für jeweils alle Zeilen die noch in der Spalte sind

            // Zeile Normalisieren
            for (int i = k; i < n; i++)
                mat.x[k][i] = mat.x[k][i] / mat.x[k][k];
            result.set(k, result.get(k) / mat.x[k][k]);


            for (int l = k + 1; l < n; l++)
            {
                // sollte die Matrix an dieser Stelle eine 0 haben, suche eine andere Zeile
        /*
        if (mat.x[k][k] == 0 ) {
           int index = searchNewPivot(mat, k, k);
          // falls nichts gefunden wurde ...
          if (index == -1) {
             return null ;
          } // end of if
          // tausche in der Matrix wie in der ergebnisspalte die zeile k mit index
          swap(mat, result, k, index);
        } // end of if
        */
                // berechne Faktor z

                // Teile die ganze Zeile durch das entsprechende Element

                //double z = ((double)mat.x[l][k] )/ ((double) mat.x[k][k]) ;
                double z = mat.x[l][k];


                //System.out.println("z berechnet: mat.x[l][k] "+mat.x[l][k] +", mat.x[k][k]: "+mat.x[k][k] + ", z = "+z );
                for (int i = k; i < n; i++)
                {
                    mat.x[l][i] = mat.x[l][i] - z * mat.x[k][i];
                }
                result.set(l, result.get(l) - z * result.get(k));
                System.out.println("******************************");
                mat.print();
            }
        }


        // jetzt Rückwärts wieder hoch gehen!

        System.out.println("Jetzt wieder Rückwärts");
        System.out.println("Ergebnisvektor zwischenzeitig");
        result.print();
        System.out.println();


        for (int k = n - 1; k >= 0; k--)
        {
            // Normalisiere die Zeilen auf das Pivot Element
            result.set(k, result.get(k) / mat.x[k][k]);
            mat.x[k][k] = 1;


            // die Elemente in der Zeile darüber  auf NULL bringen
            for (int l = k - 1; l >= 0; l--)
            {
                double zwerg1 = result.get(k) * mat.x[l][k];
                double zwerg2 = result.get(l) - zwerg1;
                System.out.println("Berechnung vom Lösungsvektor: " + result.get(l) + " - " + zwerg1 + " = " + zwerg2);

                result.set(l, zwerg2);
                mat.x[l][k] = 0;
            }
            mat.print();
            result.print();
            System.out.println();
            System.out.println("****");

        }
        mat.print();
        return result;
    }

    public int diagonalDominant()
    {
        boolean schwachdominanz = false;
        // Berechne die Diagonal dominanz
        for (int i = 0; i < AnzahlZeilen; i++)
        {

            double zeilenval = 0.0;
            for (int j = 0; j < AnzahlSpalten; j++)
            {
                if (i != j)
                {
                    zeilenval += x[i][j];
                } // end of if
                if (zeilenval > x[i][i])
                {
                    return KEINEDIAGONALDOMINAT;
                }
                if (zeilenval == x[i][i])
                {
                    schwachdominanz = true;
                }
            } // end of for
        } // end of for
        if (schwachdominanz)
        {
            return SCHWACHDIAGONALDOMINAT;
        } // end of if
        else
            return STRIKTDIAGONALDOMINAT;
    }


    public String checkDiagonalDominant()
    {
        int retval = this.diagonalDominant();
        if (retval == KEINEDIAGONALDOMINAT)
        {
            return ST_KEINEDIAGONALDOMINAT;
        }
        if (retval == SCHWACHDIAGONALDOMINAT)
        {
            return ST_SCHWACHDIAGONALDOMINAT;
        } // end of if
        else
            return ST_STRIKTDIAGONALDOMINAT;
    }

    // Funktionen zum Lösen eines linearen Gleichungssystems im IR2
    // mit ax+by = c
    // und dx+ey = f
    public double[] getLinearesGleichungsSystem(double a, double b, double c,
                                                double d, double e, double f)
    {
        double retval[] = new double[2];
        double divident = (e * a - b * d);

        if (divident == 0)
            return null;
        retval[1] = (f * a - d * c) / divident;
        retval[0] = (c - b * retval[1]) / a;
        return retval;
    }

    public double round(double zahl, int stellen)
    {
        return (int) zahl + (Math.round(Math.pow(10, stellen) * (zahl - (int) zahl))) / (Math.pow(10, stellen));
    }


    public double[] berechneFixVektor(Matrix a)
    {
        double retval[] = new double[2];

        double v11 = a.x(1, 0) / (1 + a.x(1, 0) - a.x(0, 0));
        double v12 = (1 - a.x(1, 1)) / (1 - a.x(1, 1) + a.x(0, 1));

        System.out.println("v11:" + v11);
        System.out.println("v12:" + v12);


        if (round(v11, 8) == round(v12, 8))
        {
            retval[0] = v11;
            retval[1] = 1 - v11;
            return retval;
        }
        System.out.println("Liefere NULL zurück");
        return null;
    }

    // Die Methode berechnet das n ab dem die Abweichung der Matrix zur
    // Vorgängermatrix also |A^n -A^(n+1)| < eps ist. Dabei
    // definiere ich die |A| mit max |aij|

    private double getMaxValueA(Matrix a)
    {
        double value = -1.0;

        for (int i = 0; i < a.AnzahlZeilen; i++)
        {


            for (int j = 0; j < a.AnzahlZeilen; j++)
            {
                if (Math.abs(a.x(i, j)) > value)
                    value = Math.abs(a.x(i, j));
            }
        }
        return value;
    }


    public int berechneGrenzmatrix(Matrix a, double eps)
    {
        Matrix temp = new Matrix(a);
        System.out.println("Berechne Grenzmatrix");
        temp.print();
        boolean quit = false;
        int n = 1;
        while (!quit)
        {
            Matrix temp2 = temp.Multiplikation(temp);
            System.out.println("--- Mult ----");
            temp2.print();
            Matrix temp3 = temp.Subtraktion(temp2);
            System.out.println("--- Subtr ---");
            temp3.print();
            n++;
            double maxVal = getMaxValueA(temp3);
            System.out.println("Größter Wert in temp :" + maxVal);
            if (maxVal <= eps)
                return n;
            if (n == 10000)
                quit = true;

            temp = temp2;
        }
        System.out.println("Nicht gefunden");
        return -1;
    }


    // Die nachfolgende Methode berechnet den Fixvektor für Matrizen belibiger Größe
    public double[] berechneFixVektorNxN(Matrix a)
    {
        if (a.AnzahlZeilen == 2)
            return this.berechneFixVektor(a);

        // erster Schritt Matrix der Dimension n-1 bilden
        int size = a.AnzahlZeilen;
        System.out.println("Größe der MAtrix: " + size);

        Matrix newA = new Matrix(size - 1, size - 1);

        // Werte der neuen MAtrix mit den angepassten Elementen füllen
        for (int i = 0; i < size - 1; i++)
        {
            for (int j = 0; j < size - 1; j++)
            {
                if (i == j)
                {
                    newA.x(i, j, a.x(i, j) - a.x(i, size - 1) - 1);
                } else
                {
                    newA.x(i, j, a.x(i, j) - a.x(i, size - 1));
                }
            }
        }
        System.out.println("Neue Matrix:");
        newA.print();

        // Vektor b bilden
        Vector b = new Vector(size - 1);
        for (int i = 0; i < size - 2; i++)
        {
            b.set(i, -a.x(i, size - 1));
        }

        Vector erg = this.solveDLG(newA, b);

        // Ergebnis mit der letzten Gleichung testen!
        double v_n = 1;

        for (int k = 0; k < erg.getSize(); k++)
        {
            v_n = v_n - erg.get(k);
        }

        double summe = 0.0;
        for (int i = 0; i < size - 2; i++)
            summe += erg.get(i) * a.x(size - 1, i);
        // letzten Wert mit v_n berechnen und dazu addieren
        summe += v_n * a.x(size - 1, size - 1);


        System.out.println("Vergleiche summe mit v_n:" + summe + " == " + v_n);
        if (summe == v_n)
        {
            double[] retval = new double[size];
            for (int k = 0; k < size - 2; k++)
            {
                retval[k] = erg.get(k);
            }
            retval[size - 1] = v_n;
        }
        return null;
    }


    public Vector JacobiGesamtschrittVerfahren(Matrix a, Vector b, int anzahlSchritte)
    {
        int size = b.getSize();
        Vector xNeu = new Vector(size);
        Vector xAlt = new Vector(size);

        for (int step = 0; step < anzahlSchritte; step++)
        {


            for (int i = 0; i < size; i++)
            {
                double summe = 0.0;
                for (int j = 0; j < size; j++)
                {
                    if (j != i)
                    {
                        summe = summe + (a.x(i, j) * xAlt.get(j));
                    } // end of if
                } // end of for
                xNeu.set(i, (b.get(i) - summe) / a.x(i, i));
            } // end of for
            for (int k = 0; k < size; k++)
            {
                xAlt.set(k, xNeu.get(k));
            } // end of for

        } // end of for
        return xNeu;

    }

  /*
  public double quadratischeForm(Vector val)
  {
  return  this.lmult(val);
  }
  */
}
