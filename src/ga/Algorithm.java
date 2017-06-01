package ga;

import java.util.ArrayList;
import java.util.Random;

public class Algorithm {
    
    static double swapCount = 10;
    static double mutationRate = 0.02;
    
    public static Route crossover(Route parentOne, Route parentTwo) {
        
        Random rand = new Random();
        
        int swapIndex = rand.nextInt((int)(parentOne.route.size() - Algorithm.swapCount));
        
        // making a copy of the sublist that is being crossed over
        ArrayList<City> subList = new ArrayList<City>();
        for (int i = swapIndex; i < swapIndex + Algorithm.swapCount; i++) {
            subList.add(parentOne.route.get(i));
        }
        
        // initialize the new child route
        ArrayList<City> child = new ArrayList<City>();
        
        // add cities from parentTwo that arn't in the sublist up until
        // the index i reaches the the swapIndex
        int i = 0;
        while (child.size() < swapIndex) {
            if (!Utilities.isIn(subList, parentTwo.route.get(i)))
                    child.add(parentTwo.route.get(i));
            i++;
        }
       
        // now that the i reached the swapIndex, add the sublist to the child list
        child.addAll(subList);
        
        // add the remaining cities in parentTwo to the child list
        while (child.size() < parentOne.route.size()) {
            if (!Utilities.isIn(subList, parentTwo.route.get(i)))
                child.add(parentTwo.route.get(i));
            i++;
        }
        
        return new Route(child);
    }
    
    public static void mutate(Route child) {
        
        Random rand = new Random();
        
        for (int i = 0; i < child.route.size(); i++) {
            if (Math.random() < Algorithm.mutationRate) {
                int randIndex = rand.nextInt(child.route.size());
                
                City temp = child.route.get(randIndex);
                child.route.set(randIndex, child.route.get(i));
                child.route.set(i, temp);
            }
        }
    }
}
