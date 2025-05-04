import java.sql.*;
import java.util.List;

public class DB_Helper {

	private static final String URL = "jdbc:sqlite:studenti.db";

    public static void nacitaj_z_databaze(Databaza db) {
        
    	try (Connection conn = DriverManager.getConnection(URL)) {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM studenti");

            while (rs.next()) {
                
            	int id = rs.getInt("id");
                String meno = rs.getString("meno");
                String priezvisko = rs.getString("priezvisko");
                int rok = rs.getInt("rok_narodenia");
                String typ = rs.getString("typ");
                String znamky_text = rs.getString("znamky");
                Student s;
                
                if (typ.equals("TLI")) {
                    
                	s = new TLI_Studenti(meno, priezvisko, rok);
                
                } 
                
                else {
                    
                	s = new IBE_Studenti(meno, priezvisko, rok);
                
                }

                for (String z : znamky_text.split(" ")) {
                    
                	if (!z.isEmpty()) s.pridaj_znamku(Integer.parseInt(z));               
                
                }

                db.pridaj_studenta(s);
            }

        } 
    	
    	catch (SQLException e) {
            
    		System.out.println("Chyba pri načítaní databázi: " + e.getMessage());
        
    	}
    }

    public static void uloz_do_databaze(Databaza db) {
        
    	try (Connection conn = DriverManager.getConnection(URL)) {
            
    		Statement st = conn.createStatement();
            st.executeUpdate("DROP TABLE IF EXISTS studenti");
            st.executeUpdate("CREATE TABLE studenti (id INTEGER, meno TEXT, priezvisko TEXT, rok_narodenia INTEGER, typ TEXT, znamky TEXT)");

            PreparedStatement ps = conn.prepareStatement("INSERT INTO studenti (id, meno, priezvisko, rok_narodenia, typ, znamky) VALUES (?, ?, ?, ?, ?, ?)");

            for (Student s : db.getvsetci_studenti()) {
                
            	ps.setInt(1, s.getid());
                ps.setString(2, s.getmeno());
                ps.setString(3, s.getpriezvisko());
                ps.setInt(4, s.getrok_narodenia());

                String typ = (s instanceof TLI_Studenti) ? "TLI" : "IBE";
                ps.setString(5, typ);

                List<Integer> znamky = s.getznamky();
                StringBuilder sb = new StringBuilder();
                for (int z : znamky) sb.append(z).append(" ");
                ps.setString(6, sb.toString().trim());

                ps.executeUpdate();
            
            }

        } 
    	
    	catch (SQLException e) {
            
        	System.out.println("Chyba pri ukladaní do databáze: " + e.getMessage());
        
    	}
    }	
}
