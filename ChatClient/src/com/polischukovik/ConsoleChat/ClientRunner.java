package com.polischukovik.ConsoleChat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientRunner {
	
	private static PrintStream console = System.out;
	private static Scanner input = new Scanner(System.in);
	private static String name;
	private static Socket connection;
	private static Thread inputThread;
	private static boolean running = true;

	public static void main(String[] args) throws IllegalArgumentException, IOException {
		initConnection();
		getName();
		createInputThread();
		mainOutputLoop();
	}

	private static void createInputThread() {
		inputThread = new Thread(){
			@Override
			public void run(){
				console.println("Input thread started");
				int errorTolerancy = 0;
				try(DataOutputStream os = new DataOutputStream(connection.getOutputStream())){
					while(running & errorTolerancy < 3){
						String message = name.concat(": ");
						message += input.nextLine();
						os.writeUTF(message);
						os.flush();
					}
				} catch (IOException e) {
					errorTolerancy++;
					System.out.println(e);
				}
				console.println("Input thread ended");
			}
		};
		/*
		 * Set as daemon to terminate if Output loop ends 
		 */
		inputThread.setDaemon(true);
		inputThread.start();
	}

	private static void mainOutputLoop() {
		console.println("Output loop starting");
		String message = "";
		try(DataInputStream s = new DataInputStream(connection.getInputStream())){
			/*
			 * Loop ends when Input thread terminates
			 */
			while(inputThread.isAlive()){
				message += s.readUTF();
				console.println(message);	
				
			}
			
		} catch (IOException e) {
			System.out.println(e);
		}		
		console.println("Output loop ended");		
	}

	private static void initConnection() throws IllegalArgumentException, IOException {
		console.println("Connect to server(IP:port):");
		String[] address = input.nextLine().split(":");
		if (address.length < 2){
			throw new IllegalArgumentException("Incorrect address parameter");
		}
		int port = Integer.valueOf(address[1]);
		byte[] ip = new byte[4];
		String[] sByte = address[0].split("\\.");
		if (sByte.length == 4){
			for(int i = 0; i < 4; i++){
				ip[i] = (byte) Integer.parseInt(sByte[i]);
			}
		}else{
			throw new IllegalArgumentException("Can not parse ip");
		}
		
		InetAddress ipAddress = InetAddress.getByAddress(ip);
		connection = new Socket(ipAddress, port);
		System.out.println();
		

	}

	private static void getName() {
		console.println("Enter your name");
		name = input.nextLine();
	}
	
	public void outputMessage(String message){
		console.println(message);		
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		connection.close();
	}

}
