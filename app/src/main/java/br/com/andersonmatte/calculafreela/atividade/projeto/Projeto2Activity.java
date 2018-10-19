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

public class Projeto2Activity extends AppCompatActivityBase {

    private Projeto projetoRecebido;
    private EditText cargaHoraria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto2);
        //Recebe os dados passados na Intent da Classe anterior por mecanismo de Bundle.
        Bundle bundle = getIntent().getBundleExtra("projeto");
        if (bundle != null) {
            this.projetoRecebido = (Projeto) bundle.getSerializable("resultado");
        }
        cargaHoraria = (EditText) findViewById(R.id.cargaHoraria);
        Button botaoCargaHoraria = (Button) findViewById(R.id.botaoCargaHoraria);
        botaoCargaHoraria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaForm()) {
                    projetoRecebido.setCargaHoraria(Long.parseLong(cargaHoraria.getText().toString()));
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

    //Valida se o nome do projeto foi preenchido.
    private Boolean validaForm() {
        if (cargaHoraria.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.erroValidaForm), Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

}
