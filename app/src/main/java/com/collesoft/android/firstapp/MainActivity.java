package com.collesoft.android.firstapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.collesoft.android.firstapp.client.MessageRouterClient;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.collesoft.android.firstapp.MESSAGE";

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        // Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        HttpRequestTask asyncRestCall = new HttpRequestTask();
        String[] args = { message };
        asyncRestCall.execute(args);

        // intent.putExtra(EXTRA_MESSAGE, responseMessage);
        // startActivity(intent);
    }



    private class HttpRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... args) {
            String message = args[0];
            MessageRouterClient client = new MessageRouterClient();
            try {
                return client.sendMessage(message);
            } catch (Exception e) {
                Log.e(TAG, "call to REST service failed: " + e.getMessage());
                return "restcall failed";
            }
        }

    }
}
