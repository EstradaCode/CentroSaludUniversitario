import Utils.CSVReader;

import java.net.URL;

public class testCSV {


    public static void main(String[] args) {
        URL resource = testCSV.class.getClassLoader().getResource("form-1__asis-unlp-villa-arguello.csv");
        URL resource2 = testCSV.class.getClassLoader().getResource("branch-1.csv");
        if ((resource != null) && (resource2 != null)) {
            String mainPath = resource.getPath();
            String secondaryPath = resource2.getPath();
            resource = null;
            resource2 = null;
            CSVReader.runCSVReader(mainPath, secondaryPath);
        }
    }
}
