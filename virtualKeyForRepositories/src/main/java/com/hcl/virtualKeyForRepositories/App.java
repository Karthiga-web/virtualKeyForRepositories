package com.hcl.virtualKeyForRepositories;

import java.io.File;
import java.util.Scanner;

import com.hcl.util.Constants;

/**
 * @author karthiga.ravic
 *
 */
public class App {
	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println(Constants.WELCOME_SCREEN_TEXT);
		// Create a virtual repository to store the files/folders that user may
		// process
		existingRepositoryCreation();
		// As static method, can be called with class name
		WelcomeScreenClass.welcomeScreenMethod();
	}

	public static void existingRepositoryCreation() {
		// Virtual Directory name is hard coded for self purpose
		File tmpDir = new File(Constants.MY_DIRECTORY_NAME);
		boolean exists = tmpDir.exists();
		if (!exists) {
			File dir = new File(Constants.MY_DIRECTORY_NAME);
			dir.mkdir();
		}
	}
}