package com.sdz.model;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StringtoDateFormat {
	///Convertit un string en DateFormat // YYYY - MM - JJ
	public static DateFormat StringToDateFormat(String pString){
		return new SimpleDateFormat(pString);
	}

}
