package com.sdz.model;

public class UserManager {
	private static String currentUser = "";
	
	public static String getCurrentUser() {
		return currentUser;
	}
	
	public static void setCurrentUser(String user) {
		currentUser = user;
	} 
}
