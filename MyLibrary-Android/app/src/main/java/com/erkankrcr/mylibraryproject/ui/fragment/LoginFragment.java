package com.erkankrcr.mylibraryproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.erkankrcr.mylibraryproject.R;
import com.erkankrcr.mylibraryproject.repostory.Retrofit;
import com.erkankrcr.mylibraryproject.repostory.RetrofitEndPoint;
import com.erkankrcr.mylibraryproject.repostory.model.User;
import com.erkankrcr.mylibraryproject.ui.activity.MainActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

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
 * ║     30/04/2020 - 23:09     ║
 * ╚════════════════════════════╝
 */
public class LoginFragment extends Fragment {
    View view;
    TextInputEditText usernameTI,passwordTI;
    Button submit;
    RetrofitEndPoint retrofitEndPoint;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login,container,false);
        init();
        setListener();
        return view;
    }

    private void setListener() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInput();
            }
        });
    }

    private void checkInput() {
        String username = usernameTI.getText().toString();
        String password = passwordTI.getText().toString();
        if ("".equals(username) || "".equals(password)){
            Toast.makeText(getActivity(),getString(R.string.notnullET),Toast.LENGTH_LONG).show();
        }else {
            sendRequest(username,password);
        }
    }

    private void sendRequest(String username,String password) {
        retrofitEndPoint.login(username,password).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 400){
                    Toast.makeText(getActivity(),"İlgili Kullanıcı Bulunamadı",Toast.LENGTH_LONG).show();
                }else if(response.code() == 200){
                    saveUser(response.body());
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }else{
                    Toast.makeText(getActivity(),response.code()+"",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(),t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void saveUser(User body) {
        Gson gson = new Gson();
        String userString = gson.toJson(body);
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("AppInfo", Context.MODE_PRIVATE).edit();
        editor.putString("UserInfo",userString);
        editor.commit();
    }

    private void init() {
        usernameTI = view.findViewById(R.id.login_username);
        passwordTI = view.findViewById(R.id.login_password);
        submit = view.findViewById(R.id.login_submit);
        retrofitEndPoint = new Retrofit().getRetrofit().create(RetrofitEndPoint.class);
    }
}
