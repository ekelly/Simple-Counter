package com.useric.score;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Score extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
			
	private Integer score = 0;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Clear all preferences
        SharedPreferences pref = getSharedPreferences("preferences",MODE_WORLD_WRITEABLE);
   		SharedPreferences.Editor prefedit = pref.edit();
   		prefedit.clear().commit();
        
   		// Set the Activity to display the score
        TextView current_score = (TextView) findViewById(R.id.score);
    	current_score.setText((CharSequence) "0");
        
        // Set up click listeners for all the buttons
        View plus1 = findViewById(R.id.plus1);
        plus1.setOnClickListener(this);
        View plus5 = findViewById(R.id.plus5);
        plus5.setOnClickListener(this);
        View plus10 = findViewById(R.id.plus10);
        plus10.setOnClickListener(this);
        View min1 = findViewById(R.id.min1);
        min1.setOnClickListener(this);
        View min5 = findViewById(R.id.min5);
        min5.setOnClickListener(this);
        View min10 = findViewById(R.id.min10);
        min10.setOnClickListener(this);

    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
    	super.onSaveInstanceState(outState);
    	outState.putInt("current-score", score);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
    	super.onRestoreInstanceState(savedInstanceState);
    	score = (Integer) savedInstanceState.get("current-score");
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	
    	// Get stored value, assign it to score
   		SharedPreferences pref = getSharedPreferences("preferences",MODE_WORLD_WRITEABLE);
    	score = pref.getInt("current_score", score);
    	
    	// Display score
 	   	TextView current_score = (TextView)findViewById(R.id.score);
 	   	current_score.setText((CharSequence) score.toString());
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	SharedPreferences pref = getSharedPreferences("preferences",MODE_WORLD_WRITEABLE);
   		SharedPreferences.Editor prefedit = pref.edit();
		prefedit.putInt("current_score", score).commit();
    }
    
    @Override
    public void onClick(View v) {
    	switch(v.getId()) {
    	case R.id.min1:
    		subtract(1);
    		break;
    	case R.id.min5:
    		subtract(5);
    		break;
    	case R.id.min10:
    		subtract(10);
    		break;
    	case R.id.plus1:
    		add(1);
    		break;
    	case R.id.plus5:
    		add(5);
    		break;
    	case R.id.plus10:
    		add(10);
    		break;
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       super.onCreateOptionsMenu(menu);
       MenuInflater inflater = getMenuInflater();
       inflater.inflate(R.menu.menu, menu);
       return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	   createDialog();
    	   SharedPreferences pref = getSharedPreferences("preferences",MODE_WORLD_WRITEABLE);
    	   score = pref.getInt("current_score", score);
    	   TextView current_score = (TextView)findViewById(R.id.score);
    	   current_score.setText((CharSequence) score.toString());
    	   return true;
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    }
    
    public void createDialog() {
    	// Save current score
    	SharedPreferences pref = getSharedPreferences("preferences",MODE_WORLD_WRITEABLE);
   		SharedPreferences.Editor prefedit = pref.edit();
		prefedit.putInt("current_score", score).commit();
		
		// Start dialog
    	Intent intent = new Intent(this, DialogWithInputBox.class);
        startActivity(intent);
        
        // get new score
        score = pref.getInt("current_score",score);
        Log.d("createDialog", "Score is " + score.toString());
    }

	public void add(int i) {
		score = score + i;
		Log.d("score=",score.toString());
		TextView current_score = (TextView)findViewById(R.id.score);
		current_score.setText((CharSequence) score.toString());
	}

	public void subtract(int i) {
		score = score - i;
		Log.d("score=",score.toString());
		TextView current_score = (TextView)findViewById(R.id.score);
		current_score.setText((CharSequence) score.toString());
	}
}