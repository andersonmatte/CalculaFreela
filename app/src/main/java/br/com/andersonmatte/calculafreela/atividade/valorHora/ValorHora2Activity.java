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

public class ValorHora2Activity extends AppCompatActivityBase {

    private ValorHora valorHoraRecebido;
    private EditText cargaHoraria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor_hora2);
        //Remove APP Title Bar.
        getSupportActionBar().hide();
        //Recebe os dados passados na Intent da Classe anterior por mecanismo de Bundle.
        Bundle bundle = getIntent().getBundleExtra("valorHora");
        if (bundle != null) {
            this.valorHoraRecebido = (ValorHora) bundle.getSerializable("resultado");
        }
        cargaHoraria = (EditText) findViewById(R.id.cargaHoraria);
        Button botaoCargaHoraria = (Button) findViewById(R.id.botaoCargaHoraria);
        botaoCargaHoraria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaForm()) {
                    valorHoraRecebido.setCaragaHorariaDia(Long.parseLong(cargaHoraria.getText().toString()));
                    //prepara o objeto para passar para a próxima activity.
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resultado", valorHoraRecebido);
                    //Chama a próxima Activity já com o objeto populado.
                    Intent intentPerfil = new Intent(ValorHora2Activity.this, ValorHora3Activity.class);
                    intentPerfil.putExtra("valorHora", bundle);
                    startActivity(intentPerfil);
                }
            }
        });
    }

    //Valida se o nome do valorHora foi preenchido.
    private Boolean validaForm() {
        if (cargaHoraria.getText().toString().isEmpty()) {
            Toasty.warning(this, this.getResources().getString(R.string.validaForm1), Toast.LENGTH_SHORT, true).show();
            return false;
        } else if (cargaHoraria.getText().toString() != null){
            Long cargaHorariaValor = Long.parseLong(cargaHoraria.getText().toString());
            if (cargaHorariaValor > 23){
                Toasty.error(this, this.getResources().getString(R.string.validaForm4), Toast.LENGTH_SHORT, true).show();
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
        Intent intent = new Intent(this, ValorHora1Activity.class);
        startActivity(intent);
        this.finish();
    }

}
