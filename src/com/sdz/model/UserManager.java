package com.sdz.model;

public class UserManager {
	//Cette classe agit comme une variable globale permettant d'accéder au service connecté à l'interface une fois
	//que la fenêtre de login est fermée si nécessaire.
	
	private static String currentUser = "";
	
	public static String getCurrentUser() {
		return currentUser;
	}
	
	public static void setCurrentUser(String user) {
		currentUser = user;
	} 
}
