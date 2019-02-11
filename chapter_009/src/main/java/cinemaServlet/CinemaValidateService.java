package cinemaServlet;

import crudServlet.DBStore;
import crudServlet.User;
import crudServlet.Validate;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
This is class singleton and used MemoryStore as storage.
Also used methods for adding, updating, deleting and finding users in storage, but for used methods check validation not null data.
Used pattern for check email.
 */
public class CinemaValidateService implements AutoCloseable {
    private static final CinemaValidateService instance = new CinemaValidateService();
    private static final CinemaDBStore memory = new CinemaDBStore().getINSTANCE();

    public static CinemaValidateService getInstance() {
        return instance;
    }

   public boolean addPlace(Buyer buyer, String place){
        return memory.addPlace(buyer, place);
   }

   public boolean deletePlace(String place){
        return memory.deletePlace(place);
   }

   public List<String> getBusyPlace(){
        return memory.getbusyPlace();
   }
    public void close() {
        try {
            memory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

