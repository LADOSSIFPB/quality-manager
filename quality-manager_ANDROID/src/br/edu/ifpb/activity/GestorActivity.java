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
		IdPessoa = params.getInt("Coordenador");

		// Pega a referencia do ListView
		listView = (ListView) findViewById(R.id.listView);
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
		itemsFunction.add(new ItemListView("Cadastrar Projeto",
				R.drawable.ic_action_projeto));
		itemsFunction.add(new ItemListView("Cadastrar Curso",
				R.drawable.ic_action_curso));
		itemsFunction.add(new ItemListView("Cadastrar Turma",
				R.drawable.ic_action_turma));

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
			params.putInt("Coordenador", IdPessoa);
			intent.putExtras(params);
			break;
		case "Cadastrar Programa Institucional":
			intent = new Intent(this,
					CadastrarProgramaInstitucionalActivity.class);
			params.putInt("Coordenador", IdPessoa);
			intent.putExtras(params);
			break;
		}
		startActivity(intent);
	}
}
