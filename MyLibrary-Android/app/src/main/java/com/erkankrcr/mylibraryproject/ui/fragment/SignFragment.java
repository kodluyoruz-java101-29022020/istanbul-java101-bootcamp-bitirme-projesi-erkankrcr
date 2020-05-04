package com.erkankrcr.mylibraryproject.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.erkankrcr.mylibraryproject.R;
import com.erkankrcr.mylibraryproject.repostory.Retrofit;
import com.erkankrcr.mylibraryproject.repostory.RetrofitEndPoint;
import com.erkankrcr.mylibraryproject.repostory.model.SignUser;
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
 * ║     30/04/2020 - 23:10     ║
 * ╚════════════════════════════╝
 */
public class SignFragment extends Fragment {
    View view;
    Button submit;
    RetrofitEndPoint retrofitEndPoint;
    TextInputEditText nameET,lastnameET,usernameET,passwordET,passwordRepeatET;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign,container,false);
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

    private void requestServer(String name, String lastname, String username, String password) {
        retrofitEndPoint.sign(new SignUser(name,lastname,username,password)).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == 400){
                    Toast.makeText(getActivity(),"Hata ile karşılaşıldı",Toast.LENGTH_LONG).show();
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

    private void checkInput() {
        String name = nameET.getText().toString();
        String lastname = lastnameET.getText().toString();
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();
        String passwordRepeat = passwordRepeatET.getText().toString();

        if ("".equals(name) ||
                        "".equals(lastname) ||
                        "".equals(username) ||
                        "".equals(password) ||
                        "".equals(passwordRepeat)){
            Toast.makeText(getActivity(),getString(R.string.notnullET),Toast.LENGTH_LONG).show();
        }else {
            if(password.equals(passwordRepeat)){
                requestServer(name,lastname,username,password);
            }else{
                Toast.makeText(getActivity(),getString(R.string.passwordError),Toast.LENGTH_LONG).show();
            }
        }
    }

    private void init() {
        nameET = view.findViewById(R.id.sign_name);
        lastnameET = view.findViewById(R.id.sign_lastname);
        usernameET = view.findViewById(R.id.sign_username);
        passwordET = view.findViewById(R.id.sign_password);
        passwordRepeatET = view.findViewById(R.id.sign_password_repeat);
        submit = view.findViewById(R.id.sign_submit);
        retrofitEndPoint = new Retrofit().getRetrofit().create(RetrofitEndPoint.class);
    }
}
