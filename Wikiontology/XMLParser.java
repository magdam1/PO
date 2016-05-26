//Klasa zajmująca się parsowaniem pliku XML.
package pl.edu.mimuw.wikiontology.mm345746;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser {
	
	private Database dbase = new Database();
	private static String file;
	
	public XMLParser (String file) {
		this.file = file;
	}

	public Database createDatabase () {
		
		try {
			
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
 
			DefaultHandler handler = new DefaultHandler() {
		
				boolean name = false;
				boolean text = false;
		
				String suppname;
				StringBuilder sb = new StringBuilder();
		
				//Początek znacznika.
				public void startElement(String uri, String localName,String qName, 
						Attributes attributes) throws SAXException {
			
					if (qName.equalsIgnoreCase("title")) {
						name = true;
					}	
			
					else if (qName.equalsIgnoreCase("text")) {
	    				text = true;
					}
				}
		
				//Wnętrze znacznika.
				public void characters(char ch[], int start, int length) throws SAXException {
			
					if (name) {
						suppname = new String(ch, start, length);
					}
			
					else if (text) {
						sb.append(ch, start, length);
					}
				}
		
				//Koniec znacznika.
				public void endElement(String uri, String localName,
						String qName) throws SAXException {
			
					if (qName.equalsIgnoreCase("title")) {
						name = false;
					}
			
					else if (qName.equalsIgnoreCase("text")) {
						text = false;
						Person p = new RegexCheck().newPerson(sb, suppname);
						if (p != null) {
							dbase.addPerson(p);							
						}
				
						sb.delete(0, sb.length());
					}
				}
		
			};
	
			saxParser.parse(file, handler);
			dbase.makeLinks();
			return dbase;
			
		}
	
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		finally {
			dbase.makeLinks();
			return dbase;
		}
	
	}
	
}
