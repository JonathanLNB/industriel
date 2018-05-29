package com.example.jonalnb.industriel;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Maquinas extends AppCompatActivity {
    @BindView(R.id.iImagen) ImageView iImagen;
    @BindView(R.id.iNombre) TextView vNombre;
    @BindView(R.id.vTexto) TextView vTexto;
    private BDMaquinaria bdMaquinaria;
    private SQLiteDatabase bd;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquinas);
        ButterKnife.bind(this);
        Bundle i = getIntent().getExtras();
        bdMaquinaria = new BDMaquinaria(this, "INDUSTRIEL", null, 1);
        bd = bdMaquinaria.getReadableDatabase();
        Cursor c = bd.rawQuery("SELECT * FROM maquinas WHERE id = "+Integer.parseInt(i.getString("ID")), null);
        if(c.moveToFirst()) {
            vNombre.setText(c.getString(1));
            vTexto.setText(c.getString(2));
            iImagen.setImageResource(c.getInt(3));
        }
    }
}
