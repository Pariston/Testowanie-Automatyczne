package mock;

public class MyApp {
	private ICalculator calc;
	
	public MyApp(ICalculator calc) {
		this.calc = calc;
	}
	public double calculateAvg(double a, double b) {
		return calc.divide(calc.add(a, b), 2);
	}
}
