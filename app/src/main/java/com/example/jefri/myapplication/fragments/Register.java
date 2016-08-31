package com.example.jefri.myapplication.fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jefri.myapplication.Keys;
import com.example.jefri.myapplication.R;
import com.example.jefri.myapplication.database.DbHelper;

/**
 * Created by JEFRI SINGH(ஜெப்ரி சிங்) on 3/19/2016.
 * Organization "The Tuna Group" - Kerala
 */
public class Register extends Fragment {
    TextInputLayout userNameLayout,passwordLayout;
    EditText userName,password;
    Button register,goToLogin;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_register,container,false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userNameLayout = (TextInputLayout)view.findViewById(R.id.user_name_layout);
        passwordLayout = (TextInputLayout)view.findViewById(R.id.password_layout);
        userName = (EditText)view.findViewById(R.id.user_name);
        password = (EditText)view.findViewById(R.id.password);

        register = (Button) view.findViewById(R.id.register_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userText = userName.getText().toString().trim();
                String passwordText = password.getText().toString().trim();
                if (userText.isEmpty()){
                    userNameLayout.setErrorEnabled(true);
                    userNameLayout.setError("Please Enter User Name");
                }
                else if (passwordText.isEmpty()){
                    passwordLayout.setErrorEnabled(true);
                    passwordLayout.setError("Please Enter Password");
                }
                else {

                    DbHelper helper = new DbHelper(getActivity());
                    if (helper.InsertUser(userText,passwordText)){
                        Toast.makeText(getActivity(),"Registration Success",Toast.LENGTH_SHORT).show();
                        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
                        {
                            goToLogin.performClick();
                        }
                    }else {
                        Toast.makeText(getActivity(),"User Name already in use.",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        goToLogin = (Button)view.findViewById(R.id.go_to_login);
        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login fragment = new Login();

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    setSharedElementReturnTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.change_image_trans));
                    setExitTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.fade));
                    fragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.change_image_trans));
                    fragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.fade));
                }

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.addSharedElement(view.findViewById(R.id.logo),"LOGO");
                transaction.replace(R.id.main_container, fragment);
                transaction.commit();
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().setTitle("REGISTER");
    }
}
