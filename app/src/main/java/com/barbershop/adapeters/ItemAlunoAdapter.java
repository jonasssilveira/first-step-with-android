package com.barbershop.adapeters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.barbershop.adrresses.R;
import com.barbershop.model.Aluno;
import com.barbershop.model.dao.AlunoDAO;

import java.util.List;

public class ItemAlunoAdapter extends BaseAdapter {
    private Context context;
    private List<Aluno> alunos;
    private TextView nome;
    private TextView telefone;
    public ItemAlunoAdapter(Context context) {
        this.context = context;
        alunos = AlunoDAO.listAll();
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.indexOf(alunos.get(i));
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1 = getInflate(viewGroup);
        vincula(i, view1);
        return  view1;
    }

    private View getInflate(ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_aluno, viewGroup, false);
    }

    private void vincula(int i, View view1) {
        nome = view1.findViewById(R.id.item_aluno_nome);
        telefone = view1.findViewById(R.id.item_aluno_telefone);
        Aluno aluno = alunos.get(i);
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
    }

    public void remove(Aluno aluno) {
        alunos.remove(aluno);
        notifyDataSetChanged();
    }
}
