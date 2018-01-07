package com.example.simplelist.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.simplelist.R;
import com.example.simplelist.views.DetailsActivity_;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Andrei on 06.01.2018.
 */

public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder> {

    private Context context;
    private int layoutId;
    private ArrayList<Map<String, String>> list;

    public SimpleAdapter(Context context, int layoutId, ArrayList<Map<String, String>> list) {
        this.context = context;
        this.layoutId = layoutId;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from( parent.getContext()).inflate(
                layoutId, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Map.Entry<String,String> pair = list.get(position).entrySet().iterator().next();
        Picasso.with(context)
                .load(pair.getKey())
                .into(holder.image);
        holder.text.setText(pair.getValue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView image;
        ViewHolder (View itemView) {
            super(itemView);
            text = (TextView) itemView.findViewById(R.id.text);
            image = (ImageView) itemView.findViewById(R.id.image);

            itemView.setOnClickListener(v -> {
                image.buildDrawingCache();

                DetailsActivity_.intent(context)
                        .extra("string", text.getText().toString())
                        .extra("bytebitmap", compress(image.getDrawingCache()))
                        .start();

                if (Build.VERSION.SDK_INT >= 21) {
                    ((Activity)context).overridePendingTransition(R.anim.enter_right_in, R.anim.exit_left_out);
                }
            });
        }

    }

    private byte[] compress(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;
    }
}
