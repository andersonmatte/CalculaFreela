package br.com.andersonmatte.calculafreela.atividade.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.atividade.MeusProjetosActivity;
import br.com.andersonmatte.calculafreela.base.AppCompatActivityBase;
import br.com.andersonmatte.calculafreela.entidade.Projeto;

public class Projeto5Activity extends AppCompatActivityBase {

    private Projeto projetoRecebido;
    private TextView valorEstimado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto5);
        //Recebe os dados passados na Intent da Classe anterior por mecanismo de Bundle.
        Bundle bundle = getIntent().getBundleExtra("projeto");
        if (bundle != null) {
            this.projetoRecebido = (Projeto) bundle.getSerializable("resultado");
        }
        Double valorTotalProjeto = this.calculaValorProjeto();
        Locale ptBr = new Locale("pt", "BR");
        String valorProjetoString = NumberFormat.getCurrencyInstance(ptBr).format(valorTotalProjeto);
        valorEstimado = (TextView) findViewById(R.id.valorEstimado);
        valorEstimado.setText(valorProjetoString);
        this.projetoRecebido.setTotal(valorTotalProjeto);
        Button botaoSalvarProjeto = (Button) findViewById(R.id.botaoSalvarProjetoProjeto);
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
        Intent intent = new Intent(Projeto5Activity.this, MeusProjetosActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Projeto4Activity.class);
        startActivity(intent);
        this.finish();
    }

}
