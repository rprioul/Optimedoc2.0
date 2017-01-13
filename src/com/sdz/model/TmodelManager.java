package com.sdz.model;

public class TmodelManager {
	private static TModel currentTable;
	
	public static TModel getCurrentTable() {
		return currentTable;
	}
	
	public static void setCurrentUser(TModel Table) {
		currentTable = Table;
	} 
}
