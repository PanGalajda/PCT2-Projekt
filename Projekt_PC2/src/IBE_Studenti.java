import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class IBE_Studenti extends Student implements Dovednost {

	public IBE_Studenti(String meno, String priezvisko, int rok_narodenia) {
		
		super(meno, priezvisko, rok_narodenia);
		
	}

	public void spustDovednost() {
		
		String celemeno = getmeno() + " " + getpriezvisko();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(celemeno.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            System.out.println("Meno studenta cez SHA-256:");
            System.out.println(sb.toString());
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Hashovací algoritmus nieje dostupný.");
        }
		
	}
		
}
