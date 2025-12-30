package array;

public class TwoDArray {
	public static void main(String[] args) {
		int[][]a= {{1,2,3},{4,5,6},{7,8,9}};
		int[][] arr= new int[3][3];
		arr[0][0]=1;
		arr[1][0]=2;
		arr[2][0]=3;
		arr[0][1]=4;
		arr[1][1]=5;
		arr[2][1]=6;
		arr[0][2]=7;
		arr[1][2]=8;
		arr[2][2]=9;
		
		
		System.out.print(arr[0][0]+" ");
		System.out.print(arr[1][0]+" ");
		System.out.print(arr[2][0]+" ");
		System.out.println();
		System.out.print(arr[0][1]+" ");
		System.out.print(arr[1][1]+" ");
		System.out.print(arr[2][1]+" ");
		System.out.println();
		System.out.print(arr[0][2]+" ");
		System.out.print(arr[1][2]+" ");
		System.out.print(arr[2][2]+" ");
		System.out.println();
		
		System.out.println("without new: ");
		
		System.out.print(a[0][0]+" ");
		System.out.print(a[0][1]+" ");
		System.out.print(a[0][2]+" ");
		System.out.println();
		
		System.out.print(a[1][0]+" ");
		System.out.print(a[1][2]+" ");
		System.out.print(a[1][2]+" ");
		System.out.println();
		
		System.out.print(a[2][0]+" ");
		System.out.print(a[2][1]+" ");
		System.out.print(a[2][2]+" ");
		

	
		System.out.println();

		
}
}