/*
 * Catherine Lee
 */

public class Plane3D {

    //instance variable
    private double a,b,c,d;
    
    /**
    * 
    * A constructor from 3 points that forms the 3D plane
    * <p>
    * geometric for the plane is the form: ax+by+cz=d
    * <p>
    * Use the equation a(x – x0) + b(y – y0) + c(z – z0) = 0
    * to find a,b,c,and d
    * <p>
    * The equation used for caluation is included in the references
    * @param p1 
    * @param p2 
    * @param p3 
    */
    public Plane3D(Point3D p1, Point3D p2, Point3D p3){
       double a1,b1,c1, a2,b2,c2;

       //a(x – x0) + b(y – y0) + c(z – z0) = 0

       //set point(x0,y0,z0) as p1

       a1 = p2.getX() - p1.getX();
       b1 = p2.getY() - p1.getY();
       c1 = p2.getZ() - p1.getZ();
       
       a2 = p3.getX() - p1.getX();
       b2 = p3.getY() - p1.getY();
       c2 = p3.getZ() - p1.getZ();

       this.a = b1 * c2 - b2 * c1;
       this.b = a2 * c1 - a1 * c2;
       this.c = a1 * b2 - b1 * a2;

       //d = -(ax+by+cz)
       this.d = -(a * p1.getX() + b * p1.getY() + c * p1.getZ());

        
    }

    /**
    * 
    * A constructor from parameters that forms the 3D plane
    * <p>
    * geometric for the plane is the form: ax+by+cz=d
    * <p>
    * 
    * 
    * @param a
    * @param b 
    * @param c
    * @param d
    */
    public Plane3D(double a, double b, double c, double d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    

    /**
    * A getDistance method that returns the distance from a point to the plane
    * <p>set pt as (x0,y0,z0)
    * use d = | ax0 + by0 + cz0 + d |/√(a^2 + b^2 + c^2)
    * <p>
    * The equation used for caluation is included in the references
    * 
    * @param pt the point that used to calculate the distance
    * @return the distance from a point to the plane
    */
    public double getDistance(Point3D pt){

        //set pt as (x0,y0,z0)
        //use d = |ax0 + by0 + cz0 + d |/√(a^2 + b^2 + c^2)

        return Math.abs(a*pt.getX() + b*pt.getY() + c*pt.getZ() + d)/ Math.sqrt(a*a + b*b +c*c);
    }

}
