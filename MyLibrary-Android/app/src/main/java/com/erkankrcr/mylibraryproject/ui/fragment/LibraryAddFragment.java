package com.erkankrcr.mylibraryproject.ui.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import com.erkankrcr.mylibraryproject.R;
import com.erkankrcr.mylibraryproject.repostory.Retrofit;
import com.erkankrcr.mylibraryproject.repostory.RetrofitEndPoint;
import com.erkankrcr.mylibraryproject.repostory.model.Author;
import com.erkankrcr.mylibraryproject.repostory.model.LibraryRequest;
import com.erkankrcr.mylibraryproject.repostory.model.Message;
import com.erkankrcr.mylibraryproject.repostory.model.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * For The Glory Of Machine
 * ╔════════════════════════════╗
 * ║  Created by Erkan Karacar  ║
 * ╠════════════════════════════╣
 * ║ erkankrcr@outlook.com.tr   ║
 * ╠════════════════════════════╣
 * ║     30/04/2020 - 22:15     ║
 * ╚════════════════════════════╝
 */
public class LibraryAddFragment extends Fragment {
    View view;
    TextInputEditText bookTitle,bookDescription,bookComment;
    AppCompatSpinner bookAuthor;
    TextView bookAuthorTV;
    Button bookPYear,bookSubmit;
    String pyear;
    RetrofitEndPoint retrofitEndPoint;
    User user;
    List<Author> authors;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_library_add,container,false);
        init();
        setListener();
        getAuthors();
        return view;
    }

    private void setSpinner(List<Author> body) {
        this.authors = body;
        List<String> authorList = new ArrayList<>();
        for (Author author : body){
            authorList.add(author.getAuthorName()+" "+author.getAuthorLastName());
        }
        bookAuthor.setAdapter(new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_dropdown_item,authorList));
    }

    private void getAuthors() {
        retrofitEndPoint.getAuthors(user.getId().toString()).enqueue(new Callback<List<Author>>() {
            @Override
            public void onResponse(Call<List<Author>> call, Response<List<Author>> response) {
                if (response.code() == 400){
                    Toast.makeText(getActivity(),"Hata ile karşılaşıldı",Toast.LENGTH_LONG).show();
                }else if(response.code() == 200){
                    setSpinner(response.body());
                }else{
                    Toast.makeText(getActivity(),response.code()+"",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Author>> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setListener() {
        bookPYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        LocalDate localDate = LocalDate.of(year,month,dayOfMonth);
                        pyear = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    }
                }, LocalDate.now().getYear(),LocalDate.now().getMonthValue(),LocalDate.now().getDayOfMonth()).show();
            }
        });
        bookSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkInput()){
                    saveBook();
                }else{
                    Toast.makeText(getActivity(),"Kitap Başlığı boş bırakılamaz",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void saveBook() {
        LibraryRequest libraryRequest = new LibraryRequest(
                bookTitle.getText().toString(),
                bookDescription.getText().toString(),
                bookComment.getText().toString(),
                authors.get(bookAuthor.getSelectedItemPosition()).getId(),
                pyear);
        retrofitEndPoint.setBook(libraryRequest,user.getId().toString()).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.code() == 400){
                    Toast.makeText(getActivity(),"Hata ile karşılaşıldı",Toast.LENGTH_LONG).show();
                }else if(response.code() == 200){
                    Toast.makeText(getActivity(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getActivity(),response.code()+"",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Log.d("LibraryAdd",t.getMessage());
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean checkInput() {
        if ("".equals(bookTitle.getText().toString())){
            return false;
        }else{
            return true;
        }
    }

    private void init() {
        bookTitle = view.findViewById(R.id.book_title);
        bookDescription = view.findViewById(R.id.book_description);
        bookComment = view.findViewById(R.id.book_comment);
        bookAuthorTV = view.findViewById(R.id.book_author_tv);
        bookAuthor = view.findViewById(R.id.book_author);
        bookPYear = view.findViewById(R.id.book_pyear);
        bookSubmit = view.findViewById(R.id.book_submit);
        retrofitEndPoint = new Retrofit().getRetrofit().create(RetrofitEndPoint.class);
        user = new Gson()
                .fromJson(getActivity()
                        .getSharedPreferences("AppInfo", Context.MODE_PRIVATE)
                        .getString("UserInfo","null"), User.class);
    }
}
