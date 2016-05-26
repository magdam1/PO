//Abstrackyjna nadklasa klas obsługujących filtry.
package pl.edu.mimuw.wikiontology.mm345746;

public abstract class Filter {

	//Metoda sprawdzająca, czy można rozpatrywać daną osobę podczas przeszukiwania grafu.
	public abstract boolean canAdd(Person p);
	
}
