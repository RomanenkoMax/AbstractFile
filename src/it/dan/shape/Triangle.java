package it.dan.shape;

public class Triangle extends AbstractShape {

	int a, b, c;

	public static boolean verify(int a, int b, int c) {
		if (a >= b + c || b >= a + c || c >= a + b) {
			return false;
		}

		return true;
	}

	public Triangle(int a, int b, int c) {

		this.a = a;
		this.b = b;
		this.c = c;

	}

	@Override
	public double getArea() {
		if (verify(a, b, c)) {
			double p = (a + b + c) / 2;
			double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));
			return s;
		} else {
			return -1;
		}
	}

}
