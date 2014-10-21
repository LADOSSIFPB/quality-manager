package br.edu.ifpb.util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.edu.ifpb.R;

public class AdapterListView extends BaseAdapter {
	
	private LayoutInflater mInflater;
	private List<ItemListView> itens;

	public AdapterListView(Context context, List<ItemListView> itens) {
        //Itens do listview
        this.itens = itens;
        //Objeto responsável por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }

	@Override
	public int getCount() {
		return itens.size();
	}

	@Override
	public ItemListView getItem(int position) {
		return itens.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent)
    {
        //Pega o item de acordo com a posção.
        ItemListView item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.item_list, null);
 
        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        ((TextView) view.findViewById(R.id.textView)).setText(item.getTexto());
        ((ImageView) view.findViewById(R.id.imageView)).setImageResource(item.getIconeRid());
 
        return view;
    }

}
