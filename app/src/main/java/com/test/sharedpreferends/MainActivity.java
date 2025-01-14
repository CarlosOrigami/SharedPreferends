package com.test.sharedpreferends;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_NAME = "userName";

    private TextView welcomeText;
    private EditText nameInput;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculamos los elementos de la vista
        welcomeText = findViewById(R.id.welcomeText);
        nameInput = findViewById(R.id.nameInput);
        updateButton = findViewById(R.id.updateButton);

        // Obtener SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Cargar el nombre guardado si existe
        String savedName = sharedPreferences.getString(KEY_NAME, "Bienvenido");
        welcomeText.setText(savedName);

        // Configuramos el botón de actualizar
        updateButton.setOnClickListener(v -> {
            String newName = nameInput.getText().toString().trim();

            if (newName.equalsIgnoreCase("apagado")) {
                // Cerrar la app si el texto es "apagado"
                finish();
            } else {
                // Guardar el nuevo nombre en SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, newName);
                editor.apply();

                // Actualizar el texto de bienvenida
                welcomeText.setText(newName);
                Toast.makeText(MainActivity.this, "Nombre actualizado", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
