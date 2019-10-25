package com.li.gohome.adapter;

import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.li.gohome.R;
import com.li.gohome.baen.CommonModel;
import com.li.gohome.util.CircleImageView;
import com.li.gohome.util.MyImageView;

import java.util.List;

public class LocalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<CommonModel> localList;
    int itemType;

    public LocalAdapter(List<CommonModel> LocalBean,int type) {
        this.localList = LocalBean;
        this.itemType = type;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return 1;
                }
            });
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(itemType == 1) {
            return new LocalItemHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.local_item, parent, false));
        }else if(itemType == 2){
            return new SubItemHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sub_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LocalItemHolder) {
            ((LocalItemHolder) holder).image.setImageURL(localList.get(position).getIcon());
            ((LocalItemHolder) holder).textView.setText(localList.get(position).getTitle());
        }else if(holder instanceof SubItemHolder){
            ((SubItemHolder) holder).image.setImageURL(localList.get(position).getIcon());
            ((SubItemHolder) holder).textView.setText(localList.get(position).getTitle());
        }
    }

    @Override
    public int getItemCount() {
        return localList.size();
    }

    public class LocalItemHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView textView;

        public LocalItemHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.local_image);
            textView = itemView.findViewById(R.id.local_text);
        }
    }

    public class SubItemHolder extends RecyclerView.ViewHolder {
        MyImageView image;
        TextView textView;

        public SubItemHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.sub_image);
            textView = itemView.findViewById(R.id.sub_text);
        }
    }

}
