package com.barbershop.adrresses;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.barbershop.model.Aluno;
import com.barbershop.model.dao.AlunoDAO;

public class FormRegister extends AppCompatActivity {
    EditText campoNome;
    EditText campoTelefone;
    EditText campoEmail;
    Aluno aluno;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        setTitle("Agenda");
        Intent intent = this.getIntent();
        aluno = (Aluno) intent.getSerializableExtra("aluno");
        initFields();
        updateAluno();
    }
    private void initFields(){
        campoNome = findViewById(R.id.activity_form_register_nome);
        campoTelefone = findViewById(R.id.activity_form_register_telefone);
        campoEmail = findViewById(R.id.activity_form_register_email);
        if(aluno != null) {
            campoNome.setText(aluno.getNome());
            campoEmail.setText(aluno.getEmail());
            campoTelefone.setText(aluno.getTelefone());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_form_register_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activiy_form_register_menu_save){
            Aluno aluno = updateAluno();
            if(aluno != null)
                finish();
            else
                createAluno();
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private Aluno
    updateAluno(){
        if(aluno != null){
            aluno = Aluno.builder()
                    .id(aluno.getId())
                    .nome(campoNome.getText().toString())
                    .telefone(campoTelefone.getText().toString())
                    .email(campoEmail.getText().toString()).build();
            AlunoDAO.update(aluno);
            Toast.makeText(FormRegister.this, "Aluno Salvo", Toast.LENGTH_SHORT).show();
            return aluno;
        }
        return null;

    }

    public void createAluno(){
        Aluno estudante = Aluno.builder().nome(campoNome.getText().toString()).email(campoEmail.getText().toString()).telefone(campoTelefone.getText().toString()).build();
        AlunoDAO.save(estudante);
        Toast.makeText(FormRegister.this, "Aluno Salvos", Toast.LENGTH_LONG).show();
    }


}