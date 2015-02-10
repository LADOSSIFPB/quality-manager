package br.edu.ifpb.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.conection.PreencherSpinnerEditalAsyncTask;
import br.edu.ifpb.qmanager.entidade.Edital;
import br.edu.ifpb.util.AdapterListView;
import br.edu.ifpb.util.Constantes;
import br.edu.ifpb.util.ItemListView;

public class EditalActivity extends Activity implements OnItemClickListener,
		OnClickListener {

	private ListView listView;
	private AdapterListView adapterListView;
	private ArrayList<ItemListView> itemsFunction;
	private List<Edital> editais;
	private Button buttonAdicionar;
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

		findViews();

		listView.setOnItemClickListener(this);
		buttonAdicionar.setOnClickListener(this);

		createListView();
	}

	@Override
	public void onClick(View v) {
		intent = new Intent(this, CadastrarEditalActivity.class);
		params.putInt("Gestor", IdPessoa);
		intent.putExtras(params);
		startActivity(intent);
		finish();
	}

	public void createListView() {
		PreencherSpinnerEditalAsyncTask preencherSpinner = new PreencherSpinnerEditalAsyncTask();

		try {
			editais = preencherSpinner.execute().get();

			if (editais != null) {
				itemsFunction = new ArrayList<ItemListView>();

				for (int i = 0; i < editais.size(); i++) {
					itemsFunction.add(new ItemListView(editais.get(i)
							.getNumero() + "/" + editais.get(i).getAno(),
							R.drawable.ic_write_icon));
				}

				// Cria o adapter
				adapterListView = new AdapterListView(this, itemsFunction);

				// Define o Adapter
				listView.setAdapter(adapterListView);
				// Cor quando a lista é selecionada para rolagem.
				listView.setCacheColorHint(Color.TRANSPARENT);
			} else {
				Toast toast = Toast.makeText(getApplicationContext(),
						Constantes.ERROR_EDITAL_NULL, Toast.LENGTH_LONG);
				toast.show();
				Intent intent = new Intent(getApplicationContext(),
						GestorActivity.class);
				startActivity(intent);
				finish();
			}
		} catch (InterruptedException e) {
			Toast toast = Toast.makeText(getApplicationContext(),
					Constantes.ERROR_PROBLEMA_COMUNICACAO_SERVIDOR,
					Toast.LENGTH_SHORT);
			toast.show();
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
		} catch (ExecutionException e) {
			Toast toast = Toast.makeText(getApplicationContext(),
					Constantes.ERROR_PROBLEMA_COMUNICACAO_SERVIDOR,
					Toast.LENGTH_SHORT);
			toast.show();
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
			finish();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

	}

	public void findViews() {
		listView = (ListView) findViewById(R.id.listView);
		buttonAdicionar = (Button) findViewById(R.id.buttonAdicionar);
	}

}
