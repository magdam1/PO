//Wyjątek rzucany przy braku co najmniej jednej z podanych osób w bazie.
package pl.edu.mimuw.wikiontology.mm345746;

public class NoPersonException extends Exception {
	
	public NoPersonException (int nr) {
		super("Osoby o nazwisku "+nr+" nie ma w bazie.");
	}

}
