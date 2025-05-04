import java.io.*;
import java.util.*;

public class Databaza {

	private HashMap<Integer, Student> studenti;

    public Databaza() {
        
    	studenti = new HashMap<>();
    
    }

    public boolean pridaj_studenta(Student s) {
        
    	if (studenti.containsKey(s.getid())) {
           
    		return false;
        
    	}
        
    	studenti.put(s.getid(), s);       
    	return true;
    
    }

    public boolean odober_student(int id) {
        
    	if (studenti.containsKey(id)) {
            
    		studenti.remove(id);
            return true;
        
    	}
        
    	return false;
   
    }

    public Student getStudent(int id) {
        
    	if (studenti.containsKey(id)) {
            
    		return studenti.get(id);
        
    	}
        
    	return null;
    
    }

    public boolean pridaj_znamku(int id, int znamka) {
       
    	Student s = getStudent(id);
        
    	if (s != null && znamka >= 1 && znamka <= 5) {
            
    		s.pridaj_znamku(znamka);
            return true;
        
    	}
        
    	return false;
    
    }

    public boolean spustDovednost(int id) {
       
    	Student s = getStudent(id);
        
    	if (s != null && s instanceof Dovednost) {
            
    		((Dovednost) s).spustDovednost();
            return true;
        
    	}
        
    	return false;
    
    }

    public void vypis_student_abeceda() {
       
    	List<Student> zoznam = new ArrayList<>(studenti.values());

        for (int i = 0; i < zoznam.size(); i++) {
            
        	for (int j = i + 1; j < zoznam.size(); j++) {
                
        		if (zoznam.get(i).getpriezvisko().compareTo(zoznam.get(j).getpriezvisko()) > 0) {
                   
        			Student temp = zoznam.get(i);
                    zoznam.set(i, zoznam.get(j));
                    zoznam.set(j, temp);
                
        		}       
        	}     
        }

        for (Student s : zoznam) {
           
        	System.out.println(s);
        
        }
    }

    public double vypocet_priemer(Class trieda) {
       
    	double sucet = 0;
        int pocet = 0;
       
        for (Student s : studenti.values()) {
            
        	if (trieda.isInstance(s)) {
               
        		double priemer = s.getstudijny_priemer();
               
        		if (priemer > 0) {
                   
        			sucet += priemer;
                    pocet++;
                
        		}
            }
        }
       
        if (pocet == 0) { 
        	
        	return 0;
        
        }
        
        else {
			
        	return sucet / pocet;
        	
        }       
    }

    public int pocet_student(Class trieda) {
       
    	int pocet = 0;
    	for (Student s : studenti.values()) {
            
    		if (trieda.isInstance(s)) {
                
    			pocet++;
            
    		}
        }
        
    	return pocet;
    
    }

    public boolean uloz_student_Subor(int id, String nazovSuboru) {
        
    	Student s = getStudent(id);
        if (s == null) {
        	
        	return false;

        }
        
        try {
            
        	File subor = new File(nazovSuboru);
         
            if (!subor.exists()) {
                
            	subor.createNewFile(); 
            
            }
        	
        	PrintWriter pw = new PrintWriter(new FileWriter(nazovSuboru));
            pw.println(s.getid());
            pw.println(s.getmeno());
            pw.println(s.getpriezvisko());
            pw.println(s.getrok_narodenia());
            
            for (int znamka : s.getznamky()) {
                
            	pw.print(znamka + " ");
            
            }
            
            pw.println();
            pw.close();
            return true;
        
        } 
       
        catch (IOException e) {
           
        	return false;
        
        }
    }

    public boolean nacitaj_student_subor(String nazovSuboru, boolean jeTLI) {
        
    	try {
            
        	BufferedReader br = new BufferedReader(new FileReader(nazovSuboru));
            int id = Integer.parseInt(br.readLine());
            String meno = br.readLine();
            String priezvisko = br.readLine();
            int rok = Integer.parseInt(br.readLine());
            String[] znamky_text = br.readLine().trim().split(" ");

            Student s;
            if (jeTLI) {
               
            	s = new TLI_Studenti(meno, priezvisko, rok);
            } 
            
            else {
                
            	s = new IBE_Studenti(meno, priezvisko, rok);
            
            }

            for (int i = 0; i < znamky_text.length; i++) {
                
            	int znamka = Integer.parseInt(znamky_text[i]);
                s.pridaj_znamku(znamka);
            
            }

            return pridaj_studenta(s);
        
        }
        
        catch (Exception e) {
            
        	return false;
        
        }
    }

    public Collection<Student> getvsetci_studenti() {
        
    	return studenti.values();
    
    }
	
}
