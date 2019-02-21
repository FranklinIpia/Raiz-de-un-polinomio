package modelo;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
/**
 * This is a class of Polynomial
 * @author Sebastian Rebolledo, Jorge    ,Franklin Inpia
 *
 */
public class Polinomio {
/**
 * This is atribute of type double
 */
	private double[] coefficientN;
	/**
	 * This is a array type int
	 */
	private int[] roots;
	/**
	 * This is a atribute of type double
	 */
	private double r0;
	/**
	 * This is a atribute od type double
	 */
	private double s0;
	/**
	 * This is a atribute of type double
	 */
	private double re[];
	/**
	 * This is a atribute of type double
	 */
	private double im[];//raices complejas
	
	private int cont=0;
	private int cont2=0;
/**
 * This is the class Polynomuyal
 * pre: coeffcientN is different of null
 * @param coefficientN - a array of de polynomial
 */
	public Polinomio(double[] coefficientN) {
		this.coefficientN=coefficientN;
		  re = new double[coefficientN.length]; 
		  im = new double[coefficientN.length];
		  r0=-1;
		  s0=-1;
	}
	
	
	/**
	 * This method is the method of Bairstow
	 * This method finds of root of polynomial up to grade ten
	 * pre: the polynomial existe
	 * pre:the polynomial is grade less or equal ten
	 * post: the roots of the polynomial have been found
	 */
	  public void Bairstow( )
	  {
		  

	    int n = coefficientN.length; 
	    int iter =0;
	    double b[] = new double[n], c[] = new double[n];
	    double ea1 = 1, ea2 = 1, T = 0.00001;
	    double r=r0, s=s0,det, ds, dr;
	    int MaxIter = 100, i;

	   for(iter=0; iter< MaxIter && n>3; iter++)
	   {
		   System.out.println(cont);
		   cont=0;

	      do {
	    	  cont++;
	    	  System.out.println("do: "+cont);
	        Division_Derivada(coefficientN, b, c, r, s, n);
	        
	        det = c[2]*c[2] - c[3]*c[1];

	        if(det!=0)
	        {

	          dr = (-b[1]*c[2] + b[0]*c[3])/det;
	          ds = (-b[0]*c[2] + b[1]*c[1])/det;

	          r = r+dr;
	          s = s+ds;

	          if(r!=0) 
	        	  ea1 = Math.abs(dr/r)*100.0;
	          if(s!=0) 
	        	  ea2 = Math.abs(ds/s)*100.0;

	        }

	        else
	        {

	          r = 5*r+1;
	          s = s+1;
	          iter = 0;

	        }

	      }

	      while ((ea1 > T) && (ea2 > T));
	      raices(r, s, re, im, n);
	      n = n-2;

	      for(i=0; i<n; i++)
	        coefficientN[i] = b[i+2];
	      if (n < 4) break;

	    }

	    if(n==3)
	    {

	      r = -coefficientN[1]/coefficientN[2];
	      s = -coefficientN[0]/coefficientN[2];
	      raices(r, s, re, im, n);

	    }

	    else
	    {

	      re[n-1] = -coefficientN[0]/coefficientN[1];
	      im[n-1] = 0;

	    }
//      IMPRIME RAICES
//	    for(i=1; i<re.length; i++) {
//	        int raiz=(int) Math.round(re[i]);
//	        System.out.println("las raices modificadas son: "+ "X["+i+"]= " + raiz + " j " + im[i]);
//	    }
//	    for(i=1; i<re.length; i++) {
//	        int raiz=(int) Math.round(re[i]);
//	      System.out.println("las raices modificadas son: "+ "X["+i+"]= " + raiz + " j " + im[i]);
//	    }
	  }
	
	
	/**
	 * This method finds of the root of the polynomial
	 * @param r - r is diferent of null
	 * @param s - s is diferente of null
	 * @param re - re has been initialized , is diferent of null
	 * @param im - im has been initialez, is diferent of null
	 * @param n - n is different of null
	 * post: the roots of the polynomial have been found
	 */
	 public static void raices(double r, double s, double re[], double im[], int n)
	  {

	    double d = r*r + 4*s;

	    if(d > 0)
	    {

	      re[n-1] = (r + Math.sqrt(d))/2.0;
	      re[n-2] = (r - Math.sqrt(d))/2.0;

	      im[n-1] = 0;
	      im[n-2] = 0;

	    }

	    else
	    {

	      re[n-1] = r/2.0;
	      re[n-2] = re[n-1];
	      im[n-1] = Math.sqrt(-d)/2.0;
	      im[n-2] = -im[n-1];

	    }

	  }

	 /** 
      *This methodo reduces the polynomial and derives it
      *pre: the arrays are diferent of null
	  * @param a - its a array
	  * @param b - its a array
	  * @param c - its a array
	  * @param r - its a array
	  * @param s - its a array
	  * @param n - its a array
	  * post:the polynomial has been reduced
	  */
	 public static void Division_Derivada(double a[], double b[], double c[], double r, double s, int n)
	  {

	    int i;

	    b[n-1] = a[n-1];
	    b[n-2] = a[n-2] + r*b[n-1];

	    c[n-1] = b[n-1];
	    c[n-2] = b[n-2] + r*c[n-1];

	    for(i=n-3; i>=0; i--)
	    {

	      b[i] = a[i] + r*b[i+1] + s*b[i+2];
	      c[i] = b[i] + r*c[i+1] + s*c[i+2];

	    }

	  }
	 
	 
//	 public static void imprime(double x[], int n)
//	  {
//
//	    int i;
//
//	    for (i = n - 1; i >= 0; i--)
//
//	      if(x[i] > 0) System.out.print("+ " +x[i] + "x"+i+" ");
//	      else System.out.print("- " + -x[i] + "x"+i+" ");
//	    System.out.println("");
//
//	  }

	 

	  public String getRoots(){
		  String roots="las raices del polinomio son: \n";
		  for(int i=1; i<re.length; i++) {
		        int raiz=(int) Math.round(re[i]);
		     roots+= "X["+i+"]= " + raiz + " j " + im[i]+"\n";
		    }
		  
return roots;
	  }

	
	  
	  /**
	   * Thism method is the cuadratic.
	   * This method finds of the root of a polynomail
	   * pre: The array coefficientN its has created and this is initialized
	   * post:the roots of the polynomial have been found
	   */
	  public void MetodoCuadratica() {
		  
		  int a=(int) coefficientN[0];
		  int b=(int) coefficientN[1];		
		  int c=(int) coefficientN[2];

   	   double x1 = (-b + Math.sqrt((b*b)-(4*a*c)))/(2*a);

          double x2 = (-b - Math.sqrt((b*b)-(4*a*c)))/(2*a);
          
          JOptionPane.showMessageDialog(null,"Las raices son: "+ x1 +" y  " + x2);
          JOptionPane.showMessageDialog(null,"Las raices fueron halladas por el metodo de la cuadratica");

          
          System.out.println("Raiz:" + x1 + "Raiz2" + x2);
          
   }
	  
	  
	 
	  
	  
	  
	  
	 
	  /**
	   * This method get of the polynomial
	   * pre: coeffivientN is diferent of null
	   * @return coefficientN - it's a polynomial
	   * post: the	polynomial has benn returned
	   */
	  public double[] getCoefficientN() {
			return coefficientN;
		}


/**
 * This method change of the polynomial
 * @param coefficientN
 */
		public void setCoefficientN(double[] coefficientN) {
			this.coefficientN = coefficientN;
		}


}
