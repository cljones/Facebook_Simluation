import java.util.Arrays;

public class Sorting {

	public static void main(String[] args) {
		// Insertion Sort
		System.out.println("Insertion for Integers");
		Integer[] intArray = { 30, 45, 65, 28, 49, 20, 16, 19, 4 };
		shuffle(intArray);
		System.out.println(Arrays.toString(intArray));
		insertionSort(intArray);

		System.out.println("Insertion for Strings");
		String[] strArray = { "DarkWeb", "DeepWeb", "Runner", "Fixer", "Cyberpunk", "Hacker" };
		shuffle(strArray);
		System.out.println(Arrays.toString(strArray));
		insertionSort(strArray);

		// Facebook users being instantiated
		FaceBookUser usr1 = new FaceBookUser("DeadMau5", "Arguru");
		FaceBookUser usr2 = new FaceBookUser("Skrillex", "Bangarang");
		FaceBookUser usr3 = new FaceBookUser("RoniSize", "Power");
		FaceBookUser usr4 = new FaceBookUser("CongoNatty", "Junglist");
		FaceBookUser usr5 = new FaceBookUser("DJKrush", "TripHop");

		FaceBookUser[] faceArray = { usr3, usr2, usr1, usr5, usr4 };
		shuffle(faceArray);
		System.out.println(Arrays.toString(faceArray));
		insertionSort(faceArray);

		// Quick Sorts
		System.out.println("Quick Sort for Integers");
		Integer[] intArray2 = { 2, 3, 2, 5, 6, 1,-2 };
		shuffle(intArray2);
		System.out.println(Arrays.toString(intArray2));
		quickSort(intArray2);
		System.out.println();
		
		System.out.println("Quick Sort for Strings");
		String[] strArray2 = { "Memory","Chips","Code","Zettabytes","Program","Implants" };
		shuffle(strArray2);
		System.out.println(Arrays.toString(strArray2));
		quickSort(strArray2);
		System.out.println();

		System.out.println("Quick Sort for Facebook Users");
		FaceBookUser[] faceArray2 = { usr1,usr3,usr2,usr4,usr5};
		shuffle(faceArray2);
		System.out.println(Arrays.toString(faceArray2));
		quickSort(faceArray2);
		System.out.println();
		
	}// End of Main Method

	public static <E extends Comparable<E>> void insertionSort(E[] array) {

		for (int i = 1; i < array.length; i++) {
			System.out.println("Insertion Pass " + i);
			for (int j = i; j > 0 && array[j].compareTo(array[j - 1]) < 0; j--) {
				System.out.println("\t" + Arrays.toString(array) + " " + array[j - 1] + " is compared to " + array[j]);
				
				System.out.println(" Swapping elements...");
				System.out.println();

				// Swapping algorithm
				E temp = array[j];
				array[j] = array[j - 1];
				array[j - 1] = temp;
				System.out.println("\t" + "New order " + Arrays.toString(array) + "\n");

			} // End of inner for loop

		} // End of outer for loop
		System.out.println("Completed Insertion Sort: " + Arrays.toString(array) + "\n");
	}// End of insertion method

	public static <E extends Comparable<E>> void quickSort(E[] quickList) {

		quickSort(quickList, 0, quickList.length - 1);
	}

	public static <E extends Comparable<E>> void quickSort(E[] quickList, int first, int last) {

		if (last > first) {
			int pivotIndex = partition(quickList, first, last);
			quickSort(quickList, first, pivotIndex - 1);
			quickSort(quickList, pivotIndex + 1, last);
		}

	}// End of second quick sort method

	public static <E extends Comparable<E>> int partition(E[] quickList, int first, int last) {
		E pivot = quickList[first + 1];
		int low = first;
		int high = last;

		if (quickList.length < 3) {
			pivot = quickList[first];
			low = first + 1;
			high = last;

		}
		while (high > low) {
			// Search from left
			while (low <= high && quickList[low].compareTo(pivot) <= 0)
				low++;
			// Search from right
			while (low <= high && quickList[high].compareTo(pivot) > 0)
				high--;

			// Swap elements
			System.out.println(
					"\t" + "Pivot is " + pivot + ".  " + quickList[first] + " is compared to " + quickList[last]);
			System.out.println(Arrays.toString(quickList));

			if (high > low) {
				E temp = quickList[high];
				quickList[high] = quickList[low];
				quickList[low] = temp;
			}
		} // End of while loop

		while (high > first && (quickList[high].compareTo(pivot) >= 0)) {
			high--;
		}

		// Swap pivot
		if (quickList[high].compareTo(pivot) > 0) {
			quickList[first + 1] = quickList[high];
			quickList[high] = pivot;
			return high;
		} else {
			return first;
		}

	}// End of partition method

	public static <E extends Comparable<E>> void shuffle(E[] array) {
		for (int i = 0; i < array.length; i++) {
			int index = (int) (Math.random() * array.length);
			E tmpVAR = (E) array[i];
			array[i] = array[index];
			array[index] = tmpVAR;
		}
	}// End of shuffle method
}// End of Class
