package daos;

import static com.sun.javafx.util.Utils.stripQuotes;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

public class DAOImpl implements DAOInterface{
    static final char DELIMITER = ',';

    @Override
    public Repository load(String filename) {

        Repository repository = new Repository();

        try(BufferedReader theFile = new BufferedReader(new FileReader(filename))){

            String[] tmp;
            String line = theFile.readLine();
            while(line!=null){
                tmp = line.split(Character.toString(DELIMITER));
                int id = Integer.parseInt(tmp[0]);
                String cardId = stripQuotes(tmp[1]);
                String room = stripQuotes(tmp[2]);
//                How to turn a string into a calendar
                Calendar swipeDateTime = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH);
                swipeDateTime.setTime(sdf.parse(stripQuotes(tmp[3])));
               
                //Check if the length of the array is 3 0r 5 t0 know if it is a normal swipe or visitor swipe
                if(tmp.length == 4){
                    Swipe swipe = new Swipe(id, cardId, room, swipeDateTime);
                    repository.add(swipe);
                }
                else {
                    String visitorName = stripQuotes(tmp[4]);
                    String visitorCompany = stripQuotes(tmp[5]);
                    VisitorSwipe visitorSwipe = new VisitorSwipe(id, cardId, room, swipeDateTime, visitorName, visitorCompany);
                    repository.add(visitorSwipe);
                }
                    line = theFile.readLine();
            }
            theFile.close();

        }catch (IOException | ParseException e){
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }

        return repository;
    }

    @Override
    public void store(String filename, Repository repository) {
         try (PrintWriter output = new PrintWriter(filename)) {
            output.print(repository.toString(DELIMITER));
            output.close();
        } catch (FileNotFoundException e) {
            Logger.getLogger(DAOImpl.class.getName()).log(Level.SEVERE, null, e);
        }    
    }
}