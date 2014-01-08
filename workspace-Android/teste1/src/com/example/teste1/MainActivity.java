package com.example.teste1;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	
	private final int DuracaoSplash = 5000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				Intent minhaAcao = new Intent(MainActivity.this,Login.class);
				
				MainActivity.this.startActivity(minhaAcao);
				
				MainActivity.this.finish();
				
			}
		}, DuracaoSplash);
	}
}
