package br.edu.ifpb;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class DocenteActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_docente);
		
		String lista[] = new String[] {"Inscrição Projetos", "Caixa de Mensagem", "Validar Relatório"};
		
		ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
		ListView lview = (ListView) findViewById(R.id.listView);
		lview.setAdapter(ad);
		lview.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) 
			{
	            Toast.makeText(getApplicationContext(), ((TextView) arg1).getText(), Toast.LENGTH_SHORT).show();
	        }
		});
		
	}
	
}
