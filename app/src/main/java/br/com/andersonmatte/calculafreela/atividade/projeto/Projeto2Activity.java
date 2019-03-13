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

public class Projeto2Activity extends AppCompatActivityBase {

    private Projeto projetoRecebido;
    private EditText valorHora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto2);
        //Recebe os dados passados na Intent da Classe anterior por mecanismo de Bundle.
        Bundle bundle = getIntent().getBundleExtra("projeto");
        if (bundle != null) {
            this.projetoRecebido = (Projeto) bundle.getSerializable("resultado");
        }
        valorHora = (EditText) findViewById(R.id.valorHora);
        Button botaoValorHora = (Button) findViewById(R.id.botaoValorHoraProjeto);
        botaoValorHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaForm()) {
                    projetoRecebido.setValorHora(Double.parseDouble(valorHora.getText().toString()));
                    //prepara o objeto para passar para a próxima activity.
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resultado", projetoRecebido);
                    //Chama a próxima Activity já com o objeto populado.
                    Intent intentPerfil = new Intent(Projeto2Activity.this, Projeto3Activity.class);
                    intentPerfil.putExtra("projeto", bundle);
                    startActivity(intentPerfil);
                }
            }
        });
    }

    //Valida se o valorHora foi preenchido.
    private Boolean validaForm() {
        if (valorHora.getText().toString().isEmpty()) {
            Toasty.warning(this, this.getResources().getString(R.string.validaForm1), Toast.LENGTH_SHORT, true).show();
            return false;
        } else if (valorHora.getText().toString() != null){
            Double valorHoraValor = Double.valueOf(valorHora.getText().toString().replaceAll("[$,.]", ""));
            if (valorHoraValor < 10){
                Toasty.error(this, this.getResources().getString(R.string.validaForm5), Toast.LENGTH_SHORT, true).show();
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
        Intent intent = new Intent(this, Projeto1Activity.class);
        startActivity(intent);
        this.finish();
    }

}
