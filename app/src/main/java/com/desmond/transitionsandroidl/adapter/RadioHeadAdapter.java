package com.desmond.transitionsandroidl.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.desmond.transitionsandroidl.R;

/**
 * Created by desmond on 17/8/15.
 */
public class RadioHeadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnClickListener mListener;

    public interface OnClickListener {
        void onClick(View view, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gridview_item, parent, false);
        RecyclerView.ViewHolder holder = new PhotoViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick(v, (int) v.getTag());
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final PhotoViewHolder viewHolder = (PhotoViewHolder) holder;
        final int pos = position % 4;
        viewHolder.ivPhoto.setTag(pos);
        switch (pos) {
            case 0: {
                viewHolder.ivPhoto.setImageResource(R.drawable.radiohead_art_1);
                break;
            }
            case 1: {
                viewHolder.ivPhoto.setImageResource(R.drawable.radiohead_art_2);
                break;
            }
            case 2: {
                viewHolder.ivPhoto.setImageResource(R.drawable.radiohead_art_3);
                break;
            }
            case 3: {
                viewHolder.ivPhoto.setImageResource(R.drawable.radiohead_art_4);
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public void setOnClickListener(OnClickListener listener) {
        mListener = listener;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPhoto;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ivPhoto = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
