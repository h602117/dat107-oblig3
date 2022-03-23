package no.hvl.dat107;

import java.math.BigDecimal;
import java.util.Scanner;

public class Scan {

	private Scanner sc;

	public Scan() {
		sc = new Scanner(System.in);
	}

	public int integer(String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				int i = Integer.parseInt(sc.nextLine());
				return i;
			} catch (NumberFormatException e) {
			}
		}
	}

	public String string(String prompt) {
		System.out.print(prompt);
		String s = sc.nextLine();
		return s;
	}

	public BigDecimal bigdecimal(String prompt) {
		System.out.print(prompt);
		while (true) {
			try {
				BigDecimal b = new BigDecimal(Double.parseDouble(sc.nextLine()));
				return b;
			} catch (NumberFormatException e) {
			}
		}
	}

	public void close() {
		sc.close();
	}

}
