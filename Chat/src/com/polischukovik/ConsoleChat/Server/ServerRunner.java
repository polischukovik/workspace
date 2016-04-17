package com.polischukovik.ConsoleChat.Server;
import java.io.IOException;

import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashSet;

import javax.swing.text.StyledEditorKit.ForegroundAction;

import com.polischukovik.ConsoleChat.ErrorReason;
/**
 * ServerRunner opens port and wait clients to connect
 * @author opolishc
 *
 */
public class ServerRunner {
	private static HashSet<Server> clientList = new HashSet<>();
	private static int port = 2222;
	private static boolean running = true;
	private static PrintStream console = System.out;
	
	public static HashSet<Server> getClientList() {
		return clientList;
	}

	public static void main(String[] args) throws UnknownHostException {
		setEnv(args);
		
		/*
		 * Accept incoming client connections and spawn threads for them
		 */
		System.out.println("Server starting " + InetAddress.getLocalHost().getHostAddress() + ":" + port);
		int errTolerancy = 0;
		while(running && errTolerancy < 3){
			try(ServerSocket serverSocket = new ServerSocket(port)){		
				
				Socket client = serverSocket.accept();
				clientList.add(new Server(client));
				
			} catch (IOException e) {
				errTolerancy +=1 ;
				console.println("Server create connection" + e);
				//Logging.log(ERROR,"Server create connection" + e)
				//Logging.log(DEBUG,e.getStuckTrace())
			}			
		}
		System.out.println("Server ends");
	}

	private static void setEnv(String[] args) {
		switch (args.length) {
		case 0:
			return;
		case 1:
			port = Integer.valueOf(args[0]);
			break;
			
		default:
			break;
		}			
	}
	
	private static void createConnectionMonitor(){
		Thread monitor = new Thread(()->{
			HashSet<Server>  toRemove = new HashSet<>();
		});
	}

	public static void distributeMessage(String message) {
		for (Server c: clientList){
			console.println("Distributing message: " + message);
			c.outputMessage(message);
		}
		//clientList.stream().forEach((n) -> n.outputMessage(message));		
	}

}
