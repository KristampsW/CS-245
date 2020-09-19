import java.util.Scanner;
class two{

	private static int [] twosum(int [] nums, int target){
		for(int i = 0; i <nums.length ; i++){
			for(int j = 0; j <nums.length ; j++){
				if((nums[i] + nums[j] == target) && nums[i]!= nums[j] ){
					return new int[] {i,j};
				}
			}
		}
		return new int[] {};
	}

	public static void main(String[] args){
		Scanner input = new Scanner (System.in);
		System.out.print("Enter a array size:");
		int n = input.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++ ){
			System.out.print("Enter a nums" + (i+1) +":");
			nums[i] = input.nextInt();

		}
		System.out.print("Enter a target：");
		int target = input.nextInt();
		//input.close();
		int [] solution = twosum(nums, target);
		if(solution.length == 2 ){
			System.out.println("["+solution[0] + "]" + ", [" + solution[1] +"]");
		}else{
			System.out.println("No solution found!");
		}		
	}
}