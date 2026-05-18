package com.deloitte.common.bean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DateHepler {

	public static List<String> getAllDatesInFinancialYear(int startYear) {
		List<String> datesList = new ArrayList<>();

		// Financial year starts on April 1st of 'startYear'
		LocalDate startDate = LocalDate.of(startYear, 4, 1);
		// Financial year ends on March 31st of 'startYear + 1'
		LocalDate endDate = LocalDate.of(startYear + 1, 3, 31);

		// Iterate through each date in the financial year and add to the list
		LocalDate currentDate = startDate;
		while (!currentDate.isAfter(endDate)) {
			// Format the LocalDate to "dd-MM-yyyy" format and add to list
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String formattedDate = currentDate.format(formatter);
			datesList.add(formattedDate);

			// Move to the next day
			currentDate = currentDate.plusDays(1);
		}

		return datesList;
	}

	// Method to get all dates in the specified month of the year
	public static List<String> getDatesInMonth(String input) {
		int month = Integer.parseInt(input.substring(0, 2));
		int year = Integer.parseInt(input.substring(2));

		List<String> dates = new ArrayList<>();
		LocalDate date = LocalDate.of(year, month, 1); // Start from the first day of the month
		LocalDate endDate = date.plusMonths(1).minusDays(1); // Get the last day of the month

		// Iterate through all dates in the month
		while (!date.isAfter(endDate)) {
			String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
			dates.add(formattedDate);
			date = date.plusDays(1); // Move to the next day
		}

		return dates;
	}

	public static List<String> getDateRange(String startDateStr, String endDateStr) {
		List<String> dateList = new ArrayList<>();

		// Define the date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		// Parse the input strings to LocalDate objects
		LocalDate startDate = LocalDate.parse(startDateStr, formatter);
		LocalDate endDate = LocalDate.parse(endDateStr, formatter);

		// Generate the list of dates
		LocalDate currentDate = startDate;
		while (!currentDate.isAfter(endDate)) {
			dateList.add(currentDate.format(formatter));
			currentDate = currentDate.plusDays(1);
		}

		return dateList;
	}
}
