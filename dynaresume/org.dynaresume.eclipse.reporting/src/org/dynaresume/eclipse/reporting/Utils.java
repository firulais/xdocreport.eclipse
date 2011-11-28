package org.dynaresume.eclipse.reporting;

import java.util.Date;

import fr.opensagres.xdocreport.commons.utils.DateUtils;

public class Utils {

	private static final Utils INSTANCE = new Utils();

	public static Utils getInstance() {
		return INSTANCE;
	}

	public String rangeDateMonth(Date startDate, Date endDate) {
		int startDateYear = DateUtils.getDateYear(startDate);
		int endDateYear = DateUtils.getDateYear(endDate);

		int startDateMonth = DateUtils.getDateMonth(startDate);
		int endDateMonth = DateUtils.getDateMonth(endDate);

		StringBuilder result = new StringBuilder();

		String startDateMonthLabel = getMonthLabel(startDateMonth);
		if (startDateMonthLabel != null) {
			result.append(startDateMonthLabel);
			if (startDateYear != endDateYear) {
				result.append(" ");
				result.append(startDateYear);
			}
		}

		String endDateMonthLabel = getMonthLabel(endDateMonth);
		if (endDateMonthLabel != null) {
			if (result.length() > 0) {
				result.append("-");
			}
			result.append(endDateMonthLabel);
			result.append(" ");
			result.append(endDateYear);
		} else {
			if (result.length() > 0) {
				result.append("-");
			}
			result.append("Aujourd'hui");
		}

		return result.toString();
	}

	private static String getMonthLabel(int month) {
		if (month >= 0) {
			month++;
		}
		switch (month) {
		case 1:
			return "Janvier";
		case 2:
			return "Février";
		case 3:
			return "Mars";
		case 4:
			return "Avril";
		case 5:
			return "Mai";
		case 6:
			return "Juin";
		case 7:
			return "Juillet";
		case 8:
			return "Août";
		case 9:
			return "Septembre";
		case 10:
			return "Octobre";
		case 11:
			return "Novembre";
		case 12:
			return "Décembre";

		}
		return null;
	}

	public String rangeDateYear(Date startDate, Date endDate) {
		StringBuilder result = new StringBuilder();
		int startYear = DateUtils.getDateYear(startDate);
		int endYear = DateUtils.getDateYear(endDate);
		if (startYear > 0) {
			result.append(startYear);
		}
		if (endYear > 0 && endYear != startYear) {
			if (startYear > 0) {
				result.append(" - ");
			}
			result.append(endYear);
		}
		return result.toString();
	}
}
