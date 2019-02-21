package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import modelo.Polinomio;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private Polinomio polynomial;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("Vista.fxml"));
			Scene scene = new Scene(root,456,320);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
		
		
	}
	
	
	
	public void addPolynomial(double[] p) {
		polynomial=new Polinomio(p);
	}
	
	
	public Polinomio getPolynomial() {
		return polynomial;
	}
}
