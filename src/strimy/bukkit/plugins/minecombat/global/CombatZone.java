package strimy.bukkit.plugins.minecombat.global;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class CombatZone 
{
	private ArrayList<Location> points = new ArrayList<Location>();
	
	public void addPoint(Location p)
	{
		points.add(p);
	}
	
	public void removePoint(Location p)
	{
		if(points.contains(p))
		{
			points.remove(p);
		}
	}
	
	public void clearPoints()
	{
		points.clear();
	}
	
	public boolean isInCombatZone(Location p)
	{
		Point playerPoint = new Point();
		playerPoint.X = p.getX();
		playerPoint.Y = p.getY();
		
		List<Point> listPoint = new ArrayList<Point>();
		for (Location lp : points) 
		{
			Point newPoint= new Point();
			newPoint.X = lp.getX();
			newPoint.Y = lp.getY();
			listPoint.add(newPoint);
		}
		
		return Math.abs(InsidePolygon(listPoint, playerPoint)) >= Math.PI;
	}
	
    private double InsidePolygon(List<Point> polygon, Point p)
    {
    	int n = polygon.size();
        int i;
        double angle = 0;
        Point p1 = new Point(); ;
        Point p2 = new Point() ;

        for (i = 0; i < n; i++)
        {
            p1.X = polygon.get(i).X - p.X;
            p1.Y = polygon.get(i).Y - p.Y;
            p2.X = polygon.get((i + 1) % n).X - p.X;
            p2.Y = polygon.get((i + 1) % n).Y - p.Y;
            angle += Angle2D(p1.X, p1.Y, p2.X, p2.Y);
        }

        return angle;
    }

    /*
       Return the angle between two vectors on a plane
       The angle is from vector 1 to vector 2, positive anticlockwise
       The result is between -pi -> pi
    */
    double Angle2D(double x1, double y1, double x2, double y2)
    {
        double dtheta, theta1, theta2;

        theta1 = Math.atan2(y1, x1);
        theta2 = Math.atan2(y2, x2);
        dtheta = theta2 - theta1;
        while (dtheta > Math.PI)
            dtheta -= 2*Math.PI;
        while (dtheta < -Math.PI)
            dtheta += 2*Math.PI;

        return (dtheta);
    }
}
