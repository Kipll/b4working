package game;
import java.awt.geom.Point2D;

public class CircleSector implements Shape{

	private double radius;
	public Point2D.Double center;
	private double arcStart;
	public double arcLen;
	
	private double radiusSq;
	
	/**
	 * 
	 * @param radius The circle radius value
	 * @param center The coordinate of the center of circle
	 * @param arcStart 
	 * @param arcLen The length of arc
	 */
	public CircleSector(double radius, Point2D.Double center, double arcStart, double arcLen){
		setRadius(radius);
		this.center = center;
		setArcStart(arcStart);
		this.arcLen = arcLen;
	}	

	/**
	 * 
	 * @param radius The circle radius value
	 * @param center The coordinate of the center of circle
	 */
	public CircleSector(double radius, Point2D.Double center){
		setRadius(radius);
		this.center = center;
		arcStart = 0;
		arcLen = 2*Math.PI;
	}
	
	/**
	 * Change the radius of circle
	 * @param r The radius of circle
	 */
	public void setRadius(double r){
		double R = Math.max(r,0);
		radius = R;
		radiusSq = R * R;
	}
	
	/**
	 * @return The radius of circle
	 */
	@Override
	public double getRadius(){
		return radius;
	}
	
	/**
	 * @return The coordinate of center of circle
	 */
	@Override
	public Point2D.Double getCenter(){
		return center;
	}
	
	/**
	 * 
	 * @return The value of radiusSq
	 */
	public double getRadiusSq(){
		return radiusSq;
	}
	
	/**
	 * 
	 * @param a 
	 */
	public void setArcStart(double a){
		arcStart = a % (2*Math.PI);
	}
	
	/**
	 * 
	 * @return The value of arcStart
	 */
	public double getArcStart(){
		return arcStart;
	}
	
	/**
	 * Change the value of arcLen
	 * @param arcLen The value of arcLen
	 */
	public void setArcLen(double arcLen){
		this.arcLen = arcLen;
	}
	
	/**
	 * Get the value of arcLen
	 * @return The value of arcLen
	 */
	public double getArcLen(){
		return arcLen;
	}

	/**
	 * @return The intersection value of a shape and circle sector
	 */
	public boolean intersects(Shape s){
		if(s instanceof Circle){
			Circle c = (Circle)s;
			final double Rsq = getRadiusSq();
			final double rsq = c.getRadiusSq();
			final double R = getRadius();
			final double r = c.getRadius();
			final double dx = c.center.x - center.x;
			final double dy = c.center.y - center.y;
			final double dsq = dx*dx+dy*dy;
			if(dsq >= rsq + Rsq + 2*R*r)return false;
			if(dsq <= rsq || Math.abs(arcLen) >= 2*Math.PI)return true;
			double hitrng = 0;
			if(dsq <= rsq + Rsq) hitrng = Math.asin(r/Math.sqrt(dsq));
			else hitrng = Math.acos((Rsq-rsq+dsq)/(2*Math.sqrt(dsq)*R));
			double relativeAngle = Math.atan2(dy,dx);
		
			if(relativeAngle < 0) relativeAngle += 2*Math.PI;
			if(hitrng < 0) hitrng += 2*Math.PI;
		
			if(arcLen<0){
				arcStart += arcLen;
				while(arcStart < 0) arcStart += 2*Math.PI;
				arcLen*=-1;
			}
			double diff = arcStart - relativeAngle + hitrng;
			if(diff < 0) diff += 2*Math.PI;
			return (diff <= hitrng*2) || (2*Math.PI - diff <= arcLen);
		}
		else return false;
	}
}
