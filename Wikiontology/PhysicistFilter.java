//Klasa obsługująca filtr "physicist".
package pl.edu.mimuw.wikiontology.mm345746;

public class PhysicistFilter extends Filter {
	
	public boolean canAdd(Person p) {
		return (p.isPhysicist());
	}

}
