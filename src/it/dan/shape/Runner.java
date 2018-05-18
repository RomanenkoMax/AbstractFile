package it.dan.shape;

import java.util.HashSet;
import java.util.Set;

public class Runner {

	public static void main(String[] args) {
		Set<Circle> circles = new HashSet<>();
		circles.add(new Circle(10));
		circles.add(new Circle(20));
		circles.add(new Circle(25));

		String pathToFile = System.getProperty("user.dir") + System.getProperty("file.separator") + "test" + System.getProperty("file.separator") + "circle.xml";

		ShapeSet<Circle> shapeC = new ShapeSet<>(circles);

		shapeC.writeSetToXML(pathToFile);

		Set<Triangle> triangles = new HashSet<>();
		triangles.add(new Triangle(10, 8, 6));
		triangles.add(new Triangle(12, 10, 6));
		triangles.add(new Triangle(15, 11, 6));

		String pathToFile1 = System.getProperty("user.dir") + System.getProperty("file.separator") + "test" + System.getProperty("file.separator") + "triangle.xml";

		ShapeSet<Triangle> shapeT = new ShapeSet<>(triangles);

		shapeT.writeSetToXML(pathToFile1);

		Set<Square> squares = new HashSet<>();
		squares.add(new Square(10));
		squares.add(new Square(15));
		squares.add(new Square(20));

		String pathToFile2 = System.getProperty("user.dir") + System.getProperty("file.separator") + "test" + System.getProperty("file.separator") + "squares.xml";

		ShapeSet<Square> shapeS = new ShapeSet<>(squares);

		shapeS.writeSetToXML(pathToFile2);


	}

}
