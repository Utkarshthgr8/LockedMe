package com.lockedme.com;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
public class IOHandler {

	static Scanner sc = new Scanner(System.in);

	static final String rootPathString = "./FilesFolder";

	public static void makeNewFolderAndFiles() {
		try {
			Files.createDirectories(Paths.get(rootPathString));
		} catch (FileAlreadyExistsException e) {
			// IGNORING THIS EXCEPTION
		} catch (IOException e) {
			System.out.println("**An error occured in creating FilesFolder Directory.");
			e.printStackTrace();
		}

		for (int i = 1; i < 11; i++) {
			Path fileName = Paths.get(rootPathString, "/File " + i + ".txt");
			IOHandler.makeNewDefaultFile(fileName);
		}
	}

	public static void makeNewDefaultFile(Path fileName) {
		try {
			Files.createFile(fileName);
		} catch (FileAlreadyExistsException e) {
			// IGNORING THIS EXCEPTION
		} catch (IOException e) {
			System.out.println("**An error occured in creating " + fileName + ".");
			e.printStackTrace();
		}
	}

	public static void DisplayMenu1Handler() {
		boolean run = true;
		while (run) {
			try {
				DisplayOptions.DisplayMenu1Handler();
				int option = sc.nextInt();

				switch (option) {
				case 1:
					IOHandler.showAllFiles();
					break;
				case 2:
					IOHandler.ExpertOptions();
					break;
				case 3:
					System.out.println("Exiting LockMe.com");
					run = false;
					sc.close();
					return;
				default:
					System.out.println("Enter a valid option from 1-3\n");
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter a valid option from 1-3.\n");
				sc.nextLine();
				DisplayMenu1Handler();
				return;
			}
		}
	}

	public static void showAllFiles() {
		Path rootPath = Paths.get(rootPathString);

		try {
			Stream<Path> pathStart = Files.walk(rootPath);
			
			List<String> sortableList = new ArrayList<String>();

			pathStart.forEach(object -> {
				File myObj = new File(object.toString());
				if (!myObj.isDirectory()) {
					sortableList.add(myObj.getName());
				}
			});
			
			if(sortableList.isEmpty()) {
				System.out.println("No files in " + rootPathString);
				pathStart.close();
				return;
			}

			Collections.sort(sortableList, new Comparator<String>() {
				public int compare(String o1, String o2) {
					return extractInt(o1) - extractInt(o2);
				}

				int extractInt(String s) {
					String num = s.replaceAll("\\D", "");
					return num.isEmpty() ? 0 : Integer.parseInt(num);
				}
			});

			sortableList.forEach(fileName -> {
				System.out.println(fileName);
			});

			pathStart.close();
		} catch (IOException e) {
			System.out.println("**An error occured in printing all files in FilesFolder.");
			e.printStackTrace();
		}
	}

	public static void ExpertOptions() {
		boolean run = true;
		try {
			while (run) {
				DisplayOptions.DisplayMenu2Handler();
				int option = sc.nextInt();

				String fileName;
				Path fileNamePath;

				switch (option) {
				case 1:
					sc.nextLine();
					System.out.println("Enter the name of the file to search below.");
					fileName = sc.nextLine();
					fileNamePath = Paths.get(rootPathString, "/" + fileName + ".txt");
					searchFile(fileNamePath);
					break;
				case 2:
					sc.nextLine();
					System.out.println("Enter the name of the file to add below.");
					fileName = sc.nextLine();
					fileNamePath = Paths.get(rootPathString, "/" + fileName + ".txt");
					makeNewFile(fileNamePath);
					break;
				case 3:
					sc.nextLine();
					System.out.println("Enter the name of the file to write below.");
					fileName = sc.nextLine();
					fileName = rootPathString + "/" + fileName + ".txt";
					writeFile(fileName);
					break;
				case 4:
					sc.nextLine();
					System.out.println("Enter the name of the file to delete below. (Case Insensitive)");
					fileName = sc.nextLine();
					fileNamePath = Paths.get(rootPathString, "/" + fileName + ".txt");
					deleteFile(fileNamePath);
					break;
				case 5:
					System.out.println("Exiting To Main Menu.");
					return;
				case 6:
					System.out.println("Exiting LockMe.com\nBbye!");
					run = false;
					sc.close();
					System.exit(0);
					return;
				default:
					System.out.println("Enter a valid option from 1-6");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("Enter a valid option from 1-6.\n");
			sc.nextLine();
			ExpertOptions();
		}
	}

	private static void makeNewFile(Path fileNamePath) {
		try {
			Files.createFile(fileNamePath);
			System.out.println(fileNamePath + " created successfully.");
		} catch (FileAlreadyExistsException e) {
			// IGNORING THIS EXCEPTION
		} catch (IOException e) {
			System.out.println("**An error occured in creating " + fileNamePath + ".");
			e.printStackTrace();
		}
	}

	private static void writeFile(String fileNamePath) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileNamePath));
			System.out.println("Enter the text you want to write to " + fileNamePath);
			String textToWrite = sc.nextLine();
			writer.write(textToWrite);
			writer.close();
			System.out.println("File written successfully.");
		} catch (IOException e) {
			System.out.println("**An error occured in writing in " + fileNamePath + ".");
			e.printStackTrace();
		}
	}

	private static void searchFile(Path fileName) {
		if (Files.exists(fileName)) {
			System.out.println(fileName + " exists.");
		} else {
			System.out.println(fileName + " does not exist.");
		}
	}

	private static void deleteFile(Path fileName) {
		try {
			Files.delete(fileName);
			System.out.println(fileName + " deleted successfully.");
		} catch (NoSuchFileException e) {
			System.out.println("**An error occured in deleting " + fileName + ".\n**" + fileName + " does not exist.");
		} catch (IOException e) {
			System.out.println("**An error occured in deleting " + fileName + ".");
			e.printStackTrace();
		}
	}

}