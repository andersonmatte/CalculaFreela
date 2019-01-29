package br.com.andersonmatte.calculafreela.atividade;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.adapter.ProjetoAdapter;
import br.com.andersonmatte.calculafreela.atividade.projeto.NovoProjetoActivity;
import br.com.andersonmatte.calculafreela.base.AppCompatActivityBase;
import br.com.andersonmatte.calculafreela.entidade.Projeto;
import io.realm.RealmResults;

public class MeusProjetosActivity extends AppCompatActivityBase {

    private List<Projeto> listaProjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_projetos);
        this.buscaProjetos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    //Busca no BD os projetos já salvos.
    public void buscaProjetos(){
        RealmResults<Projeto> listaProjetoRecuperada = super.realm.where(Projeto.class).findAllAsync();
        listaProjetoRecuperada.load();
        if (listaProjetoRecuperada != null && !listaProjetoRecuperada.isEmpty()){
            this.listaProjeto = listaProjetoRecuperada;
            //Chama montagem da ListView.
            this.populaListViewProjetos(this.listaProjeto);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_menu: {
                //Chama a criação do novo projeto.
                Intent intent = new Intent(MeusProjetosActivity.this, NovoProjetoActivity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //Chama o Adapter.
    public void populaListViewProjetos(List<Projeto> listaProjetoRecebida){
        ListView listView = (ListView) findViewById(R.id.listView);
        final ProjetoAdapter adapter = new ProjetoAdapter(this, listaProjetoRecebida);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

}
