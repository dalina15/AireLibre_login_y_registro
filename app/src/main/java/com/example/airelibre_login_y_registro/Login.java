package com.example.airelibre_login_y_registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText usuario, contrasena;
    Button btn_ingresar;
    BDHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = (EditText) findViewById(R.id.usuario1);
        contrasena = (EditText) findViewById(R.id.contrasena3);
        btn_ingresar = (Button) findViewById(R.id.btn_ingresar1);
        DB = new BDHelper(this);

        btn_ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = usuario.getText().toString();
                String contrasena = contrasena.getText().toString();

                if (usuario.equals("") || contrasena(""))
                    Toast.makeText(Login.this, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show();
                else {
                    Boolean verificarusuariocontrasena = DB.verificarusuariocontrasena(usuario, contrasena);
                    if (verificarusuariocontrasena == true) {
                        Toast.makeText(Login.this, "Has ingresado correctamente!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplication(), Mapa.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Información incorrecta", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });
    }}
