package com.test.sharedpreferends;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "MyPrefs";
    private static final String KEY_NAME = "userName";

    private TextView bienvenidaText;
    private EditText nameInput;
    private Button actulizarBoton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculamos los elementos de la vista
        bienvenidaText = findViewById(R.id.bienvenidaText);
        nameInput = findViewById(R.id.nameInput);
        actulizarBoton = findViewById(R.id.actualizarBoton);

        // Obtener SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Cargar el nombre guardado
        String savedName = sharedPreferences.getString(KEY_NAME, "Bienvenido");
        bienvenidaText.setText(savedName);

        // Configuramos el botón de actualizar
        actulizarBoton.setOnClickListener(v -> {
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
                bienvenidaText.setText(newName);
            }
        });
    }
}
