package com.example.teste1;

import android.net.ParseException;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class Register extends Activity {
	
	Button Registrar, Cancelar;
	EditText E_Username, E_Email, E_Password;
	
	String Username, Email, Password;
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
                
        Registrar = (Button) findViewById(R.id.Register_Register);
        Cancelar = (Button) findViewById(R.id.Register_Cancelar);
        E_Username = (EditText) findViewById(R.id.Register_Username);
        E_Email = (EditText) findViewById(R.id.Register_Email);
        E_Password = (EditText) findViewById(R.id.Register_Password);
        
        
        Registrar.setOnClickListener(new View.OnClickListener() {
        				
			@Override
			public void onClick(View view) {
				switch (view.getId())
				{
				case R.id.Register_Register : ButtonRegister();
				break;
				}	
			}
		});
        
        
        Cancelar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				switch (view.getId())
				{
				case R.id.Register_Cancelar : ButtonCancelClick();
				break;
				}	
			}
		});
	}
    
    private void ButtonRegister() {
    	
    	Username = E_Username.getText().toString();
    	Email = E_Email.getText().toString();
    	Password = E_Password.getText().toString();
    	
    	ParseUser Object_Parse_Usuario = new ParseUser();
    	Object_Parse_Usuario.setUsername(Username);
    	Object_Parse_Usuario.setEmail(Email);
    	Object_Parse_Usuario.setPassword(Password);
    	
    	Object_Parse_Usuario.signUpInBackground(new SignUpCallback() {
			
			@Override
			public void done(com.parse.ParseException e) {
				// TODO Auto-generated method stub
				if (e == null) {
						ShowOneButtonDialog("Cadastrado");
				    } else {
				    	ShowOneButtonDialog("Náo foi possivel cadastrar o usuario, por favor, verifique a sua conexão com a internet.");
				    }
			}
		});
    }
    
    private void ButtonCancelClick() {
    	startActivity(new Intent("com.exemple.teste1.Login"));
    }
    
    private void ShowOneButtonDialog(String Mensagem)
 	{
 		AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
 		dialogBuilder.setTitle("Teste1");
 		dialogBuilder.setMessage(Mensagem);
 		dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Toast.makeText(getApplicationContext(), "Você clicou em OK", Toast.LENGTH_SHORT).show();
				
			}
		});
 		AlertDialog alertDialog = dialogBuilder.create();
 		alertDialog.show();
 	}
    
    
  //Função que esconde o teclado quando se clica na área fora do teclado.
   	@Override
   	public boolean dispatchTouchEvent(MotionEvent event) {
   	View view = getCurrentFocus();
   	boolean ret = super.dispatchTouchEvent(event);

   	if (view instanceof EditText) {
   	    View w = getCurrentFocus();
   	    int scrcoords[] = new int[2];
   	    w.getLocationOnScreen(scrcoords);
   	    float x = event.getRawX() + w.getLeft() - scrcoords[0];
   	    float y = event.getRawY() + w.getTop() - scrcoords[1];

   	    if (event.getAction() == MotionEvent.ACTION_UP 
   	&& (x < w.getLeft() || x >= w.getRight() 
   	|| y < w.getTop() || y > w.getBottom()) ) { 
   	        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
   	        imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
   	    }
   	}
   	return ret;
   	}
}
