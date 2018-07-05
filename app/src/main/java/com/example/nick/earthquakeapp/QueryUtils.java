package com.example.nick.earthquakeapp;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {

    private static final String LOG_TAG = "LOG";


    //regular expression to find substring "*km *** of ...."
    private static String REGEXP_TO_SPLIT_DATA = "^.*([0-9]km ([A-Z]){1,3} (of|OF) ).*$";

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(String urlString) {
        URL url = createURL(urlString);
        ArrayList<Earthquake> earthquakes = null;
        try {
            earthquakes = extractFeatureFromJson(makeHttpRequest(url));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return earthquakes;
    }

    private static URL createURL(String stringURL) {
        URL url = null;
        try {
            url = new URL(stringURL);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }


        }

        if (inputStream != null) {
            inputStream.close();
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static ArrayList<Earthquake> extractFeatureFromJson(String earthquakeJSONString) {

        if (TextUtils.isEmpty(earthquakeJSONString)) {
            return null;
        }

        ArrayList<Earthquake> earthquakes = new ArrayList<>();
        //stringBuilder for split location data into 2 textViews
        StringBuilder stringBuilder;
        Matcher matcher;
        Pattern pattern = Pattern.compile(REGEXP_TO_SPLIT_DATA);

        try {

            JSONObject earthquakesJSON = new JSONObject(earthquakeJSONString);
            JSONArray featuresArray = earthquakesJSON.getJSONArray("features");
            for (int i = 0; i < featuresArray.length(); ++i) {
                Earthquake earthquake = new Earthquake();
                JSONObject earthquakeJSON = featuresArray.getJSONObject(i);
                //setting the date of the earthquake
                long date = earthquakeJSON.getJSONObject("properties").getLong("time");
                Date dateObject = new Date(date);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd, yyyy");
                String dateToDisplay = dateFormatter.format(dateObject);
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm");
                String timeToDisplay = timeFormatter.format(dateObject);
                String place = earthquakeJSON.getJSONObject("properties").getString("place");
                stringBuilder = new StringBuilder(place);
                earthquake.setDate(dateToDisplay);
                earthquake.setTime(timeToDisplay);
                //setting the magnitude for the earthquake
                //here is a bug, if data is not float an error occurs, it will be fixed later
                earthquake.setMagnitude(earthquakeJSON.getJSONObject("properties").getString("mag"));
                //setting the place of the earthquake
                matcher = pattern.matcher(place);
                if (matcher.matches()) {
                    earthquake.setDirection(" " + stringBuilder.substring(0, stringBuilder.indexOf(" of ") + 3));
                    earthquake.setExactPlace(stringBuilder.substring(stringBuilder.indexOf(" of ") + 3, stringBuilder.length()));
                } else {
                    earthquake.setExactPlace(stringBuilder.toString());
                    earthquake.setDirection("Near the");
                }
                //setting
                earthquake.setFullUrl(earthquakeJSON.getJSONObject("properties").getString("url"));
                earthquakes.add(earthquake);

            }
            // TODO: Parse the response given by the SAMPLE_JSON_RESPONSE string and
            // build up a list of Earthquake objects with the corresponding data.

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        return earthquakes;
    }


}
