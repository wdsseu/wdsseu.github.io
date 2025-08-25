package cn.edu.seu.cose.javacourse.ch02.atm;

import java.util.Scanner;

public class ATM {

	private Bank bank;
	private Scanner sc;
	private int currentUserIndex;

	public ATM() {
		bank = new Bank();
		sc = new Scanner(System.in);
		currentUserIndex = -1;
	}

	public ATM(String bankName) {
		bank = new Bank(bankName);
	}

	public Bank getBank() {
		return bank;
	}

	public void userMenu() {
		System.out
				.println("Welcome, please sign up or log in: ");
		System.out.println("1. sign up;");
		System.out.println("2. log in;");
		System.out.println("3. exit;");
	}

	public void nameMenu() {
		System.out.println("Please input your name: ");
	}

	public void passwordMenu() {
		System.out.println("Please input your password: ");
	}

	public void transactionMenu() {
		System.out.println("Welcome "
				+ (bank.getUsers())[currentUserIndex]
						.getName()
				+ ", please select your transaction: ");
		System.out.println("1. deposit");
		System.out.println("2. withdrawal");
		System.out.println("3. query");
		System.out.println("4. log out");
	}

	public void amountForDepositMenu() {
		System.out
				.println("Please input the amount to deposit:");
	}

	public void amountForWithdrawalMenu() {
		System.out
				.println("Please input the amount to withdrawal:");
	}

	public void showMenu() {
		int selection = 0;
		int amount = 0;
		int result = -1;
		String name = null;
		String password = null;
		while (true) {
			userMenu();
			selection = sc.nextInt();
			switch (selection) {
			case 3:
				System.out.println("Bye!");
				return;
			case 1:
				nameMenu();
				name = sc.next();
				passwordMenu();
				password = sc.next();
				result = bank.createNewUser(name, password);
				if (result == -1) {
					System.out
							.println("Sorry, no new user is allowed. Gonna quit now.");
					return;
				} else if (result == -2) {
					System.out
							.println("Sorry, this name is occupied, please use another name.");
					continue;
				} else {
					currentUserIndex = result;
					System.out
							.println("Welcome, "
									+ (bank.getUsers())[currentUserIndex]
											.getName()
									+ "!");
					break;
				}
			case 2:
				while (true) {
					nameMenu();
					name = sc.next();
					passwordMenu();
					password = sc.next();
					result = bank.getUser(name, password);
					if (result == -1) {
						System.out
								.println("Sorry, wrong name or wrong password. Please input again.");
						continue;
					} else {
						currentUserIndex = result;
						while (true) {
							transactionMenu();
							selection = sc.nextInt();
							switch (selection) {
							case 1:
								amountForDepositMenu();
								amount = sc.nextInt();
								bank.deposit(
										currentUserIndex,
										amount);
								continue;
							case 2:
								amountForWithdrawalMenu();
								amount = sc.nextInt();
								result = bank.withdrawal(
										currentUserIndex,
										amount);
								if (result == -1) {
									System.out
											.println("No enough money.");
								}
								continue;
							case 3:
								System.out
										.println("The balance of "
												+ bank
														.getUsers()[currentUserIndex]
														.getName()
												+ " is "
												+ bank
														.getUsers()[currentUserIndex]
														.getBalance());
								continue;
							case 4:
								System.out.println("Bye!");
							}
							break;
						}
						break;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		ATM atm = new ATM();
		atm.showMenu();
		atm.sc.close();
	}
}
