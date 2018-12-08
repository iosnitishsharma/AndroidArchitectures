package com.nitishsharma7.androidarchitectures;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nitishsharma7.androidarchitectures.models.tagtoptracks.Track;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<Track> modelList;

    private OnItemClickListener mItemClickListener;


    public RecyclerViewAdapter(Context context, ArrayList<Track> modelList) {
        this.mContext = context;
        this.modelList = modelList;
    }

    public void updateList(ArrayList<Track> modelList) {
        this.modelList = modelList;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_list, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof ViewHolder) {
            final Track model = getItem(position);
            ViewHolder trackViewHolder = (ViewHolder) holder;
            trackViewHolder.itemTxtTitle.setText(model.getName());
            trackViewHolder.itemTxtMessage.setText(model.getArtist().getName());
            Glide.with(mContext).load(model.getImage().get(0).getText()).into(trackViewHolder.imgUser);

        }
    }


    @Override
    public int getItemCount() {

        return modelList.size();
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    private Track getItem(int position) {
        return modelList.get(position);
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position, Track model);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgUser;
        private TextView itemTxtTitle;
        private TextView itemTxtMessage;

        private ViewHolder(final View itemView) {
            super(itemView);
            this.imgUser = itemView.findViewById(R.id.img_user);
            this.itemTxtTitle =  itemView.findViewById(R.id.item_txt_title);
            this.itemTxtMessage =  itemView.findViewById(R.id.item_txt_message);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mItemClickListener.onItemClick(itemView, getAdapterPosition(), modelList.get(getAdapterPosition()));

                }
            });

        }
    }

}

