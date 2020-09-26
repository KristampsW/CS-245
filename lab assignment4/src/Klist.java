import java.util.ArrayList;

public class Klist {
    public static ArrayList<Double> mergeKlists(double[][] output_Arr){
        int k = output_Arr.length;
        int[] index = new int[k];
        ArrayList<Double> result = new ArrayList<>();
        //index[i] = value of pointer of i th list i <0=[0, k-1]
        while (true){
            int minIndex = 0;
            double min = Double.MAX_VALUE;
            for ( int i =0; i < k; i++){
                if(index[i] == -1){
                    continue;
                }
                if(output_Arr[i][index[i]] < min){
                    min = output_Arr[i][index[i]];
                    minIndex = i;
                }

            }
            if(min == Double.MAX_VALUE ){
                break;
            }
            result.add(min);
            if(index[minIndex] == output_Arr[minIndex].length-1){
                index[minIndex] = -1;
            }
            else
                index[minIndex]++;

        }

        return result;
    }
    public static void main(String [] args) {
        double[][] output_Arr = {{1.1, 4, 4, 5, 5}, {1.1, 3.3, 4.4}, {2.2, 6.6}};
        System.out.println(mergeKlists(output_Arr));
    }
}
