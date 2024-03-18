package com.gugawag.so.ipc;

/**
 * Time-of-day server listening to port 6013.
 *
 * Figure 3.21
 *
 * @author Silberschatz, Gagne, and Galvin. Pequenas alterações feitas por Gustavo Wagner (gugawag@gmail.com)
 * Operating System Concepts  - Ninth Edition
 * Copyright John Wiley & Sons - 2013.
 */
import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Scanner;

public class DateServer{
	public static void main(String[] args)  {
		try {
			ServerSocket sock = new ServerSocket(6013);

			System.out.println("=== Servidor iniciado ===\n");
			System.out.println("Aluno: Jonas Ariel Passos de Medeiros");
			// escutando por conexões
			while (true) {
				Socket socket = sock.accept();
				Thread thread = new Thread(() -> {
					try {
						DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
						DataInputStream dis = new DataInputStream(socket.getInputStream());
						
						// dos.writeUTF("Jonas Ariel Passos de Medeiros");
						while (true) {
							String mensagemRecebida = dis.readUTF();
							System.out.println(socket.getInetAddress()
									+ ":" + socket.getPort()
									+ "- Cliente enviou: " + mensagemRecebida);
							Scanner teclado = new Scanner(System.in);
							dos.writeUTF(teclado.nextLine());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				thread.start();
			}
		}
		catch (IOException ioe) {
				System.err.println(ioe);
		}
	}
}
