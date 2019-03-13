package br.com.andersonmatte.calculafreela.atividade.projeto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.base.AppCompatActivityBase;
import br.com.andersonmatte.calculafreela.entidade.Projeto;
import es.dmoral.toasty.Toasty;

public class Projeto4Activity extends AppCompatActivityBase {

    private Projeto projetoRecebido;
    private EditText diasProjeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto4);
        //Remove APP Title Bar.
        getSupportActionBar().hide();
        //Recebe os dados passados na Intent da Classe anterior por mecanismo de Bundle.
        Bundle bundle = getIntent().getBundleExtra("projeto");
        if (bundle != null) {
            this.projetoRecebido = (Projeto) bundle.getSerializable("resultado");
        }
        diasProjeto = (EditText) findViewById(R.id.diasProjeto);
        Button botaoDiasProjeto = (Button) findViewById(R.id.botaoDiasProjetoProjeto);
        botaoDiasProjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaForm()) {
                    projetoRecebido.setDias(Long.parseLong(diasProjeto.getText().toString()));
                    //prepara o objeto para passar para a próxima activity.
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resultado", projetoRecebido);
                    //Chama a próxima Activity já com o objeto populado.
                    Intent intentPerfil = new Intent(Projeto4Activity.this, Projeto5Activity.class);
                    intentPerfil.putExtra("projeto", bundle);
                    startActivity(intentPerfil);
                }
            }
        });
    }

    //Valida se o nome do valorHora foi preenchido.
    private Boolean validaForm() {
        if (diasProjeto.getText().toString().isEmpty()) {
            Toasty.warning(this, this.getResources().getString(R.string.validaForm1), Toast.LENGTH_SHORT, true).show();
            return false;
        } else if (diasProjeto.getText().toString() != null){
            Long diasProjetoValor = Long.parseLong(diasProjeto.getText().toString());
            if (diasProjetoValor > 365){
                Toasty.error(this, this.getResources().getString(R.string.validaForm6), Toast.LENGTH_SHORT, true).show();
                return false;
            }
            return true;
        } else {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, Projeto3Activity.class);
        startActivity(intent);
        this.finish();
    }

}
