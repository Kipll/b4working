package game;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class Circle implements Shape{

	private double radius;
	public Point2D.Double center;

	private double radiusSq;
	
	/**
	 * 
	 * @param radius The value of radius of a circle
	 * @param center The coordinate of the center of a circle
	 */
	public Circle(double radius, Point2D.Double center) {
			setRadius(radius);
			this.center = center;
	}
	
	/**
	 * Change the radius of circle
	 * @param r The radius of circle
	 */
	public void setRadius(double r) {
		double R = Math.max(r, 0);
		radius = R;
		radiusSq = R * R;
	}
	
	/**
	 * @return The radius of circle
	 */
	@Override
	public double getRadius() {
		return radius;
	}
	
	/**
	 * @return The coordinate of center of circle
	 */
	@Override
	public Point2D.Double getCenter() {
		return center;
	}

	/**
	 * 
	 * @return The value of radiusSq
	 */
	public double getRadiusSq() {
		return radiusSq;
	}

	/**
	 * 
	 * @param c Circle object
	 * @return The intersection value of two circles
	 */
	public boolean intersects(Circle c) {
		double dx = c.center.x - center.x;
		double dy = c.center.y - center.y;
		return (dx * dx + dy * dy < c.getRadiusSq() + getRadiusSq() + 2 * getRadius() * c.getRadius());
	}

	/**
	 * 
	 * @param rect Rectangle Object
	 * @return The intersection value of a rectangle and a circle
	 */
	public boolean intersects(Rectangle2D.Double rect) {
		Rectangle2D.Double cToSquare = new Rectangle2D.Double(center.x - radius, center.y - radius, radius * 2,
				radius * 2);

		if (!cToSquare.intersects(rect))
			return false;

		Circle sToCircle = new Circle(rect.getWidth() / 2,
				new Point2D.Double(rect.getX() + rect.getWidth() / 2, rect.getY() + rect.getHeight() / 2));

		return sToCircle.intersects(this);
	}
	
	/**
	 * @return The intersection value of a shape and a circle
	 */
	@Override
	public boolean intersects(Shape s) {
		if(s instanceof CircleSector) return s.intersects(this);
		else if(s instanceof Circle){
			return (new CircleSector(s.getRadius(),s.getCenter())).intersects(this);
		}
		return false;
	}
}
