package com.sdz.model;

public class UserManager {
	//Cette classe agit comme une variable globale permettant d'acc�der au service connect� � l'interface une fois
	//que la fen�tre de login est ferm�e si n�cessaire.
	
	private static String currentUser = "";
	
	public static String getCurrentUser() {
		return currentUser;
	}
	
	public static void setCurrentUser(String user) {
		currentUser = user;
	} 
}
