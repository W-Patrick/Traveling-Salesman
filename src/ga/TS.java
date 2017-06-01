package ga;

import java.util.ArrayList;
import java.util.Random;

public class TS {

    public final static int populationSize = 500;
    public final static int numberOfCities = 20;
    
    public final static int gridWidth = 200;
    public final static int gridHeight = 200;
    
    public final static int generationLimit = 400;
    
    public final static ArrayList<City> cities = TS.generateRandomCities(TS.numberOfCities);
    
    public static ArrayList<City> generateRandomCities(int howMany) {
        
        Random rand = new Random();
        
        ArrayList<City> cities = new ArrayList<City>();
        for (int i = 0; i < howMany; i++) {
            int randx = rand.nextInt(TS.gridWidth);
            int randy = rand.nextInt(TS.gridHeight);
            
            cities.add(new City(randx, randy));
        }
        
        return cities;
    }
    
    public static void main(String[] argv) {
        
        Population pop = new Population(TS.populationSize, true);    
        double bestDistance = pop.getFittest(pop.population).getDistance();
        
        System.out.println("Initial Best Distance: " + bestDistance);   
        Utilities.outputRouteData(pop.getFittest(pop.population), "InitialRoute.csv");
        
        System.out.print("\n");
        
        int generation = 0;
        while (generation < TS.generationLimit) {
            pop = pop.applyEvolution();
            bestDistance = pop.getFittest(pop.population).getDistance();
            generation++;
        }
        
        System.out.println("After " + generation + " generations, the best distance is: " + bestDistance);
        Utilities.outputRouteData(pop.getFittest(pop.population), "FinalRoute.csv");
    }
}
