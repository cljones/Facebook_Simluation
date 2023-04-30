import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 */

public class Driver {

	public static void main(String[] args) throws CloneNotSupportedException {
		Scanner input = new Scanner(System.in);

		Facebook facebook = new Facebook();

		String name = "";
		String pw = "";
		String hint = "";
		String menu = "";
		final String file = "fbFile";

		// De-Serialization
		try {

			ObjectInputStream deserial = new ObjectInputStream(new FileInputStream(file));
			facebook = (Facebook) deserial.readObject();

		} catch (IOException e) {
			System.err.println("Could not de-serialize file\n");
		} catch (ClassNotFoundException e) {
			System.out.println("Cannot cast the de-serialized file\n");
		}

		do {

			System.out.println(
					"Enter one of the following option: \n 1. List users alphabetically\n 2. List users by number of friends\n 3. Add a user\n"
							+ " 4. Delete a user\n 5. Get password hint\n 6. Add a friend\n 7. Remove a friend\n 8. List friends\n 9. Recommend friends\n"
							+ " 10. Like Something\n 11. List Likes Alphabetically\n 12. Undo\n 13.Quit");
			menu = input.nextLine();
			switch (menu) {
			case "1":
				System.out.println("Here are the users in alphabetical order.");
				facebook.listInOrder();
				break;
			case "2":
				System.out.println("Here are the users listed by number of friends.");
				facebook.listByNumber();
				break;
			case "3":
				// Authentication
				System.out.println("You chose add user");
				System.out.println("Enter username");
				name = input.nextLine();
				System.out.println("Enter password");
				pw = input.nextLine();
				System.out.println("Enter password hint");
				hint = input.nextLine();

				FaceBookUser newFB = new FaceBookUser(name, pw); // New user
																	// created
																	// for the
																	// add
																	// option
				newFB.setPasswordHint(hint);
				facebook.addUser(newFB);

				break;
			case "4":
				// Authentication
				System.out.println("You chose delete user");
				System.out.println("Enter username");
				name = input.nextLine();
				System.out.println("Enter password");
				pw = input.nextLine();
				System.out.println("Enter password hint");
				hint = input.nextLine();

				FaceBookUser delFB = new FaceBookUser(name, pw);
				delFB.setPasswordHint(hint);

				facebook.delUser(delFB); // User is deleted
				break;
			case "5":
				System.out.println("You chose hint");
				System.out.println("Enter username");
				name = input.nextLine();

				facebook.passwordHint(name);

				break;

			case "6":
				// Authentication
				System.out.println("You chose to friend someone");
				System.out.println("Enter username");
				name = input.nextLine();
				System.out.println("Enter password");
				pw = input.nextLine();

				for (FaceBookUser e : facebook.getUsers()) {
					if (e.getUsername().equals(name)) {
						System.out.println("Welcome");

						// Adding the friend
						System.out.println("Enter the name of the user...");
						String userA = input.nextLine();

						for (FaceBookUser a : facebook.getUsers()) {
							if (a.getUsername().equals(userA)) {
								e.friend(a);
								break;
							} // End of inner if-statement
						} // End of inner for-each loop

					} // End of outer if-statement
				} // End of outer for-each loop

				break;

			case "7":
				// Authentication
				System.out.println("You chose to defriend someone");
				System.out.println("Enter username");
				name = input.nextLine();
				System.out.println("Enter password");
				pw = input.nextLine();

				for (FaceBookUser d : facebook.getUsers()) {
					if (d.getUsername().equals(name)) {
						System.out.println("Welcome");

						// Deleting the friend
						System.out.println("Enter the name of the user...");
						String userD = input.nextLine();

						for (FaceBookUser b : facebook.getUsers()) {
							if (b.getUsername().equals(userD)) {
								d.defriend(b);
								break;
							} // End of inner if-statement
						} // End of inner for-each loop

					} // End of outer if-statement
					break;
				} // End of outer for-each loop

				break;

			case "8":
				// Authentication
				System.out.println("You chose to list friends");
				System.out.println("Enter username");
				name = input.nextLine();
				System.out.println("Enter password");
				pw = input.nextLine();

				FaceBookUser userL = new FaceBookUser(name, pw);
				facebook.listFriends(userL);

				break;

			case "9":
				// Authentication
				System.out.println("You chose recommend");
				System.out.println("Enter username");
				name = input.nextLine();
				System.out.println("Enter password");
				pw = input.nextLine();

				for (FaceBookUser f : facebook.getUsers()) {
					if (f.getUsername().equals(name)) {
						if (f.getPassword().equals(pw)) {
							System.out.println("Welcome");

							ArrayList<FaceBookUser> recFriends = facebook.recommend(f);
							for (FaceBookUser c : recFriends) {
								System.out.println(c.toString());
							} // End of inner for-each loop
						} // End of inner if-statement
					} // End of outer if-statement

				} // End of outer for-each loop

				break;

			case "10":
				/*
				 * EXPLANATION: I chose to use a linked hash set for the
				 * facebook user class to store the likes in an insertion order
				 * of strings. For alphabetical operations I took the elements
				 * from the linked hash set and put them in a tree set for them
				 * to be stored alphabetically.
				 * 
				 */
				System.out.println("You chose to like something");
				System.out.println("Enter username");
				name = input.nextLine();
				System.out.println("Enter password");
				pw = input.nextLine();

				for (FaceBookUser f : facebook.getUsers()) {
					if (f.getUsername().equals(name)) {
						if (f.getPassword().equals(pw)) {

							System.out.println("Type something to like");
							String like = input.nextLine();
							// The data structure in action
							f.likeSome(like);
							// LinkedHashSet<String> likes = f.getLikeSet();
							for (String str : f.getLikeSet()) {
								System.out.println(f.getUsername() + " likes " + str);
							} // End of inner for-each loop
						}
					}

				} // End of outer for-each loop
				break;

			case "11":
				System.out.println("You chose to list in alphabetical order");
				System.out.println("Enter username");
				name = input.nextLine();
				System.out.println("Enter password");
				pw = input.nextLine();

				FaceBookUser userAlpha = new FaceBookUser(name, pw);
				TreeSet<String> treeLike = new TreeSet<>();
				for (FaceBookUser usr : facebook.getUsers()) {
					if (usr.getUsername().equals(name)) {
						if (usr.getPassword().equals(pw)) {

							// The data structure for alphabetical order
							System.out.println("Likes sorted in alphabetical order:");
							for (String st : usr.getLikeSet()) {
								treeLike.add(st);
							}
							for (String lk : treeLike)
								System.out.println(userAlpha.getUsername() + " likes " + lk);
							System.out.println();
						}
					}

				} // End of outer loop

				break;
			case "12":
				/*
				 * EXPLAINATION: An enums are used for keeping track of the undo-able actions in a stack specifically used for
				 * the enums in the FaceBookUser class.  Another stack contains copies of FaceBookUsers for reference use for
				 * friending and defriending in the driver class.  The undo option authenticates for username and password and
				 * then undo the users recent action. 
				 * 
				 **/
				
				//Authentication
				System.out.println("Welcome to the undo option");
				System.out.println("Enter username");
				name = input.nextLine();
				System.out.println("Enter password");
				pw = input.nextLine();

				for (FaceBookUser ud : facebook.getUsers()) {
					if (ud.getUsername().equals(name)) {
						if (ud.getPassword().equals(pw)) {
							// Undo portion
							if (!(ud.getUndoStack().isEmpty() && ud.getRefStack().isEmpty())) {
								switch (ud.getUndoStack().peek()) {
								case FRIEND:

									FaceBookUser udFriend = ud.getRefStack().pop();
									ud.defriend(udFriend);
									ud.getUndoStack().pop();
									System.out.println("Add friend is undone");
									break;
								case DEFRIEND:
									FaceBookUser udDEfriend = ud.getRefStack().pop();
									ud.friend(udDEfriend);
									ud.getUndoStack().pop();
									System.out.println("Remove friend is undone");
									break;

								default:
									System.out.println("No other action to do");
									break;
								}// End of switch
							} else {
								System.out.println("No further action to take");
							}

						} // inner if statement

					} // Outer if statement

				} // End of for loop

				break;
			case "13":
				// Serializaion

				try {
					ObjectOutputStream seri = new ObjectOutputStream(new FileOutputStream(file));
					seri.writeObject(facebook);
					seri.close();

					System.out.println("Facebook saved");
				} catch (FileNotFoundException e) {
					System.err.println("Cannot make the file");
				} catch (IOException e) {
					System.err.println("Cannot serialize object");
				}
				break;
			default:
				System.err.println("Not one of the options.\nTry again");
				break;
			}

		} while (!(menu.equals("13")));

	}// End of Main Method

}// End of Main Class
