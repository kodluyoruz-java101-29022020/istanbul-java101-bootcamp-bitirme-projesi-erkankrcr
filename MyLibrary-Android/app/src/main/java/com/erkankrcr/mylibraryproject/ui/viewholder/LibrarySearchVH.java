package com.erkankrcr.mylibraryproject.ui.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erkankrcr.mylibraryproject.R;
import com.erkankrcr.mylibraryproject.repostory.model.Library;
import com.erkankrcr.mylibraryproject.ui.listener.onLongClickListener;

import java.text.DateFormat;
import java.util.Date;

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
public class LibrarySearchVH extends RecyclerView.ViewHolder {
    TextView title,author,isComplete;
    View view;
    onLongClickListener longClickListener;
    public LibrarySearchVH(@NonNull View itemView, onLongClickListener longClickListener) {
        super(itemView);
        title = itemView.findViewById(R.id.rv_title);
        author = itemView.findViewById(R.id.rv_author);
        view = itemView.findViewById(R.id.rv_layout);
        isComplete = view.findViewById(R.id.rv_iscomplete);
        this.longClickListener = longClickListener;
    }

    public void bind(Library library){
        title.setText(library.getBook().getTitle());
        author.setText(
                library.getBook()
                        .getAuthor()
                        .getAuthorName()
                        + " "+
                        library.getBook()
                                .getAuthor()
                                .getAuthorLastName());
        if (library.isComplete()){
            isComplete.setText("Tamamlandı");
        }else{
            isComplete.setText("Tamamlanmadı");
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                longClickListener.onLongClick(library);
            }
        });
    }
}
