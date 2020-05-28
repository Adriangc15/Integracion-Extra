package com.example.adrian.mobile.Activities;

import android.content.Intent;
import android.os.Bundle;

import com.example.adrian.mobile.Models.CursoModelo;
import com.example.adrian.mobile.Models.EstudianteModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adrian.mobile.R;

import java.util.ArrayList;
import java.util.Collection;

public class AddUpdEstudianteActivity extends AppCompatActivity {

    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private EditText nomFld;
    private EditText apellidoid;
    private EditText edadid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_estudiante);

        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdCarreraBtn);

        //cleaning stuff
        codFld = findViewById(R.id.idAddUpdEstudiante);
        nomFld = findViewById(R.id.nombreAddUpdEstudiante);
        apellidoid = findViewById(R.id.apellidoAddUpdEstudiante);
        edadid = findViewById(R.id.edadAddUpdEstudiante);
        codFld.setText("");
        nomFld.setText("");
        apellidoid.setText("");
        edadid.setText("");

        //receiving data from admCarreraActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                EstudianteModel aux = (EstudianteModel) getIntent().getSerializableExtra("carrera");
                codFld.setText(String.valueOf(aux.getId()));
                codFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                apellidoid.setText(aux.getApellido());
                edadid.setText(aux.getEdad());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editEstudiante();
                    }
                });
            } else {         // is adding new CarreraModel object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addEstudiante();
                    }
                });
            }
        }
    }

    public void addEstudiante() {
        if (validateForm()) {
            //do something
            EstudianteModel car = new EstudianteModel(Integer.parseInt(codFld.getText().toString()), nomFld.getText().toString(), apellidoid.getText().toString(), edadid.getText().toString(), new ArrayList<CursoModelo>() {});
            Intent intent = new Intent(getBaseContext(), AdmEstudianteActivity.class);
            //sending carrera data
            intent.putExtra("addCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editEstudiante() {
        if (validateForm()) {
            EstudianteModel car = new EstudianteModel(Integer.parseInt(codFld.getText().toString()), nomFld.getText().toString(),  apellidoid.getText().toString(), edadid.getText().toString(), new ArrayList<CursoModelo>() {});
            Intent intent = new Intent(getBaseContext(), AdmEstudianteActivity.class);
            //sending carrera data
            intent.putExtra("editCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.nomFld.getText())) {
            nomFld.setError("Nombre requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.codFld.getText())) {
            codFld.setError("Codigo requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.apellidoid.getText())) {
            apellidoid.setError("Titulo requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.edadid.getText())) {
            edadid.setError("Titulo requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
