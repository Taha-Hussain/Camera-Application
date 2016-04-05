package com.tfs.camera_application;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    ImageView MyImage;
    Bitmap photo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyImage = (ImageView) findViewById(R.id.ImageView_Catured);
    }

    public void Capture_image(View view)
    {
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            photo = (Bitmap) data.getExtras().get("data");
            MyImage.setImageBitmap(photo);
        }
    }


    public void SetWallpaper_image(View view)
    {
        WallpaperManager myWallpaperManager = WallpaperManager.getInstance(MainActivity.this);

        try {
            // Change the current system wallpaper
            myWallpaperManager.setBitmap(photo);
//            MyImage.setBackground(new BitmapDrawable(getResources(),photo));

            // Show a toast message on successful change
            Toast.makeText(MainActivity.this,
                    "Wallpaper successfully changed", Toast.LENGTH_SHORT)
                    .show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
        }

    }
}
