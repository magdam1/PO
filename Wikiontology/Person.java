//Wszystkie obiekty dodawane do bazy są reprezentantami tej klasy.
package pl.edu.mimuw.wikiontology.mm345746;

import java.util.*;

public class Person {
  
	private String Name;
	private List<String> References; //Wszystkie referencje.
	private List<String> Categories; //Kategorie, do których należy dana osoba.
	private List<Person> Neighbours; //Referencje do artykułów opisujących osoby.
	
	//Konstruktory
	public Person (String name, List<String> References, List<String> Categories) {
		this.Name = name;
		this.References = References;
		this.Categories = Categories;
		this.Neighbours = new ArrayList<Person>();
	}
	
	public Person (String name) {
		this.Name = name;
	}
	
	//Gettery
	public List<Person> getNeighbours () {
		return Neighbours;
	}
	
	public String getName () {
		return Name;
	}
	
	//Dodawanie sąsiada.
	public void addNeighbour (Person p) {
		
		if (!p.getName().toLowerCase().equals(this.Name.toLowerCase())) {
			
			List<String> names = new ArrayList<String>();
		
			for (int i=0; i<Neighbours.size(); i++) {
				names.add(Neighbours.get(i).getName().toLowerCase());
			}
		
			if (!names.contains(p.getName().toLowerCase())) {
				Neighbours.add(p);
			}
		}
	}
	
	//Metoda tworzy listę sąsiadów na podstawie uprzednio otrzymanej listy wszystkich referencji.
	public void makeLinks (Database d) {
		for (int i=0; i<References.size(); i++) {
			
			Person p = new Person(References.get(i));
			
			if (d.contains(p)) {
				this.addNeighbour(d.get(d.indexOf(p)));
			}
		}
	}
	
	//Dodawanie kategorii.
	public void addCategory (String cat) {
		if (!Categories.contains(cat)) {
			Categories.add(cat);
		}
	}
	
	//ToString
	public String toString () {
		String s = Name+"\n";
		
		for (int i=0; i<Neighbours.size(); i++) {
			s+="    ";
			s+=Neighbours.get(i).getName();
			s+="\n";
		}
		
		return s;
	}
	
	//Czy jestem fizykiem?
	public boolean isPhysicist () {
		return false;
	}
	
}
