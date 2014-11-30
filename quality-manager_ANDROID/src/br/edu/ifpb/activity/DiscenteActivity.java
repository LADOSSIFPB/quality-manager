package br.edu.ifpb.activity;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;
import br.edu.ifpb.R;
import br.edu.ifpb.util.AdapterListView;
import br.edu.ifpb.util.ItemListView;

public class DiscenteActivity extends Activity implements OnItemClickListener {

	private ListView listView;
	private AdapterListView adapterListView;
	private ArrayList<ItemListView> itemsFunction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_generic_list);

		// Pega a referencia do ListView
		findViews();
		
		// Define o Listener quando alguem clicar no item.
		listView.setOnItemClickListener(this);

		createListView();
	}

	public void createListView() {
		// Criamos nossa lista que preenchera o ListView
		itemsFunction = new ArrayList<ItemListView>();

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
		// Demostração
		Toast.makeText(this, items.getTexto(), Toast.LENGTH_LONG).show();
	}
	
	public void findViews() {
		listView = (ListView) findViewById(R.id.listView);
	}
}