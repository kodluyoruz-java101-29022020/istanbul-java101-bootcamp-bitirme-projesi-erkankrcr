package com.erkankrcr.mylibraryproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.erkankrcr.mylibraryproject.R;
import com.erkankrcr.mylibraryproject.repostory.Retrofit;
import com.erkankrcr.mylibraryproject.repostory.RetrofitEndPoint;
import com.erkankrcr.mylibraryproject.repostory.model.Library;
import com.erkankrcr.mylibraryproject.repostory.model.Message;
import com.erkankrcr.mylibraryproject.repostory.model.User;
import com.erkankrcr.mylibraryproject.ui.activity.MainActivity;
import com.erkankrcr.mylibraryproject.ui.adapter.LibrarySearchAdapter;
import com.erkankrcr.mylibraryproject.ui.listener.onLongClickListener;
import com.google.gson.Gson;

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
public class LibrarySearchFragment extends Fragment {
    View view;
    RecyclerView recyclerView;
    RetrofitEndPoint retrofitEndPoint;
    SearchView searchView;
    User user;
    LibrarySearchAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_library_search,container,false);
        init();
        setListener();
        return view;
    }

    private void setListener() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                requestServer(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        adapter.clickListener(new onLongClickListener() {
            @Override
            public void onLongClick(Library library) {
                changeLibraryStatus(library);
            }
        });
    }

    private void changeLibraryStatus(Library library) {
        retrofitEndPoint.setChangeLibrary(library.getId(),user.getId().toString()).enqueue(new Callback<Message>() {
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
                Log.d("LibrarySearch",t.getMessage());
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void requestServer(String query) {
        retrofitEndPoint.getSearchLibrary(query,user.getId().toString()).enqueue(new Callback<List<Library>>() {
            @Override
            public void onResponse(Call<List<Library>> call, Response<List<Library>> response) {
                if (response.code() == 400){
                    Toast.makeText(getActivity(),"Hata ile karşılaşıldı",Toast.LENGTH_LONG).show();
                }else if(response.code() == 200){
                    refleshRV(response.body());
                }else{
                    Toast.makeText(getActivity(),response.code()+"",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Library>> call, Throwable t) {
                Log.d("LibrarySearch",t.getMessage());
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void refleshRV(List<Library> body){
        adapter.setList(body);
    }

    private void init() {
        recyclerView = view.findViewById(R.id.library_rv);
        searchView = view.findViewById(R.id.library_search_view);
        searchView.setSubmitButtonEnabled(true);
        retrofitEndPoint = new Retrofit().getRetrofit().create(RetrofitEndPoint.class);
        user = new Gson()
                .fromJson(getActivity()
                        .getSharedPreferences("AppInfo", Context.MODE_PRIVATE)
                        .getString("UserInfo","null"),User.class);
        adapter = new LibrarySearchAdapter(getActivity(),new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
