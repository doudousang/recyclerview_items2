package com.li.gohome.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.li.gohome.R;
import com.li.gohome.baen.SalesBox;
import com.li.gohome.util.MyImageView;

import java.util.ArrayList;
import java.util.List;

public class SalesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    SalesBox salesbox;
    private List<String> datas = new ArrayList<>();
    public static final int TITLE_TYPE = 0;
    public static final int IMAGE_BIG_TYPE = 1;
    public static final int IMAGE_SMALL_TYPE = 2;


    public SalesAdapter(SalesBox salesbox) {
        this.salesbox = salesbox;
        datas.add(salesbox.getIcon());
        datas.add(salesbox.getMoreUrl());
        datas.add(salesbox.getBigCard1().getIcon());
        datas.add(salesbox.getBigCard2().getIcon());
        datas.add(salesbox.getSmallCard1().getIcon());
        datas.add(salesbox.getSmallCard2().getIcon());
        datas.add(salesbox.getSmallCard3().getIcon());
        datas.add(salesbox.getSmallCard4().getIcon());
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TITLE_TYPE) {
            return new TitleHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sales_title, parent, false));
        } else if (viewType == IMAGE_BIG_TYPE) {
            return new ImageBigHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sales_big_image, parent, false));
        }else if (viewType == IMAGE_SMALL_TYPE) {
            return new ImageSmallHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.sales_small_image, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TitleHolder) {
            ((TitleHolder) holder).imageView.setImageURL(salesbox.getIcon());
        } else if (holder instanceof ImageBigHolder) {
            ((ImageBigHolder) holder).imageView.setImageURL(datas.get(position+1));
        } else if (holder instanceof ImageSmallHolder) {
            ((ImageSmallHolder) holder).imageView.setImageURL(datas.get(position+1));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TITLE_TYPE;
        } else if (position == 1 || position == 2) {
            return IMAGE_BIG_TYPE;
        }else if(position > 2){
            return IMAGE_SMALL_TYPE;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return datas.size() - 1;
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
                    if (TITLE_TYPE == getItemViewType(position)) {
                        return 2;
                    } else if (IMAGE_BIG_TYPE == getItemViewType(position)
                    || IMAGE_SMALL_TYPE == getItemViewType(position)) {
                        return 1;
                    } else {
                        return 2;
                    }
                }
            });
        }
    }

    public class TitleHolder extends RecyclerView.ViewHolder {
        MyImageView imageView;
        TextView textView;

        public TitleHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sales_title_left);
            textView = itemView.findViewById(R.id.sales_title_right);
        }
    }

    public class ImageBigHolder extends RecyclerView.ViewHolder {
        MyImageView imageView;

        public ImageBigHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sales_big_image);
        }
    }

    public class ImageSmallHolder extends RecyclerView.ViewHolder {
        MyImageView imageView;

        public ImageSmallHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sales_small_image);
        }
    }
}
