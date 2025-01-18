/*
 * Catherine Lee
 */

public class Point3D {

    //instant varible
    private double x,y,z;


    /**
    * A constructor for the 3D points in (x,y,z) form
    *
    * @param x
    * @param y
    * @param z
    */
    public Point3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
    * The getter method for the x coordinate of the point
    * @return the x coordinate
    */
    public double getX(){
        return x;
    }

    /**
    * The getter method for the y coordinate of the point
    * @return the y coordinate
    */
    public double getY(){
        return y;
    }

    /**
    * The getter method for the z coordinate of the point
    * @return the z coordinate
    */
    public double getZ(){
        return z;
    }

    /**
    * a string representation of the 3D point
    * @return the xyz format
    */
    @Override
    public String toString(){
        return String.valueOf(this.x) +" "+ String.valueOf(this.y) +" " + String.valueOf(this.z);
    }
}