package com.example.android.InternetBasedActivity;

import android.content.Context;
import android.database.Cursor;
import android.renderscript.Script;
import android.util.Log;

import com.example.android.News;
import com.example.android.free.BuildConfig;
import com.example.android.free.R;

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
import java.util.ArrayList;
import java.util.List;

public class NewsData {
    private static final String LOG_TAG = NewsData.class.getSimpleName();
    public static ArrayList<News> newsForAdapter;
    static Context mContext;

    //Fetches the films that will populate the grid. Calls the createURL method and extractFeaturesFromJson method.
    public static ArrayList<News> fetchNews(String requestUrl, Context context) {
        ArrayList<News> news = new ArrayList<>(  );
        Log.e( "Fetch news ", "has started" );
        mContext = context;
        URL url = createURL( urlStringGenerator() );
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest( url );
        } catch (IOException e) {
            Log.e( LOG_TAG, "Problem making the htpp request" );
        }
        if (jsonResponse.length()>1) {
            news = extractFeaturesFromJson( jsonResponse );
        }
        return news;
    }

    //Takes the information from the JSON file and creates a new film for each new entry and stores the relevant information about that film within the new object.
    public static ArrayList<News> extractFeaturesFromJson (String jsonResponse){
        Log.e( "Extraction", "has started" );
        ArrayList<News> news= new ArrayList<News>();
        try {
            JSONObject newsJsonFile = new JSONObject(jsonResponse);
            JSONArray newsJsonArray = newsJsonFile.getJSONArray( "articles" );
            for (int i=0; i<newsJsonArray.length(); i++){
                JSONObject newNews = newsJsonArray.getJSONObject( i );
                String newsName = newNews.getString( "title" );
                String newsUrl = newNews.getString( "url" );
                String description = newNews.getString( "description" );

                news.add(new News (newsName, newsUrl, description));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        newsForAdapter = news;
        return news;
    }

    //Attempts to connect to the internet using the URL provided. If the connection is unavailable, the jsonResponse will be blank. Uses the readFromStream method to generate the response.
    private static String makeHttpRequest (URL url) throws IOException{
        Log.e( "Http request", "has started" );
        String jsonResponse = "";
        if (url == null){
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        int urlResponse=0;
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setConnectTimeout( 150000 );
            urlConnection.setReadTimeout( 100000 );
            urlConnection.setRequestMethod( "GET" );
            urlConnection.connect();
            if (urlConnection.getResponseCode()<300){
                inputStream=urlConnection.getInputStream();
                jsonResponse= readFromStream (inputStream);
            }
            else if (urlConnection.getResponseCode()==429){
                urlConnection.disconnect();
                urlResponse=429;
            }

            else {
                Log.e( LOG_TAG, "Problem with the URL connection " + urlConnection.getResponseCode() + urlConnection.getInstanceFollowRedirects());
            }
        }
        catch (IOException e) {
            Log.e( LOG_TAG, "Problem getting the JSON file" );
            return jsonResponse;
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null && urlResponse!=429) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    //Creates the jsonResponse by appending each new paramater retrieved.
    private static String readFromStream (InputStream inputStream) throws IOException{
        StringBuilder output = new StringBuilder(  );
        if (inputStream!=null){
            InputStreamReader inputStreamReader = new InputStreamReader( inputStream, Charset.forName ("UTF-8") );
            BufferedReader bufferedReader = new BufferedReader( inputStreamReader );
            String line = bufferedReader.readLine();
            while (line!=null){
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }


    //Turns the String query into a URL. If there are any issues, returns null.
    private static URL createURL (String stringUrl){
        URL url = null;
        try{
            url = new URL (stringUrl);
        }
        catch (MalformedURLException e){
            Log.e (LOG_TAG, "Problem generating the URL");
        }
        return url;
    }


    public static String urlStringGenerator () {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(mContext.getString( R.string.newsJson ));
        stringBuilder.append((BuildConfig.API_KEY).toString());
        return stringBuilder.toString();
    }

}

