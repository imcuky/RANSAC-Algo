
/*
 * Catherine Lee

 */

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class PlaneRANSAC {

    private PointCloud pc;
    private double eps;


    /**
    * A constructor for PlaneRANSAC that takes as input a point cloud
    * <n>
    * The epsilon value has set to -1 as defult, implimenting no epsilon value as asign
    * @param pc the point cloud that use to run the RANSAC
    */
    public PlaneRANSAC(PointCloud pc){
        this.pc = pc;
        eps = -1;
    }


    /**
    * The setter for the epsilon value
    *
    * @param eps the new epsilon value
    */
    public void setEps(double eps){
        this.eps = eps;
    }


    /**
    * The getter for the epsilon value
    *
    * @return eps the epsilon value
    */
    public double getEps(){
        return eps;
    }

    /**
    * a method that returns the estimated number of iterations required to obtain 
    * a certain level of confidence to identify a plane made of a certain percentage of points
    * <p>
    * k= log( 1 - C ) / log( 1- p^3 )
    * <p>
    * Where p is percentage of points that support the dominant plane of the total number of points in the cloud
    * <n>
    * Where c is confidence probability
    * @param double confidence
    * @param double percentageOfPointsOnPlane
    * @return the number of iterations
    */
    public int getNumberOfIterations(double confidence, double percentageOfPointsOnPlane){
        //k= log( 1 - C ) / log( 1- p^3 )
        return (int)(Math.ceil( Math.log10(1-confidence)/Math.log10(1-Math.pow(percentageOfPointsOnPlane, 3)) ));
        
    }

    /**
    * a run method that runs the RANSAC algorithm for identifying the dominant plane of the point cloud (only one plane)
    * <p>
    * this method will also remove the plane points from the point cloud
    *  
    * @param numberOfIterations of running the algorithm
    * @param filename being the xyz file that will contain the points of the dominant plane
    */
    public void run(int numberOfIterations, String filename) {

        int support = 0,temp = 0;
        Point3D pt1, pt2,pt3;
        
        PointCloud resultPlaneCloud = new PointCloud(), tempPointCloud = new PointCloud();

        for (int i = 0; i < numberOfIterations; i++) {
            pt1 = pc.getPoint();
            pt2 = pc.getPoint();
            pt3 = pc.getPoint();

            Plane3D pl = new Plane3D(pt1, pt2, pt3);
            Iterator<Point3D> iterator = pc.iterator();
            while (iterator.hasNext()) {
                Point3D currentPoint = iterator.next();
                if (pl.getDistance(currentPoint) <= eps) {
                    temp++;
                    tempPointCloud.addPoint(currentPoint);
                }
            }
            if (temp > support) {
                support = temp;
                resultPlaneCloud = tempPointCloud;
            }
            temp = 0;
            tempPointCloud = new PointCloud();
        }
        
        resultPlaneCloud.save(filename);

        pc.removepCloud(resultPlaneCloud);
    
    }


    /**
    * The main method that runs the RANSAC algorithm to generate the three most dominant planes in a cloud of 
    * 3D points
    */
    
    public static void main(String[] args) {

        final long startTime = System.nanoTime();

        final String filename = "PointCloud";
        final int numFile = 3;
        final int planes = 3;
        final double eps = 0.05; //the eps value, max distance of a point to the plane that allowed to be the plane support

        for (int i = 1; i <= numFile; i++) {
            PointCloud pointCloud = new PointCloud(filename+ i+".xyz");
            PlaneRANSAC ransacPt = new PlaneRANSAC(pointCloud);
            ransacPt.setEps(eps);
            for (int j = 1; j <= planes; j++) {
                
                int NumberOfIterations = ransacPt.getNumberOfIterations(0.99, 0.10);
                ransacPt.run(NumberOfIterations, filename+ i +"_p" + j +".xyz");
            }

            pointCloud.save(filename+ i +"_p" + 0 +".xyz");
            
        }

        final long duration = System.nanoTime() - startTime;

        long seconds = TimeUnit.NANOSECONDS.toSeconds(duration);

        System.out.println("Runtime: " + seconds + "s");
    }
}
