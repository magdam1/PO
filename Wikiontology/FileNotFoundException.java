//Wyjątek rzucany przy braku podanego pliku.
package pl.edu.mimuw.wikiontology.mm345746;

public class FileNotFoundException extends Exception {
	
	public FileNotFoundException () {
		super("Nie znaleziono pliku o podanej ścieżce.");
	}

}
