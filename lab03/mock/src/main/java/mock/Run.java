package mock;

public class Run {

	public static void main(String[] args) {
		MyApp app= new MyApp(new DummyCalc());
		System.out.println(app.calculateAvg(2.0, 3.0));
		System.out.println(app.calculateAvg(3.0, 3.0));
	}

}

class DummyCalc implements ICalculator {

	public double add(double a, double b) {
		if(a == 2.0 && b == 3.0) return 5.0;
		if(a == 3.0 && b == 3.0) return 6.0;
		return 0.0;
	}

	public double divide(double a, double b) {
		if(a == 5.0) return 2.5;
		if(a == 6.0) return 3.0;
		return 0.0;
	}
	
}