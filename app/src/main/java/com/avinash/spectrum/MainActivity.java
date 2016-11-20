package com.avinash.spectrum;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
private static final int REQUEST_IMAGE_CAPTURE = 1;
    private FloatingActionButton mImageProcessButton;
    private ImageView mImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = (ImageView)findViewById(R.id.mImageView);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //mImageProcessButton = (FloatingActionButton)findViewById(R.id.mImageProcessButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                dispatchCameraIntent();
            }
        });
        /*mImageProcessButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                processImage(data);
            }
        });*/
    }

    private void processImage(){

    }
    private void dispatchCameraIntent(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode!=RESULT_CANCELED && requestCode == REQUEST_IMAGE_CAPTURE ) {
            final Bundle extras = data.getExtras();
            Log.d("MAIN ACTIVITY","data is: "+data);
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            Log.d("MAIN ACTIVITY","Btmap: "+imageBitmap.getByteCount()+" "+imageBitmap.getHeight());
            mImageView.setImageBitmap(imageBitmap);
            mImageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, ColorActivity.class);
                    i.putExtras(extras);
                    startActivity(i);
                    finish();
                }
            });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
