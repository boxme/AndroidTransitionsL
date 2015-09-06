package com.desmond.transitionsandroidl.ActivityTransition.listview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.desmond.transitionsandroidl.R;

/**
 * Created by desmond on 6/9/15.
 */
public class TextAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnClickListener mListener;

    public interface OnClickListener {
        void onClick(View view, int position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_text_item, parent, false);
        TextViewHolder viewHolder = new TextViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.onClick(view, (int) v.getTag());
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TextViewHolder viewHolder = (TextViewHolder) holder;
        viewHolder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public void setOnClickListener(OnClickListener listener) {
        mListener = listener;
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {

        public TextViewHolder(View itemView) {
            super(itemView);
        }
    }
}
