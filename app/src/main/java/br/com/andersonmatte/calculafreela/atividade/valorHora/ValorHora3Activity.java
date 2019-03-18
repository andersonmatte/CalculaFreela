package br.com.andersonmatte.calculafreela.atividade.valorHora;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.base.AppCompatActivityBase;
import br.com.andersonmatte.calculafreela.entidade.ValorHora;
import es.dmoral.toasty.Toasty;

public class ValorHora3Activity extends AppCompatActivityBase {

    private ValorHora valorHoraRecebido;
    private EditText diasSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_hora3);
        //Remove APP Title Bar.
        getSupportActionBar().hide();
        //Recebe os dados passados na Intent da Classe anterior por mecanismo de Bundle.
        Bundle bundle = getIntent().getBundleExtra("valorHora");
        if (bundle != null) {
            this.valorHoraRecebido = (ValorHora) bundle.getSerializable("resultado");
        }
        diasSemana = (EditText) findViewById(R.id.diasSemana);
        Button botaoDiasSemana = (Button) findViewById(R.id.botaoDiasSemana);
        botaoDiasSemana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaForm()) {
                    valorHoraRecebido.setDiasSemana(Long.parseLong(diasSemana.getText().toString()));
                    //prepara o objeto para passar para a próxima activity.
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resultado", valorHoraRecebido);
                    //Chama a próxima Activity já com o objeto populado.
                    Intent intentPerfil = new Intent(ValorHora3Activity.this, ValorHora4Activity.class);
                    intentPerfil.putExtra("valorHora", bundle);
                    startActivity(intentPerfil);
                }
            }
        });
    }

    //Valida se o nome do valorHora foi preenchido.
    private Boolean validaForm() {
        if (diasSemana.getText().toString().isEmpty()) {
            Toasty.warning(this, this.getResources().getString(R.string.validaForm1), Toast.LENGTH_SHORT, true).show();
            return false;
        } else if (diasSemana.getText().toString() != null){
            Long diasSemanaValor = Long.parseLong(diasSemana.getText().toString());
            if (diasSemanaValor > 7){
                Toasty.error(this, this.getResources().getString(R.string.validaForm3), Toast.LENGTH_SHORT, true).show();
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
        Intent intent = new Intent(this, ValorHora2Activity.class);
        startActivity(intent);
        this.finish();
    }

}
