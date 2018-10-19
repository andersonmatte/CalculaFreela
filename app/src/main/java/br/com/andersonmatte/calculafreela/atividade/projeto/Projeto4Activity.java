package br.com.andersonmatte.calculafreela.atividade.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.atividade.MeusProjetosActivity;
import br.com.andersonmatte.calculafreela.base.AppCompatActivityBase;
import br.com.andersonmatte.calculafreela.entidade.Projeto;

public class Projeto4Activity extends AppCompatActivityBase {

    private Projeto projetoRecebido;
    private TextView valorEstimado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto4);
        //Recebe os dados passados na Intent da Classe anterior por mecanismo de Bundle.
        Bundle bundle = getIntent().getBundleExtra("projeto");
        if (bundle != null) {
            this.projetoRecebido = (Projeto) bundle.getSerializable("resultado");
        }
        Double valorProjeto = this.calculaValorProjeto();
        valorEstimado = (TextView) findViewById(R.id.valorEstimado);
        valorEstimado.setText(valorProjeto.toString());
        Button botaoSalvarProjeto = (Button) findViewById(R.id.botaoSalvarProjeto);
        botaoSalvarProjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvar();
            }
        });
    }

    //Retorna o valor do projeto.
    public Double calculaValorProjeto(){
        return this.projetoRecebido.getValorHora() * this.projetoRecebido.getCargaHoraria() * this.projetoRecebido.getDias();
    }

    //Salva o objeto no Banco,
    public void salvar() {
        super.realm.beginTransaction();
        super.realm.insertOrUpdate(this.projetoRecebido);
        super.realm.commitTransaction();
        //Volta para a Lista de projetos.
        Intent intent = new Intent(Projeto4Activity.this, MeusProjetosActivity.class);
        startActivity(intent);
        finish();
    }

}
