package br.com.andersonmatte.calculafreela.atividade.valorHora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.entidade.ValorHora;
import es.dmoral.toasty.Toasty;

public class ValorHora4Activity extends AppCompatActivity {

    private ValorHora valorHoraRecebido;
    private EditText semanas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_hora4);
        //Recebe os dados passados na Intent da Classe anterior por mecanismo de Bundle.
        Bundle bundle = getIntent().getBundleExtra("valorHora");
        if (bundle != null) {
            this.valorHoraRecebido = (ValorHora) bundle.getSerializable("resultado");
        }
        semanas = (EditText) findViewById(R.id.semanas);
        Button botaoDiasSemanas = (Button) findViewById(R.id.botaoSemanas);
        botaoDiasSemanas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaForm()) {
                    valorHoraRecebido.setSemanasFerias(Long.parseLong(semanas.getText().toString()));
                    //prepara o objeto para passar para a próxima activity.
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resultado", valorHoraRecebido);
                    //Chama a próxima Activity já com o objeto populado.
                    Intent intentPerfil = new Intent(ValorHora4Activity.this, ValorHora5Activity.class);
                    intentPerfil.putExtra("valorHora", bundle);
                    startActivity(intentPerfil);
                }
            }
        });
    }

    //Valida se o nome do valorHora foi preenchido.
    private Boolean validaForm() {
        if (semanas.getText().toString().isEmpty()) {
            Toasty.warning(this, this.getResources().getString(R.string.validaForm1), Toast.LENGTH_SHORT, true).show();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, ValorHora3Activity.class);
        startActivity(intent);
        this.finish();
    }

}
