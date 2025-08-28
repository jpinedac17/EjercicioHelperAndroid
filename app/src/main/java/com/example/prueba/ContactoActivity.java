package com.example.prueba;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prueba.basedatos.DbHelper;

public class ContactoActivity extends AppCompatActivity {

    private EditText editTextNombre, editTextTelefono, editTextDireccion, editTextCorreo;
    private Button buttonCrearDB, buttonGuardarContacto, buttonRegresarInicio;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        dbHelper = new DbHelper(this);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextTelefono = findViewById(R.id.editTextTelefono);
        editTextDireccion = findViewById(R.id.editTextDireccion);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        buttonCrearDB = findViewById(R.id.buttonCrearDB);
        buttonGuardarContacto = findViewById(R.id.buttonGuardarContacto);
        buttonRegresarInicio = findViewById(R.id.buttonRegresarInicio);

        // Botón para regresar al inicio
        buttonRegresarInicio.setOnClickListener(v -> finish());

        buttonCrearDB.setOnClickListener(v -> {
            if (dbHelper.getWritableDatabase() != null) {
                Toast.makeText(ContactoActivity.this, "Base de datos lista.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ContactoActivity.this, "Error al crear DB.", Toast.LENGTH_SHORT).show();
            }
        });

        buttonGuardarContacto.setOnClickListener(v -> {
            String nombre = editTextNombre.getText().toString().trim();
            String telefono = editTextTelefono.getText().toString().trim();
            String direccion = editTextDireccion.getText().toString().trim();
            String correo = editTextCorreo.getText().toString().trim();

            if (nombre.isEmpty() || telefono.isEmpty()) {
                Toast.makeText(ContactoActivity.this, "Nombre y teléfono son obligatorios.", Toast.LENGTH_SHORT).show();
                return;
            }

            long id = dbHelper.addContact(nombre, telefono, direccion, correo);
            if (id != -1) {
                Toast.makeText(ContactoActivity.this, "Contacto guardado.", Toast.LENGTH_SHORT).show();
                editTextNombre.setText("");
                editTextTelefono.setText("");
                editTextDireccion.setText("");
                editTextCorreo.setText("");
            } else {
                Toast.makeText(ContactoActivity.this, "Error al guardar contacto.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
