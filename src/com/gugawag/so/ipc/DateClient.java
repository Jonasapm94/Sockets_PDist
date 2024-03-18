package com.gugawag.so.ipc;

/**
 * Client program requesting current date from server.
 *
 * Figure 3.22
 *
 * @author Silberschatz, Gagne and Galvin. Pequenas alterações feitas por Gustavo Wagner (gugawag@gmail.com)
 * Operating System Concepts  - Eighth Edition
 */ 

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class DateClient {
	public static void main(String[] args)  {
		try {
			// this could be changed to an IP name or address other than the localhost
			Socket sock = new Socket("localhost",6013);

			System.out.println("=== Cliente iniciado ===\n");

			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
			DataInputStream dis = new DataInputStream(sock.getInputStream());

			while (true) {
				Scanner teclado = new Scanner(System.in);
				dos.writeUTF(teclado.nextLine());
				String mensagemRecebida = dis.readUTF();
				System.out.println("Servidor me respondeu: "+mensagemRecebida);
			}
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
	}
}
