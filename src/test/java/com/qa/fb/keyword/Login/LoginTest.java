package com.qa.fb.keyword.Login;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.Test;

import com.qa.fb.keyword.engine.KeyWordEngine;

public class LoginTest {
	
	public KeyWordEngine keyWordEngine;

	@Test
	public void loginTest() throws InvalidFormatException  {
		keyWordEngine = new KeyWordEngine();
		keyWordEngine.startExecution("Login");
	}
	@Test
	public void signUpTest() throws InvalidFormatException {
		keyWordEngine = new KeyWordEngine();
		keyWordEngine.startExecution("SignUp");
	}


}
