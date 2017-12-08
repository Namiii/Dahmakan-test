package dahmakan.helptest;


import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Locale;

class Helper {

	static String getStringTime(long timeStamp) {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.setTimeInMillis(timeStamp);
		return DateFormat.format("dd/MM/yyyy HH:mm a", cal).toString();
	}
}
