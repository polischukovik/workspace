package com.polischukovik.ConsoleChat;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClientRunner {
	
	private static PrintStream console = System.out;
	private static Scanner input = new Scanner(System.in);
	private static String name;
	private static Socket connection;

	public static void main(String[] args) {
		initConnection();
		getName();

	}

	private static void initConnection() {
		// TODO Auto-generated method stub
		
	}

	private static void getName() {
		console.println("Enter your name");
		name = input.nextLine();
	}
	
	public void outputMessage(String message){
		console.println(message);		
	}

}
