package com.example.rfid_doc2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;


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
        String titre = model.getTitre();
        Log.d(TAG, "Adapter : " + titre);

        if(titre != null) {
            String auteur = model.getAuteur();
            String formatDoc = model.getFormat();


            holder.tvTitre.setText(titre);
            holder.tvAuteur.setText(auteur);


            int iconeDoc = R.drawable.ic_launcher_background;
            if (formatDoc.equals("Audio")) {
                iconeDoc = R.drawable.audio;
            } else if (formatDoc.equals("Photo")) {
                iconeDoc = R.drawable.photo;
            } else if (formatDoc.equals("Video")) {
                iconeDoc = R.drawable.video;
            }


            RequestOptions opts = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.mipmap.ic_launcher);

            Context ctx = holder.ivFormatDoc.getContext();

            holder.ivFormatDoc.setImageResource(iconeDoc);
            Glide.with(ctx)
                    .load(iconeDoc)
                    .apply(opts)
                    .fitCenter()
                    .override(100, 100)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.ivFormatDoc);

          //  holder.tvTitre.setOnClickListener((View.OnClickListener) this);
          //  holder.ivFormatDoc.setOnClickListener((View.OnClickListener) this);
        }
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

            Context mContext = ivFormatDoc.getContext();
            ivFormatDoc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v2) {
                    Toast.makeText(v2.getContext(), "format", Toast.LENGTH_SHORT).show();
                    int pos = getBindingAdapterPosition();
                    Log.d(TAG, "Adapter position image : " + pos);

                        DocumentSnapshot doc1 = getSnapshots().getSnapshot(pos);
                    String contenuDoc2 = doc1.getString("contenuDoc");
                    String format2 = doc1.getString("format");


                        Log.d(TAG, "Adapter click image" +  doc1.get("titre") + " : " + format2 );
                    Log.d(TAG, "Adapter contenuDoc : " + contenuDoc2);

                    if(format2.equals("Photo")) {
                        Intent i2 = new Intent(mContext, Detail2Activity.class);
                        i2.putExtra("contenuDoc", contenuDoc2);
                        mContext.startActivity(i2);
                    } else if(format2.equals("Audio")) {
                        Intent i3 = new Intent(mContext, Detail3Activity.class);
                        i3.putExtra("contenuDoc", contenuDoc2);
                        mContext.startActivity(i3);
                    }
                    else {
                        Log.d(TAG, "Adapter erreur format : " + format2);
                    }



                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v1) {
                    int pos = getBindingAdapterPosition();


                    Log.d(TAG, "Adapter click :" + v1.getId() + " image : " + ivFormatDoc.getId() + " titre : " + tvTitre.getId());
                    Log.d(TAG, "Adapter position : " + pos);

                    DocumentSnapshot doc1 = getSnapshots().getSnapshot(pos);
                    String idDoc = doc1.getId();

                    Intent i4 = new Intent(mContext, Detail4Activity.class);
                    i4.putExtra("idDoc", idDoc);
                    mContext.startActivity(i4);

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(DocumentSnapshot documentSnapshot, int pos);
    }

    private OnItemClickListener filmClickListener;

    public void setOnItemClickListener(OnItemClickListener docCL1) {
        this.filmClickListener = docCL1;
    }

}
