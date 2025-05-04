import java.util.HashMap;
import java.util.Map;

public class TLI_Studenti extends Student implements Dovednost{

	public TLI_Studenti(String meno, String priezvisko, int rok_narodenia) {
		
		super(meno, priezvisko, rok_narodenia);
		
	}
	
	public void spustDovednost() {
		
		String cele_meno = getmeno() + " " + getpriezvisko();
        System.out.println("Meno a priezvisko studenta v Morseovej abecede:");

        for (char c : cele_meno.toUpperCase().toCharArray()) {
            String morse = MORSE_MAP.get(c);
            System.out.print(morse != null ? morse + " " : " ");
        }
       System.out.println();
       
	}

	private static final Map<Character, String> MORSE_MAP = new HashMap<>();
    static {
        MORSE_MAP.put('A', ".-"); MORSE_MAP.put('B', "-...");
        MORSE_MAP.put('C', "-.-."); MORSE_MAP.put('D', "-..");
        MORSE_MAP.put('E', "."); MORSE_MAP.put('F', "..-.");
        MORSE_MAP.put('G', "--."); MORSE_MAP.put('H', "....");
        MORSE_MAP.put('I', ".."); MORSE_MAP.put('J', ".---");
        MORSE_MAP.put('K', "-.-"); MORSE_MAP.put('L', ".-..");
        MORSE_MAP.put('M', "--"); MORSE_MAP.put('N', "-.");
        MORSE_MAP.put('O', "---"); MORSE_MAP.put('P', ".--.");
        MORSE_MAP.put('Q', "--.-"); MORSE_MAP.put('R', ".-.");
        MORSE_MAP.put('S', "..."); MORSE_MAP.put('T', "-");
        MORSE_MAP.put('U', "..-"); MORSE_MAP.put('V', "...-");
        MORSE_MAP.put('W', ".--"); MORSE_MAP.put('X', "-..-");
        MORSE_MAP.put('Y', "-.--"); MORSE_MAP.put('Z', "--..");
        MORSE_MAP.put('0', "-----"); MORSE_MAP.put('1', ".----");
        MORSE_MAP.put('2', "..---"); MORSE_MAP.put('3', "...--");
        MORSE_MAP.put('4', "....-"); MORSE_MAP.put('5', ".....");
        MORSE_MAP.put('6', "-...."); MORSE_MAP.put('7', "--...");
        MORSE_MAP.put('8', "---.."); MORSE_MAP.put('9', "----.");
        MORSE_MAP.put(' ', "/");
    }
	
}
