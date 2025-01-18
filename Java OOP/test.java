
public class test {
    public static void main(String[] args) {
        final String filename = "PointCloud";
        PointCloud pointCloud = new PointCloud(filename+ "1"+".xyz");
        pointCloud.save("TEST");
    }
}
