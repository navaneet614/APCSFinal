package backend;

import java.io.*;
import java.net.*;
import java.rmi.ServerException;
import java.util.*;

public class Server implements AutoCloseable {
	private volatile List<Connection> connections;
	private ServerSocket s;

	public Server(int port) throws IOException {
		s = new ServerSocket(port);
		connections = new Vector<Connection>();
		(new Thread(new Accepter(), "Connections")).start();
	}

	private class Accepter implements Runnable {

		@Override
		public void run() {
			while (true) {
				try {
					Connection c = new Connection(s.accept());
					new Thread(c).start();
					connections.add(c);
					Thread.yield();
				} catch (ServerException | SocketException e) {
					e.printStackTrace();
					System.exit(1);
				} catch (IOException e) {

					e.printStackTrace(System.out);
				}
			}

		}

	}

	public synchronized void broadcast(String s, Connection me) {
		for (Connection c : connections) {
			if (me != c) {
				c.sendData(s);
			}
		}

	}

	public void close() throws IOException {
		s.close();
		for (Connection c : connections) {
			c.close();
		}
	}

	private class Connection implements AutoCloseable, Runnable {
		private Socket s;
		private ObjectInputStream in;
		private BufferedWriter out;

		public Connection(Socket s) throws IOException {
			this.s = s;
			in = new ObjectInputStream(s.getInputStream());
			out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		}

		public void sendData(String s) {
			try {

				out.write(s);
				out.newLine();
				out.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void run() {
			try {
				String line = null;

				// while reader line is not empty
				while ((line = (String) in.readObject()) != null) {
					System.out.println(line);
					broadcast(line, this);
				}
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

		public void close() throws IOException {
			s.close();
		}

		public String toString() {
			return s.toString();
		}
	}

}
