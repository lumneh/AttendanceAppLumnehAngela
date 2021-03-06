package controllers;

import helpers.InputHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import repositories.Repository;
import model.Swipe;
import model.VisitorSwipe;


/**
 *
 * @author mga
 */
public class AttendanceController_Increment3 {
    private final Repository repository;
    
    /**
     *
     */
        
    public AttendanceController_Increment3() {
        // to be completed
        InputHelper inputHelper = new InputHelper();
        char c = inputHelper.readCharacter("Load File (Y/N)?");
        if(c == 'Y' || c == 'y'){
            String fileName = inputHelper.readString("Enter filename");
            this.repository = new Repository(fileName);
        }
        else{
            this.repository = new Repository();
        }
    }


    /**
     *
     */
    public void run() {
        boolean finished = false;
        
        do {
            char choice = displayAttendanceMenu();
            switch (choice) {
                case 'A': 
                    addSwipe();
                    break;
                case 'B':  
                    listSwipes();
                    break;
                case 'C': 
                    listSwipesByCardIdOrderedByDateTime(); // 
                    break;                    
                case 'D': 
                    listSwipeStatistics(); //
                    break;
                case 'Q': 
                    finished = true;
            }
        } while (!finished);
    }
    
    private char displayAttendanceMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Swipe");
        System.out.print("\tB. List Swipes");        
        System.out.print("\tC. List Swipes In Date Time Order");
        System.out.print("\tD. List Swipes Which Match Card Id");       
        System.out.print("\tQ. Quit\n");         
        return inputHelper.readCharacter("Enter choice", "ABCDQ");
    }    
    
    private void addSwipe() {
        System.out.format("\033[31m%s\033[0m%n", "Add Swipe");
        System.out.format("\033[31m%s\033[0m%n", "========="); 
//        Increment 2
        InputHelper inputHelper = new InputHelper();
        String checker = inputHelper.readString("Are you a Student or a Visitor?");
//        check if a student or visitor
        if(checker.equals("Student")){
            String cardId = inputHelper.readString("Enter your Card Id:");
            String room = inputHelper.readString("Enter the classroom name:");
            Swipe newSwipe = new Swipe(cardId, room);
            repository.add(newSwipe);
        }
        else{
            String cardId = inputHelper.readString("Enter your Card Id:");
            String room = inputHelper.readString("Enter the classroom name:");
            String visitorName = inputHelper.readString("Enter your name:");
            String visitorCompany = inputHelper.readString("Enter your company's name:");
            VisitorSwipe newVisitorSwipe = new VisitorSwipe(cardId, room, visitorName, visitorCompany);
            repository.add(newVisitorSwipe);
        }

    }
    
    private void listSwipes() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes");
        System.out.format("\033[31m%s\033[0m%n", "======");
        Iterator  items = repository.getItems().listIterator();
        while(items.hasNext()){
            Swipe swipe = (Swipe)items.next();
            System.out.println(swipe);
        }
    }


    private void listSwipesByCardIdOrderedByDateTime() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes By Card Id");
        System.out.format("\033[31m%s\033[0m%n", "=================");
       InputHelper inputHelper = new InputHelper();
        String prompt = inputHelper.readString("Enter the Card Id:");
        Iterator swipeItems = repository.getItems().listIterator();
        List<Swipe> matchedSwipe = new ArrayList<>();
        while (swipeItems.hasNext()) {
            Swipe swipe = (Swipe) swipeItems.next();
            if (swipe.getCardId().equals(prompt)){
                matchedSwipe.add(swipe);
            }
        
        }
        Collections.sort(matchedSwipe,  Swipe.SwipeDateComparator.reversed());//sorts the collection in reverse order using the comparator
        for(Swipe swipe : matchedSwipe){
            System.out.println(swipe.toString());
        }
       
    }    
    
    private void listSwipeStatistics() {
        System.out.format("\033[31m%s\033[0m%n", "Swipe Statistics for room");
        System.out.format("\033[31m%s\033[0m%n", "========================="); 
    }
}
