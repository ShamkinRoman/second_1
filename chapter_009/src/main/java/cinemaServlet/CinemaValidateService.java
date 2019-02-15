package cinemaServlet;

import java.util.List;


public class CinemaValidateService implements AutoCloseable {
    private static final CinemaValidateService instance = new CinemaValidateService();
    private static final CinemaDBStore memory = new CinemaDBStore().getINSTANCE();

    public static CinemaValidateService getInstance() {
        return instance;
    }

   public boolean addPlace(Buyer buyer){
        return memory.addPlace(buyer);
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

