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
		console.println("Client joined " + client.getInetAddress().getHostAddress());
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
				try(DataInputStream input = new DataInputStream(client.getInputStream())){
					
					/*
					 * Loop ends when Input thread terminates
					 */
					while(readerThread.isAlive()){
						message = input.readUTF();
						ServerRunner.distributeMessage(message);						
					}
				}				
				catch (IOException e) {
					console.println("Message reader error " + e);
					e.printStackTrace();
				}
				console.print("Client left " + client.getInetAddress());
			}
		};
		
		/*
		 * Set as daemon to terminate if Output loop ends 
		 */
		readerThread.setDaemon(true);
		readerThread.start();
		
	}
	
	public void outputMessage(String message){
		try(DataOutputStream output = new DataOutputStream(client.getOutputStream())){
			output.writeUTF(message);	
			output.flush();
		}				
		catch (IOException e) {
			console.println("Output message error " + e);
		}		
			
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		client.close();
	}

}
