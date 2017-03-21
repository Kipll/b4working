package game;
import java.awt.geom.Point2D;

public interface Shape{
	public boolean intersects(Shape s);
	public double getRadius();
	public Point2D.Double getCenter();
	public Shape clone();
}
