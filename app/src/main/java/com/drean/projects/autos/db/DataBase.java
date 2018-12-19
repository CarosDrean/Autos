package com.drean.projects.autos.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.drean.projects.autos.pojo.Pedido;

import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {

    private Context context;

    public DataBase(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryCreteTable = "CREATE TABLE " + ConstantesBaseDatos.TABLE_NAME + " ("
                + ConstantesBaseDatos.ID  + " INTEGER PRIMARY KEY, "
                + ConstantesBaseDatos.AUTO + " TEXT, "
                + ConstantesBaseDatos.AUTO_IMG + " TEXT, "
                + ConstantesBaseDatos.CANTIDAD + " TEXT, "
                + ConstantesBaseDatos.CELULAR + " TEXT, "
                + ConstantesBaseDatos.DNI + " TEXT, "
                + ConstantesBaseDatos.EMAIL + " TEXT, "
                + ConstantesBaseDatos.FECHA + " TEXT, "
                + ConstantesBaseDatos.IGV + " TEXT, "
                + ConstantesBaseDatos.NOMBRE + " TEXT, "
                + ConstantesBaseDatos.PRECIO_UNIDAD + " TEXT, "
                + ConstantesBaseDatos.TOTAL + " TEXT"
                + ")";

        db.execSQL(queryCreteTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<Pedido> getItems () {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);
        while (registros.moveToNext()) {
            Pedido pedidoActual = new Pedido();
            pedidoActual.setId(registros.getInt(0));
            pedidoActual.setNombreAuto(registros.getString(1));
            pedidoActual.setImgAuto(registros.getString(2));
            pedidoActual.setCantidad(registros.getInt(3));
            pedidoActual.setCelular(registros.getInt(4));
            pedidoActual.setDni(registros.getInt(5));
            pedidoActual.setEmail(registros.getString(6));
            pedidoActual.setFecha(registros.getString(7));
            pedidoActual.setIgv(Double.parseDouble(registros.getString(8)));
            pedidoActual.setNombre(registros.getString(9));
            pedidoActual.setPrecioUnidad(Double.parseDouble(registros.getString(10)));
            pedidoActual.setTotal(Double.parseDouble(registros.getString(11)));

            pedidos.add(pedidoActual);
        }
        db.close();
        return pedidos;
    }

    public void createItem (ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_NAME, null, contentValues);
        db.close();
    }

    public void updateItem(ContentValues contentValues, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(ConstantesBaseDatos.TABLE_NAME, contentValues, ConstantesBaseDatos.ID + " = " + id, null);
        db.close();
    }

    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + ConstantesBaseDatos.TABLE_NAME + " WHERE " + ConstantesBaseDatos.ID + "=" + id);
        db.close();
    }

}
