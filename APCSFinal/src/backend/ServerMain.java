package backend;

import java.io.IOException;

public class ServerMain {
	public static void main(String[] args) {
		try {
			@SuppressWarnings({ "unused", "resource" }) // I need this to stay open, and all processing happens internally
			Server s = new Server(8888);
		} catch (IOException e0) {
			e0.printStackTrace(System.out);
			System.exit(0);
		}

	}
}
