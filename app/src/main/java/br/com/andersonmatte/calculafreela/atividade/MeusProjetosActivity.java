package br.com.andersonmatte.calculafreela.atividade;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.adapter.ProjetoAdapter;
import br.com.andersonmatte.calculafreela.atividade.projeto.Projeto1Activity;
import br.com.andersonmatte.calculafreela.base.AppCompatActivityBase;
import br.com.andersonmatte.calculafreela.entidade.Projeto;
import es.dmoral.toasty.Toasty;
import io.realm.RealmResults;

public class MeusProjetosActivity extends AppCompatActivityBase {

    private List<Projeto> listaProjeto;
    private ListView listView;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_projetos);
        listView = (ListView) findViewById(R.id.listView);
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
            case R.id.adicionar: {
                //Chama a criação do novo projeto.
                Intent intent = new Intent(MeusProjetosActivity.this, Projeto1Activity.class);
                startActivity(intent);
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    //Chama o Adapter.
    public void populaListViewProjetos(List<Projeto> listaProjetoRecebida){
        final ProjetoAdapter adapter = new ProjetoAdapter(this, listaProjetoRecebida);
        this.listView.setAdapter(adapter);
    }

    //Pega a linha selecionada na ListView e dá a opção ao usuário de apagar ou não o item da lista de projetos.
    public void onClickApagar(final View view){
        if (view.getTag() != null){
            AlertDialog.Builder builderAlert = new AlertDialog.Builder(this, R.style.AlertDialog);
            builderAlert.setTitle(getResources().getString(R.string.apagar));
            builderAlert.setMessage(getResources().getString(R.string.apagar_projeto));
            builderAlert.setPositiveButton(getResources().getString(R.string.sim), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (view.getTag() != null){
                        excluir((Integer) view.getTag());
                    }
                }
            });
            builderAlert.setNegativeButton(getResources().getString(R.string.nao), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toasty.warning(getApplicationContext(), getResources().getString(R.string.nao_salvar), Toast.LENGTH_SHORT, true).show();
                }
            });
            alertDialog = builderAlert.create();
            alertDialog.show();
        }
    }

    //Apaga o registro selecionado da Lista de favoritos.
    public void excluir(int posicao){
        if (this.listaProjeto != null && !this.listaProjeto.isEmpty()){
            super.realm.beginTransaction();
            Projeto projeto = this.listaProjeto.get(posicao);
            projeto.deleteFromRealm();
            super.realm.commitTransaction();
            Toasty.warning(this, getResources().getString(R.string.registro_deletado), Toast.LENGTH_SHORT, true).show();
            //reseleciona
            this.buscaProjetos();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

}
