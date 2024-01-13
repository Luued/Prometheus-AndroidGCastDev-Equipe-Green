package com.example.exercicio18;

// MainActivity.java
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referências aos elementos da interface do usuário
        final TextView welcomeMessage = findViewById(R.id.welcomeMessage);
        Button exitButton = findViewById(R.id.exitButton);

        // Configuração do texto de boas-vindas
        welcomeMessage.setText("Bem-vindo à TV Android!");

        // Configuração do evento de clique para o botão de sair
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Fecha a aplicação
            }
        });
    }



}
