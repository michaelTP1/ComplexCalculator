package dad.javafx.calculadora;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
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

	// modelo

	private DoubleProperty operando1Real = new SimpleDoubleProperty();
	private DoubleProperty operando1Imaginario = new SimpleDoubleProperty();
	private DoubleProperty operando2Real = new SimpleDoubleProperty();
	private DoubleProperty operando2Imaginario = new SimpleDoubleProperty();
	private DoubleProperty resultadoReal = new SimpleDoubleProperty();
	private DoubleProperty resultadoImaginario = new SimpleDoubleProperty();
	
	private StringProperty operador = new SimpleStringProperty();

	// vista

	private TextField operando1RealText,operando1ImaginarioText;
	private TextField operando2RealText,operando2ImaginarioText;
	private TextField resultadoRealText,resultadoImaginarioText;

	private ComboBox<String> operadorCombo;

	@Override
	public void start(Stage primaryStage) throws Exception {

		operando1RealText = new TextField();
		operando1RealText.setPrefColumnCount(4);
		
		operando1ImaginarioText = new TextField();
		operando1ImaginarioText.setPrefColumnCount(4);
		
		operando2RealText = new TextField();
		operando2RealText.setPrefColumnCount(4);

		operando2ImaginarioText = new TextField();
		operando2ImaginarioText.setPrefColumnCount(4);

		resultadoRealText = new TextField();
		resultadoRealText.setPrefColumnCount(4);
		resultadoRealText.setDisable(true);
		
		resultadoImaginarioText = new TextField();
		resultadoImaginarioText.setPrefColumnCount(4);
		resultadoImaginarioText.setDisable(true);

		operadorCombo = new ComboBox<String>();
		operadorCombo.getItems().addAll("+", "-", "*", "/");

		
		HBox operando1Box=new HBox(5, operando1RealText,new Label("+"), operando1ImaginarioText, new Label("i"));
		HBox operando2Box=new HBox(5, operando2RealText,new Label("+"), operando2ImaginarioText,new Label("i"));
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

		Bindings.bindBidirectional(operando1RealText.textProperty(), operando1Real, new NumberStringConverter());
		Bindings.bindBidirectional(operando1ImaginarioText.textProperty(), operando1Imaginario, new NumberStringConverter());
		Bindings.bindBidirectional(operando2RealText.textProperty(), operando2Real, new NumberStringConverter());
		Bindings.bindBidirectional(operando2ImaginarioText.textProperty(), operando2Imaginario, new NumberStringConverter());
		Bindings.bindBidirectional(resultadoRealText.textProperty(), resultadoReal, new NumberStringConverter());
		Bindings.bindBidirectional(resultadoImaginarioText.textProperty(), resultadoImaginario, new NumberStringConverter());

		operador.bind(operadorCombo.getSelectionModel().selectedItemProperty());

		// listeners

		operador.addListener((o, ov, nv) -> onOperadorChanged(nv));

		operadorCombo.getSelectionModel().selectFirst();

	}

	private void onOperadorChanged(String nv) {

		switch (nv) {
		case "+":
			resultadoReal.bind(operando1Real.add(operando2Real));
			resultadoImaginario.bind(operando1Imaginario.add(operando2Imaginario));
			break;
		case "-":
			resultadoReal.bind(operando1Real.subtract(operando2Real));
			resultadoImaginario.bind(operando1Imaginario.subtract(operando2Imaginario));
			break;
		case "*":
			resultadoReal.bind(operando1Real.multiply(operando2Real).subtract(operando1Imaginario.multiply(operando2Imaginario)));
			resultadoImaginario.bind(operando1Real.multiply(operando2Imaginario).add(operando1Imaginario.multiply(operando2Real)));
			
			break;
		case "/":
			resultadoReal.bind((operando1Real.multiply(operando2Real).add(operando1Imaginario.multiply(operando2Imaginario))
					.divide(operando2Real.multiply(operando2Real).add(operando2Imaginario.multiply(operando2Imaginario)))));
			resultadoImaginario.bind((operando1Imaginario.multiply(operando2Real).subtract(operando1Real.multiply(operando2Imaginario))
					.divide(operando2Real.multiply(operando2Real).add(operando2Imaginario.multiply(operando2Imaginario)))));
			
			break;
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

}
