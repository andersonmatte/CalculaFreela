package br.com.andersonmatte.calculafreela.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.andersonmatte.calculafreela.R;
import br.com.andersonmatte.calculafreela.entidade.Projeto;

public class ProjetoAdapter extends ArrayAdapter<Projeto> {

    private List<Projeto> listaProjetos;
    private Context context;
    private TextView tv_nomeProjeto, tv_diasProjeto, tv_horasDiarias, tv_valorHora, tv_totalProjeto;

    public ProjetoAdapter(Context context, List<Projeto> listaProjetoRecebido) {
        super(context, 0, listaProjetoRecebido);
        this.listaProjetos = listaProjetoRecebido;
        this.context = context;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.lista_projeto, null);
        //Aqui ocorre a mágica no setTag onde é passado a posição da ListView!
        view.setTag(position);
        if (listaProjetos.get(position) != null) {
            tv_nomeProjeto = (TextView) view.findViewById(R.id.tv_nomeProjeto);
            tv_nomeProjeto.setText(listaProjetos.get(position).getNome());
            tv_diasProjeto = (TextView) view.findViewById(R.id.tv_diasProjeto);
            if (listaProjetos.get(position).getDias() != null) {
                tv_diasProjeto.setText(listaProjetos.get(position).getDias() + " " + context.getResources().getString(R.string.projeto_2));
            } else {
                tv_diasProjeto.setText("0" + " " + context.getResources().getString(R.string.projeto_2));
            }
            tv_horasDiarias = (TextView) view.findViewById(R.id.tv_horasDiarias);
            if (listaProjetos.get(position).getCargaHoraria() != null) {
                tv_horasDiarias.setText(listaProjetos.get(position).getCargaHoraria().toString() + " " + context.getResources().getString(R.string.projeto_3).toString());
            } else {
                tv_horasDiarias.setText("0" + " " + context.getResources().getString(R.string.projeto_3).toString());
            }
            tv_valorHora = (TextView) view.findViewById(R.id.tv_valorHora);
            if (listaProjetos.get(position).getValorHora() != null) {
                tv_valorHora.setText(context.getResources().getString(R.string.projeto_4) + " " + listaProjetos.get(position).getValorHora().toString());
            } else {
                tv_valorHora.setText(context.getResources().getString(R.string.projeto_4) + " " + "0,00");
            }
            tv_totalProjeto = (TextView) view.findViewById(R.id.tv_totalProjeto);
            if (listaProjetos.get(position).getTotal() != null) {
                tv_totalProjeto.setText(context.getResources().getString(R.string.projeto_5) + " " + listaProjetos.get(position).getTotal().toString());
            } else {
                tv_totalProjeto.setText(context.getResources().getString(R.string.projeto_5) + " " + "0,00");
            }
        }
        return view;
    }

}
