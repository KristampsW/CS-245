public class Solution {
    public static int deletct(String[] A){
        int ans = 0;
        for (int i = 0; i <A[0].length(); i++ ){
            for(int n = 0; n < A.length - 1; n++){
                if(A[n].charAt(i) > A[n+1].charAt(i)){
                    ans ++;
                    break;
                }
            }
        } return ans;
    }
    public static void main(String[] args){
        String [] A = {"cba","daf","ghi"};
        int solu = deletct(A);
        System.out.println(solu);
    }
}
