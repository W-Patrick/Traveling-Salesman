package ga;

import java.util.ArrayList;

public class Route {
    ArrayList<City> route;
    
    Route(ArrayList<City> route) {
        this.route = route;
    }
    
    public double getDistance() {
        double totalDistance = 0;
        
        // iterate through the route, skip the last city since that
        // is the last city that will be visited
        for (int i = 0; i < this.route.size() - 1; i++) {      
            City from = this.route.get(i);
            City to = this.route.get(i + 1);
            
            double aSquared = (to.x - from.x) * (to.x - from.x);
            double bSquared = (to.y - from.y) * (to.y - from.y);
            
            double distance = Math.sqrt(aSquared + bSquared);
            
            totalDistance = totalDistance + distance;
        }
        return totalDistance;
    }
} 
