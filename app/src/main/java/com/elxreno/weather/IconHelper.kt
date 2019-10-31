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
                R.drawable.thunder
            } // Thunderstorm | 11d
            300, 301, 302, 310, 311, 312, 313, 314, 321 -> {
                if (isDay) R.drawable.day_rain else R.drawable.night_rain
            } // Drizzle | 09d
            500, 501, 502, 503, 504 -> {
                if (isDay) R.drawable.day_rain else R.drawable.night_rain
            } // Rain | 10d
            511 -> {
                if (isDay) R.drawable.day_sleet else R.drawable.night_sleet
            } // Rain | 13d
            520, 521, 522, 531 -> {
                if (isDay) R.drawable.day_sleet else R.drawable.night_sleet
            } // Rain | 09d
            600, 601, 602, 611, 612, 613, 615, 616, 620, 621, 622 -> {
                if (isDay) R.drawable.day_snow else R.drawable.night_snow
            } // Snow | 13d
            701 -> {
                R.drawable.mist
            } // Mist | 50d
            741 -> {
                R.drawable.fog
            } // Fog | 50d
            781 -> {
                R.drawable.tornado
            } // Tornado | 50d
            711, 721, 731, 751, 761, 762, 771 -> {
                R.drawable.image_not_found
            } // Smoke, Haze, Dust, Sand, Dust, Ash, Squall | 50d
            800 -> {
                if (isDay) R.drawable.day_clear else R.drawable.night_clear
            } // Clear | 01d, 01n
            801 -> {
                if (isDay) R.drawable.day_partial_cloud else R.drawable.night_partial_cloud
            } // Clouds | 02d, 02n
            802 -> {
                R.drawable.cloudy
            } // Clouds | 03d, 03n
            803, 804 -> {
                R.drawable.cloudy
            } // Clouds | 04d, 04n
            else -> R.drawable.image_not_found
        }
    }
}