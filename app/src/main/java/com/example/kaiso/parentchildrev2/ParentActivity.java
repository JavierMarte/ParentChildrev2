package com.example.kaiso.parentchildrev2;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;
public class ParentActivity extends Activity {
    public String json_urld = "https://turntotech.firebaseio.com/digitalleash/kaison4.json";

    JSONObject jsonObject = new JSONObject();
    double distance;
    int newRadius;
    String x;


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
    }


    public void buttonClicked1(View view) {

        EditText userName = (EditText) findViewById(R.id.editText);
        final EditText latitude = (EditText) findViewById(R.id.editText2);
        final EditText longitude = (EditText) findViewById(R.id.editText3);
        EditText radius = (EditText) findViewById(R.id.editText4);


        String user2 = requestData("https://parentchild-6751b.firebaseio.com/users/"+ userName.getText() +".json").replace("\"", "");
        user2 = user2.replaceAll("[{}]", " ");
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
//
//
//        String hello = Double.toString(distance);



    private String requestData(String uri) {

        RequestPackage p = new RequestPackage();
        p.setMethod("GET");
        p.setUri(uri);
        p.setParam("latitude", "50");
        //p.setParam("price", "13.95");

        MyTask task = new MyTask();
        try {
            return task.execute(p).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;

    }
    public void check(View view){
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
    public void create(View view){
        EditText userName = (EditText) findViewById(R.id.editText);
        final EditText latitude = (EditText) findViewById(R.id.editText2);
        final EditText longitude = (EditText) findViewById(R.id.editText3);
        requestData("https://parentchild-6751b.firebaseio.com/users/"+ userName.getText() +".json");

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
    public String updateDisplay(String x){

        return x;

    }



}
