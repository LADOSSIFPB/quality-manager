package br.edu.ifpb.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.conection.PreencherSpinnerInstituicaoFinanciadoraAsyncTask;
import br.edu.ifpb.qmanager.entidade.InstituicaoFinanciadora;
import br.edu.ifpb.util.AdapterListView;
import br.edu.ifpb.util.ItemListView;

public class ConsultarInstituicaoFinanciadoraActivity extends Activity implements
		OnItemClickListener {

	private ListView listView;
	private AdapterListView adapterListView;
	private ArrayList<ItemListView> itemsFunction;
	private List<InstituicaoFinanciadora> instituicoesFinanciadoras;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generic_list);

		// Pega a referencia do ListView
		listView = (ListView) findViewById(R.id.listView);
		// Define o Listener quando alguem clicar no item.
		listView.setOnItemClickListener(this);

		createListView();
	}

	public void createListView() {
		PreencherSpinnerInstituicaoFinanciadoraAsyncTask preencherSpinner = new PreencherSpinnerInstituicaoFinanciadoraAsyncTask(
				this);

		try {
			instituicoesFinanciadoras = preencherSpinner.execute().get();
		} catch (InterruptedException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
		} catch (ExecutionException e) {
			Toast toast = Toast.makeText(this, "ERRO: Conexão Encerrada",
					Toast.LENGTH_LONG);
		}

		itemsFunction = new ArrayList<ItemListView>();

		for (int i = 0; i < instituicoesFinanciadoras.size(); i++) {
			itemsFunction
					.add(new ItemListView(instituicoesFinanciadoras.get(i)
							.getNomeInstituicaoFinanciadora(),
							R.drawable.ic_write_icon));
		}

		// Cria o adapter
		adapterListView = new AdapterListView(this, itemsFunction);

		// Define o Adapter
		listView.setAdapter(adapterListView);
		// Cor quando a lista é selecionada para rolagem.
		listView.setCacheColorHint(Color.TRANSPARENT);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}

}
