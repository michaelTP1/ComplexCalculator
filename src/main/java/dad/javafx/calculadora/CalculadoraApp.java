package dad.javafx.calculadora;

import javafx.application.Application;
import javafx.beans.binding.Bindings;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraApp extends Application {

	Complex complex1, complex2, complexAns;
	
	private StringProperty operador = new SimpleStringProperty();

	// vista

	private TextField real1Text,imaginary1Text;
	private TextField real2Text,imaginary2Text;
	private TextField resultadoRealText,resultadoImaginarioText;

	private ComboBox<String> operadorCombo;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		complex1=new Complex();
		complex2=new Complex();
		complexAns=new Complex();
		
		
		real1Text = new TextField();
		real1Text.setPrefColumnCount(4);
		
		imaginary1Text = new TextField();
		imaginary1Text.setPrefColumnCount(4);
		
		real2Text = new TextField();
		real2Text.setPrefColumnCount(4);

		imaginary2Text = new TextField();
		imaginary2Text.setPrefColumnCount(4);

		resultadoRealText = new TextField();
		resultadoRealText.setPrefColumnCount(4);
		resultadoRealText.setEditable(false);
		
		resultadoImaginarioText = new TextField();
		resultadoImaginarioText.setPrefColumnCount(4);
		resultadoImaginarioText.setEditable(false);

		operadorCombo = new ComboBox<String>();
		operadorCombo.getItems().addAll("+", "-", "*", "/");

		
		HBox operando1Box=new HBox(5, real1Text,new Label("+"), imaginary1Text, new Label("i"));
		HBox operando2Box=new HBox(5, real2Text,new Label("+"), imaginary2Text,new Label("i"));
		HBox answerBox=new HBox(5, resultadoRealText,new Label("+"), resultadoImaginarioText,new Label("i") );
		
		VBox operadorBox=new VBox(5, operadorCombo);
		operadorBox.setAlignment(Pos.CENTER);
		
		VBox operandosBox=new VBox(5, operando1Box, operando2Box,new Separator(), answerBox);
		operandosBox.setAlignment(Pos.CENTER);
		
		HBox root=new HBox(5, operadorBox, operandosBox);
		root.setAlignment(Pos.CENTER);
				

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Calculadora Compleja");
		primaryStage.setScene(scene);
		primaryStage.show();

		// bindeos

		
		
		Bindings.bindBidirectional(real1Text.textProperty(), complex1.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(imaginary1Text.textProperty(), complex1.imaginarioProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(real2Text.textProperty(), complex2.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(imaginary2Text.textProperty(), complex2.imaginarioProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(resultadoRealText.textProperty(), complexAns.realProperty(), new NumberStringConverter());
		Bindings.bindBidirectional(resultadoImaginarioText.textProperty(), complexAns.imaginarioProperty(), new NumberStringConverter());
		

		operador.bind(operadorCombo.getSelectionModel().selectedItemProperty());

		// listeners

		operador.addListener((o, ov, nv) -> onOperadorChanged(nv));

		operadorCombo.getSelectionModel().selectFirst();

	}

	private void onOperadorChanged(String nv) {

		switch (nv) {
		case "+":
			 complexAns.setComplex(complex1.add(complex2));
			break;
		case "-":
			complexAns.setComplex(complex1.subtract(complex2));
			break;
		case "*":
			complexAns.setComplex(complex1.multiply(complex2));
			
			break;
		case "/":
			complexAns.setComplex(complex1.divide(complex2));
			
			break;
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

}
