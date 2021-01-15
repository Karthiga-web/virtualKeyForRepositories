package com.hcl.virtualKeyForRepositories;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.hcl.util.Constants;

/**
 * @author karthiga.ravic
 *
 */
public class WelcomeScreenClass {
	static Scanner scan = new Scanner(System.in);

	protected static void welcomeScreenMethod() {
		System.out.println(Constants.MAIN_SCREEN_TEXT);
		String number = scan.nextLine();
		// As static method, can be called with class name
		App.existingRepositoryCreation();
		switch (number) {

		case "1":
			displayContentsMethod();
			welcomeScreenMethod();
			break;
		case "2":
			// As static method, can be called with class name
			SecondScreenClass.secondOptionScreenMethod();
			break;
		case "3":
			System.out.println(Constants.EXIT_MESSAGE);
			System.exit(0);
			break;
		default:
			System.out.println(Constants.INVALID_OPTION);
			welcomeScreenMethod();
			break;
		}
	}

	private static void displayContentsMethod() {
		File tempDirectory = new File(Constants.MY_DIRECTORY_NAME);
		File directoryPath = new File(tempDirectory.getAbsolutePath());
		List<String> listFile = new ArrayList<>();
		if (directoryPath.exists() && directoryPath.isDirectory()) {
			// array for files and sub-directories of directory pointed by
			// directoryPath
			String[] arr = directoryPath.list();
			if (arr.length > 0) {
				// Calling recursive method
				System.out.println(Constants.FILES_AVAILABLE);
				for (int i = 0; i < arr.length; i++) {
					listFile.add(arr[i]);
				}
				listFile.stream().sorted().forEach(l -> {
					System.out.println(l);
				});
			} else {
				System.out.println(Constants.NO_FILES_AVAILABLE);
			}
		}
	}

	// private static void RecursivePrintMethod(List<String> listFile, File[]
	// arr, int index, int level) {
	// // terminate condition
	// if (index == arr.length) {
	// return;
	// }
	// if (arr[index].isFile()) {
	// listFile.add(arr[index].getName().toString());
	// }
	// // for sub-directories
	// else if (arr[index].isDirectory()) {
	// RecursivePrintMethod(listFile, arr[index].listFiles(), 0, level + 1);
	// }
	// // recursion for main directory
	// RecursivePrintMethod(listFile, arr, ++index, level);
	// }
}
