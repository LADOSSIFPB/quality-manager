package br.edu.ifpb;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DocenteActivity extends Activity implements OnItemClickListener {
	
	private ListView listView;
    private AdapterListView adapterListView;
    private ArrayList<itemListView> itens;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lista);
		
		//Pega a referencia do ListView
		listView = (ListView) findViewById(R.id.listView);
		//Define o Listener quando alguem clicar no item.
		listView.setOnItemClickListener(this);
		
		createListView();
	}
	
	public void createListView()
	{
		//Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<itemListView>();
        itens.add(new itemListView("Inscrição Projeto", R.drawable.inscricao));
        itens.add(new itemListView("Caixa de Mensagem", R.drawable.caixa_mensagem));
        itens.add(new itemListView("Validar Relatório", R.drawable.validacao));
        
        //Cria o adapter
        adapterListView = new AdapterListView(this, itens);
        
        //Define o Adapter
        listView.setAdapter(adapterListView);
        //Cor quando a lista é selecionada para rolagem.
        listView.setCacheColorHint(Color.TRANSPARENT);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		//Pega o item que foi selecionado.
        itemListView item = adapterListView.getItem(arg2);
        //Demostração
        Toast.makeText(this, item.getTexto(), Toast.LENGTH_LONG).show();
	}
}