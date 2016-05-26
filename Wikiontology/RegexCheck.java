/*Klasa zajmująca się przetwarzeaniem tekstu wychwyconego przez parser XML.
  Tworzy nowe obiekty klas Person i Physicist wraz z odpowiednimi metadanymi.*/
package pl.edu.mimuw.wikiontology.mm345746;

import java.util.*;
import java.util.regex.*;

public class RegexCheck {

	private boolean isPerson = false, physicist = false;
	
	private List<String> References = new ArrayList<String>();
	private List<String> Categories = new ArrayList<String>();
	
	//Metoda pomocnicza, sprawdza obecność podsłowa w słowie.
	private void helper (String word, String s) {
		if (word.contains(s)) {
			isPerson = true;
			if (s.equals("physicists")) {
				physicist = true;
			}
		}
	}
	
	//Metoda porządkuje słowa (ucina niepotrzebne części wyrazów).
	private String truncate (String word) {
		
		if (word.contains("file:")) {
			if (word.contains("[")) {
				int i = word.indexOf("[");
				char[] ch = word.toCharArray();
				word = new String(ch, i+2, ch.length-i-2);
			}
		}
		
		if (word.contains("|")) {
			int i = word.indexOf("|");
			char[] ch = word.toCharArray();
			word = new String(ch, 0, i);
		}
		
		if (word.contains("#")) {
			int i = word.indexOf("#");
			char[] ch = word.toCharArray();
			word = new String(ch, 0, i);
		}
		
		return word;
	}
	
	//Metoda sprawdza obecność szablonu 'Persondata'.
	private void persondataCheck (String s) {
		
		Pattern pattern = Pattern.compile("\\{\\{([^\\}]*)\\}\\}");
		Matcher matcher = pattern.matcher(s);
		
		while (matcher.find() && (!isPerson)) {
			String word = matcher.group(1);
			helper(word, "persondata");
		}
	}
	
	//Metoda sprawdza przynależność do odpowiednich kategorii.
	private void categoryCheck (String s) {
		
		Pattern pattern = Pattern.compile("\\[\\[([^\\]]*)\\]\\]");
		Matcher matcher = pattern.matcher(s);
		
		while (matcher.find()) {
			
			String word = matcher.group(1);
			
			word = truncate(word);
			
			if (word.contains("category:")) {
				
				int index = word.indexOf(":");
				char[] ch = word.toCharArray();
				word = new String(ch, index+1, ch.length-index-1);
				Categories.add(word);
				
				helper(word, "births");
				
				helper(word, "deaths");
				
				helper(word, "philosophers");
				
				helper(word, "physicists");
				
				helper(word, "mathematicians");
			}
			
			else {
			
			References.add(word);
			
			}
		}
	}
	
	//Jeżeli artykuł dotyczy osoby, metoda tworzy nowy obiekt klasy Person (lub Physicist).
	public Person newPerson (StringBuilder sb, String suppname) {
		
		String text = sb.toString();	
		text = text.toLowerCase();
		
		persondataCheck(text);
		categoryCheck(text);
		
		if (isPerson) {
			
			if (physicist) {
				return new Physicist(suppname, References, Categories);
			}
			else {
				return new Person(suppname, References, Categories);
			}
		}
		
		else {
			return null;
		}
		
	}
}
