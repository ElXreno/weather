package com.elxreno.weather

/**
 * Icon list:
 * 01d, 01n - Clear sky
 * 02d, 02n - Few clouds
 * 03d, 03n - Scattered clouds
 * 04d, 04n - Broken clouds
 * 09d, 09n - Shower rain
 * 10d, 10n - Rain
 * 11d, 11n - Thunderstorm
 * 13d, 13n - Snow
 * 50d, 50n - Mist
 */

class IconHelper {
    fun getDrawable(iconId: Int, isDay: Boolean): Int {
        return when (iconId) {
            200, 201, 202, 210, 211, 212, 221, 230, 231, 232 -> {
                R.drawable.ic_thunder
            } // Thunderstorm | 11d
            300, 301, 302, 310, 311, 312, 313, 314, 321 -> {
                if (isDay) R.drawable.ic_day_rain else R.drawable.ic_night_rain
            } // Drizzle | 09d
            500, 501, 502, 503, 504 -> {
                if (isDay) R.drawable.ic_day_rain else R.drawable.ic_night_rain
            } // Rain | 10d
            511 -> {
                if (isDay) R.drawable.ic_day_sleet else R.drawable.ic_night_sleet
            } // Rain | 13d
            520, 521, 522, 531 -> {
                if (isDay) R.drawable.ic_day_sleet else R.drawable.ic_night_sleet
            } // Rain | 09d
            600, 601, 602, 611, 612, 613, 615, 616, 620, 621, 622 -> {
                if (isDay) R.drawable.ic_day_snow else R.drawable.ic_night_snow
            } // Snow | 13d
            701 -> {
                R.drawable.ic_mist
            } // Mist | 50d
            741 -> {
                R.drawable.ic_fog
            } // Fog | 50d
            781 -> {
                R.drawable.ic_tornado
            } // Tornado | 50d
            711, 721, 731, 751, 761, 762, 771 -> {
                R.drawable.ic_not_available
            } // Smoke, Haze, Dust, Sand, Dust, Ash, Squall | 50d
            800 -> {
                if (isDay) R.drawable.ic_day_clear else R.drawable.ic_night_clear
            } // Clear | 01d, 01n
            801 -> {
                if (isDay) R.drawable.ic_day_partial_cloud else R.drawable.ic_night_partial_cloud
            } // Clouds | 02d, 02n
            802 -> {
                R.drawable.ic_cloudy
            } // Clouds | 03d, 03n
            803, 804 -> {
                R.drawable.ic_cloudy
            } // Clouds | 04d, 04n
            else -> R.drawable.ic_not_available
        }
    }
}