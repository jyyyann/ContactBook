import ecs100.*;
/**
 * Write a description of class Contact here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Contact
{
    // instance variables 
    private int id;
    private String name;
    private String countryCode;
    private String phoneNo;
    private String image;
    static final String DEFAULT_IMAGE = "avatar.png"; // set default image 
    
    // position and size of avatar
    private int locX = 100;
    private int locY = 100;
    private final double WIDTH = 100;
    private final double HEIGHT = 100;
    
    private boolean contact = false;

    /**
     * Constructor for objects of class Contact
     */
    public Contact(int key, String nm, String cc, String num, String img)
    {
        // initialise instance variables
        id = key;
        name = nm;
        countryCode = cc;
        phoneNo = num;
        if (img == null) {
            this.image = DEFAULT_IMAGE; // add default img if user clicks cancel
        } else {
            this.image = img; // set img as chosen one
        }
    }

    /**
     * Constructor overloading
     * Set default image to obj
     */
    public Contact(int key, String nm, String cc, String num) {
        this(key, nm, cc, num, DEFAULT_IMAGE);
    }
    
    /**
     * Displace image on GUI
     */
    public void displayContact() {
        UI.drawImage(this.image, locX, locY, WIDTH, HEIGHT);
    }
    
    /**
     * Getter for contact id
     */
    public int getId() {
        return this.id;
    }
    
    /**
     * Getter for contact name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * Getter for contact country code
     */
    public String getCC() {
        return this.countryCode;
    }
    
    /**
     * Getter for contact phone number
     */
    public String getNum() {
        return this.phoneNo;
    }
    
    /**
     * Check if position of mouse is on the avatar/icon
     */
    public boolean onAvatar(double x, double y) {
        if ((x >= locX) && (x <= locX + WIDTH) && 
            (y >= locY) && (y <= locY + HEIGHT)) {
                contact = true;
            }
        return contact;
    }
}
