package cn.edu.seu.cose.javacourse.ch05.atm;

import java.io.Serializable;

public class User implements Serializable{

	private String name;
	private String password;
	private int balance;

	public User(String name, String password) {
		this.name = name;
		this.password = password;
		this.balance = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

}
