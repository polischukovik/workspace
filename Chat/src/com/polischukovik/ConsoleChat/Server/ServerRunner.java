package com.polischukovik.ConsoleChat.Server;
import java.io.IOException;

import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
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
	
	public static HashSet<Server> getClientList() {
		return clientList;
	}

	public static void main(String[] args) {
		setEnv(args);
		
		/*
		 * Accept incoming client connections and spawn threads for them
		 */
		try(ServerSocket serverSocket = new ServerSocket(port)){		
			System.out.println("Server started " + InetAddress.getLocalHost().getHostAddress() + " port " + serverSocket.getLocalPort());
			Socket client = serverSocket.accept();
			clientList.add(new Server(client));
			
		} catch (IOException e) {
			System.out.println("Server error");		
		}
		
		while(running){
			
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

	public static void distributeMessage(String message) {
		clientList.stream().forEach((n) -> n.outputMessage(message));		
	}

}
