package com.leanovate.moodly.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Settings extends Activity {

    private String moodlyId;
    private SharedPreferences sharedPreferences;
    private String host;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        sharedPreferences = getSharedPreferences(getString(R.string.moodlyId), Context.MODE_PRIVATE);
        moodlyId = sharedPreferences.getString("moodlyId", "");
        host = sharedPreferences.getString("host", "");
        EditText editMoodlyId = (EditText) findViewById(R.id.moodlyIdValue);
        EditText editHost = (EditText) findViewById(R.id.hostValue);
        editMoodlyId.setText(moodlyId);
        editHost.setText(host);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateButton(View view) {
        EditText editMoodly = (EditText) findViewById(R.id.moodlyIdValue);
        moodlyId = editMoodly.getText().toString();
        EditText editHost = (EditText) findViewById(R.id.hostValue);
        host = editHost.getText().toString();
        sharedPreferences.edit().putString("moodlyId", moodlyId).commit();
        sharedPreferences.edit().putString("host", host).commit();
        finish();
    }


}
