package com.example.tutorial.materialdesign;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.tutorial.materialdesign.Tab.MyAdapter;
import com.example.tutorial.materialdesign.Tab.SlidingTabLayout;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;
    String Token;
    boolean thread_running = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().subscribeToTopic("test");
        String a = FirebaseInstanceId.getInstance().getToken();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (thread_running){
                    Token = FirebaseInstanceId.getInstance().getToken();
                    if(Token != null){
                        System.out.println("Device Token is " + Token);
                        registerToken(Token);
                    } else {
                        System.out.println("Token is not loaded");
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });t.start();

        System.out.println("MainActivity.onCreate:" + FirebaseInstanceId.getInstance().getToken());

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        //set logo beside title on toolbar
        toolbar.setLogo(R.mipmap.ic_launcher_round);
        //set title on toobar
        toolbar.setTitle("My App");

        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.vp_tabs);
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager(),this));

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));
        slidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.tv_tab);
        slidingTabLayout.setViewPager(viewPager);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    private void registerToken(String token) {

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("Token",token)
                .build();

        Request request = new Request.Builder()
                .url("https://ukmandroidtutorial.000webhostapp.com/fcm/register.php")
                .post(body)
                .build();

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Intent intent = new Intent("com.example.tutorial.materialdesign.About");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
