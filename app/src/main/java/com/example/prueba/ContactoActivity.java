package com.example.prueba;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prueba.basedatos.DbHelper;

public class ContactoActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextTelefono, editTextDireccion, editTextCorreo;
    private Button buttonCrearDB, buttonGuardarContacto;




    // Asumiremos que tienes una clase DBHelper para manejar la base de datos
    //private DbHelper dbHelper; // manejador de la base de datos, la clase está en mayuscula y la variable en minusculas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        // Inicializar la base de datos
       // dbHelper = new DbHelper(this);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextDireccion = findViewById(R.id.editTextDireccion);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        buttonCrearDB = findViewById(R.id.buttonCrearDB);
        buttonGuardarContacto = findViewById(R.id.buttonGuardarContacto);

        buttonCrearDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lógica para crear la base de datos (se puede inicializar en DBHelper)
                // SQLiteDatabase db = dbHelper.getWritableDatabase();
                // if (db != null) {
                //     Toast.makeText(ContactoActivity.this, "Base de datos preparada.", Toast.LENGTH_SHORT).show();
                // } else {
                //     Toast.makeText(ContactoActivity.this, "Error al preparar la base de datos.", Toast.LENGTH_SHORT).show();
                // }
                Toast.makeText(ContactoActivity.this, "Funcionalidad de Crear DB pendiente.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonGuardarContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextNombre.getText().toString();
                String telefono = editTextTelefono.getText().toString();
                String direccion = editTextDireccion.getText().toString();
                String correo = editTextCorreo.getText().toString();

                if (nombre.isEmpty() || telefono.isEmpty()) {
                    Toast.makeText(ContactoActivity.this, "Nombre y teléfono son obligatorios.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Lógica para guardar el contacto en la base de datos
                // long id = dbHelper.addContact(nombre, telefono, direccion, correo);
                // if (id != -1) {
                //     Toast.makeText(ContactoActivity.this, "Contacto guardado.", Toast.LENGTH_SHORT).show();
                //     editTextNombre.setText("");
                //     editTextTelefono.setText("");
                //     editTextDireccion.setText("");
                //     editTextCorreo.setText("");
                // } else {
                //     Toast.makeText(ContactoActivity.this, "Error al guardar contacto.", Toast.LENGTH_SHORT).show();
                // }
                 Toast.makeText(ContactoActivity.this, "Funcionalidad de Guardar Contacto pendiente.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
