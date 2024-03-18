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
						dos.writeUTF("Esta é uma calculadora de números fatoriais!\n" +
						"Envie seu número inteiro e receberá como resposta o fatorial deste número!");
						while (true) {
							String mensagemRecebida = dis.readUTF();
							System.out.println(socket.getInetAddress()
									+ ":" + socket.getPort()
									+ "- Cliente enviou: " + mensagemRecebida);
							// Scanner teclado = new Scanner(System.in);
							// dos.writeUTF(teclado.nextLine());
							try {
								Integer num = Integer.parseInt(mensagemRecebida);
								num = fact(num);
								dos.writeUTF("Servidor responde: " + num);
							} catch (Exception e) {
								// TODO: handle exception
								dos.writeUTF("Servidor responde: Número enviado em formato incorreto (ou outro erro: )" + e.getStackTrace());
							}
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

	private static Integer fact(Integer x){
		if (x == 1 || x == 0){
			return 1;
		}
		Integer y=1;
		for (int i=0; i< x; i++){
			y*=(x-i);
		}
		return y;

	}
}
