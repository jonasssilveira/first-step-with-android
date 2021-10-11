package com.barbershop.model.dao;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.barbershop.model.Aluno;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

public class AlunoDAO {

    private static final List<Aluno> alunos = new ArrayList<>();

    public static void save(Aluno aluno){
        aluno.setId(UUID.randomUUID().toString());
        alunos.add(aluno);
    }

    public static List<Aluno> listAll(){
        return alunos;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void update(Aluno aluno){
        Aluno al = alunos.stream().filter(aluno1 -> aluno.getId().equals(aluno1.getId())).findAny().orElse(null);
        al.setTelefone(aluno.getTelefone());
        al.setEmail(aluno.getEmail());
        al.setNome(aluno.getNome());
        int i = alunos.indexOf(al);
        alunos.set(i, al);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void remove(Aluno aluno) {
        Aluno al = alunos.stream().filter(aluno1 -> aluno.getId().equals(aluno1.getId())).findAny().orElse(null);
        alunos.remove(al);
    }
}
