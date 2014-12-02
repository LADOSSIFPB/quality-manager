package br.edu.ifpb.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.conection.PreencherSpinnerProgramaInstitucionalAsyncTask;
import br.edu.ifpb.qmanager.entidade.ProgramaInstitucional;
import br.edu.ifpb.util.AdapterListView;
import br.edu.ifpb.util.Constantes;
import br.edu.ifpb.util.ItemListView;

public class ConsultarProgramaInstitucionalActivity extends Activity implements
		OnItemClickListener {

	private ListView listView;
	private AdapterListView adapterListView;
	private ArrayList<ItemListView> itemsFunction;
	private List<ProgramaInstitucional> programasInstitucionais;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generic_list);

		findViews();

		listView.setOnItemClickListener(this);

		createListView();
	}

	public void createListView() {
		
		PreencherSpinnerProgramaInstitucionalAsyncTask preencherSpinner = 
				new PreencherSpinnerProgramaInstitucionalAsyncTask();

		try {
			programasInstitucionais = preencherSpinner.execute().get();

			if (programasInstitucionais != null) {
				itemsFunction = new ArrayList<ItemListView>();

				for (int i = 0; i < programasInstitucionais.size(); i++) {
					itemsFunction.add(new ItemListView(programasInstitucionais
							.get(i).getNomeProgramaInstitucional(),
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
						Constantes.ERROR_PROGRAMA_INSTITUCIONAL_NULL,
						Toast.LENGTH_LONG);
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
	}

}
