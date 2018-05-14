/*
 * *
 *  * Created by Youssef Assad on 5/12/18 8:41 PM
 *  * Copyright (c) 2018 . All rights reserved.
 *  * Last modified 5/12/18 8:34 PM
 *
 */

package youssef.revealactivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    public final static String FAB_X_POSITION = "fab_x";
    public final static String FAB_Y_POSITION = "fab_y";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int x= (int) fab.getX() + fab.getWidth()  / 2;
                int y= (int) fab.getY() + + fab.getHeight()  / 2;

                Intent intent = new Intent(MainActivity.this, RevealActivity.class);
                intent.putExtra(FAB_X_POSITION,x);
                intent.putExtra(FAB_Y_POSITION,y);
                startActivity(intent);
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
