package com.example.myapplication;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DatabaseHelper;
import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNome, editTextNumero, editTextEndereco;
    private Button buttonSalvar, buttonCarregar;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = findViewById(R.id.editTextNome);
        editTextNumero = findViewById(R.id.editTextNumero);
        editTextEndereco = findViewById(R.id.editTextEndereco);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        buttonCarregar = findViewById(R.id.buttonCarregar);

        // Criação ou abertura do banco de dados SQLite
        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();


        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvarDados();
            }
        });

        buttonCarregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                carregarDados();
            }
        });
    }

    private void salvarDados() {
        String nome = editTextNome.getText().toString().trim();
        String numero = editTextNumero.getText().toString().trim();
        String endereco = editTextEndereco.getText().toString().trim();

        // Verifica se todos os campos estão preenchidos
        if (nome.isEmpty() || numero.isEmpty() || endereco.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Insere os dados no banco de dados SQLite
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("numero", numero);
        values.put("endereco", endereco);

        long result = database.insert("contatos", null, values);

        if (result != -1) {
            Toast.makeText(this, "Dados salvos com sucesso", Toast.LENGTH_SHORT).show();
            // Limpa os campos após salvar
            editTextNome.getText().clear();
            editTextNumero.getText().clear();
            editTextEndereco.getText().clear();
        } else {
            Toast.makeText(this, "Erro ao salvar dados", Toast.LENGTH_SHORT).show();
        }
    }

    private void carregarDados() {
        // Carrega os dados do banco de dados SQLite
        Cursor cursor = database.rawQuery("SELECT * FROM contatos", null);

        if (cursor.moveToFirst()) {
            do {
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String numero = cursor.getString(cursor.getColumnIndex("numero"));
                String endereco = cursor.getString(cursor.getColumnIndex("endereco"));

                String mensagem = "Nome: " + nome + ", Número: " + numero + ", Endereço: " + endereco;
                Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
            } while (cursor.moveToNext());
        } else {
            Toast.makeText(this, "Nenhum dado encontrado", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
    }
}
