package com.example.prueba.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 2; // subir versión para aplicar cambios en la tabla
    private static final String DATABASE_NOMBRE = "agenda.db";
    public static final String TABLE_CONTACTOS = "t_contactos";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_CONTACTOS + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nombre TEXT NOT NULL, " +
                "telefono TEXT, " +
                "direccion TEXT, " +
                "email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTOS);
        onCreate(db);
    }

    // Inserta un contacto. Retorna id o -1 en error.
    public long addContact(String nombre, String telefono, String direccion, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("telefono", telefono);
        values.put("direccion", direccion);
        values.put("email", email);
        long id = db.insert(TABLE_CONTACTOS, null, values);
        db.close();
        return id;
    }

    // Busca por nombre y devuelve un objeto Contacto (o null si no existe)
    public Contacto getContactByName(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_CONTACTOS,
                null,
                "nombre = ?",
                new String[]{ nombre },
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono"));
            String direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion"));
            String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            Contacto c = new Contacto(id, nombre, telefono, direccion, email);
            cursor.close();
            db.close();
            return c;
        }

        if (cursor != null) cursor.close();
        db.close();
        return null;
    }

    // Clase modelo incluida dentro del mismo archivo
    public static class Contacto {
        private int id;
        private String nombre;
        private String telefono;
        private String direccion;
        private String email;

        public Contacto(int id, String nombre, String telefono, String direccion, String email) {
            this.id = id;
            this.nombre = nombre;
            this.telefono = telefono;
            this.direccion = direccion;
            this.email = email;
        }

        public int getId() { return id; }
        public String getNombre() { return nombre; }
        public String getTelefono() { return telefono; }
        public String getDireccion() { return direccion; }
        public String getEmail() { return email; }
    }
}
