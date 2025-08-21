package com.example.prueba;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class GestionActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_CALL_PHONE = 1;
    private EditText editTextBuscarNombre;
    private Button buttonBuscar, buttonLlamar;
    private TextView textViewResultadoNombre, textViewResultadoTelefono;
    private String numeroTelefonoActual;
    // private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion);

        // dbHelper = new DBHelper(this);

        editTextBuscarNombre = findViewById(R.id.editTextBuscarNombre);
        buttonBuscar = findViewById(R.id.buttonBuscar);
        buttonLlamar = findViewById(R.id.buttonLlamar);
        textViewResultadoNombre = findViewById(R.id.textViewResultadoNombre);
        textViewResultadoTelefono = findViewById(R.id.textViewResultadoTelefono);

        buttonBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = editTextBuscarNombre.getText().toString();
                if (nombre.isEmpty()) {
                    Toast.makeText(GestionActivity.this, "Ingrese un nombre para buscar", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Lógica para buscar contacto en la DB
                // Contacto contacto = dbHelper.getContactByName(nombre);
                // if (contacto != null) {
                //     textViewResultadoNombre.setText("Nombre: " + contacto.getNombre());
                //     textViewResultadoTelefono.setText("Teléfono: " + contacto.getTelefono());
                //     numeroTelefonoActual = contacto.getTelefono();
                //     buttonLlamar.setEnabled(true);
                // } else {
                //     textViewResultadoNombre.setText("Contacto no encontrado");
                //     textViewResultadoTelefono.setText("");
                //     numeroTelefonoActual = null;
                //     buttonLlamar.setEnabled(false);
                // }
                Toast.makeText(GestionActivity.this, "Funcionalidad de Buscar Contacto pendiente.", Toast.LENGTH_SHORT).show();
                textViewResultadoNombre.setText("Resultado Nombre Apellido"); // Placeholder
                textViewResultadoTelefono.setText("123456789"); // Placeholder
                numeroTelefonoActual = "123456789"; // Placeholder
                buttonLlamar.setEnabled(true); // Placeholder
            }
        });

        buttonLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numeroTelefonoActual != null && !numeroTelefonoActual.isEmpty()) {
                    if (ContextCompat.checkSelfPermission(GestionActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(GestionActivity.this, new String[]{Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST_CALL_PHONE);
                    } else {
                        iniciarLlamada();
                    }
                } else {
                    Toast.makeText(GestionActivity.this, "No hay número para llamar.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void iniciarLlamada() {
        Intent intentLlamada = new Intent(Intent.ACTION_CALL);
        intentLlamada.setData(Uri.parse("tel:" + numeroTelefonoActual));
        try {
            startActivity(intentLlamada);
        } catch (SecurityException e) {
            Toast.makeText(GestionActivity.this, "Permiso para llamar denegado.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                iniciarLlamada();
            } else {
                Toast.makeText(this, "Permiso para llamar denegado.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
