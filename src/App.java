import java.util.Calendar;
import java.util.Hashtable;
import java.util.Scanner;

public class App {
    
    private static Hashtable<String, Integer> createRoadMap(){
        Hashtable<String, Integer> cities = new Hashtable<String, Integer>();
        
        cities.put("New York City", 0); 
        cities.put("Washington, D.C.", 1);
        cities.put("Miami", 2);
        cities.put("Milwaukee", 3);
        cities.put("Detroit", 4);
        cities.put("Kansas", 5);
        cities.put("Oklahoma City", 6);
        cities.put("Atlanta", 7);
        cities.put("Austin", 8);
        cities.put("Chicago", 9);
        cities.put("Denver", 10);
        cities.put("Dallas", 11);
        cities.put("Seattle", 12);
        cities.put("Salt Lake", 13);
        cities.put("Las Vegas", 14);
        cities.put("Phoenix", 15);
        cities.put("Portland", 16);
        cities.put("Los Angeles", 17);
        return cities;
    }
    
    private static Hashtable<String, String> getRoadNames(){
        Hashtable<String, String> routes = new Hashtable<String, String>();
        
        routes.put("0-1", "Wall St"); routes.put("0-4", "8 Mile St");
        routes.put("1-0", "Wall St"); routes.put("1-2", "Main St"); routes.put("1-5", "Ridge St");
        routes.put("2-1", "Main St"); routes.put("2-7", "Shore St"); routes.put("2-8", "Navajo St");
        routes.put("3-4", "Hill St"); routes.put("3-9", "Deer St");
        routes.put("4-0", "8 Mile St"); routes.put("4-3", "Hill St"); routes.put("4-5", "County St"); routes.put("4-9", "Jordan St");
        routes.put("5-1", "Ridge St"); routes.put("5-4", "County St"); routes.put("5-6", "Redwood St"); routes.put("5-10", "Canyon St");
        routes.put("6-5", "Redwood St"); routes.put("6-7", "1st St"); routes.put("6-11", "Sunset St");
        routes.put("7-2", "Shore St"); routes.put("7-6", "1st St");
        routes.put("8-2", "Navajo St"); routes.put("8-15", "Route 66");
        routes.put("9-3", "Deer St"); routes.put("9-4", "Jordan St"); routes.put("9-10", "Lincoln St"); routes.put("9-12", "Bull St"); routes.put("9-13", "Aspen St");
        routes.put("10-5", "Canyon St"); routes.put("10-9", "Lincoln St"); routes.put("10-13", "Oak St");
        routes.put("11-6", "Sunset St"); routes.put("11-14", "Spruce St"); routes.put("11-10", "Pine St");
        routes.put("12-9", "Bull St"); routes.put("12-13", "Bay St"); routes.put("12-16", "Sonic St");
        routes.put("13-9", "Aspen St"); routes.put("13-10", "Oak St"); routes.put("13-12", "Bay St"); routes.put("13-14", "Birch St"); routes.put("13-16", "Valley St");
        routes.put("14-11", "Spruce St"); routes.put("14-13", "Birch St"); routes.put("14-16", "Park St");
        routes.put("15-8", "Route 66"); routes.put("15-17", "Lakers St");
        routes.put("16-12", "Sonic St"); routes.put("16-13", "Valley St"); routes.put("16-14", "Park St"); routes.put("16-17", "Hollywood St");
        routes.put("17-15", "Lakers St"); routes.put("17-16", "Hollywood St");
        
        return routes;
    }
    
    
    public static int[] timeAndEta(int time,int o, int m){
        
        int[] finalTime = new int[4];
        int hour = time/60;
        int min  = time % 60;
        int hourETA = o + (((m + min) / 60) + hour) ;
        if(hourETA>=24)
            hourETA = hourETA %24;
        int minETA = (m + min) % 60;
        
        finalTime[0]=hour;
        finalTime[1]=min;
        finalTime[2]=hourETA;
        finalTime[3]=minETA;
        return finalTime;
    };
    

    public static void main(String[] args) {
        
        Hashtable<String, Integer> cities = createRoadMap();
        Hashtable<String, String> routes = getRoadNames();
        Scanner input = new Scanner(System.in);
        
        String[] options = {"Atlanta","Austin","Chicago","Dallas","Denver","Detroit","Kansas","Las Vegas","Los Angeles",
                            "Miami","Milwaukee","New York City","Oklahoma City","Phoenix","Portland","Salt Lake","Seattle","Washington, D.C."};

        System.out.println("Welcome to the United States of America!");

        for (String option : options) {
            System.out.println(option);
        }

        int ora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minuta = Calendar.getInstance().get(Calendar.MINUTE);

        System.out.println("Shenoni lokacionin fillestar:");
        String location = input.nextLine();

        System.out.println("Shenoni destinacionin e deshiruar:");
        String destination = input.nextLine();

        System.out.println("Koha aktuale: " + ora + ":" + minuta);

        if(location.equals(destination)){
            System.out.println("Ju aktualisht gjendeni ne destinacionin e deshiruar!");
        }

        In in = new In("grafi.txt");
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(in,ora);
        
        DijkstraSP sp = new DijkstraSP(G, cities.get(location),cities.get(destination),(ora*60+minuta));
        
        int time[] = timeAndEta((int)sp.distTo(cities.get(destination)),ora,minuta);
        
        String result = "";
        int count = 1;

        if(sp.hasPathTo(cities.get(destination)))
        {
            for (DirectedEdge x: sp.pathTo(cities.get(destination))){
                result +=( count + ". " + routes.get(String.valueOf(x.from()) + "-" + String.valueOf(x.to())));
                result += ("\n");
                count++;
            }
            String min = ((time[3]<10) ? ("0"+time[3]):(""+time[3]));
            String hour = ((time[2]<10) ? ("0"+time[2]):(""+time[2]));
            result += ("Kohezgjatja: " + ((time[0]>0) ? (time[0]+" ore e "):(""))+ time[1] +" minuta" + "\nETA: " + hour  + ":" + min);
        }

        else{
            result += ("Nuk ekziston rruge");
        }
        
        //JOptionPane.showMessageDialog(null, "Rruga prej "+location.getSelectedItem()+ " tek " + destination.getSelectedItem() + " eshte \n" + result, "Shortest route", JOptionPane.INFORMATION_MESSAGE);

        System.out.println("Rruga prej " + location + " tek " + destination + " eshte \n" + result);

    }
}
