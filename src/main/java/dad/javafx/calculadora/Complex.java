package dad.javafx.calculadora;


import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complex {

	private DoubleProperty real = new SimpleDoubleProperty(0);
	private DoubleProperty imaginario = new SimpleDoubleProperty(0);
	
	public Complex() {}
	
	public Complex(double real, double imaginario) {
		super();
		setReal(real);
		setImaginario(imaginario);
	}
	
	public final DoubleProperty realProperty() {
		return this.real;
	}

	public final double getReal() {
		return this.realProperty().get();
	}

	public final void setReal(final double real) {
		this.realProperty().set(real);
	}

	public final DoubleProperty imaginarioProperty() {
		return this.imaginario;
	}

	public final double getImaginario() {
		return this.imaginarioProperty().get();
	}

	public final void setImaginario(final double imaginario) {
		this.imaginarioProperty().set(imaginario);
	}
	
	@Override
	public String toString() {
		return getReal() + "+" + getImaginario() + "i";
	}
	
	public Complex add(Complex c) {
		Complex r = new Complex();
		r.realProperty().bind(real.add(c.realProperty()));
		r.imaginarioProperty().bind(imaginario.add(c.imaginarioProperty()));
		return r;
	}
	public Complex subtract(Complex c) {
		Complex r = new Complex();
		r.realProperty().bind(real.subtract(c.realProperty()));
		r.imaginarioProperty().bind(imaginario.subtract(c.imaginarioProperty()));
		return r;
	}
	
	public Complex multiply(Complex c) {
		Complex r=new Complex();
		r.realProperty().bind(realProperty().multiply(c.realProperty())
				.subtract(imaginarioProperty().multiply(c.imaginarioProperty())));
		r.imaginarioProperty().bind(realProperty().multiply(c.imaginarioProperty()
				.add(imaginarioProperty().multiply(c.realProperty()))));
		return r;
	}
	public Complex divide(Complex c) {
		Complex r=new Complex();
		r.realProperty().bind((realProperty().multiply(c.realProperty()).add(imaginarioProperty().multiply(c.imaginarioProperty()))
				.divide(c.realProperty().multiply(c.realProperty()).add(c.imaginarioProperty().multiply(c.imaginarioProperty())))));
		r.imaginarioProperty().bind((imaginarioProperty().multiply(c.realProperty()).subtract(realProperty().multiply(c.imaginarioProperty()))
				.divide(c.realProperty().multiply(c.realProperty()).add(c.imaginarioProperty().multiply(c.imaginarioProperty())))));
		return r;
		
	}
	
	public void setComplex(Complex c) {
		this.realProperty().bind(c.realProperty());;
		this.imaginarioProperty().bind(c.imaginarioProperty());
	}
	
	

	
	
	
	
	
	
	
	
}