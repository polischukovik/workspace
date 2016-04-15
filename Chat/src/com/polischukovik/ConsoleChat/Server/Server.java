package com.polischukovik.ConsoleChat.Server;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashSet;

public class Server{	
	private static HashSet<Server> clientList = new HashSet<>();
	private Thread readerThread;
	private PrintStream console = System.out;
	private Socket client;

	public Server(Socket client) {	
		this.client = client; 
		createInpoutThread();
	}
	
	/*
	 * Create writer thread and set it as daemon
	 */
	private void createInpoutThread(){
		readerThread = new Thread(){
			@Override
			public void run(){
				//here input message and send to server			
				String message = "";
				try(DataInputStream input = (DataInputStream) client.getInputStream()){
					
					/*
					 * Loop ends when Input thread terminates
					 */
					while(readerThread.isAlive()){
						if (input.available() > 0) {
							message = input.readUTF();
							ServerRunner.distributeMessage(message);
						}						
					}
				}				
				catch (IOException e) {
					System.out.println(e);
				}
			}
		};
		readerThread.start();
		/*
		 * Set as daemon to terminate if Output loop ends 
		 */
		readerThread.setDaemon(true);
	}
	
	public void outputMessage(String message){
		try(DataOutputStream output = new DataOutputStream(client.getOutputStream())){
			output.writeUTF(message);		
		}				
		catch (IOException e) {
			System.out.println(e);
		}		
			
	}

}
