//package GUI;
//
//import java.util.ArrayList;
//
//import org.math.plot.Plot3DPanel;
//
//import Regression.Polynomial;
//
//public class test extends javax.swing.JFrame {
//
//	 private ArrayList<Polynomial> funcX;
//	 private ArrayList<Polynomial> funcY;
//	 private double genauigkeit = 0.01; // user input
//
//	 public double fx(Double[] f, double y) {
//		 double z = 0;
//		 double exp;
//
//		 for(int i = 0; i < f.length; i++)
//		 {
//			 exp = f.length - i - 1;
//			 z += f[i] * Math.pow(y, exp);
//		 }
//
//		 return z;
//	 }
//
//	 public double fy(Double[] f, double x) {
//		 double z = 0;
//		 double exp;
//
//		 for(int i = 0; i < f.length; i++)
//		 {
//			 exp = f.length - i - 1;
//			 z += f[i] * Math.pow(x, exp);
//		 }
//
//		 return z;
//	 }
//
//	 public double[][] fx(double[] y) {
//		double[][] z = new double[funcX.size()][y.length];
//		for(int i = 0; i < funcX.size(); i++)
//		{
//
//			for(int j = 0; j < y.length; j++)
//			{
//				z[i][j] = funcX.get(i).function(y[j]);
//			}
//		}
//		return z;
//	 }
//
//	 public double[][] fy(double[] x) {
//			double[][] z = new double[x.length][funcY.size()];
//			for(int i = 0; i < funcY.size(); i++)
//			{
//				for(int j = 0; j < x.length; j++)
//				{
//					z[j][i] = funcY.get(i).function(x[j]);
//				}
//			}
//			return z;
//	}
//
//	public double[] increment(double start, double step, double end) {
//	      double range = end - start;
//	      int steps = (int)(range / step);
//	      double[] rv = new double[steps];
//	      for (int i = 0; i<steps; i++) {
//	         rv[i] = start + (step * i);
//	      }
//	      return rv;
//	   }
//
//    public test(ArrayList<Polynomial> funcX2, ArrayList<Polynomial> funcY2, double genauigkeit2) {
//        initComponents();
//
//        funcX = funcX2;
//		funcY = funcY2;
//		genauigkeit = genauigkeit2;
//
//         // define your data
//         double[] x = increment(-5.0, 1.0, 6.0); // x = 0.0:0.1:1.0
//         double[] y = increment(-5.0, 1.0, 6.0);// y = 0.0:0.05:1.0
//
////         double[][] zx = fx(x);
////         double[][] zy = fy(x);
//
//         // create your PlotPanel (you can use it as a JPanel) with a legend at SOUTH
//         Plot3DPanel plot = new Plot3DPanel("SOUTH");
//
//         // add grid plot to the PlotPanel
////         plot.addGridPlot("Funktionen nach x", x, y, zx);
////         plot.addGridPlot("Funktionen nach y", x, y, zy);
//
//
//    }
//
//    /** This method is called from within the constructor to
//     * initialize the form.
//     * WARNING: Do NOT modify this code. The content of this method is
//     * always regenerated by the Form Editor.
//     */
//    // <editor-fold defaultstate="collapsed" desc="Generated Code">
//    private void initComponents() {
//
//        jPanel1 = new javax.swing.JPanel();
//        jPanel2 = new javax.swing.JPanel();
//        jTextField1 = new javax.swing.JTextField();
//        jLabel1 = new javax.swing.JLabel();
//        jLabel2 = new javax.swing.JLabel();
//        jLabel3 = new javax.swing.JLabel();
//        jLabel4 = new javax.swing.JLabel();
//        jLabel5 = new javax.swing.JLabel();
//        jLabel6 = new javax.swing.JLabel();
//        jToggleButton1 = new javax.swing.JToggleButton();
//
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
//        setTitle("Plot");
//
//        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(" Position/Direction "));
//        jPanel1.setPreferredSize(new java.awt.Dimension(700, 700));
//
//        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
//        jPanel1.setLayout(jPanel1Layout);
//        jPanel1Layout.setHorizontalGroup(
//            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//            .add(0, 688, Short.MAX_VALUE)
//        );
//        jPanel1Layout.setVerticalGroup(
//            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//            .add(0, 677, Short.MAX_VALUE)
//        );
//
//        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(" System "));
//        jPanel2.setPreferredSize(new java.awt.Dimension(700, 300));
//
//        jTextField1.setText("");
//        jTextField1.setPreferredSize(new java.awt.Dimension(100, 30));
//        jTextField1.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jTextField1ActionPerformed(evt);
//            }
//        });
//
//        jLabel1.setText("Genauigkeit");
//
//        jLabel2.setText("Polynomial in x-Richtung");
//
//        jLabel3.setText("Polynomial in y-Richtung");
//
//        jLabel4.setText("Px");
//
//        jLabel5.setText("Py");
//
//        jLabel6.setText("Neue Input-Datei einlesen");
//
//        jToggleButton1.setText("Einlesen");
//        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                jToggleButton1ActionPerformed(evt);
//            }
//        });
//
//        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
//        jPanel2.setLayout(jPanel2Layout);
//        jPanel2Layout.setHorizontalGroup(
//            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//            .add(jPanel2Layout.createSequentialGroup()
//                .addContainerGap()
//                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//                    .add(jPanel2Layout.createSequentialGroup()
//                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//                            .add(jLabel1)
//                            .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
//                        .add(128, 128, 128)
//                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//                            .add(jLabel2)
//                            .add(jLabel4))
//                        .add(112, 112, 112)
//                        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//                            .add(jLabel3)
//                            .add(jLabel5)))
//                    .add(jLabel6)
//                    .add(jToggleButton1))
//                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//        );
//        jPanel2Layout.setVerticalGroup(
//            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//            .add(jPanel2Layout.createSequentialGroup()
//                .addContainerGap()
//                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
//                    .add(jLabel1)
//                    .add(jLabel2)
//                    .add(jLabel3))
//                .add(1, 1, 1)
//                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
//                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
//                    .add(jLabel4)
//                    .add(jLabel5))
//                .add(76, 76, 76)
//                .add(jLabel6)
//                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
//                .add(jToggleButton1)
//                .addContainerGap(112, Short.MAX_VALUE))
//        );
//
//        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
//        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//            .add(layout.createSequentialGroup()
//                .addContainerGap()
//                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                    .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                .addContainerGap())
//        );
//        layout.setVerticalGroup(
//            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
//            .add(layout.createSequentialGroup()
//                .addContainerGap()
//                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
//                .addContainerGap())
//        );
//
//        jPanel1.getAccessibleContext().setAccessibleName("Plot");
//
//        pack();
//    }// </editor-fold>
//
//    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
//        // TODO add your handling code here:
//       System.out.println(jTextField1.getText());
//    }
//
//    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {
//        // TODO add your handling code here:
//        //einlese-button
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//         */
//        try {
//            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels=javax.swing.UIManager.getInstalledLookAndFeels();
//            for (int idx=0; idx<installedLookAndFeels.length; idx++)
//                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
//                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
//                    break;
//                }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//            	// TODO: Aufruf von ausserhalb
//                new test(null, null, 1.0).setVisible(true);
//            }
//        });
//    }
//
//    // Variables declaration - do not modify
//    private javax.swing.JLabel jLabel1;
//    private javax.swing.JLabel jLabel2;
//    private javax.swing.JLabel jLabel3;
//    private javax.swing.JLabel jLabel4;
//    private javax.swing.JLabel jLabel5;
//    private javax.swing.JLabel jLabel6;
//    private javax.swing.JPanel jPanel1;
//    private javax.swing.JPanel jPanel2;
//    private javax.swing.JTextField jTextField1;
//    private javax.swing.JToggleButton jToggleButton1;
//    // End of variables declaration
//
//}
