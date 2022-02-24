package com.example.rfid_doc2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


//<ModelFilm, AdapterFilm.FilmsViewHolder>
public class AdapterDoc extends FirestoreRecyclerAdapter<ModelDoc, AdapterDoc.DocViewHolder> {
    private static final String TAG = "Adapter";
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */

    public AdapterDoc(@NonNull FirestoreRecyclerOptions options) {
        super(options);
    }




    @Override
    protected void onBindViewHolder(@NonNull DocViewHolder holder, int position, @NonNull ModelDoc model) {
        String titre = model.getDoc_titre();
        String auteur = model.getDoc_auteur();
        String formatDoc = model.getDoc_format();

        Log.d(TAG, "Adapter : " + titre);
        /*
        holder.tvTitre.setText(titre);
        holder.tvAuteur.setText(auteur);


        int iconeDoc = R.drawable.ic_launcher_background;
        if(formatDoc.equals("audio")) {
            iconeDoc = R.drawable.ic_audiotrack_48;
        } else if(formatDoc.equals("photo")) {
            iconeDoc = R.drawable.ic_photo_48;
        }*/

        /*
        RequestOptions opts = new RequestOptions()
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.mipmap.ic_launcher);

        Context ctx = holder.ivFormatDoc.getContext();*/

        //holder.ivFormatDoc.setImageResource(iconeDoc);
        /*Glide.with(ctx)
                .load(iconeDoc)
                .apply(opts)
                .fitCenter()
                .override(100, 100)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivFormatDoc);*/
    }

    @NonNull
    @Override
    public DocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v1 = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.row_doc, parent, false);
        return new DocViewHolder(v1);
    }

    public class DocViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFormatDoc;
        private TextView tvTitre, tvAuteur;

        public DocViewHolder(@NonNull View itemView) {
            super(itemView);

            ivFormatDoc = itemView.findViewById(R.id.ivFormatDoc);
            tvTitre = itemView.findViewById(R.id.tvTitre);
            tvAuteur = itemView.findViewById(R.id.tvAuteur);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
