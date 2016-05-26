//Osoby zajmujące się fizyką są reprezentatnami tej klasy.
package pl.edu.mimuw.wikiontology.mm345746;

import java.util.*;

public class Physicist extends Person {

	//Konstruktor
	public Physicist (String name, List<String> References, List<String> Categories) {
		super(name, References, Categories);
	}
	
	@Override
	public boolean isPhysicist () {
		return true;
	}
}
