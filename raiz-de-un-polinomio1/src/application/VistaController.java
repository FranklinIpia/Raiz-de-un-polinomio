package application;

import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.swing.plaf.metal.MetalPopupMenuSeparatorUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
/**
 * 
 * @author Jorge Calambás , Franklin Ipia, Sebastian Rebolledo
 *
 */

public class VistaController {
	@FXML
	 private GridPane gp;
	@FXML
	 private TextField amountCoefficent;
	@FXML
	private TextField[] coefficent;
	@FXML
	private Label[] exponent;
	@FXML
	private Button calculate;
	@FXML
	private Button btnAleatorio;
	@FXML
	private ImageView portada;
	
	private int size;

	private  Main m;
	/**
	 * This is the builder method
	 * pre: the relation with the class is not m
	 * post:the variable m has been initialized
	 */
	public VistaController() {
		m=new Main();
		
	}
	
	/**
	 * This method creates a new array with numbers random and
	 * size random less that o equals ten 
	 * pre:Creates un array of size less or equals a ten
	 * post:is created a array with size less than or euals to ten
	 * 
	 */
	public void polynomialRandom() {
		double array[]=createArrayPolynomialRandom();
		 size= array.length;

		m.addPolynomial(array);
		amountCoefficent.setText(size-1+"");
		amountCoefficentAleatorio();
	}
	
	
	
	/**
	 * This method creates a new array with numbers random and
	 * size random less that o equals ten 
	 * pre:Creates un array of size less or equals a ten
	 * post:is created a array with size less than or euals to ten
	 * @return
	 */
	  public double[] createArrayPolynomialRandom() {
		  int numero=(int ) (Math.random() * 10+2);
		  double arregloDeCoeficientes[]=createNumbersRandom(numero);
		
			return arregloDeCoeficientes;
	  }
	  
	  
	  /**
	   * This method create numbers random less than ten
	   * pre:the array has already ben initialized
	   * @param size - the size of the array
	   * @return array - a array with numbers random
	   * post: the array is not emty or array it's !=null
	   */
	  public double[] createNumbersRandom(int size) {
		  int limit = 10;
			double array[] = new double[size];
			for (int i=0; i<array.length; i++) {
				array[i] = (int) (Math.random() * limit);
			}
			return array;
	  }

	
	
	 public void amountCoefficentAleatorio() {
		 gp.getChildren().clear();
		 int size=Integer.parseInt(amountCoefficent.getText())+1;
		 if(size<=11) {
		 exponent=new Label[size];
		 for (int i = 0; i < size; i++) {
				exponent[i]=new Label("x^"+(i));
		}
		 showPolynomialRandom();
		 
	 }

	
		 else {
			 messageAlert("El maximo de exponentes es hasta 10");
		 }
		 
	 }
	 
	 /**
	  * 
	  */
	 public void showPolynomialRandom() {
		 int size=Integer.parseInt(amountCoefficent.getText())+1;
		 coefficent=new TextField[size];
		 for (int i = 0; i < size; i++) {
			 gp.add(exponent[i],i,0);	 
			 gp.add(coefficent[i]=new TextField(),i,1);	 

		}
		 double[] arreglo2= m.getPolynomial().getCoefficientN();
		 			for(int j=0;j<arreglo2.length;j++) {
			coefficent[j].setText(arreglo2[j]+"");
					
				}
		 			calculatePolynomialRandom();
		 calculate.setVisible(true);
	 }
	
	 
	 
	 /**
	  * 
	  */
		public void calculatePolynomialRandom() {
			 int size=Integer.parseInt(amountCoefficent.getText())+1;
			 
			if(size==3) {
				calcularPolynomio2();
			}else {
				
				double[] c=new double[ coefficent.length];
				for (int i = 0; i < coefficent.length; i++) {
					if(coefficent[i]==null) {
						c[i]=0;
					}
					else {
					
						c[i]=Double.parseDouble(coefficent[i].getText());
					}
				}
				m.addPolynomial(c);
				m.getPolynomial().Bairstow();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Raices");
				alert.setHeaderText(m.getPolynomial().getRoots() + "\n" + " Solucionado por: Metodo Bairstow" );
				alert.showAndWait();
				amountCoefficent.clear();
				
			}
			
			
		}
	 
	
	// segundo Lo que hace es que despues de que se hayan creado los arreglos de textfield y labels lo agrega al gridlayout y los muestra
	 //pre amountCoefficent es diferente de null && >=10


	/***
	 * 
	 */
	 public void showPolynomial() {

		 coefficent=new TextField[size];
		 for (int i = 0; i < size; i++) {
			 gp.add(exponent[i],i,0);	 
			 gp.add(coefficent[i]=new TextField(),i,1);	 

		}
		 calculate.setVisible(true);
	 }
	 
	 
	 //primero entra aqui. lo que hace es que depenciendo del numero que ingresa el usuario crea los esponentes y los textfield
	 //Pos amountCoefficent debe ser diferente de null && >=10
	 
	 
	 /**
	  * 
	  */
	 public void amountCoefficent() {
		 gp.getChildren().clear();
		 if(amountCoefficent.getText().equals("")) {
			 messageAlert("Primero escriba el exponente mas grande del polinomio");
		 }
		 else {
			  size=Integer.parseInt(amountCoefficent.getText())+1;
		 
			 if(size<=11) {
				 exponent=new Label[size];
					 for (int i = 0; i < size; i++) {
							exponent[i]=new Label("x^"+(i));
					}
					 showPolynomial();
			 
			 }
			
			 else {
				 messageAlert("El maximo de exponentes es hasta 10");
			 }
		 }
	 }
	 
	 
	 
	 //tercero luego de que el usuario escriba el polinomio lo calcula
	 
	 /**
	  * 
	  */
	public void calculatePolynomial() {
		
			double[] c=new double[coefficent.length];
			for (int i = 0; i < coefficent.length; i++) {

				if(coefficent[i]==null||coefficent[i].getText().equals("")) {
					c[i]=0;
				}
				else {
					c[i]=Double.parseDouble(coefficent[i].getText());
				}
			}
			m.addPolynomial(c);
			
			if(size==3) {
				method1(c);
			
			}else {
				method2(c);
		
			}
		
		calculate.setVisible(false);
		
	}
	
	private void method1(double[] c) {
		m.addPolynomial(c);
		m.getPolynomial().MetodoCuadratica();	
		messageInformation(m.getPolynomial().getRoots()+ "\n"+"Este metodo se soluciono por el metodo de la cuadratica");		

	}
	
	
	private void method2(double[] c) {
		m.getPolynomial().Bairstow();
		messageInformation(m.getPolynomial().getRoots()+ "\n"+"Este metodo se soluciono por Bairstow");		
	}
	
	/**
	 * 
	 */
	public void calcularPolynomio2() {
		double[] c=new double[ coefficent.length];
		for (int i = 0; i < coefficent.length; i++) {
			if(coefficent[i]==null) {
				c[i]=0;
			}
			else {
			
				c[i]=Double.parseDouble(coefficent[i].getText());
			}
		}
		m.addPolynomial(c);
		m.getPolynomial().MetodoCuadratica();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Raiz cuadratica");
//		alert.setHeaderText(m.getPolynomial().getRoots());
		alert.showAndWait();
		amountCoefficent.clear();
	}
	
	//mensaje de alerta en caso de que escriba mas de 10 expoentes
	/**
	 * 
	 * @param message
	 */
	public void messageAlert(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Error");
		alert.setHeaderText(message);
		alert.showAndWait();
		amountCoefficent.clear();
	}
	
	public void messageInformation(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Raices");
		alert.setHeaderText(message);
		alert.showAndWait();
		amountCoefficent.clear();
	}
	
	
	
	public void generarAleatorio() {
		
	}
	
}
