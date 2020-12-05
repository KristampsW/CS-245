import java.util.ArrayList;
import java.util.Random;

public class Tower {
    int [][] board;
    int rows;
    int cols;
    int filled;
    public Tower(int tubes, int balls, int filled){
        board=new int[tubes][balls];
        this.rows = tubes;
        this.cols = balls;
        this.filled = filled;
    }

    /**
     * shows the solve
     */
    public void solve(){
        int oneCount=0;
        int zeroCount=0;
        int gameCheck;
        int count=0;
        System.out.println("Moves:");
        while(count<filled-1) {
            gameCheck = 0;
            for (int i = count; i < count+2; i++) {
                for (int j = 0; j < rows; j++) {
                    int m = board[rows - j - 1][cols - gameCheck - 1];
                    if (board[j][i] == 0)
                        zeroCount++;
                    else if (board[j][i] == 1)
                        oneCount++;
                    board[rows - j - 1][cols - gameCheck - 1] = board[j][i];
                    System.out.println(" t"+ i +" - t"+(cols-gameCheck-1));
                    board[j][i] = m;
                }
                gameCheck++;
            }
            if (zeroCount >= oneCount) {
                MoveStep();
            }
            count++;
            zeroCount=0;
            oneCount=0;
        }
        System.out.println();
    }
    public void MoveStep(){
        int index = 0;
        int x = 0;
        int y = 0;
        for (int k = cols - 2; k < cols; k++) {
            for (int l = 0; l < rows; l++) {
                if (!isCol(index)) {
                    if (board[l][k] == 0) {
                        board[rows - x - 1][index] = board[l][k];
                        System.out.println(" t "+ k + " - t " + (index));
                        x++;
                        board[l][k] = -1;
                    }
                    else {
                        board[rows - y - 1][index + 1] = board[l][k];
                        System.out.println(" t " + k + " - t " + (index+1));
                        y++;
                        board[l][k] = -1;
                    }
                }
                else {
                    board[rows - y - 1][index + 1] = board[l][k];
                    System.out.println(" t " + k + " - t " + (index + 1));
                    y++;
                    board[l][k] = -1;
                }
            }
        }
    }
    public boolean isCol(int column){
        int tempVal=board[0][column];
        boolean flag=true;
        for(int i=0;i<rows;i++){
            if(board[i][column]==tempVal && tempVal!=-1){
                continue;
            }
            else{
                flag=false;
                break;
            }
        }
        return flag;
    }

    public void RandomBoard(){
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                board[i][j]=-1;
            }
        }
        ArrayList<Integer> tempList=new ArrayList<>(filled*rows);
        int x=0;
        for(int i=0;i<filled;i++){
            for(int j=0;j<rows;j++){
                tempList.add(x);
            }
            if(x==0)
                x++;
            else
                x--;
        }
        int k=0;
        while(!(tempList.isEmpty())){
            for(int j=0;j<filled;j++){
                int ran=new Random().nextInt(tempList.size());
                board[k][j]=tempList.get(ran);
                tempList.remove(ran);
            }
            k++;
        }
    }
    public void print(){
        System.out.println("-- - - - - - ");
        for(int i=0;i<rows;i++){
            System.out.print("t"+i + " ");
            for(int j=0;j<cols;j++){
                if(board[i][j] != -1) {
                    System.out.print(board[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("-- - - - - -");
        System.out.println("   0 1 2 3 4");
    }
    public static void main(String[] args){
        Tower tower=new Tower(5,4,3);
        tower.RandomBoard();
        System.out.println("Random board:");
        tower.print();
        System.out.println();
        tower.solve();
        System.out.println("Solved board: ");
        tower.print();
        System.out.println("Default input parameter: 5,4,3");
        }
    }