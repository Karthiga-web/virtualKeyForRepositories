
package com.hcl.virtualKeyForRepositories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.hcl.util.Constants;

/**
 * @author karthiga.ravic
 *
 */
public class SecondScreenClass {
	static Scanner scan = new Scanner(System.in);

	protected static void secondOptionScreenMethod() {
		System.out.println(Constants.SECOND_SCREEN_TEXT);
		String option = scan.nextLine();
		switch (option) {
		case "1":
			addFileMethod();
			secondOptionScreenMethod();
			break;
		case "2":
			deleteFileMethod();
			secondOptionScreenMethod();
			break;
		case "3":
			searchDirectory();
			secondOptionScreenMethod();
			break;
		case "4":
			// As static method, can be called with class name
			WelcomeScreenClass.welcomeScreenMethod();
			break;
		default:
			System.out.println(Constants.INVALID_OPTION);
			secondOptionScreenMethod();
			break;
		}
	}

	public static void searchDirectory() {
		int temporary = 0;
		System.out.println("Enter the filename:");
		String filename = scan.nextLine();
		File dir = new File(Constants.MY_DIRECTORY_NAME);
		temporary = search(dir, filename);
		if (temporary == 0) {
			System.out.println(Constants.NO_FILES_AVAILABLE);
		}
	}

	private static int search(File file, String filename) {
		int temporary = 0;
		List<String> result = new ArrayList<String>();
		try {
			for (File temp : file.listFiles()) {
				if (temp.isDirectory()) {
					search(temp, filename);
				} else {
					if ((filename.toLowerCase()).equals(temp.getName().toLowerCase())) {
						result.add(temp.getAbsoluteFile().toString());
					}
				}
			}
			int count = result.size();
			if (count == 0) {
			} else {
				for (String matched : result) {
					System.out.println("Found : " + matched);
					temporary++;
				}
			}
			return temporary;
		} catch (Exception e) {
			System.out.println("Sorry! File cannot be searched!");
		}
		return temporary;
	}

	private static void deleteFileMethod() {
		File tempDirectory = new File(Constants.MY_DIRECTORY_NAME);
		System.out.println("Enter the filename:");
		String filename = scan.nextLine();
		File file = new File(tempDirectory.getAbsolutePath() + File.separator + filename);
		if (file.delete()) {
			System.out.println(Constants.FILE_DELETED);
		} else {
			System.out.println(Constants.FILE_DELETE_FAIL);
		}
	}

	private static void addFileMethod() {
		System.out.println(Constants.PROVIDE_PATH);
		String filePath = scan.nextLine();
		try {
			Path path = Paths.get(filePath);
			if (!Files.exists(path)) {
				System.out.println(Constants.NO_FILE);
				return;
			}
			File tempDirectory = new File(Constants.MY_DIRECTORY_NAME);
			String newFilePath = tempDirectory.getAbsolutePath() + File.separator + path.getFileName();
			int inc = 0;
			while (Files.exists(Paths.get(newFilePath))) {
				inc++;
				newFilePath = tempDirectory.getAbsolutePath() + File.separator + inc + "_" + path.getFileName();
			}
			Files.copy(path, Paths.get(newFilePath));
			System.out.println(path.getFileName() + Constants.FILE_ADDED);
		} catch (IOException e) {
			System.out.println("Unable to add file! Invalid Path entered!");
		} catch (InvalidPathException i) {
			System.out.println("Unable to add file! Invalid Path entered!");
		}
	}
}