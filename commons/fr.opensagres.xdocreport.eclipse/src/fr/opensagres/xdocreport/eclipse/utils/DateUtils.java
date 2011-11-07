package fr.opensagres.xdocreport.eclipse.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static final String FRENCH_PATTERN = "dd/MM/yyyy";
	public static final String ENGLISH_PATTERN = "MM/dd/yyyy";

	public static Date toDate(String date, String outputPattern)
			throws ParseException {
		DateFormat df = new SimpleDateFormat(outputPattern);
		return df.parse(date);
	}
}
