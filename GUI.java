import java.util.HashMap;
import ecs100.*;
/**
 * Write a description of class GUI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class GUI
{
    // instance variables 
    private Contacts contacts; // declare contacts instance
    private Contact contact;   // declare contact instance

    /**
     * Constructor for objects of class GUI
     */
    public GUI()
    {
        // initialise instance variables
        contacts = new Contacts(); // instantiate the contacts object
        UI.initialise(); // create GUI window
        UI.addButton("Print All", contacts::printAll);
        UI.addButton("Add", this::addContact);
        UI.addButton("Find", this::findContact);
        UI.addButton("Clear", UI::clearPanes);
        UI.addButton("Quit", UI::quit);
        
        UI.setMouseListener(this::doMouse);
    }

    /**
     * Add a book to collection
     */
    public void addContact()
    {
        final int INCREMENT = 1;
        String cc;
        String num;
        String name;
        
        // boolean of validity of input
        boolean nmValid = false;
        boolean ccValid = false;
        boolean numValid = false;
        
        // Check name
        do{
            name = UI.askString("Name: ");
            
            nmValid = inputChecker(name); // pass onto validity checker 
        } while (nmValid == false);
        
        //Check country code
        do{
            String cc_input = UI.askString("Country Code: ");
            cc = cc_input.replaceAll(" ", "");
            int cLength = cc.length();
            
            ccValid = inputChecker(cc, cLength, 1, 3);
        } while (ccValid == false);
        
        // Check phone number
        do{
            String num_input = UI.askString("Phone Number: ");
            num = num_input.replaceAll(" ", "");
            int nLength = num.length();
            
            numValid = inputChecker(num, nLength, 7, 14);
        } while (numValid == false);
        
        // add a avatar image for diaplay in the GUI
        String imgFileName = UIFileChooser.open("Choose Image File");
        
        // Increment the contact ID count and add the hashmap
        contacts.setContactId(); // increment the id by 1
        contacts.addContact(name, cc, num, imgFileName);
    }
    
    /**
     * A checker method that returns true if the text input is valid
     */
    public boolean inputChecker(String x) {
        boolean valid = false;
        if (x.isEmpty()) {
                // prevent empty input
                UI.println("Don't leave it blank");
            } else {
                valid = true;
            }
        return valid;
    }
    
    /**
     * A checker method that returns true if the numerical input is valid
     * Use for both country code and phone no. with customised min & max value 
     */
    public boolean inputChecker(String x, int len, int min, int max) {
        boolean valid = false;
        if ((len <= max) && (len >= min) && (x.matches("[0-9]+"))) {
                valid = true;
            } else if (x.isEmpty()) {
                UI.println("Don't leave it blank");
            } else if (!x.matches("[0-9]+")) {
                // prevent non-numerical input
                UI.println("Must be a number");
            } else if ((len > max) || (len < min)) {
                // restrict length of input
                UI.println("Invalid length");
            } 
        return valid;
    }
    
    /**
     * Finds contact based on name
     * Prints out the name and phone num if found
     */
    public void findContact() {
        String contactName = UI.askString("Name of contact: ");
        if (contacts.findContact(contactName.toLowerCase())) {
            // matching name found 
            UI.println("Found Contact!");
            contact = contacts.getContact();
            UI.println("Name: " + contact.getName());
            UI.println("Phone Number: " + "+" + contact.getCC() + " " + contact.getNum());
            UI.printMessage("Click on the icon to clear panes.");
        } else {
            // no matches
            UI.println("That contact does not exist!");
        }
    }
    
    /**
     * Clear text and graphics
     */
    public void doMouse(String action, double x, double y) {
        if (action.equals("clicked")) {
            if (contact.onAvatar(x,y)) {
                UI.clearPanes();
            }
        }
    }
}
