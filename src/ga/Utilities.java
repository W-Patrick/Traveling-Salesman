package ga;

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Utilities {

    public static boolean Used(int index, ArrayList<Integer> used) {   
        for (int number : used) {
            if (index == number) {
                return true;
            }
        }     
        return false;
    }
    
    public static boolean isIn(ArrayList<City> list, City city) {
        for (City c : list) {
            if (c.equals(city)) {
                return true;
            }
        }
        return false;
    }
    
    public static void outputRouteData(Route route, String filename) {
        try {
            PrintWriter output = new PrintWriter(new File(filename));
            String data = "x_values,y_values\n";
            
            for (City city : route.route) {
                data += city.x + "," + city.y + "\n";
            }
            
            output.write(data);
            output.close();
            System.out.println("Exported route to file: " + filename);
            
        } catch (FileNotFoundException e) {
            System.out.println("Could not export data to file: " + filename);
        }
    }
}
