package com.useric.score;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import java.lang.Integer;
import android.content.SharedPreferences;

public class DialogWithInputBox extends Activity {
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final AlertDialog.Builder alert = new AlertDialog.Builder(this);
		final EditText input = new EditText(this);
		alert.setView(input);
		input.setInputType(DEFAULT_KEYS_DIALER);
		input.setText("20");
		alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				String value = input.getText().toString().trim();				
				try {
					int v = Integer.parseInt(value);
			        SharedPreferences pref = getSharedPreferences("preferences",MODE_WORLD_WRITEABLE);
					SharedPreferences.Editor prefedit = pref.edit();
					prefedit.putInt("current_score", v).commit();
					Log.d("Dialog","Score is " + value);
				} catch(NumberFormatException nfe) {
				   System.out.println("Could not parse " + nfe);
				}
				finish();
			}
		});

		alert.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.cancel();
						finish();
					}
				});
		alert.show();

	}
}
