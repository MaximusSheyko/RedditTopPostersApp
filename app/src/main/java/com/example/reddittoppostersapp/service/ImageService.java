package com.example.reddittoppostersapp.service;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;
import java.io.OutputStream;
import java.util.Objects;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Deprecated
public class ImageService {

    @Deprecated
    public void loadImageByUrl(@NonNull String url, ImageView view){
        Glide.with(view.getContext())
                .asBitmap()
                .load(url)
                .into(view);
    }

   @Deprecated
   public void downloadImage(String imageURL, View view){
        var nameImg = imageURL.substring(imageURL.lastIndexOf(File.separator));
        Glide.with(view.getContext())
                .asBitmap()
                .load(imageURL)
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        Toast.makeText(view.getContext(), "Saving Image...", Toast.LENGTH_SHORT).show();
                        saveImageToGallery(resource, view.getContext(), nameImg);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });
    }

    @Deprecated
    private void saveImageToGallery(Bitmap bitmap, Context context, String name){
        OutputStream stream;
        try{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
                ContentResolver resolver = context.getContentResolver();
                ContentValues contentValues =  new ContentValues();
                contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, name);
                contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
                contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + "MyPictures");
                Uri imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);

                stream = resolver.openOutputStream(Objects.requireNonNull(imageUri));
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                stream.close();
                Toast.makeText(context, "Image Saved", Toast.LENGTH_SHORT).show();
            }
        }catch(Exception e){
            Toast.makeText(context, "Image not saved \n" + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
