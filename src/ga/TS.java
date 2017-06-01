package ga;

import java.util.ArrayList;
import java.util.Random;

public class TS {

    public final static int populationSize = 500;
    public final static int numberOfCities = 20;
    
    public final static int gridWidth = 200;
    public final static int gridHeight = 200;
    
    public final static int convergenceDetection = 5;
    
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
        
        int convergenceCounter = 0;
        int generation = 0;
        while (convergenceCounter < TS.convergenceDetection) {
            generation++;
            
            pop = pop.applyEvolution();
            
            if (Math.abs(pop.getFittest(pop.population).getDistance() - bestDistance) < 0.00001)
                convergenceCounter++;
            else
                convergenceCounter = 0;
            
            bestDistance = pop.getFittest(pop.population).getDistance();
            
            if (generation % 5 == 0)
                System.out.println("Generation " + generation + ": " + bestDistance);
        }
        
        System.out.println("\nAfter " + generation + " generations, the best distance is: " + bestDistance);
        Utilities.outputRouteData(pop.getFittest(pop.population), "FinalRoute.csv");
    }
}
