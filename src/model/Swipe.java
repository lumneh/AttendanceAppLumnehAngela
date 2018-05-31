package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author mga
 */
public class Swipe implements Comparable<Swipe>{

    /**
     *
     */
    protected final int id;

    /**
     *
     */
    protected String cardId;

    /**
     *
     */
    protected String room;

    /**
     *
     */
    protected final Calendar swipeDateTime;
    
    private static int lastSwipeIdUsed = 0;
    static final char EOLN='\n';       
    static final String QUOTE="\"";    
    
    /**
     *
     */
    public Swipe() {
        this.id = ++lastSwipeIdUsed;
        this.cardId = "Unknown";
        this.room = "Unknown";
        this.swipeDateTime = getNow();
    }
    
    /**
     *
     * @param cardId
     * @param room
     */
    public Swipe(String cardId, String room) {
        this.id = ++lastSwipeIdUsed;
        this.cardId = cardId;
        this.room = room;        
        this.swipeDateTime = getNow();
    }    
    
    /**
     *
     * @param swipeId
     * @param cardId
     * @param room
     * @param swipeDateTime
     */
    public Swipe(int swipeId, String cardId, String room, Calendar swipeDateTime) {
        this.id = swipeId;
        this.cardId = cardId;
        this.room = room;
        this.swipeDateTime = swipeDateTime;
        if (swipeId > Swipe.lastSwipeIdUsed)
            Swipe.lastSwipeIdUsed = swipeId;          
    }     

    
    private Calendar getNow() {
        Calendar now = Calendar.getInstance();  
        return now;
    }    

    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    // Methods required: getters, setters, hashCode, equals, compareTo, comparator


//    Increment 1a


    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Calendar getSwipeDateTime() {
        return swipeDateTime;
    }

    //   Implementation of equals method
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        Swipe swipe = (Swipe) o;
        return id == swipe.id &&
                java.util.Objects.equals(cardId, swipe.cardId) &&
                java.util.Objects.equals(room, swipe.room) &&
                java.util.Objects.equals(swipeDateTime, swipe.swipeDateTime);
    }

    //    Implementation of hashCode method
    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), id, cardId, room, swipeDateTime);
    }

    //    Implementation of the compareTo method
    @Override
    public int compareTo(Swipe compareSwipe){

        int crdId = compareSwipe.getId();
        //ascending order
        return this.id - crdId;

    }
   
   public static Comparator<Swipe>SwipeDateComparator = new Comparator<Swipe>() {
 
   @Override
   public int compare(Swipe swipe1, Swipe swipe2) {
 
      Calendar swipeDate1 = swipe1.getSwipeDateTime();
      Calendar swipeDate2 = swipe2.getSwipeDateTime();
      
      if(swipeDate1.before(swipeDate2)){
          return -1;
      }      
      if(swipeDate1.after(swipeDate2)){
          return 1;
      }
      return 0;
   }
   };
           
    /**
     *
     * @param calendar
     * @return
     */
        
    public static String formatSwipeDateTime(Calendar calendar) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar now = Calendar.getInstance();  
        return dateFormat.format(calendar.getTime());
    }    

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "\nSwipe Id: " + this.id + " - Card Id: " + this.cardId +            
                " - Room: " + this.room + " - Swiped: " + formatSwipeDateTime(this.swipeDateTime);
    }
    
    public String toString(char DELIMITER){
        return  Integer.toString(this.id)+ DELIMITER 
                + QUOTE + this.cardId + QUOTE + DELIMITER 
                + QUOTE + this.room + QUOTE + DELIMITER 
                + QUOTE + formatSwipeDateTime(this.swipeDateTime) + QUOTE;
    }
}
