package com.erkankrcr.mylibraryproject.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erkankrcr.mylibraryproject.R;
import com.erkankrcr.mylibraryproject.repostory.model.Library;
import com.erkankrcr.mylibraryproject.ui.listener.onLongClickListener;
import com.erkankrcr.mylibraryproject.ui.viewholder.LibrarySearchVH;

import java.util.List;

/**
 * For The Glory Of Machine
 * ╔════════════════════════════╗
 * ║  Created by Erkan Karacar  ║
 * ╠════════════════════════════╣
 * ║ erkankrcr@outlook.com.tr   ║
 * ╠════════════════════════════╣
 * ║     01/05/2020 - 16:43     ║
 * ╚════════════════════════════╝
 */
public class LibrarySearchAdapter extends RecyclerView.Adapter<LibrarySearchVH> {
    Context context;
    List<Library> libraries;
    onLongClickListener longClickListener;

    public LibrarySearchAdapter(Context context, List<Library> libraries) {
        this.context = context;
        this.libraries = libraries;
    }

    @NonNull
    @Override
    public LibrarySearchVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LibrarySearchVH(
                LayoutInflater.from(context).inflate(R.layout.rv_item,parent,false)
                ,longClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LibrarySearchVH holder, int position) {
        holder.bind(libraries.get(position));
    }

    @Override
    public int getItemCount() {
        return libraries.size();
    }

    public void setList(List<Library> list){
        this.libraries = list;
        notifyDataSetChanged();
    }

    public void clickListener(onLongClickListener longClickListener){
        this.longClickListener = longClickListener;
    }
}
