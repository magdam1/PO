//Tworzona baza danych jest reprezentantem tej klasy.
package pl.edu.mimuw.wikiontology.mm345746;

import java.util.*;

public class Database {

	private List<Person> People = new ArrayList<Person>();	//Lista osób w bazie.
	
	//Dodawanie nowej osoby.
	public void addPerson(Person p) {
		People.add(p);
	}
	
	//Metoda sprawdza, czy baza zawiera podaną osobę.
	public boolean contains(Person p) {
		List<String> names = new ArrayList<String>();
		
		for (int i=0; i<People.size(); i++) {
			names.add(People.get(i).getName().toLowerCase());
		}
		
		return (names.contains(p.getName().toLowerCase()));
	}
	
	
	//Metoda zwraca indeks podanej osoby w bazie.
	public int indexOf(Person p) {
		if (this.contains(p)) {
			
			List<String> names = new ArrayList<String>();
			
			for (int i=0; i<People.size(); i++) {
				names.add(People.get(i).getName().toLowerCase());
			}
			
			return (names.indexOf(p.getName().toLowerCase()));
		}
		
		else {
			return -1;
		}
	}
	
	//Wielkość bazy.
	public int size() {
		return People.size();
	}
	
	//Gettery
	public Person get (int index) {
		return People.get(index);
	}
	
	public List<Person> getPeople() {
		return People;
	}
	
	public void makeLinks () {
		for (int i=0; i<People.size(); i++) {
			People.get(i).makeLinks(this);
		}
	}
	
	
	//Szukanie najkrótszej ścieżki między dwiema osobami w grafie.
	private List<String> BFS (Filter filter, Person p1, Person p2) {
		
		Queue<Person> q = new LinkedList<Person>();
		List<String> visited = new ArrayList<String>();
		Map<String, String> parents = new TreeMap<String, String>();
		
		List<String> path = new ArrayList<String>();
		
		q.add(p1);
		visited.add(p1.getName().toLowerCase());
		parents.put("", "");
		parents.put(p1.getName(), "");
		
		
		while (!q.isEmpty()) {
			
			Person candidate = q.peek();
			
			if ((candidate.getName().toLowerCase()).equals(p2.getName().toLowerCase())) {
				
				String child = candidate.getName(), parent = parents.get(child);
				
				do {
					path.add(child);
					child = parent;
					parent = parents.get(child);
				}
				while (!child.equals(""));
				
				return path;
				
			}
			
			else {
				
				q.remove();
				List<Person> Neighbours = candidate.getNeighbours();
				
				for (int i=0; i<Neighbours.size(); i++) {
					
					Person p = Neighbours.get(i);
					
					if (filter.canAdd(p) && (!visited.contains(p.getName().toLowerCase()))) {
						q.add(p);
						visited.add(p.getName().toLowerCase());
						parents.put(p.getName(), candidate.getName());
					}
				}		
				
			}
		}
		
		return null;
	}
	
	//Metoda zwraca listę osób tworzących ścieżkę, o ile taka istnieje.
	public List<String> findPath (Filter filter, Person p1, Person p2) throws NoPathException {
		
		List<String> path = BFS(filter, p1, p2);
		
		if (path == null) {
			throw new NoPathException();
		}
		
		else {
			return path;
		}
	}
	
	
	//ToString
	public String toString () {
		String s = "";
		for (int i=0; i<People.size(); i++) {
			s+=People.get(i).toString();
		}
		
		return s;
	}
}
