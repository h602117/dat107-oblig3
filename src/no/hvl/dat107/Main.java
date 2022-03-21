package no.hvl.dat107;

import no.hvl.dat107.employee.*;

public class Main {

	public static void main(String[] args) {
		EmployeeDAO edao = new EmployeeDAO();
		Employee e = edao.retrieveEmployee(1);
		System.out.println(e);
	}

}
