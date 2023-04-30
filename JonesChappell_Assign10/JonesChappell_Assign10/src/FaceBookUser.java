import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Stack;

/**
 * 
 */

/**
 * @author jones
 *
 */
public class FaceBookUser extends UserAccount implements Comparable<FaceBookUser>, Serializable {
	private String passwordHint;
	private ArrayList<FaceBookUser> friends;
	private LinkedHashSet<String> likeSet;
	private Stack<UndoActions> undoStack;
	private Stack<FaceBookUser> refStack;

	public FaceBookUser(String username, String password) {
		super(username, password);

		friends = new ArrayList<>();
		likeSet = new LinkedHashSet<>();
		undoStack = new Stack<>();
		refStack = new Stack<>();
	}

	/**
	 * @return the undoStack
	 */
	public Stack<UndoActions> getUndoStack() {
		return undoStack;
	}

	/**
	 * @param undoStack
	 *            the undoStack to set
	 */
	public void setUndoStack(Stack<UndoActions> undoStack) {
		this.undoStack = undoStack;
	}

	/**
	 * @return the refStack
	 */
	public Stack<FaceBookUser> getRefStack() {
		return refStack;
	}

	/**
	 * @param refStack
	 *            the refStack to set
	 */
	public void setRefStack(Stack<FaceBookUser> refStack) {
		this.refStack = refStack;
	}

	/**
	 * @param passwordHint
	 *            the passwordHint to set
	 */
	public void setPasswordHint(String hint) {
		this.passwordHint = hint;
	}

	@Override
	public void getPasswordHelp() {
		System.out.println(passwordHint);

	}

	/**
	 * @return the likeSet
	 */
	public LinkedHashSet<String> getLikeSet() {
		return likeSet;
	}

	/**
	 * @param likeSet
	 *            the likeSet to set
	 */
	public void setLikeSet(LinkedHashSet<String> likeSet) {
		this.likeSet = likeSet;
	}

	public void likeSome(String something) {
		likeSet.add(something);
	}

	/**
	 * The friend method adds a new friend to a user's list
	 * 
	 * @param newFriend
	 *            - the user to be add to friends list
	 * @throws CloneNotSupportedException
	 */
	public void friend(FaceBookUser newFriend) throws CloneNotSupportedException {
		FaceBookUser copied = (FaceBookUser) newFriend.clone();
		if (this.friends.contains(newFriend)) {
			System.out.println("This person is already a friend");
		} else {

			this.friends.add(newFriend);
			this.undoStack.push(UndoActions.FRIEND);
			this.refStack.push(copied);
		}

	}// End of friend method

	/**
	 * The defriend method removes the selected friend from list
	 * 
	 * @param formerFriend
	 *            - the user to be removed from friends list
	 * @throws CloneNotSupportedException
	 */
	public void defriend(FaceBookUser formerFriend) throws CloneNotSupportedException {
		FaceBookUser copiedDE = (FaceBookUser) formerFriend.clone();
		if (this.friends.contains(formerFriend)) {

			this.friends.remove(formerFriend);
			this.undoStack.push(UndoActions.DEFRIEND);
			this.refStack.push(copiedDE);
		} else {
			System.out.println("User is not a friend");
		}
	}// End of defriend method

	/**
	 * The method returns friends
	 * 
	 * @return - list of friends is returned
	 */
	public ArrayList<FaceBookUser> getFriends() {

		ArrayList<FaceBookUser> newList = new ArrayList<>();
		for (FaceBookUser f : this.friends) {
			newList.add(f);
			Collections.sort(newList);
		}
		return newList;
	}

	public Object clone() throws CloneNotSupportedException {

		FaceBookUser fClone = new FaceBookUser(this.getUsername(), this.getPassword());
		// Fresh copy of the twitter followers
		for (FaceBookUser f : this.getFriends()) {
			fClone.friend(f);
			;

		}
		return fClone;

	}

	@Override
	public int compareTo(FaceBookUser o) {
		if (this.getUsername().compareToIgnoreCase(o.getUsername()) != 0) {
			return this.getUsername().compareToIgnoreCase(o.getUsername());
		}

		return 0;
	}
}
