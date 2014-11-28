package br.edu.ifpb.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.edu.ifpb.R;
import br.edu.ifpb.util.AdapterListView;
import br.edu.ifpb.util.ItemListView;

public class GestorActivity extends Activity implements OnItemClickListener {

	private ListView listView;
	private AdapterListView adapterListView;
	private ArrayList<ItemListView> itemsFunction;
	private Intent intent;
	private Bundle params;
	private int IdPessoa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generic_list);

		intent = getIntent();
		params = intent.getExtras();
		IdPessoa = params.getInt("Gestor");

		findView();

		// Define o Listener quando alguem clicar no item.
		listView.setOnItemClickListener(this);

		createListView();
	}

	public void createListView() {
		// Criamos nossa lista que preenchera o ListView
		itemsFunction = new ArrayList<ItemListView>();
		itemsFunction.add(new ItemListView(
				"Cadastrar Instituição Financiadora",
				R.drawable.ic_action_cadastrar_instituicao));
		itemsFunction.add(new ItemListView("Cadastrar Programa Institucional",
				R.drawable.ic_action_cadastrar_programa_institucional));
		itemsFunction.add(new ItemListView("Cadastrar Edital",
				R.drawable.ic_action_edital));
		itemsFunction.add(new ItemListView(
				"Consultar Instituição Financiadora",
				R.drawable.ic_action_cadastrar_instituicao));
		itemsFunction.add(new ItemListView("Consultar Programa Institucional",
				R.drawable.ic_action_cadastrar_programa_institucional));
		itemsFunction.add(new ItemListView("Consultar Edital",
				R.drawable.ic_action_edital));

		// Cria o adapter
		adapterListView = new AdapterListView(this, itemsFunction);

		// Define o Adapter
		listView.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para rolagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// Pega o item que foi selecionado.
		ItemListView items = adapterListView.getItem(arg2);
		// Selecionar qual item
		switch (items.getTexto()) {
		case "Cadastrar Instituição Financiadora":
			intent = new Intent(this,
					CadastrarInstituicaoFinanciadoraActivity.class);
			params.putInt("Gestor", IdPessoa);
			intent.putExtras(params);
			break;
		case "Cadastrar Programa Institucional":
			intent = new Intent(this,
					CadastrarProgramaInstitucionalActivity.class);
			params.putInt("Gestor", IdPessoa);
			intent.putExtras(params);
			break;
		case "Cadastrar Edital":
			intent = new Intent(this, CadastrarEditalActivity.class);
			params.putInt("Gestor", IdPessoa);
			intent.putExtras(params);
			break;
		case "Consultar Instituição Financiadora":
			intent = new Intent(this,
					ConsultarInstituicaoFinanciadoraActivity.class);
			break;
		case "Consultar Programa Institucional":
			intent = new Intent(this,
					ConsultarProgramaInstitucionalActivity.class);
			break;
		case "Consultar Edital":
			intent = new Intent(this, ConsultarEditalActivity.class);
			break;
		}
		startActivity(intent);
	}

	public void findView() {
		listView = (ListView) findViewById(R.id.listView);
	}
}
