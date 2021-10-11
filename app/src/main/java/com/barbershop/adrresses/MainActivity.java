package com.barbershop.adrresses;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.barbershop.model.Aluno;
import com.barbershop.model.dao.AlunoDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ListView listaAlunos;
    private ArrayAdapter<Aluno> adapterView;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lists de alunos");
        generateArrayAdapter();
        initView();
        setOnclickItem();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_main_menu_remove) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Aluno aluno = AlunoDAO.listAll().get(menuInfo.position);
            remove(aluno);
        }
        return super.onContextItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void remove(Aluno aluno){
        AlunoDAO.remove(aluno);
        adapterView.remove(aluno);
    }

    private void setOnclickItem() {
        listaAlunos.setOnItemClickListener((adapterView, view, posicao, l) -> {
            Aluno aluno = AlunoDAO.listAll().get(posicao);
            Intent intent = new Intent(MainActivity.this, FormRegister.class);
            intent.putExtra("aluno", aluno);
            startActivity(intent);
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_main_menu,menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        listaAlunos.setAdapter(adapterView);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initView(){
        listaAlunos = findViewById(R.id.activity_main_lista_alunos);
        listaAlunos.setAdapter(adapterView);
        FloatingActionButton fab = findViewById(R.id.activity_form_register_add_aluno);
        fab.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, FormRegister.class)));
        registerForContextMenu(listaAlunos);
    }

    private void generateArrayAdapter(){
        adapterView = new ArrayAdapter(this, android.R.layout.simple_list_item_1, AlunoDAO.listAll());
    }

}
