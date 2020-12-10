package com.guillermo.hito;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button bFacil;
    Button bMedio;
    Button bDificil;
    List<Button> botonesLista;
    TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        declaracion();
        eventos();
    }

    public void declaracion(){
        salida = findViewById(R.id.lblSalida);
        bFacil = findViewById(R.id.cmdFacil);
        bMedio = findViewById(R.id.cmdMedio);
        bDificil = findViewById(R.id.cmdDificil);
        botonesLista = new ArrayList<>(Arrays.asList(bFacil,bMedio,bDificil));

    }

    public void eventos(){
        /* Función lambda muy abrebiada. Se asume que todos los parametros son validos a la función botonera de esta clase.
        La idea es que de esta manera nosotros podemos pasar funciones como parametros
         */
        for(Button b : botonesLista) b.setOnClickListener(this::botonera);


    }

    //  Función encargada de generar el menú superior con el .xml de Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu); // Incorpora al menú los elementos del xml
        menu.add(0,0,1,"Otro");
        menu.add(0,1,0,"Mas");

        for(int i=0;i<menu.size();i++){
            menu.getItem(i).setOnMenuItemClickListener(this::eventosMenu);
        }

        return true;
    }

    private boolean eventosMenu(MenuItem item) {
        salida.setText("Menu");
        toasShow(String.valueOf(item.getItemId()));
        return true;
    }

    public void toasShow(Object o){
        toasShow(String.valueOf(o));
    }
    public void toasShow(String mensaje){
        Context c = getApplicationContext();
        int i = Toast.LENGTH_LONG;
        Toast.makeText(c,mensaje,i).show();
    }

    public void botonera (View v){
        Intent intent;

        int code = 2;
        if(v.getId()==bFacil.getId()) code = 0;
        if(v.getId()==bMedio.getId()) code = 1;

        intent = new Intent(this,Listado.class);
        intent.putExtra("code",code);
        startActivity(intent);

    }
}