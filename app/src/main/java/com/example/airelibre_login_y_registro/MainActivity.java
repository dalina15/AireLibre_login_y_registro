package com.example.airelibre_login_y_registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usuario, contrasena, contrasena2;
    Button registro, ingresar;
    BDHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = (EditText) findViewById(R.id.usuario);
        contrasena = (EditText) findViewById(R.id.contrasena);
        contrasena2 = (EditText) findViewById(R.id.contrasena2);
        registro = (Button) findViewById(R.id.btn_registro);
        ingresar = (Button) findViewById(R.id.btn_ingresar);
        DB = new BDHelper(this);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuario = usuario.getText ().toString();
                String contrasena = contrasena.getText().toString();
                String contrasena2 = contrasena2.getText().toString();

                if (usuario.equals("")|| contrasena.equals("")|| contrasena2.equals("")){
                    Toast.makeText(MainActivity.this, "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();
                    else{
            if (contrasena.equals(contrasena2)){
                Boolean verificarusuario = DB.verificarusuario((usuario));
                if (verificarusuario == false){
                    Boolean insert = DB.insertarData(usuario, contrasena);
                    if (insert == true)  {
                        Toast.makeText( MainActivity.this, "El registro se completó correctamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent (getApplicationContext(), Mapa.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText( MainActivity.this, "Verifica los datos", Toast.LENGTH_SHORT).show());
                    }
                }else{
                    Toast.makeText( MainActivity.this, "El usuario ya existe, por favor, ingresa con él", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText( MainActivity.this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
                            }
                    }
                }
            }
    });
        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);

            }
        });

}