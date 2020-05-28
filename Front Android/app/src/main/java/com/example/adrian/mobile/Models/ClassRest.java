package com.example.adrian.mobile.Models;

import android.app.job.JobScheduler;
import android.os.AsyncTask;
import android.util.JsonReader;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ClassRest  extends AsyncTask<String, String, String> {

    private String apiUrl;
    private  String metodo;
    private String json;
    private JSONObject jsonObject;

    public ClassRest(String apiUrl, String metodo) {
        this.apiUrl = apiUrl;
        this.metodo = metodo;
    }

    public ClassRest(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public ClassRest() {
        this.apiUrl = "";
        this.metodo = "";
        this.json = "";
        this.jsonObject = null;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public JSONArray getLista(String url) throws  JSONException {
        this.apiUrl = url;
        this.metodo = "GET";
        this.execute();
        JSONArray jsonArray = null;
        jsonArray = new JSONArray(json);
        return jsonArray;
    }

    public JSONObject getObjeto(String url) throws JSONException {
        this.apiUrl = url;
        this.metodo = "GET";
        this.execute();
        jsonObject = new JSONObject(json);
        return jsonObject;
    }

    public void post(String url , JSONObject json){
        this.jsonObject = json;
        this.apiUrl = url;
        this.metodo = "POST";
        this.execute();
    }
    public void put(String url , JSONObject json){
        this.jsonObject = json;
        this.apiUrl = url;
        this.metodo = "PUT";
        this.execute();
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();

    }
    @Override
    protected String doInBackground(String... strings) {
        URL url ;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(this.apiUrl);
            urlConnection =  (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(metodo);


            if(metodo.equals("GET")){
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isw = new InputStreamReader(in,"UTF-8");
                BufferedReader  stringBufer= new BufferedReader(isw);

                String data = "";
                while ((data = stringBufer.readLine()) != null)
                    json +=data;
            }else if(metodo.equals("POST") || metodo.equals("PUT")){
                urlConnection.setReadTimeout(15000 /* milliseconds */);
                urlConnection.setConnectTimeout(15000 /* milliseconds */);
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(jsonObject.toString());
                writer.flush();
                writer.close();
                os.close();
                int responseCode=urlConnection.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader in=new BufferedReader(
                            new InputStreamReader(
                                    urlConnection.getInputStream()));
                    StringBuffer sb = new StringBuffer("");
                    String line="";
                    while((line = in.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    in.close();
                    return sb.toString();
                }else {
                    return new String("false : "+responseCode);
                }
            }
        } catch (Exception e){
            return new String("Exception: " + e.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s){

    }
}
