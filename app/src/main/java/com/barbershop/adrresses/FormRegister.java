package com.barbershop.adrresses;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.barbershop.model.Aluno;
import com.barbershop.model.dao.AlunoDAO;

import java.util.UUID;

public class FormRegister extends AppCompatActivity {
    EditText campoNome;
    EditText campoTelefone;
    EditText campoEmail;
    Button salvar;
    Aluno aluno;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        setTitle("Agenda");
        initFields();
        salvar.setOnClickListener(view -> {
            createAluno();
        });
        update();

    }
    private void initFields(){
        salvar = findViewById(R.id.activity_form_register_button_salva);
        campoNome = findViewById(R.id.activity_form_register_nome);
        campoTelefone = findViewById(R.id.activity_form_register_telefone);
        campoEmail = findViewById(R.id.activity_form_register_email);


    }
    private void createAluno(){
        Toast.makeText(FormRegister.this, "Salvando dados", Toast.LENGTH_LONG).show();
        Aluno estudante = Aluno.builder().nome(campoNome.getText().toString()).email(campoEmail.getText().toString()).telefone(campoTelefone.getText().toString()).build();
        AlunoDAO.save(estudante);
        Toast.makeText(FormRegister.this, "Dados Salvos", Toast.LENGTH_LONG).show();
        finish();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void update(){
        Intent intent = this.getIntent();
        Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if(aluno != null){
            campoNome.setText(aluno.getNome());
            campoEmail.setText(aluno.getEmail());
            campoTelefone.setText(aluno.getTelefone());
            salvar.setOnClickListener(view -> {
                Toast.makeText(FormRegister.this, "Atualizando aluno", Toast.LENGTH_SHORT).show();
                Aluno a = Aluno.builder().id(aluno.getId()).nome(campoNome.getText().toString()).telefone(campoTelefone.getText().toString()).email(campoEmail.getText().toString()).build();
                AlunoDAO.update(a);
                Toast.makeText(FormRegister.this, "Aluno Salvo", Toast.LENGTH_SHORT).show();
                finish();
            });
        }
    }

}