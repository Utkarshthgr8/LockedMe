package com.lockedme.com;

public class DisplayOptions {

	public static void DisplayIntro(String name, String email) {
		String asciiLockIntro = String.format("" + "\r\n" + "            .-\"\"-.\n" + "           / .--. \\\n"
				+ "          / /    \\ \\ \t\t\tLockedMe.com\n" + "          | |    | |\n" + "          | |.-\"\"-.|\n"
				+ "         ///`.::::.`\\ \t\t\tDeveloped By :\n" + "        ||| ::/  \\:: ; \t\t\tName : %s\n"
				+ "        ||; ::\\__/:: ; \t\t\tEmail : %s\n" + "         \\\\\\ '::::' /\n"
				+ "           `=':-..-'`\n", name, email);
		System.out.println(asciiLockIntro);
	}

	public static void DisplayMenu1Handler() {
		String menuString = "\nSelect an option from below and press enter.\n" + "1. Show all files.\n"
				+ "2. Display menu for operations. (Expert Mode)\n" + "3. Exit Program.";
		System.out.println(menuString);
	}

	public static void DisplayMenu2() {
		String menuFile = "\nSelect an option from below and press enter.\n" + "1. Search a file.\n"
				+ "2. Add a file.\n" + "3. Write to a file.\n" + "4. Delete file.\n" + "5. Main Menu.\n"
				+ "6. Exit program.";
		System.out.println(menuFile);
	}

}