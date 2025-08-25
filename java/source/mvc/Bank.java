package cn.edu.seu.cose.javacourse.ch02.atm;

public class Bank {

	private final int NUMBEROFUSERS = 5;
	private User[] users;
	private String bankName;

	public Bank() {
		this.users = new User[NUMBEROFUSERS];
		this.bankName = "Anonymous Bank";
	}

	public Bank(String bankName) {
		this.bankName = bankName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public User[] getUsers() {
		return users;
	}

	/**
	 * @param name
	 *            : the name of the new user;
	 * @param password
	 *            : the password of the new user;
	 * @return the index of the new user, which is in the range of [0,
	 *         users.length-1]; or -1 if the array of users is full; or -2 if
	 *         the name is already existed in users.
	 * 
	 */
	public int createNewUser(String name, String password) {
		if (!existedUserName(name)) {
			for (int i = 0; i < users.length; i++) {
				if (users[i] == null) {
					users[i] = new User(name, password);
					return i;
				} else {
					continue;
				}
			}
			return -1;
		} else {
			return -2;
		}
	}

	/**
	 * @param name
	 * @return true if there is an existed user with the same name, or false
	 *         otherwise;
	 */
	private boolean existedUserName(String name) {
		for (int i = 0; i < users.length; i++) {
			User user = users[i];
			if (user != null && user.getName() != null
					&& user.getName().equals(name))
				return true;
		}
		return false;
	}

	/**
	 * @param name
	 * @param password
	 * @return the user index according to name&password, or -1 if no such user
	 *         or wrong password
	 */
	public int getUser(String name, String password) {
		for (int i = 0; i < users.length; i++) {
			if (users[i] != null) {
				if (users[i].getName().equals(name)
						&& users[i].getPassword().equals(
								password))
					return i;
			}
		}
		return -1;
	}

	/**
	 * @param user_index
	 * @param amount
	 * @return 1 for a successful deposit, -1 for a failed deposit
	 */
	public int deposit(int user_index, int amount) {
		if (users[user_index] != null) {
			users[user_index].setBalance(users[user_index]
					.getBalance()
					+ amount);
			return 1;
		}
		return -1;
	}

	/**
	 * @param user_index
	 * @param amount
	 * @return 1 for a successful withdrawal, -1 for a failed withdrawal
	 */
	public int withdrawal(int user_index, int amount) {
		if (users[user_index] != null
				&& users[user_index].getBalance() >= amount) {
			users[user_index].setBalance(users[user_index]
					.getBalance()
					- amount);
			return 1;
		}
		return -1;
	}

	/**
	 * @param user_index
	 * @return the balance of the user, or -1 if no such user
	 */
	public int query(int user_index) {
		if (users[user_index] != null) {
			return users[user_index].getBalance();
		} else {
			return -1;
		}
	}
}
