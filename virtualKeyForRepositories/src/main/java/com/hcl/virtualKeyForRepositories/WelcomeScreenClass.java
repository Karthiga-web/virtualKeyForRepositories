/**
 *
 */
package com.hcl.virtualKeyForRepositories;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.hcl.util.Constants;

/**
 * @author karthiga.ravic
 *
 */
public class WelcomeScreenClass {
static Scanner scan = new Scanner(System.in);

protected static void welcomeScreenMethod() {
System.out.println(Constants.MAIN_SCREEN_TEXT);
// getting the option to proceed
String number = scan.nextLine();
// As static method, can be called with class name
App.existingRepositoryCreation();
switch (number) {

case "1":
// displaying the contents of the virtual repository
displayContentsMethod();
// For showing first user interaction screen
welcomeScreenMethod();
break;
case "2":
// As static method, can be called with class name
// For showing second user interaction screen
SecondScreenClass.secondOptionScreenMethod();
break;
case "3":
// exiting the application
System.out.println(Constants.EXIT_MESSAGE);
System.exit(0);
break;
default:
System.out.println(Constants.INVALID_OPTION);
// For showing first user interaction screen
welcomeScreenMethod();
break;
}
}

private static void displayContentsMethod() {
File tempDirectory = new File(Constants.MY_DIRECTORY_NAME);
File directoryPath = new File(tempDirectory.getAbsolutePath());
List<String> listFile = new ArrayList<>();
if (directoryPath.exists() && directoryPath.isDirectory()) {
// array for files and sub-directories of directory pointed by directoryPath
String[] arr = directoryPath.list();
if (arr.length > 0) {
// Calling recursive method
System.out.println(Constants.FILES_AVAILABLE);
for (int i = 0; i < arr.length; i++) {
listFile.add(arr[i]);
}
// sorting with stream sorting (ASCII value sorting of files)
listFile = listFile.stream().sorted().collect(Collectors.toList());
//displaying the sorted contents
listFile.stream().forEach(l -> {
System.out.println(l);
});
} else {
System.out.println(Constants.NO_FILES_AVAILABLE);
}
}
}
}