import java.util.ArrayList;

public class Utilities {

	public static void main(String[] args) {
		// Remove duplicate values
		ArrayList<Integer> intList = new ArrayList<>();
		ArrayList<Integer> intList2 = new ArrayList<>();
		ArrayList<Character> charList = new ArrayList<>();
		ArrayList<String> strList = new ArrayList<>();
		ArrayList<FaceBookUser> faceList = new ArrayList<>();

		FaceBookUser usr1 = new FaceBookUser("Turner", "merc");
		FaceBookUser usr2 = new FaceBookUser("Count Zero", "deck");
		FaceBookUser usr3 = new FaceBookUser("Marly", "art");
		FaceBookUser usr4 = new FaceBookUser("Angie", "biochip");
		FaceBookUser usr5 = new FaceBookUser("Jumper", "burn");
		FaceBookUser usr6 = new FaceBookUser("Jackie", "dancer");

		// Linear Search values
		Integer[] numArray = { 3, 5, 7, 9, 11, 13 };
		Character[] charArray = { 'a', '1', 'd', '8', '3', 's', '4', 'f' };
		String[] strArray = { "cyber", "mech", "matrix", "robot", "laser" };
		FaceBookUser[] fbArray = { usr1, usr2, usr3 };

		/** The algorithm to remove duplicates */

		System.out.println("Remove duplicates");
		// For the integers
		intList.add(9);
		intList.add(7);
		intList.add(6);
		intList.add(6);
		intList.add(9);
		intList.add(19);
		System.out.println("Before remove: " + intList);
		removeDuplicates(intList);
		System.out.println("After remove: " + intList);

		//For empty list
		System.out.println("Before remove: " + intList2);
		removeDuplicates(intList2);
		System.out.println("After remove: " + intList2);
		
		// For characters
		charList.add('c');
		charList.add('i');
		charList.add('s');
		charList.add('e');
		charList.add('g');
		System.out.println("Before remove: " + charList);
		removeDuplicates(charList);
		System.out.println("After remove: " + charList);

		// For Strings
		strList.add("fox");
		strList.add("raven");
		strList.add("scorpion");
		strList.add("deep");
		strList.add("deep");
		strList.add("mayfly");
		System.out.println("Before remove: " + strList);
		removeDuplicates(strList);
		System.out.println("After remove: " + strList);

		// For facebook users
		faceList.add(usr1);
		faceList.add(usr2);
		faceList.add(usr3);
		faceList.add(usr4);
		faceList.add(usr5);
		faceList.add(usr6);
		faceList.add(usr3);
		faceList.add(usr4);
		faceList.add(usr2);
		System.out.println("Before remove: " + faceList);
		removeDuplicates(faceList);
		System.out.println("After remove: " + faceList);

		System.out.println();

		// Linear search
		System.out.println("Linear Search Indeces");
		System.out.println("Index in the integer array " + linearSearch(numArray, 24));
		System.out.println("Index in the integer array " + linearSearch(numArray, 5));
		System.out.println("Index in the character array " + linearSearch(charArray, 's'));
		System.out.println("Index in the character array " + linearSearch(charArray, '8'));
		System.out.println("Index in the string array " + linearSearch(strArray, "matrix"));
		System.out.println("Index in the string array " + linearSearch(strArray, "lane"));
		System.out.println("Index in the facebook user array " + linearSearch(fbArray, usr3));
		System.out.println("Index in the facebook user array " + linearSearch(fbArray, usr5));

	}// Main Method

	/**
	 * Precondition - An arraylist must be an argument for the method to remove duplicates.
	 * Postconidtion - The arraylist is then traversed and duplicate elements are removed.
	 * @return - A list without duplicates is returned.
	 */
	public static <E> ArrayList<E> removeDuplicates(ArrayList<E> list) {

		if (!list.isEmpty()) {
			for (Object e : list.toArray()) {
				if (!(list.indexOf(e) == list.lastIndexOf(e))) {
					list.remove(list.lastIndexOf(e));
				}
			} // End of for-each loop
		} else {
			System.out.println("Nothing in the list.");
		}

		return list;
	}//End of removeDuplicates method

	/**
	 * Precondition - An array with elements need to be examined inorder to return an index.
	 * Postcondition - After the Generic array is used an index of a target element is returned.
	 * @param list - The array to contain the elements.
	 * @param key - The target for the index.
	 * @return - An index of the element in the array is returned.
	 */
	public static <E extends Comparable<E>> int linearSearch(E[] list, E key) {
		for (int i = 0; i < list.length; i++) {
			if (key == list[i]) {
				return i;
			}
		}
		return -1;
	}// End of linearSearch Method
}
