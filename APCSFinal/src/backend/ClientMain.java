package backend;

import java.io.IOException;
import java.util.Scanner;

public class ClientMain {
	public static void main(String[] args) {
		try (Client c = new Client("localhost", 8888); Scanner in = new Scanner(System.in);) {
			new Thread(c).start();
			while (in.hasNext()) {
				c.sendData(in.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace(System.out);
			System.exit(0);
		}

	}
}
