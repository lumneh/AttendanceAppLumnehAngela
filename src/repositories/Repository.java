package repositories;

import daos.DAOImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import model.Swipe;


public class Repository implements RepositoryInterface {
    private List<Swipe> items;

    public Repository() {
        this.items = new ArrayList<>();
    }

    public Repository(List<Swipe> items) {
        this.items = items;
    }

    public Repository(String filename) {
        this();
        // Create dao and execute load
         DAOImpl dao = new DAOImpl();
        this.items = dao.load(filename).getItems();   
    }

    @Override
    public List<Swipe> getItems() {
        return this.items;
    }

    @Override
    public void setItems(List<Swipe> items) {
        this.items = items;
    }

    @Override
    public void add(Swipe item) {
        this.items.add(item);
    }

    @Override
    public void remove(int id) {
        Predicate<Swipe> predicate = e->e.getId() == id;
        this.items.removeIf(predicate);
    }

    @Override
    public Swipe getItem(int id) {
        for (Swipe item:this.items) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }

    @Override
    public String toString() {
        return "\nItems: " + this.items;
    }

    @Override
    public void store(String filename) {
        // create dao and execute store
         DAOImpl dao = new DAOImpl();
        dao.store(filename, this); 
    }

    public String toString(char DELIMITER) {
       String output = "";
       final String EOLN = "\n";
        for (Swipe swipe: this.items) {
            output += swipe.toString(DELIMITER);
            output += EOLN;
        }
        return output ; 
    }
}
