package com.lockedme.com;

public class LockedMeMain {

	public static void main(String[] args) {
		IOHandler.makeNewFolderAndFiles();
		DisplayOptions.DisplayIntro("Utkarsh Upadhyay", "utkupadh@cisco.com");
		IOHandler.DisplayMenu1Handler();
		System.out.println("Bbye!");
	}

}