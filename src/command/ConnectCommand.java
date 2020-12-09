package command;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import expression.ShuntingYardAlgorithm;

public class ConnectCommand extends Command {

	private static volatile boolean isConnected = false;
	private static Socket socket = null;
	private static PrintWriter outToServer = null;

	public ConnectCommand() {
		super();
	}

	@Override
	public int doCommand() {

		ArrayList<String[]> tokens = this.interpreter.getTokens();
		int indexBlockOfTokens = this.interpreter.getIndexBlockOfTokens();
		int indexToken = this.interpreter.getIndexToken();
		String ip = tokens.get(indexBlockOfTokens)[indexToken + 1];
		int port = 0;

		ArrayList<String> expression = new ArrayList<String>();
		String[] block = this.interpreter.getTokens().get(this.interpreter.getIndexBlockOfTokens());

		for (int i = (indexToken + 2); i < block.length; i++) {
			expression.add(block[i]);
		}

		port = (int) ShuntingYardAlgorithm.execute(expression, this.interpreter.getServerSymbolTable());

		while (isConnected == false) {
			try {
				socket = new Socket(ip, port);
				outToServer = new PrintWriter(socket.getOutputStream());

				isConnected = true;
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		this.interpreter.setIndexToken(expression.size() + 1);

		return 0;
	}

	public static void sendToServer(String line) {
		if (isConnected == true) {
			outToServer.println(line);
			outToServer.flush();
		}
	}

	public static void closeConnection() {
		if (isConnected == true) {
			
			sendToServer("bye");
			outToServer.close();

			while (true) {
				try {
					socket.close();
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			isConnected = false;
		}
	}

}