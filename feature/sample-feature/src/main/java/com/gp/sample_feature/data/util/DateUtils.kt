package com.gp.sample_feature.data.util

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeFormatterBuilder
import java.util.Locale

object DateUtils {
    fun parseTimeStamp(timestamp: String?): LocalDateTime {
        return if (timestamp?.isNotEmpty() == true) {
            try {
                val inputFormatter = DateTimeFormatterBuilder()
                    .appendPattern(NetworkConstants.SERVER_DATE_FORMAT)
                    .appendLiteral(NetworkConstants.GMT_STRING)
                    .appendOffset(NetworkConstants.OFFSET_PATTERN, NetworkConstants.GMT_NO_OFFSET_STRING)
                    .toFormatter()
                    .withLocale(Locale.ENGLISH)

                LocalDateTime.parse(timestamp.substringBefore(" ("), inputFormatter)
            } catch (e: Exception) {
                LocalDateTime.MAX
            }
        } else {
            LocalDateTime.MAX
        }
    }

    fun parseAndFormatTimestamp(timestamp: String): String {
        return if (timestamp != "Just now") {
            try {
                // Define the input format
                val inputFormatter = DateTimeFormatterBuilder()
                    .appendPattern(NetworkConstants.SERVER_DATE_FORMAT)
                    .appendLiteral(NetworkConstants.GMT_STRING)
                    .appendOffset(NetworkConstants.OFFSET_PATTERN, NetworkConstants.GMT_NO_OFFSET_STRING)
                    .toFormatter()
                    .withLocale(Locale.ENGLISH)

                // Parse the input string to LocalDateTime
                val dateTime = LocalDateTime.parse(timestamp.substringBefore(" ("), inputFormatter)

                // Define the output format
                val outputFormatter = DateTimeFormatter.ofPattern(NetworkConstants.OUTPUT_DATE_FORMAT, Locale.ENGLISH)

                // Format the LocalDateTime to the desired output string
                dateTime.format(outputFormatter)
            } catch (e: Exception) {
                timestamp
            }
        } else {
            NetworkConstants.JUST_NOW_TEXT
        }
    }
}