package com.example.airelibre_login_y_registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText usuario, contrasena, contrasena2;
    Button registro, ingresar;
    BDHelper DB;
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        progressBar =findViewById(R.id.progressbarr);
        textView = findViewById(R.id.textView);


        //progressAnimation();

        usuario = (EditText) findViewById(R.id.usuario);
        contrasena = (EditText) findViewById(R.id.contrasena);
        contrasena2 = (EditText) findViewById(R.id.contrasena2);
        registro = (Button) findViewById(R.id.btn_registro);
        ingresar = (Button) findViewById(R.id.btn_ingresar);
        DB = new BDHelper(this);



            registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usuario.getText ().toString();
                String passcode = contrasena.getText().toString();
                String passcode2 = contrasena2.getText().toString();

                if (user.equals("")|| passcode.equals("")|| passcode2.equals(""))
                    Toast.makeText(MainActivity.this, "Debes ingresar todos los datos", Toast.LENGTH_SHORT).show();
                    else{
                        if (passcode.equals(passcode2)) {
                            Boolean verificarusuario = DB.verificarusuario((user));
                            if (verificarusuario == false) {
                                Boolean insert = DB.insertarData(user, passcode);
                                if (insert == true) {
                                    Toast.makeText(MainActivity.this, "El registro se completó correctamente", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Loading.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(MainActivity.this, "Verifica los datos", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "El usuario ya existe, por favor, ingresa con él", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                                    Toast.makeText( MainActivity.this, "Las contraseñas no son iguales", Toast.LENGTH_SHORT).show();
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

    //public void setProgressBar (int progress, boolean animate) {
        //AnimacionBarraInicial anim = new AnimacionBarraInicial(this, progressBar, textView, 0f, 100f);
        //anim.setDuration (8000);
        //progressBar.setAnimation(anim);

    //}
}