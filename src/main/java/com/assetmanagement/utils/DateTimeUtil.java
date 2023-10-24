package com.assetmanagement.utils;

import com.assetmanagement.enums.ErrorEnum;
import com.assetmanagement.exceptions.AssetManagementException;
import com.assetmanagement.response.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

@Component
@Log4j2
public class DateTimeUtil {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final Pattern datePattern =  Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static long getTotalTime(final long startTime) {
        final long endTime = getCurrentTime();
        return endTime - startTime;
    }
    public static void validateDateFormat(final String date){
        if(!datePattern.matcher(date).matches()){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_DATE_FORMAT.getErrorCode(), ErrorEnum.INVALID_DATE_FORMAT.getErrorMessage(), false
            ));
        }
    }
    public static Date convertStringToDate(final String date){
        try {
            return simpleDateFormat.parse(date);
        } catch (final ParseException exception) {
            log.error("Error when parsing date: {0}", exception);
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.UNABLE_TO_CONVERT_STRING_TO_DATE.getErrorCode(), ErrorEnum.UNABLE_TO_CONVERT_STRING_TO_DATE.getErrorMessage(), false
            ));
        }
    }

    public static void validateYearsMonthsDays(final String date) {
        final Integer year = Integer.parseInt(date.substring(0,4));
        final int month = Integer.parseInt(date.substring(5,7));
        final Integer day = Integer.parseInt(date.substring(8,10));

        //validate is valid month and years
        isValidYearMonthDays(year, month, day);

        //validate is month is feb
        checkIfMonthIsFeb(year, month, day);

        if (month == 4 || month == 6 || month == 9 || month == 11){
            if (!(day <= 30)){
                throw new AssetManagementException(new ErrorResponse(
                        ErrorEnum.INVALID_DAYS.getErrorCode(), ErrorEnum.INVALID_DAYS.getErrorMessage(), false));
            }
        }
    }
    private static void checkIfMonthIsFeb(final Integer year, final Integer month, final Integer day) {
        if (month == 2)
        {
            if (isLeap(year))
                if (day <= 29){
                    return;
                }else {
                    throw new AssetManagementException(new ErrorResponse(
                            ErrorEnum.INVALID_FEB_MONTH.getErrorCode(), ErrorEnum.INVALID_FEB_MONTH.getErrorMessage(), false));
                }
            else {
                if (day <= 28) {
                    return;
                }else {
                    throw new AssetManagementException(new ErrorResponse(
                            ErrorEnum.INVALID_FEB_MONTH.getErrorCode(), ErrorEnum.INVALID_FEB_MONTH.getErrorMessage(), false));
                }
            }
        }
    }

    private static boolean isLeap(final Integer year) {
        return (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0));
    }

    private static void isValidYearMonthDays(final Integer year, final Integer month, final Integer day) {
        final Integer maxYear = 2100;
        final int minYear = 2000;

        if (year > maxYear || year < minYear){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_YEAR.getErrorCode(), ErrorEnum.INVALID_YEAR.getErrorMessage(), false
            ));
        }
        if (month < 1 || month > 12){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_MONTHS.getErrorCode(), ErrorEnum.INVALID_MONTHS.getErrorMessage(), false
            ));
        }
        if (day < 1 || day > 31){
            throw new AssetManagementException(new ErrorResponse(
                    ErrorEnum.INVALID_DAYS.getErrorCode(), ErrorEnum.INVALID_DAYS.getErrorMessage(), false
            ));
        }

    }

    public static String convertDateToString(final Date date) {
        return simpleDateFormat.format(date);
    }
}
