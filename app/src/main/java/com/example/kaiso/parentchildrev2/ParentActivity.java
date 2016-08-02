package com.example.kaiso.parentchildrev2;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

public class ParentActivity extends Activity {
    public String json_urld = "https://turntotech.firebaseio.com/digitalleash/kaison4.json";

    JSONObject jsonObject = new JSONObject();
    double distance;
    int newRadius;
    String x;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent2);
//        final EditText latitude = (EditText) findViewById(R.id.editText2);
//        final EditText longitude = (EditText) findViewById(R.id.editText3);
//        EditText radius = (EditText) findViewById(R.id.editText4);
//        String lon = "40";
//        String lat = "40";
//        latitude.setText(lat);
//        longitude.setText(lon);
//        double locationx = Double.parseDouble(latitude.toString());
//        //Double.parseDouble(text);
//        double locationy = Double.parseDouble(longitude.toString());
//        //latitude.getText()
//        double childLocationX = Double.parseDouble(lat);
//        double childLocationY = Double.parseDouble(lon);
//
//
//        distance = Math.sqrt(((locationx - childLocationX) * (locationx - childLocationX)) + ((locationy - childLocationY) * (locationy - childLocationY)));
//
////int newRadius = Integer.parseInt(radius.g);
//        newRadius = Integer.parseInt(radius.getText().toString());
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void buttonClicked1(View view) {

        EditText userName = (EditText) findViewById(R.id.editText);
        final EditText latitude = (EditText) findViewById(R.id.editText2);
        final EditText longitude = (EditText) findViewById(R.id.editText3);
        EditText radius = (EditText) findViewById(R.id.editText4);


        requestData("https://parentchild-6751b.firebaseio.com/users/" + userName.getText() + ".json");//.replace("\"", "");
    }
//
//
//        String hello = Double.toString(distance);


    private void requestData(String uri) {

        RequestPackage p = new RequestPackage();
        p.setMethod("GET");
        p.setUri(uri);
        p.setParam("latitude", "50");
        //p.setParam("price", "13.95");

        MyTask task = new MyTask();
        task.execute(p);

    }

    public void check(View view) {
        if (distance > newRadius) {
            Context context = getApplicationContext();
            CharSequence text = "Child out of Zone!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //toast out of zone
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Child in Zone!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //toast in zone
        }

    }

    public void create(View view) {
        EditText userName = (EditText) findViewById(R.id.editText);
        requestData("https://parentchild-6751b.firebaseio.com/users/" + userName.getText() + ".json");

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Parent Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.kaiso.parentchildrev2/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Parent Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.kaiso.parentchildrev2/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    //    private class MyTask extends AsyncTask<RequestPackage, String, String> {
////        public MyAsyncTask() {
////            super();
////            // do stuff
////        }
//        @Override
//        protected String doInBackground(String... params) {
//
//            // http client
////            URL url = null;
////            HttpURLConnection urlConnection = null;
////            try {
////                url = new URL(params[params.length -1]);
////                DataOutputStream printout;
////                urlConnection = (HttpURLConnection) url.openConnection();
////                //-----------------------------------------------
////
////                //----------------------------------------
////                InputStreamReader in = new InputStreamReader(urlConnection.getInputStream());
////                BufferedReader br = new BufferedReader(in);
////                String line = "";
////                StringBuilder responseoutput = new StringBuilder();
////                //-----------------------------------------------------------
////
////
////                while ((line = br.readLine()) != null) {
////
////                    responseoutput.append(line);
////
////
////                }
////                br.close();
////                System.out.println("-------------------------" + responseoutput.toString());
////                //System.out.println("-------------------------" + finalResult);
////                return responseoutput.toString();
////            } catch (Exception e) {
////                e.printStackTrace();
////            } finally {
////                urlConnection.disconnect();
////            }
////
////            return null;
//            String content = HttpManager.getData(params[0]);
//            System.out.println(content);
//            return content;
//
//        }
//
//    }
    private class MyTask extends AsyncTask<RequestPackage, String, String> {


        @Override
        protected String doInBackground(RequestPackage... params) {
            String content = HttpManager.getData(params[0]);
            System.out.println("heyyyyyyyyyyyyyyyy" + content);

            //ParentActivity.this.x = content;

            return content;
        }

        @Override
        protected void onPostExecute(String result) {

            System.out.println("heyyyyyyyyyyy6" + result);
            updateDisplay(result);

        }


    }

    public void updateDisplay(String x) {

        try {

            JSONObject json = new JSONObject(x);
            double lat = json.getDouble("lat");
            System.out.println("lat = " + lat);


        } catch (JSONException e) {
            e.printStackTrace();
        }


        final EditText latitude = (EditText) findViewById(R.id.editText2);
        final EditText longitude = (EditText) findViewById(R.id.editText3);
        EditText radius = (EditText) findViewById(R.id.editText4);


        String user2 = x.replaceAll("[{}]", " ");
        String[] array = user2.replaceAll("[\n\r\"]", "").split(",|:");
        //String[] array = user2.split(",|:");
        //String user = array[5];
        String lon = array[3];
        String lat = array[1];
        System.out.println(array[1]);

        //userName.setText(user);
        //latitude.setText(lat);
        //longitude.setText(lon);
        double locationx = Double.parseDouble(latitude.getText().toString());
        //Double.parseDouble(text);
        double locationy = Double.parseDouble(longitude.getText().toString());
        //latitude.getText()
        double childLocationX = Double.parseDouble(lat);
        double childLocationY = Double.parseDouble(lon);

       // Location cloc = new Lo

        //loc.distanceTo()


        distance = Math.sqrt(((locationx - childLocationX) * (locationx - childLocationX)) + ((locationy - childLocationY) * (locationy - childLocationY)));

//int newRadius = Integer.parseInt(radius.g);
        newRadius = Integer.parseInt(radius.getText().toString());

        if (distance > newRadius) {
            Context context = getApplicationContext();
            CharSequence text = "Child out of Zone!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //toast out of zone
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Child in Zone!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            //toast in zone
        }

    }


}
