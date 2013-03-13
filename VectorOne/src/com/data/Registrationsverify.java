package com.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registrationsverify {

	private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	private static final String Postcode_PATTERN = "^([A-PR-UWYZ](([0-9](([0-9]|[A-HJKSTUW])?)?)|([A-HK-Y][0-9]([0-9]|[ABEHMNPRVWXY])?)) [0-9][ABD-HJLNP-UW-Z]{2})|GIR 0AA$";

	public boolean isProperEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		boolean result = matcher.matches();
		return result;
	}

	public boolean checkPasswordsAreEqual(String password,
			String passwordconfirm) {
		boolean result = password.equals(passwordconfirm);
		return result;
	}

	public boolean isProperDate(String date) {
		boolean result = true;
		if (date.length() == 10) {
			try {

				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				df.setLenient(false);
				df.parse(date);

			} catch (ParseException e) {
				result = false;
			}
		} else {
			result = false;
		}
		return result;
	}

	public boolean isProperPostCode(String postecode) {
		pattern = Pattern.compile(Postcode_PATTERN);
		matcher = pattern.matcher(postecode);
		boolean result = matcher.matches();
		return result;
	}
	
	public boolean olderthan13(String date) {
		String dateStart = date;

		// Custom date format
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		Date d1 = null;
		Date d2 = new Date();
		try {
			d1 = format.parse(dateStart);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long diff = d2.getTime() - d1.getTime();
		double diffYears = diff / 365.25 / 24 / 60 / 60 / 1000;
		if(diffYears>13){
			return true;
		}else{
			return false;
		}
		
	}

}