//Wyjątek rzucany w sytuacji, gdy po założeniu filtra "physicist" co najmniej jedna z podanych osób nie jest fizykiem.
package pl.edu.mimuw.wikiontology.mm345746;

public class NotPhysicistException extends Exception {
	
	public NotPhysicistException (int nr) {
		super("Osoba "+nr+" nie jest fizykiem.");
	}

}
