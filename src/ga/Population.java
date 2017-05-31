package ga;

import java.util.ArrayList;
import java.util.Random;

public class Population {
    
    static int competitors = 10;

    ArrayList<Route> population;
    int populationSize;
    
    Population(int populationSize, boolean initialize) {
        if (initialize) {
            
            ArrayList<Route> temp = new ArrayList<Route>();
            for (int i = 0; i < populationSize; i++) {
                temp.add(this.getRandomRoute());
            }
            
            this.population = temp;
        }
        
        this.populationSize = populationSize;
    }
    
    public Route getRandomRoute() {
        
        Random rand = new Random();
        
        ArrayList<Integer> used = new ArrayList<Integer>();
        ArrayList<City> route = new ArrayList<City>();
        
        int i = 0;
        while (i < TS.cities.size()) {
          
            int randIndex = rand.nextInt(TS.cities.size());
            
            while (Utilities.Used(randIndex, used)) {
                randIndex = rand.nextInt(TS.cities.size());
            }
            
            route.add(TS.cities.get(randIndex));
            used.add(randIndex);
            
            i++;
        }
        
        return new Route(route);
    }
    
    public ArrayList<Route> getCompetitors() {
        Random rand = new Random();
        
        ArrayList<Integer> usedInd = new ArrayList<Integer>();
        ArrayList<Route> competition = new ArrayList<Route>();
        
        for (int i = 0; i < Population.competitors; i++) {
            
            int randIndex = rand.nextInt(Population.competitors);
            while (Utilities.Used(randIndex, usedInd)) {
                randIndex = rand.nextInt(Population.competitors);
            }    
            
            competition.add(this.population.get(randIndex));
            usedInd.add(randIndex);
        }
        
        return competition;
    }
    
    public Route getFittest(ArrayList<Route> competition) {
        Route bestRoute = competition.get(0);
        for (int i = 1; i < competition.size(); i++) {
            if (competition.get(i).getDistance() < bestRoute.getDistance()) {
                bestRoute = competition.get(i);
            }
        }
        
        return bestRoute;
    }
    
    public Population applyEvolution() {
        
        ArrayList<Route> newRoutes = new ArrayList<Route>();
        
        for (int i = 0; i < this.populationSize; i++) {
            Route gladiatorOne = this.getFittest(this.getCompetitors());
            Route gladiatorTwo = this.getFittest(this.getCompetitors());
        
        
            Route child = Algorithm.crossover(gladiatorOne, gladiatorTwo);
            Algorithm.mutate(child);
            
            newRoutes.add(child);
        }
        
        Population pop = new Population(this.populationSize, false);
        pop.population = newRoutes;
        return pop;
    }
}
