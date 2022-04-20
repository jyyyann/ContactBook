import java.util.HashMap;
import ecs100.*;
/**
 * Write a description of class Contacts here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Contacts
{
    // instance variables 
    private HashMap<Integer, Contact> contactBook;
    private int currContactId;
    private Contact currContact;

    /**
     * Constructor for objects of class Contacts
     */
    public Contacts()
    {
        // initialise instance variables
        contactBook = new HashMap<Integer, Contact>();
        
        // Creating Contacts
        Contact c1 = new Contact(1, "Jane Smith", "64", "210220755");
        Contact c2 = new Contact(2, "Parker Colon", "1", "6505130514");
        Contact c3 = new Contact(3, "Maia Graves", "852", "69723678");
        
        // Add contacts to contact book
        contactBook.put(1, c1);
        contactBook.put(2, c2);
        contactBook.put(3, c3);
        
        this.currContactId = 3; // Store the current contact id
    }
    
    /**
     * Set ContactId
     * @param int amount to increment contact id by 
     */
    public void setContactId() {
        this.currContactId += 1;
    }

    /** 
     * Add a contact to the map
     */
    public void addContact(String name, String cc, String num, String img) {
        contactBook.put(currContactId, new Contact(currContactId, name, cc, num, img));
    }
    
    /** 
     * Finds a contact based on name
     * Sets the current contact instance if found
     * @return boolean false if not found
     */
    public boolean findContact(String name) {
        //Search for book
        for (int contactId : contactBook.keySet()) {
            if (contactBook.get(contactId).getName().toLowerCase().equals(name)) {
                currContact = contactBook.get(contactId); // set the current contact
                contactBook.get(contactId).displayContact(); // show avatar on Canvas
                return true;
            }
        }
        return false;
    }
    
    /**
     * Getter for the current contact instance
     */
    public Contact getContact() {
        return this.currContact;
    }
    
    /** 
     * Print detail of all contacts
     */
    public void printAll() {
        //Traverse Map
        for (int contactId : contactBook.keySet()) {
            UI.println(contactId + "." + " Details: ");
            UI.println(contactBook.get(contactId).getName() + " "
                        + "+" + contactBook.get(contactId).getCC() + " "
                        + contactBook.get(contactId).getNum());
        }
    }
}
