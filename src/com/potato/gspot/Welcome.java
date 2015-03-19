package com.potato.gspot;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

public class Welcome extends Activity {
	
	AccountsDatabaseAdapter mh;
	
	Button action;
	
	LinearLayout form_login;
	LinearLayout form_signUp;
	
	EditText password;
	EditText password_reEnter;
	EditText answer;
	
	EditText passwordLogin;
	
	Spinner questions;
	
	String[] questArray;
	
	int whatINeed = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.welcome);
	    
	    mh =  new AccountsDatabaseAdapter(this);
	    
	    setUpSignUp();
	    setUpLogin();
	    
	    action = (Button) findViewById(R.id.buttonAction);
	
	    form_login = (LinearLayout) findViewById(R.id.form_login);
	    form_signUp = (LinearLayout) findViewById(R.id.form_signUp);
    	
	    form_login.setVisibility(View.GONE);
	
	    if(mh.helper.isThereAnAccount()){
	    	form_signUp.setVisibility(View.GONE);
	    	form_login.setVisibility(View.VISIBLE);
	    }
	    
	}
	
	public void forget(View v){
		Intent i = new Intent(this, ForgetPassword.class);
		startActivity(i);
	}
	
	private void setUpSignUp(){
		password = (EditText) findViewById(R.id.editTextPassword);
		password_reEnter = (EditText)  findViewById(R.id.editTextPasswordReenter);
		
		questions = (Spinner) findViewById(R.id.spinnerQuestions);	    
	    questArray  = getResources().getStringArray(R.array.security_questions);    
  
	    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
	    		android.R.layout.simple_spinner_item, questArray);
	    	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    
	    questions.setAdapter(dataAdapter);
	    
	    answer = (EditText) findViewById(R.id.editTextAnswer);
		
	    
	}
	
	public void signUp(View v){		
		String passwerd = password.getText().toString();
		String questioning = questions.getSelectedItem().toString();
		String answering = answer.getText().toString();
		
		String passwerd_reenter = password_reEnter.getText().toString();
		
		if(passwerd.length() > 0 && questioning.length() > 0 && answering.length() > 0){
			
			if(passwerd_reenter.equals(passwerd)){
				if(mh.helper.insertData(questioning, answering, passwerd) > 0){
					form_signUp.setVisibility(View.GONE);
					form_login.setVisibility(View.VISIBLE);
				}
			}else{
				Toast.makeText(this, "Password mismatched", 1).show();
			}
			
			
		}else{
			Toast.makeText(this, "Please fill all information.", 1).show();
		}
		
	}
	
	private void setUpLogin(){
		passwordLogin = (EditText) findViewById(R.id.editTextPasswordLogin);
	}
	
	public void logIn(View v){
		String passwordx = passwordLogin.getText().toString();
		
		if(mh.helper.isPINOkay(passwordx)){
			Intent i = new Intent(this, MainActivity.class);
			startActivity(i);
			//Toast.makeText(this, "KAY", 1).show();
		}else{
			Toast.makeText(this, "Login failed.", 1).show();
		}
		
	}
	
	//Tumblr inspired, mas panget nga lang to
	public void showForm(View v){
		
		Animation slide = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
		slide.setInterpolator(new BounceInterpolator());
		
		if(whatINeed == 0){
			
			form_signUp.setVisibility(View.GONE);
			form_login.setVisibility(View.VISIBLE);
						
			form_login.startAnimation(slide);
			
			action.setText("Sign up");
			
			whatINeed = 1;
		}else if(whatINeed == 1){
			
			form_login.setVisibility(View.GONE);
			form_signUp.setVisibility(View.VISIBLE);
			
			form_signUp.startAnimation(slide);
			
			action.setText("Log in");
			
			whatINeed = 0;
		}else{
			//WTF??
		}
	}

}
