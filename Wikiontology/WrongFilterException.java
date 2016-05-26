//Wyjątek rzucany przy podaniu błędnej nazwy filtru.
package pl.edu.mimuw.wikiontology.mm345746;

public class WrongFilterException extends Exception {

	public WrongFilterException () {
		super("Zły filtr.");
	}
	
}
