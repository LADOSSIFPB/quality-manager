package br.edu.ifpb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ChamadaTelefonicaBroadcast extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) 
	{
		Toast toast = Toast.makeText(context, "Ligação Telefonica!", Toast.LENGTH_SHORT);
		toast.show();
	}

}
