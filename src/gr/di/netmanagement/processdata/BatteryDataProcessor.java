package gr.di.netmanagement.processdata;

import gr.di.netmanagement.beans.Battery;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Class BatteryDataProcessor.
 */
public class BatteryDataProcessor {

	/**
	 * Gets the low levels for batteries under 15%.
	 *
	 * @param batteryMap
	 *            the battery map
	 * @return the low levels
	 */
	public static TreeMap<String, Float> getLowLevels(
			final HashMap<String, ArrayList<Object>> batteryMap) {

		/* map: date as key, number of users found as value */
		TreeMap<String, Float> dateMap = new TreeMap<String, Float>();
		/* map: date as key, unique users as value */
		HashMap<String, HashSet<String>> dateUserMap = new HashMap<String, HashSet<String>>();

		for (ArrayList<Object> batteries : batteryMap.values()) {
			for (Object battery : batteries) {
				/* if date has not recorded yet */
				if (!dateMap.containsKey((((Battery) battery)
						.getTimestampShortString()))) {
					dateMap.put(
							(((Battery) battery).getTimestampShortString()),
							0.0f);
					dateUserMap.put(
							(((Battery) battery).getTimestampShortString()),
							new HashSet<String>());
				}
				/* if battery was low */
				if (((Battery) battery).getLevel() <= 15) {
					/* if user was not found in that date */
					if (!dateUserMap.get(
							(((Battery) battery).getTimestampShortString()))
							.contains(((Battery) battery).getUser())) {
						dateUserMap
								.get((((Battery) battery)
										.getTimestampShortString())).add(
										((Battery) battery).getUser());
						dateMap.put((((Battery) battery)
								.getTimestampShortString()), dateMap
								.get((((Battery) battery)
										.getTimestampShortString())) + 1.0f);
					}
				}
			}

		}
		return dateMap;
	}

	public static TreeMap<Date, Float> getLowLevels2(
			final HashMap<String, ArrayList<Object>> batteryMap) {

		/* map: date as key, number of users found as value */
		TreeMap<Date, Float> dateMap = new TreeMap<Date, Float>();
		/* map: date as key, unique users as value */
		HashMap<Date, HashSet<String>> dateUserMap = new HashMap<Date, HashSet<String>>();

		for (ArrayList<Object> batteries : batteryMap.values()) {
			for (Object batteryObj : batteries) {
				Battery battery = (Battery) batteryObj;
				/* if date has not recorded yet */
				if (!dateMap.containsKey((battery.getTimestamp()))) {
					dateMap.put((battery.getTimestamp()), 0.0f);
					dateUserMap.put((battery.getTimestamp()),
							new HashSet<String>());
				}
				/* if battery was low */
				if (battery.getLevel() <= 15) {
					/* if user was not found in that date */
					if (!dateUserMap.get((battery.getTimestamp())).contains(
							battery.getUser())) {
						dateUserMap.get((battery.getTimestamp())).add(
								battery.getUser());
						dateMap.put((battery.getTimestamp()),
								new Float(dateUserMap.get((battery.getTimestamp())).size()));
					}
				}
			}

		}
		return dateMap;
	}

	/**
	 * Convert user numbers to percentages.
	 *
	 * @param dateMap
	 *            the date map
	 * @param size
	 *            the size
	 * @return the float[]
	 */
	public static Float[] convertToPercentages(
			final TreeMap<String, Float> dateMap, final int size) {

		Set<String> set = dateMap.keySet();
		for (String key : set) {
			dateMap.put(key, dateMap.get(key) / size * 100.0f);
		}
		System.out.println("dateMap after " + dateMap);
		Float[] newArray = new Float[dateMap.values().size()];
		int i = 0;
		for (Object obj : dateMap.values()) {
			/* round up to 2 decimal digits */
			newArray[i++] = round(((Float) obj), 1).floatValue();
		}
		System.out.println("newArray = " + newArray);
		for (Float f : newArray) {
			System.out.println(f);
		}
		return newArray;
	}

	public static BigDecimal round(final float d, final int decimalPlace) {

		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd;
	}

	public static TreeMap<String, Integer> getUserLevelsWithinDates(
			final Date from, final Date to, final ArrayList<Object> list) {

		if (to.before(from)) {
			return null;
		}
		TreeMap<String, Integer> retMap = new TreeMap<String, Integer>();
		// Calendar cal = Calendar.getInstance();
		for (Object battery : list) {
			/* if battery is within dates given */
			if (((Battery) battery).getTimestamp().after(from)
					&& ((Battery) battery).getTimestamp().before(to)) {
				retMap.put(((Battery) battery).getTimestamp().toString(),
						((Battery) battery).getLevel());
			}
		}

		return retMap;

	}

	@SuppressWarnings("unchecked")
	public static void foo() throws JSONException {

		JSONObject json = new JSONObject();
		json.put("1", "test1");
		json.put("2", "test2");
		json.put("4", 3);
		System.out.println(json);

		JSONArray array = new JSONArray();
		array.put(json);
		array.put(json);
		array.put(json);
		array.put(4);
		System.out.println("array " + array);
		System.out.println("array.toJSONString() " + array.toString());
		System.out.println("array.toString() " + array.toString());

	}

	public static void main(final String[] args) throws JSONException {

		BigDecimal bd = new BigDecimal("12");
		bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		String str = "12.8";
		System.out.println(str.replace(".", ""));
	}
}
