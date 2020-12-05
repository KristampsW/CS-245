import java.io.*;
import java.util.*;

public class RoadTrip {
    ArrayList<Routes> routes=new ArrayList<Routes>(522);
    HashMap<String,String> attraction=new HashMap<String, String>(145);
    HashMap<String,Boolean> visited=new HashMap<String, Boolean>(522);
    HashMap<String,Integer> miles=new HashMap<String,Integer>(522);
    HashMap<String,String> travelled=new HashMap<String, String>(522);
    ArrayList<String> cities=new ArrayList<String>(1000);
    Stack<String> stack=new Stack<String>();
    int mile=0;
    ArrayList<Integer> check=new ArrayList<Integer>(522);

    public class Routes {
        String start_location;
        String end_location;
        int miles;
        int time;

        public Routes(String start_location,String end_location,int miles, int time){
            this.start_location = start_location;
            this.end_location = end_location;
            this.miles = miles;
            this.time = time;
            HashTable
        }
    }
    public void FileInput(String filename1, String filename2) throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filename1));
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(",");
                Routes r = new Routes(temp[0], temp[1], Integer.parseInt(temp[2]), Integer.parseInt(temp[3]));
                routes.add(r);
            }
            reader = new BufferedReader(new FileReader(filename2));
            while ((line = reader.readLine()) != null) {
                String[] temp = line.split(",");
                attraction.put(temp[0], temp[1]);
            }
            reader.close();
    }
    public List<String> route(String starting_city, String ending_city, List<String> attractions){
        for(int i=0;i<attractions.size();i++){
            attractions.set(i,attractions.get(i));
            if(attraction.get(attractions.get(i)).equals(starting_city)||attraction.get(attractions.get(i)).equals(ending_city))
                attractions.remove(i);
        }
        updateMaps(starting_city);
        String temp=starting_city;
        while(!(attractions.isEmpty())){
            int t=miles.get(attraction.get(attractions.get(0)));
            int index=0;
            for(int i=0;i<attractions.size();i++){
                if(t>miles.get(attraction.get(attractions.get(i)))){
                    index=i;
                    t=miles.get(attraction.get(attractions.get(i)));
                }
            }
            test(temp,travelled.get(attraction.get(attractions.get(index))),attraction.get(attractions.get(index)));
            temp=attraction.get(attractions.get(index));
            visited=new HashMap<String, Boolean>(522);;
            miles=new HashMap<String,Integer>(522);;
            travelled=new HashMap<String, String>(522);
            check=new ArrayList<Integer>(522);
            updateMaps(temp);
            attractions.remove(index);
        }
        test(temp,ending_city,ending_city);

        return cities;
    }
    public void updateMaps(String start_location) {
        int w=0;
        stack.add(start_location);
        miles.put(start_location, 0);
        travelled.put(null, start_location);
        while (!(stack.isEmpty())) {
            String temp = stack.pop();
            visited.put(temp, false);
            for (int i = 0; i < routes.size(); i++) {
                if (routes.get(i).start_location.equals(temp) || routes.get(i).end_location.equals(temp)) {
                    if (routes.get(i).start_location.equals(temp)&&!(check.contains(i))) {
                        if (visited.get(routes.get(i).end_location) == null && !(stack.contains(routes.get(i).end_location))) {
                            w++;
                            stack.add(routes.get(i).end_location);
                            travelled.put(routes.get(i).end_location, temp);
                            miles.put(routes.get(i).end_location, routes.get(i).miles + miles.get(temp));
                        }
                        else if (miles.get(temp)+routes.get(i).miles<miles.get(routes.get(i).end_location)) {
                            travelled.put(routes.get(i).end_location, temp);
                            miles.put(routes.get(i).end_location, routes.get(i).miles + miles.get(temp));
                        }
                        check.add(i);
                    }
                    else if (routes.get(i).end_location.equals(temp)&&!(check.contains(i))) {
                        if (visited.get(routes.get(i).start_location) == null && !(stack.contains(routes.get(i).start_location))) {
                            w++;
                            stack.add(routes.get(i).start_location);
                            travelled.put(routes.get(i).start_location, temp);
                            miles.put(routes.get(i).start_location, routes.get(i).miles + miles.get(temp));
                        }
                        else if (miles.get(temp)+routes.get(i).miles<miles.get(routes.get(i).start_location)) {
                            travelled.put(routes.get(i).start_location, temp);
                            miles.put(routes.get(i).start_location, routes.get(i).miles + miles.get(temp));
                        }
                        check.add(i);
                    }
                }
            }
            String[] arr=new String[1000];
            int counter=0;
            while(!(stack.isEmpty())){
                arr[counter]=stack.pop();
                counter++;
            }
            for(int i=0;i<counter;i++){
                boolean truth=false;
                int m=miles.get(arr[i]);
                int index=i;
                for(int j=i;j<counter;j++){
                    if(m<miles.get(arr[j])){
                        m=miles.get(arr[j]);
                        index=j;
                        truth=true;
                    }
                }
                if(truth==true){
                    String t=arr[i];
                    arr[i]=arr[index];
                    arr[index]=t;
                }
            }
            for(int i=0;i<counter;i++)
                stack.add(arr[i]);
            w=0;
        }
    }
    public void test(String start_location, String end_localtion, String mid_point){
        updateMaps(start_location);
        ArrayList<String> temp=new ArrayList<>();
        temp.add(end_localtion);
        String t=end_localtion;
        mile+=miles.get(mid_point);
        while(travelled.get(t)!=null){
            t=travelled.get(t);
            temp.add(t);
        }
        for(int i=0;i<temp.size();i++){
            cities.add(temp.get(temp.size()-i-1));
        }

    }
    public static void main(String[] args) throws IOException {

        RoadTrip map=new RoadTrip();
        ArrayList<String> att=new ArrayList<>();

        att.add("Las Vegas Strip");
        map.FileInput("roads.csv","attractions.csv");
        System.out.println("Route in order: ");

        System.out.println(map.route("San Francisco CA","New York NY",att));
        System.out.println("Miles traveled: "+map.mile);
    }
}