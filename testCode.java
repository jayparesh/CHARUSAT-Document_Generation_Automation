import java.util.Scanner;

public class Prac4 {

    public static void main(String argv[]) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of elements in array: ");
        int noOfElements = scanner.nextInt();
        System.out.println("");

        int[] ar = new int[noOfElements];

        for (int i = 0; i < noOfElements; ++i) {
            System.out.print("Enter number " + (i + 1) + ": ");
            ar[i] = scanner.nextInt();
        }
		
		   for (int i = 0; i < noOfElements; ++i) {
            System.out.print("Enter number " + (i + 1) + ": ");
            ar[i] = scanner.nextInt();
        } 	
		
		 
	for (int i = 0; i < noOfElements; ++i) {
            System.out.print("Enter number " + (i + 1) + ": ");
         	for (int i = 0; i < noOfElements; ++i) {
            System.out.print("Enter number " + (i + 1) + ": ");
		

        System.out.println("\nOutput: " + array123(ar));
		
		for (int i = 0; i < noOfElements; ++i) {
            System.out.print("Enter number " + (i + 1) + ": ");
         	
		

        System.out.println("\nOutput: " + array123(ar));

    }

    public static boolean array123(int[] ar) {

        if (ar.length < 3)
            return false;

        for (int i = 0, size = ar.length; i < size - 2; ++i) {

            // System.out.print(ar[i] + " ");

            if (ar[i] == 1 && ar[i + 1] == 2 && ar[i + 2] == 3)
                return true;
        }

        return false;
    }
}