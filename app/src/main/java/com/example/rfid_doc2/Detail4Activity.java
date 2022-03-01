package com.example.rfid_doc2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Detail4Activity extends AppCompatActivity {
    private static final String TAG = "Detail4Activity";
    private TextView tvResume;
    private String idDoc;

    //Firestore
    private FirebaseFirestore db;
    private DocumentReference idDocRef;

    private void init() {
        tvResume = findViewById(R.id.tvResume);

        db = FirebaseFirestore.getInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail4);

        init();

        Intent i2 = getIntent();
        idDoc = i2.getStringExtra("idDoc");
        Log.i(TAG, "onCreate idDoc : " + idDoc);

        idDocRef = db.collection("Doc").document(idDoc);
        getDetail();
    }


    private void getDetail() {
        Log.i(TAG, "getDetail : " + idDoc);
        idDocRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot ds1) {
                        if (ds1.exists()) {
                            String resume = ds1.getString("resume");

                            tvResume.setText(resume);
                        } else
                            Toast.makeText(Detail4Activity.this, "No Data", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e(TAG, "onFailure: ", e);
                    }
                });

    }

    public void btndelDoc(View v1) {
        idDocRef.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            // this method is called when the task is success
                            // after deleting we are starting our MainActivity.
                            Toast.makeText(Detail4Activity.this, "Course has been deleted from Database.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Detail4Activity.this, MainActivity.class);
                            startActivity(i);
                        } else {
                            // if the delete operation is failed
                            // we are displaying a toast message.
                            Toast.makeText(Detail4Activity.this, "Fail to delete the document. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}