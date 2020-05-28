package com.example.adrian.mobile.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.adrian.mobile.AccesoDatos.Model;
import com.example.adrian.mobile.Models.ClassRest;
import com.example.adrian.mobile.Models.EstudianteModel;
import com.example.adrian.mobile.Models.UserModel;
import com.example.adrian.mobile.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText mPassword;
    private EditText mUsername;
    private  Button mLogin;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.model = new Model();

        this.mUsername = (EditText) findViewById(R.id.email);
        this.mPassword = (EditText) findViewById(R.id.password);
        this.mLogin = (Button) findViewById(R.id.loginBtn);

        this.mLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = "";
                for (UserModel user: model.getUsers()){
                    if (mUsername.getText().toString().equals(user.getEmail()) && mPassword.getText().toString().equals(user.getPassword())){
                        model.setLoggedUser(user);
                        message = getString(R.string.login_success, user.getDisplayName());
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("model", model);
                        LoginActivity.this.startActivity(intent);
                    }
                }
                if (model.getLoggedUser() == null){
                    message = getString(R.string.login_failed);
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }
        });

        /*ClassRest consulta = new ClassRest();
        try {
            consulta.getLista("http://192.168.0.119:8080/LAB_INTEGRACION/ListarEstudiantes");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        //consulta.execute();


    }

}

