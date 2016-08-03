package com.example.kaiso.parentchildrev2;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
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
    Tracker gpsTracker;
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
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        final Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
               // showPopup(v);

            }
        });

        TextView hello = (TextView) findViewById(R.id.textView5);
        TextView hello2 = (TextView) findViewById(R.id.textView6);
        gpsTracker = new Tracker(ParentActivity.this);

        // check if GPS enabled
        if(gpsTracker.canGetLocation()) {

            hello.setText(String.valueOf(gpsTracker.getLatitude()));
            hello2.setText(String.valueOf(gpsTracker.getLongitude()));
        }

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

//    public void check(View view) {
//        if (distance > newRadius) {
//            Context context = getApplicationContext();
//            CharSequence text = "Child out of Zone!";
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//            //toast out of zone
//        } else {
//            Context context = getApplicationContext();
//            CharSequence text = "Child in Zone!";
//            int duration = Toast.LENGTH_SHORT;
//
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//            //toast in zone
//        }
//
//    }

    public void create(View view) {
        EditText userName = (EditText) findViewById(R.id.editText);
        requestData("https://parentchild-6751b.firebaseio.com/users/" + userName.getText() + ".json");




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
    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }
//    public void showPopup(View anchorView) {
//
//        View popupView = getLayoutInflater().inflate(R.layout.fragment_one, null);
//
//        PopupWindow popupWindow = new PopupWindow(popupView,
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//        // Example: If you have a TextView inside `popup_layout.xml`
////        TextView tv = (TextView) popupView.findViewById(R.id.textView1);
////
////        tv.setText("heyyyyyyyyyyy");
//        ImageView hello = (ImageView) popupView.findViewById(R.id.imageView);
//        // Initialize more widgets from `popup_layout.xml`
//
//        // If the PopupWindow should be focusable
//        //popupWindow.setFocusable(true);
//
//        // If you need the PopupWindow to dismiss when when touched outside
//        popupWindow.setBackgroundDrawable(new ColorDrawable());
//
//        int location[] = new int[2];
//
//        // Get the View's(the one that was clicked in the Fragment) location
//        anchorView.getLocationOnScreen(location);
//
//        // Using location, the PopupWindow will be displayed right under anchorView
////        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
////                location[0], location[1] + anchorView.getHeight());
//        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,0,0);
//
//    }

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
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new FragmentTwo()).addToBackStack("FRAG") .commit();
            //showPopup(FragmentOne);

            //toast out of zone
        } else {
            Context context = getApplicationContext();
            CharSequence text = "Child in Zone!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new FragmentOne()).addToBackStack("FRAG") .commit();
            //toast in zone
        }



    }


}
