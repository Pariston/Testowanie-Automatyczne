package jUnit;

public class calculator {
	public calculator() {
		System.out.println("Calc: " + this);
	}
	
	public int add(int a, int b) {
		return a+b;
	}
	
	public int sub(int a, int b) {
		return a-b;
	}
	
	public int multi(int a, int b) {
		return a*b;
	}
	
	public int div(int a, int b) {
		if(b == 0) throw new ArithmeticException();
		return a/b;
	}
	
	public boolean greater(int a, int b) {
		if(a > b) return true;
		return false;
	}
}
