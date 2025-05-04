
import java.util.ArrayList;
import java.util.List;

public abstract class Student {
    
	private static int generator_id = 1;
    private int id;
    private String meno;
    private String priezvisko;
    private int rok_narodenia;
    private List<Integer> znamky;

    
    public Student(String meno, String priezvisko, int rok_narodenia) {
        
    	this.id = generator_id++;
        this.meno = meno;
        this.priezvisko = priezvisko;
        this.rok_narodenia = rok_narodenia;
        this.znamky = new ArrayList<>();
    
    }

    public int getid(){
    	
    	return id;
    	
    }
    
    public String getmeno(){
    	
    	return meno;
    	
    }
    
    public String getpriezvisko(){
    	
    	return priezvisko;
    	
    }
    
    public int getrok_narodenia(){
    	
    	return rok_narodenia;
    	
    }
    
    public List<Integer> getznamky(){
    	
    	return new ArrayList<>(znamky);
    	
    }

    
    public void pridaj_znamku(int znamka) {
       
    	if (znamka >= 1 && znamka <= 5) znamky.add(znamka);
    
    }

    public double getstudijny_priemer() {
       
    	if (znamky.isEmpty()) {
           
    		return 0;
        
    	}

        int suma = 0;
        for (int i = 0; i < znamky.size(); i++) {
			
        	suma += znamky.get(i);
        	
		}

        return (double) suma / znamky.size();
        
    }

    @Override
    public String toString() {
    	
    	return String.format("ID: %d, %s %s, rok narození: %d, průměr: %.2f", id, meno, priezvisko, rok_narodenia, getstudijny_priemer());
   
    }
}