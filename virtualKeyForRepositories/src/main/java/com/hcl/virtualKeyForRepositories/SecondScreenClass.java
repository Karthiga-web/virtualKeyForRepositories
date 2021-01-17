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
// getting the option to proceed
String option = scan.nextLine();
switch (option) {
case "1":
// Adding the file to the virtual repository
addFileMethod();
// For showing second user interaction screen
secondOptionScreenMethod();
break;
case "2":
// Deleting the file from the virtual repository
deleteFileMethod();
// For showing second user interaction screen
secondOptionScreenMethod();
break;
case "3":
// Searching the file in the virtual repository
searchDirectory();
// For showing second user interaction screen
secondOptionScreenMethod();
break;
case "4":
// As static method, can be called with class name
// For showing first user interaction screen
WelcomeScreenClass.welcomeScreenMethod();
break;
default:
System.out.println(Constants.INVALID_OPTION);
// For showing second user interaction screen
secondOptionScreenMethod();
break;
}
}

public static void searchDirectory() {
// ASking for filename
System.out.println(Constants.FILENAME);
String filename = scan.nextLine();
// Going to virtual repository path
File dir = new File(Constants.MY_DIRECTORY_NAME);
//creating a list to store files if found
List<String> result = new ArrayList<String>();
// Searching the virtual repository
result = search(result, dir, filename);
if (result.size() == 0) {
System.out.println(Constants.NO_FILES_AVAILABLE);
} else {
System.out.println(Constants.FILE_EXISTS);
}
}

private static List<String> search(List<String> result, File file, String filename) {
try {
for (File temp : file.listFiles()) {
// Searching in each directory in virtual repository
if (temp.isDirectory()) {
search(result, temp, filename);
} else {
// Checking for case sensitivity
if ((filename.toLowerCase()).equals(temp.getName().toLowerCase())) {
result.add(temp.getAbsoluteFile().toString());
}
}
}
} catch (Exception e) {
System.out.println(Constants.FILE_CANNOT_BE_SEARCHED);
}
return result;
}

private static void deleteFileMethod() {
File tempDirectory = new File(Constants.MY_DIRECTORY_NAME);
// ASking for filename
System.out.println(Constants.FILENAME);
String filename = scan.nextLine();
File file = new File(tempDirectory.getAbsolutePath() + File.separator + filename);
try {
if (file.delete()) {
System.out.println(Constants.FILE_DELETED);
} else {
System.out.println(Constants.FILE_DELETE_FAIL);
}
}catch(Exception e) {
System.out.println(e.getMessage());
}

}

private static void addFileMethod() {
System.out.println(Constants.PROVIDE_PATH);
// ASking for filepath
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
System.out.println(Constants.INVALID_PATH);
} catch (InvalidPathException i) {
System.out.println(Constants.INVALID_PATH);
}
}
}