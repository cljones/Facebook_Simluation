import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 */

public class Facebook implements Serializable {
	private class FbCompare implements Comparator<FaceBookUser> {

		@Override
		public int compare(FaceBookUser o1, FaceBookUser o2) {

			return o2.getFriends().size() - o1.getFriends().size();
		}

	}

	private ArrayList<FaceBookUser> users;

	public Facebook() {
		users = new ArrayList<>();
	}

	/**
	 * @return the users
	 */
	public ArrayList<FaceBookUser> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            - the users to set
	 */
	public void setUsers(ArrayList<FaceBookUser> users) {
		this.users = users;
	}

	/**
	 * The list method displays the users
	 */
	public void list() {
		for (FaceBookUser f : users) {
			System.out.println(f);
		}
	}

	/**
	 * The addUser method adds the user to Facebook list
	 * 
	 * @param newUser
	 *            - facebook user object used for authentication
	 */
	public void addUser(FaceBookUser newUser) {

		if (!(users.contains(newUser))) {
			users.add(newUser);
		} else {
			System.out.println("User is already on Facebook");
		}

	}// End of addUser method

	/**
	 * The delUser method deletes a user from the facebook list
	 * 
	 * @param dUser
	 *            - the user object is used for comparison if it exists in the
	 *            list or not
	 */
	public void delUser(FaceBookUser dUser) {
		for (FaceBookUser o : users) {
			if ((o.getUsername().equals(dUser.getUsername()))) {
				if (o.getPassword().equals(dUser.getPassword())) {
					System.out.println("User deleted");
					users.remove(dUser);
					break;
				}
				// End of the outer if statement
			} else {
				System.out.println("User does not exist");
				break;
			} // End of else

		} // End of for each loop
	}// End of delUser method

	/**
	 * The method displays the password hint for the user
	 * 
	 * @param name
	 *            - used for authentication for the hint method
	 */
	public void passwordHint(String name) {
		// boolean result = false;
		for (FaceBookUser fb : users) {
			if ((fb.getUsername().equals(name))) {
				System.out.print("Password hint is ");
				fb.getPasswordHelp();
				break;
			} else {
				System.out.println("No match");
				break;
			}
		}
	}// End of passwordHint method

	/**
	 * The method displays the friends of the facebook user
	 * 
	 * @param usr
	 *            - used for getting the friends
	 */
	public void listFriends(FaceBookUser usr) {
		for (FaceBookUser f : users) {
			if (f.getUsername().equals(usr.getUsername())) {
				System.out.println("Welcome\n");
				ArrayList<FaceBookUser> friends = f.getFriends();
				System.out.print("[");

				for (FaceBookUser e : friends) {
					System.out.print(e.toString() + "  ");
				}

				System.out.println("]");
			}

		} // End of outer for each loop

	}// End of listFriends method

	/**
	 * The method displays the users in alphabetical order.
	 */
	public void listInOrder() {
		for (FaceBookUser f : users) {
			Collections.sort(users);
			System.out.println(users);
			break;
		}
	}//End of listInOrder method

	/**
	 * The users in facebook are displayed based on the number of friends they have.
	 */
	public void listByNumber() {
		for (FaceBookUser f : users) {
			Collections.sort(users, new FbCompare());
			System.out.println(users);
			break;
		}
	}//End of listByNumber method

	/**
	 * A recursive method for returning the friends and their friends according to how many
	 * friends they have.
	 * 
	 * @return - a list of user's friends and their friend's friend's
	 */
	public ArrayList<FaceBookUser> recommend(FaceBookUser userRec) {

		ArrayList<FaceBookUser> rList = new ArrayList<>();
		rList = recommend(userRec, rList); // The recursive call
		Collections.sort(rList,new FbCompare());
		return rList;

	}// End of public recommend method

	private ArrayList<FaceBookUser> recommend(FaceBookUser user, ArrayList<FaceBookUser> list) {
		if (user.getFriends().isEmpty()) {
			return list;
		} else {

			for (FaceBookUser u : user.getFriends()) {
				if (!list.contains(u)) {
					list.add(u);
					recommend(u, list);
					break;
				}
			} // End for-each loop

		} // End else statement for recursive instruction

		return list;
	}// End of private recommend method

}// End of Facebook class
