package com.kkdev.mdbm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kkdev.mdbm.R;
import com.kkdev.mdbm.databinding.ItemCastListBinding;
import com.kkdev.mdbm.model.Cast;

import java.util.ArrayList;

public class CastAdapter extends RecyclerView.Adapter<CastAdapter.CastViewHolder>{

    private Context context;
    private ArrayList<Cast> castArrayList;

    public CastAdapter(Context context, ArrayList<Cast> castArrayList) {
        this.context = context;
        this.castArrayList = castArrayList;
    }

    @NonNull
    @Override
    public CastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCastListBinding itemCastListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cast_list,parent,false);
        return new CastViewHolder(itemCastListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull CastViewHolder holder, int position) {
        Cast cast = castArrayList.get(position);
        holder.itemCastListBinding.setCast(cast);
    }

    @Override
    public int getItemCount() {
        return castArrayList.size();
    }

    class CastViewHolder extends RecyclerView.ViewHolder{
        private ItemCastListBinding itemCastListBinding;
        CastViewHolder(@NonNull ItemCastListBinding itemCastListBinding) {
            super(itemCastListBinding.getRoot());
            this.itemCastListBinding = itemCastListBinding;
        }
    }

}
