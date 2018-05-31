package controllers;

import helpers.InputHelper;
import java.util.Iterator;
import repositories.Repository;
import model.Swipe;


/**
 *
 * @author mga
 */
public class AttendanceController_Increment1 {
    private final Repository repository;
    
    /**
     *
     */
        
    public AttendanceController_Increment1() {
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
