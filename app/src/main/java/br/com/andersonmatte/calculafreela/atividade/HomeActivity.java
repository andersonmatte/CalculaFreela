package br.com.andersonmatte.calculafreela.atividade;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.base.AppCompatActivityBase;

public class HomeActivity extends AppCompatActivityBase implements BottomNavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Cria o BottomNavigationView.
        this.navigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        this.navigationView.setOnNavigationItemSelectedListener(this);
    }

    //Define os itens do BottomNavigationView e chama as suas Activities conforme seleção.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_valorHora: {
                Intent intent = new Intent(this, ValorHoraActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.menu_meusProjetos: {
                Intent intent = new Intent(this, MeusProjetosActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.menu_sobre: {
                Intent intent = new Intent(this, SobreActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        return true;
    }

}
