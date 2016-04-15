package com.polischukovik.ConsoleChat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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

	public static void main(String[] args) {
		initConnection();
		getName();
		createInputThread();
		mainOutputLoop();
	}

	private static void createInputThread() {
		inputThread = new Thread(){
			@Override
			public void run(){
				String message = name.concat(": ");
				try(DataOutputStream os = new DataOutputStream(connection.getOutputStream())){
					message += input.nextLine();
					
					os.writeUTF(message);
				} catch (IOException e) {
					System.out.println(e);
				}
			}
		};
		inputThread.setDaemon(true);
		inputThread.start();
	}

	private static void mainOutputLoop() {
		String message = "";
		try(DataInputStream s = new DataInputStream(connection.getInputStream())){
			while(true){
				if(s.available() > 0){
					message += s.readUTF();
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}		
		console.println(message);		
	}

	private static void initConnection() {
		console.println("Connect to server(IP:port):");
		String[] address = input.nextLine().split(":");
		if (address.length < 1){
			throw new IllegalArgumentException("Incorrect address parameter");
		}
		int port = Integer.valueOf(address[1]);
		Byte[] ip = Arrays.asList(address[1].split(".")).stream()
				.map(Byte::parseByte)
				.collect(Collectors.toList())
				.toArray(new Byte[0]);
		
		//extra operation. How to convert Byte[] to byte[] in stream...
		byte[] ipPrim = new byte[ip.length];
		for (int i = 0; i<ip.length; i++){
			ipPrim[i] = (byte) ip[i]; 
		}
		
		try{
			InetAddress ipAddress = InetAddress.getByAddress(ipPrim);
			connection = new Socket(ipAddress, port);
		}
		catch(UnknownHostException e){
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}

	private static void getName() {
		console.println("Enter your name");
		name = input.nextLine();
	}
	
	public void outputMessage(String message){
		console.println(message);		
	}

}
