package com.example.rfid_doc2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText etProduit;
    private TextView tvTitre;
    private FirebaseFirestore db;
    private AdapterDoc adapterDoc;

    private RecyclerView rvDoc;
    private Context ctx;


    private String idProd;

    private void init() {
        etProduit = findViewById(R.id.etProduit);
        tvTitre = findViewById(R.id.tvTitre);
        db = FirebaseFirestore.getInstance();

        rvDoc = findViewById(R.id.rvDoc);
        rvDoc.setHasFixedSize(true);
        rvDoc.setLayoutManager(new LinearLayoutManagerWrapper(ctx, LinearLayoutManager.VERTICAL, false));

    }

    public class LinearLayoutManagerWrapper extends LinearLayoutManager {

        public LinearLayoutManagerWrapper(Context context) {
            super(context);
        }

        public LinearLayoutManagerWrapper(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        @Override
        public boolean supportsPredictiveItemAnimations() {
            //return super.supportsPredictiveItemAnimations();
            return false;       //Pas d'animation entre les elt dans l'item recycler
        }
    }

    public void btnValider(View v1) {
        String numProduit = etProduit.getText().toString().trim();


        //Query q1 = db.collection("Produits")                .whereEqualTo("numero", numProd);

        db.collection("Produits")
                .whereEqualTo("numero", numProduit)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot qds) {
                        if (qds.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY 1" + numProduit);

                        } else {

                            for (DocumentSnapshot documentSnapshot : qds) {
                                if (documentSnapshot.exists()) {
                                    Log.d(TAG, "onSuccess: DOCUMENT" + documentSnapshot.getId() + " ; " + documentSnapshot.getData());
                                    idProd = documentSnapshot.getId();
                                    Log.d(TAG, "onSuccess: name " + documentSnapshot.get("name") );
                                    listDoc();
                                }
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
                    }
                });

    }

    public void listDoc() {

        /**** Prendre les datas suivant un modelDoc. Attention le modelDoc doit avoir les nommages des champs identiques à celui du firebase ****/
        Query q1 = db.collection("Doc")
                .whereEqualTo("idProd", idProd)
                .whereEqualTo("archive", "false");

        Log.d(TAG, "onSuccess: idProd 2:" + idProd );
        FirestoreRecyclerOptions<ModelDoc> listDoc =
                new FirestoreRecyclerOptions.Builder<ModelDoc>()
                        .setQuery(q1, ModelDoc.class)
                        .build();

        adapterDoc = new AdapterDoc((listDoc));
        rvDoc.setAdapter(adapterDoc);
        adapterDoc.startListening();

 /*     Prend les datas sans le modelDoc
        db.collection("Doc")
                .whereEqualTo("idProd", idProd)
                .whereEqualTo("archive", "false").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot qds) {
                        if (qds.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY2");
                            return;
                        } else {
                            String s2 = "";
                            for (DocumentSnapshot documentSnapshot : qds) {
                                if (documentSnapshot.exists()) {
                                    Log.d(TAG, "onSuccess: DOCUMENT" + documentSnapshot.getId() + " ; " + documentSnapshot.getData());
                                    String s = documentSnapshot.getString("titre");

                                    s2 += (":" + s);
                                    tvTitre.setText(s2);

                                    ModelDoc doc1 = documentSnapshot.toObject(ModelDoc.class);      //au lieu de prendre par get, on s'appuit sur le modelDoc

                                }
                            }


                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error getting data!!!", Toast.LENGTH_LONG).show();
                    }
                });

*/
    }

    public void rbCateg(View v1) {


    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }
}