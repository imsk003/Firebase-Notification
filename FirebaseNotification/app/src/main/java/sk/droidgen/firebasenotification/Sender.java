package sk.droidgen.firebasenotification;

import android.os.AsyncTask;
import android.util.Log;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

class Sender extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... strings) {
        try {

            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n\t\r\n    \"to\" : \"/topics/all\",\r\n   \r\n      \"data\" : {\r\n      \t\"text\" : \""+strings[0]+"\"\r\n      }\r\n\t     \r\n}");
            Request request = new Request.Builder()
                    .url("https://fcm.googleapis.com/fcm/send")
                    .post(body)
                    .addHeader("authorization", "key=AAAAHPPltC8:APA91bFv5ler-imtLiZcTNlMYT-1ZYdbmXsfLMbcmKS2JsvhzL1CweH8N6JXZkxm83dI-FtkWNEilYMpAllI3K3p8rAC7M17mXeZtUh6IKNITApkkIdU7bZhWHvaZJHtyHXMXIQwYi9Z")
                    .addHeader("content-type", "application/json")
                    .build();

            Response response = client.newCall(request).execute();

        } catch (Exception e) {
            Log.e("CustomFilter","Exception Caught:"+e.getMessage());
        }
        return null;
    }
}
