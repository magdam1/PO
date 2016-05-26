//Wyjątek rzucany przy braku szukanej ścieżki w grafie.
package pl.edu.mimuw.wikiontology.mm345746;

public class NoPathException extends Exception {

	public NoPathException() {
		super("Ścieżka nie istnieje.");
	}
}
