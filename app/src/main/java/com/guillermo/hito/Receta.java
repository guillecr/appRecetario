package com.guillermo.hito;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class Receta extends Activity implements PopupMenu.OnMenuItemClickListener{

    TextView titulo;
    ImageView img;
    TextView receta;
    int listaCode = 0;
    int recetaCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recetario);
        listaCode = getIntent().getIntExtra("listaCode",listaCode);
        recetaCode = getIntent().getIntExtra("recetaCode",recetaCode);
        iniciador();
    }

    public void iniciador(){
        findElements();
        setElement();
    }

    public void findElements(){
        titulo = findViewById(R.id.lblTituRec);
        img = findViewById(R.id.imgReceta);
        receta = findViewById(R.id.lblReceta);
    }

    @SuppressLint("Recycle")
    public void setElement(){
        Resources res = getResources();
        int listaImg = R.array.imgFaciles;
        int listaTit = R.array.listaFaciles;
        int listaRec = R.array.recFacil;

        if(listaCode==1) {
            listaImg = R.array.imgMedio;
            listaTit = R.array.listaMedio;
            listaRec = R.array.recMedio;
        }
        else if(listaCode==2){
            listaImg = R.array.imgDificil;
            listaTit = R.array.listaDificil;
            listaRec = R.array.recDificil;
        }

        img.setImageDrawable(res.obtainTypedArray(listaImg).getDrawable(recetaCode));
        titulo.setText(res.obtainTypedArray(listaTit).getString(recetaCode));
        receta.setText(res.obtainTypedArray(listaRec).getString(recetaCode));

        img.setLongClickable(true);
        img.setOnLongClickListener(this::showPopup);
    }

    public void ampliarImg(int ancho, int alto){
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ancho, alto);
        img.setLayoutParams(params);
    }

    public void ocultar(){
        img.setVisibility(View.INVISIBLE);
    }

    public boolean showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);

        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_img,popup.getMenu());

        popup.show();
        return true;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcAmpliar:
                ampliarImg(1200,1200);
                toasShow("Ampliar");
                return true;
            case R.id.opcResta:
                ocultar();
                toasShow("Ocultar");
                return true;
            default:
                return true;
        }
    }

    public void toasShow(String mensaje){
        Context c = getApplicationContext();
        int i = Toast.LENGTH_LONG;
        Toast.makeText(c,mensaje,i).show();
    }
}
