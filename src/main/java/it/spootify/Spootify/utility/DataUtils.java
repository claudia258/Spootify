package it.spootify.Spootify.utility;

import java.util.Date;

public class DataUtils {
	
	public static Date addMinutesToDate(int minutes, Date beforeTime) {
		final long ONE_MINUTE_IN_MILLIS = 60000;// millisecs

		long curTimeInMs = beforeTime.getTime();
		Date afterAddingMins = new Date(curTimeInMs + (minutes * ONE_MINUTE_IN_MILLIS));
		return afterAddingMins;
	}

}
