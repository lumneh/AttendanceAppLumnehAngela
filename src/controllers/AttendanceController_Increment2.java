package controllers;

import helpers.InputHelper;
import java.util.Iterator;
import repositories.Repository;
import model.Swipe;
import model.VisitorSwipe;


/**
 *
 * @author mga
 */
public class AttendanceController_Increment2 {
    private final Repository repository;
    
    /**
     *
     */
        
    public AttendanceController_Increment2() {
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
        if(checker.equals("Student") || checker.equals("student")){
            String cardId = inputHelper.readString("Enter your Card Id:");
            String room = inputHelper.readString("Enter the classroom name:");
            Swipe newSwipe = new Swipe(cardId, room);
            repository.add(newSwipe);
        }
        else if(checker.equals("Visitor") || checker.equals("visitor")){
            String cardId = inputHelper.readString("Enter your Card Id:");
            String room = inputHelper.readString("Enter the classroom name:");
            String visitorName = inputHelper.readString("Enter your name:");
            String visitorCompany = inputHelper.readString("Enter your company's name:");
            VisitorSwipe newVisitorSwipe = new VisitorSwipe(cardId, room, visitorName, visitorCompany);
            repository.add(newVisitorSwipe);
        }
        else {
               System.out.println("Invalid Input. Please input either Student or Visitor!"); 
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
        
    }    
    
    private void listSwipeStatistics() {
        System.out.format("\033[31m%s\033[0m%n", "Swipe Statistics for room");
        System.out.format("\033[31m%s\033[0m%n", "========================="); 
    }
}
