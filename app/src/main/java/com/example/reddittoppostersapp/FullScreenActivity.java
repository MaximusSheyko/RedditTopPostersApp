package com.example.reddittoppostersapp;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.reddittoppostersapp.util.Notifier;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FullScreenActivity extends AppCompatActivity {

    private static final String IMG_URL_NOT_FOUND = "image url not found";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fullscreen_activity);
        Log.d("fullScreen", "started");
        getIncomingIntent();
        saveImageToGallery();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("url")) {
            Log.d("extra", "found intent url;");
            var imageUrl = getIntent().getStringExtra("url");
            setImage(imageUrl);
        } else {
            Notifier.message(IMG_URL_NOT_FOUND, this);
        }
    }

    private void setImage(String url) {
        ImageView view = findViewById(R.id.fullscreen_image);
        loadImageByUrl(url, view);
    }

    public void saveImageToGallery() {
        Button saveToGallery = findViewById(R.id.fullscreen_save_to_gallery);
        saveToGallery.setOnClickListener(view -> {
            if (getIntent().hasExtra("url")) {
                var imageUrl = getIntent().getStringExtra("url");
                downloadImageToDevice(imageUrl);
            } else {
                Notifier.message(IMG_URL_NOT_FOUND, FullScreenActivity.this);
            }
        });
    }

    public void loadImageByUrl(@NonNull String url, ImageView view) {
        Glide.with(this)
                .load(url)
                .into(view);
    }

    private void downloadImageToDevice(String imageUrl) {
        var nameImg = imageUrl.substring(imageUrl.lastIndexOf(File.separator));
        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Toast.makeText(FullScreenActivity.this, "Saving Image...", Toast.LENGTH_SHORT).show();
                        saveImageToGallery(nameImg, resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    private void saveImageToGallery(String name, Bitmap bitmap) {
        try {
            var resolver = getContentResolver();
            var imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    createContentValues(name));
            var stream = resolver.openOutputStream(Objects.requireNonNull(imageUri));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            stream.close();
            Notifier.message("Image Saved", FullScreenActivity.this);

        } catch (IOException e) {
            Notifier.message("Image not saved", FullScreenActivity.this);
        }
    }

    private ContentValues createContentValues(String name) {
        var contentValues = new ContentValues();
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + "RedditPictures");
        return contentValues;
    }

}
