package it.dan.shape;

public class Square extends AbstractShape{

	int side;
	
	public Square(int side) {
	
		this.side = side;
	}

	@Override
	public double getArea() {
		
		return side * side;
	}

}
