package com.polischukovik.ConsoleChat.Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashSet;

public class Server{	
	private static HashSet<Server> clientList = new HashSet<>();
	private Thread writerThread;
	private PrintStream console = System.out;
	private Socket client;

	public Server(Socket client) {	
		this.client = client; 
		createInputThread();			
	}
	
	/*
	 * Create writer thread and set it as daemon
	 */
	private void createInputThread(){
		writerThread = new Thread(){
			@Override
			public void run(){
				//here input message and send to server								
				byte[] buff = null; 
				try(BufferedInputStream input = (BufferedInputStream) client.getInputStream()){
					while(true){
						if (input.available() > 0) {
							input.read(buff);
							ServerRunner.distributeMessage(String.valueOf(buff));
						}						
					}
				}				
				catch (IOException e) {
					System.out.println(e);
				}
			}
		};
		writerThread.start();
		writerThread.setDaemon(true);
	}
	
	public void outputMessage(String message){
		try(BufferedOutputStream output = (BufferedOutputStream) client.getOutputStream()){
			output.write(message.getBytes());		
		}				
		catch (IOException e) {
			System.out.println(e);
		}		
	}

}
