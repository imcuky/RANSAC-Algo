/*
 * Catherine Lee
 */

import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class PointCloud {
    

    private ArrayList<Point3D> pointCloud;
    private final String directoryName = "PointCloud";


    /**
    * A constructor from a xyz file that generate the point cloud from the given xyz file
    * @param filename 
    * 
    */
    public PointCloud(String filename) {
        
        try {
            Point3D pt;
            Scanner sc = new Scanner(new File(".." + File.separator + directoryName + File.separator + filename));
            pointCloud = new ArrayList<>();

            sc.nextLine();//skip the x y z notation line
            while(sc.hasNextLine()){
                pt = toPoint3D(sc.nextLine().trim());
                pointCloud.add(pt);
            }
            sc.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        
    }

    /**
    * An empty constructor that constructs an empty point cloud 
    */
    public PointCloud(){
        pointCloud = new ArrayList<>();
    }


    /**
    * A helper method that helps generate a string format of a 3D point into object Point3D
    * <p>
    * This method can only be access by any method in class PointCloud
    * @param st
    * @return Point3D
    */
   
    private Point3D toPoint3D(String st){
        String[] tempPt = st.split("\\s+");
        
        return new Point3D(Double.parseDouble(tempPt[0]), Double.parseDouble(tempPt[1]), Double.parseDouble(tempPt[2]));
    }
    

    /**
    * A addPoint method that adds a point to the point cloud 
    *
    * @param pt that needs to add it into the point cloud
    *
    */
    public void addPoint(Point3D pt){
        pointCloud.add(pt);
    }



    /**
    * A getPoint method that returns a random point from the cloud
    * @return  a random point from the cloud
    */
    public Point3D getPoint(){
        Random rn = new Random();
        return pointCloud.get(rn.nextInt(pointCloud.size()));
    }


    /**
    * A save method that saves the point cloud into a xyz file
    * <p>
    * The method will create a new file if the given file 
    * does not exits
    * <p>
    * The method will override the file if the given file exits
    * 
    * @param filename a string
    */
    public void save(String filename) {
        final String directoryName = "result";
        try {

            File file = new File(directoryName, filename);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
                
                //delete the current file then regenerate
                if (file.delete()) { 
                    System.out.println("Deleted the file: " + file.getName());
                    if (file.createNewFile()) {
                        System.out.println("File created: " + file.getName());
                    }
                } else {
                    System.out.println("Failed to delete the file.");
                    return;
                } 
                
            }
            BufferedWriter write = new BufferedWriter(new FileWriter(filename));
            write.append("x	y	z");
            
            Iterator<Point3D> it = iterator();
            while (it.hasNext()) {
                write.newLine();
                write.append(it.next().toString());
                
            }
            write.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    

    /**
    * An iterator method that returns an iterator to the points in the cloud
    * @return an iterator
    */
    public Iterator<Point3D> iterator(){
        return pointCloud.iterator();
    }


    /**
    * A method that remove all point from the current point could if it exits in the given point could
    * @param pCloud the point cloud containing 3D points to be removed from this point cloud
    */
    public void removepCloud(PointCloud pCloud){
        pointCloud.removeAll(pCloud.pointCloud);
    }

}
