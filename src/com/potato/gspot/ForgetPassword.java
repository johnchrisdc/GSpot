package com.potato.gspot;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ForgetPassword extends Activity {

	EditText answer;
	TextView question;
	TextView password;
	
	AccountsDatabaseAdapter mh;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.forget_password);
	    
	    answer = (EditText) findViewById(R.id.editTextAnswering);
	    question = (TextView) findViewById(R.id.textViewQuestioning);
	    password = (TextView) findViewById(R.id.textViewOMGPasswording);
	    
	    mh =  new AccountsDatabaseAdapter(this);
	    
	    question.setText(getSecurityQuestion());
	}
	
	private String getSecurityQuestion(){	
		return mh.helper.getSecurityQuestion();	
	}

}
