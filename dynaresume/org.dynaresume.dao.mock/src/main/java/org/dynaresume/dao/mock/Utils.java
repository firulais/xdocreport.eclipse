package org.dynaresume.dao.mock;

public class Utils {

	public static String getCriteria(String criteria) {
		if (criteria == null) {
			return null;
		}
		if (criteria.endsWith("%")) {
			criteria = criteria.substring(0, criteria.length() - 1);
		}
		if (criteria.length() == 0) {
			return null;
		}
		return criteria;
	}
}
