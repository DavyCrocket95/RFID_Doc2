package com.example.rfid_doc2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class Detail2Activity extends AppCompatActivity {
    private static final String TAG = "Detail2Activity";

    private ImageView ivImage2;
    String contenu2;

    public void init() {
        ivImage2 = findViewById(R.id.ivImage2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail2);

        init();
        //Recup idFilm
        Intent i2 = getIntent();
        contenu2 = i2.getStringExtra("contenuDoc");
        Log.i(TAG, "onCreate contenu2 : " + contenu2);

        RequestOptions opts = new RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.mipmap.ic_launcher);

        Context ctx = ivImage2.getContext();

        Glide.with(ctx)
                .load(contenu2)
                .apply(opts)
                .fitCenter()
                //  .override(100, 100)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImage2);

    }
}