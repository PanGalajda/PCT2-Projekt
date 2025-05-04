import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		 	
			Databaza databaza = new Databaza();
			DB_Helper.nacitaj_z_databaze(databaza);
	        Scanner sc = new Scanner(System.in);
	        boolean konec = false;

	        while (!konec) {
	            
	        	System.out.println("\n--- MENU ---");
	            System.out.println("1 - Pridaj študenta");
	            System.out.println("2 - Pridaj známku");
	            System.out.println("3 - Odstráň študenta");
	            System.out.println("4 - Zobraz študenta");
	            System.out.println("5 - Spusť dovednost");
	            System.out.println("6 - Vypíš študentov (zoradené)");
	            System.out.println("7 - Vypočítaj priemer (TLI/IBE)");
	            System.out.println("8 - Počet študentov (TLI/IBE)");
	            System.out.println("9 - Ulož študenta do suboru");
	            System.out.println("10 - Načítaj študenta zo suboru");
	            System.out.println("0 - Konec");
	            System.out.print("Zadaj voľbu: ");
	            int volba = Integer.parseInt(sc.nextLine());

	            if (volba == 1) {
	                
	            	System.out.print("Zadaj skupinu (1 = TLI, 2 = IBE): ");
	                int obor = Integer.parseInt(sc.nextLine());
	                System.out.print("Meno: ");
	                String meno = sc.nextLine();
	                System.out.print("Priezvisko: ");
	                String priezvisko = sc.nextLine();
	                System.out.print("Rok narodenia: ");
	                int rok = Integer.parseInt(sc.nextLine());
	                Student novy;
	                
	                if (obor == 1) {
	                    
	                	novy = new TLI_Studenti(meno, priezvisko, rok);
	                
	                } 
	                
	                else {
	                   
	                	novy = new IBE_Studenti(meno, priezvisko, rok);
	               
	                }

	                databaza.pridaj_studenta(novy);
	                System.out.println("Študent pridaný s ID: "+novy.getid());
	           
	            }

	            else if (volba == 2) {
	                
	            	System.out.print("Zadaj ID studenta: ");
	                int id = Integer.parseInt(sc.nextLine());
	                System.out.print("Zadaj známku (1–5): ");
	                int znamka = Integer.parseInt(sc.nextLine());
	                
	                if (databaza.pridaj_znamku(id, znamka)) {
	                   
	                	System.out.println("Známka pridaná.");
	               
	                }
	                
	                else {
	                   
	                	System.out.println("Chyba – študent alebo známka neexistuje.");
	               
	                }
	            }

	            else if (volba == 3) {
	                
	            	System.out.print("Zadaj ID študenta: ");
	                int id = Integer.parseInt(sc.nextLine());
	                
	                if (databaza.odober_student(id)) {
	                    
	                	System.out.println("Študent bol odstránení.");
	                
	                } 
	                
	                else {
	                    
	                	System.out.println("Študent sa nenašiel.");
	                
	                }
	            }

	            else if (volba == 4) {
	                
	            	System.out.print("Zadaj ID študenta: ");
	                int id = Integer.parseInt(sc.nextLine());
	                Student s = databaza.getStudent(id);
	                
	                if (s != null) {
	                    
	                	System.out.println(s);
	                
	                } 
	                
	                else {
	                    
	                	System.out.println("Študent sa nenašiel.");
	               
	                }
	            }

	            else if (volba == 5) {
	                
	            	System.out.print("Zadaj ID študenta: ");
	                int id = Integer.parseInt(sc.nextLine());
	                
	                if (!databaza.spustDovednost(id)) {
	                    
	                	System.out.println("Dovednosť nemôžem spustit – študent sa nenašiel alebo nemá danú dovednost.");
	                
	                }
	            }

	            else if (volba == 6) {
	                
	            	databaza.vypis_student_abeceda();
	            
	            }

	            else if (volba == 7) {
	                
	            	System.out.print("Zadej obor (1 = TLI, 2 = IBE): ");
	                int obor = Integer.parseInt(sc.nextLine());
	                double priemer = 0;
	                
	                if (obor == 1) {
	                    
	                	priemer = databaza.vypocet_priemer(TLI_Studenti.class);
	                } 
	                
	                else if (obor == 2) {
	                    
	                	priemer = databaza.vypocet_priemer(IBE_Studenti.class);
	                
	                }
	                
	                System.out.printf("Priemer študentov daného oboru: "+priemer);
	            
	            }

	            else if (volba == 8) {
	                
	            	System.out.print("Zadaj obor (1 = TLI, 2 = IBE): ");
	                int obor = Integer.parseInt(sc.nextLine());
	                int pocet = 0;
	                
	                if (obor == 1) {
	                    
	                	pocet = databaza.pocet_student(TLI_Studenti.class);
	                
	                } 
	                
	                else if (obor == 2) {
	                    
	                	pocet = databaza.pocet_student(IBE_Studenti.class);
	                
	                }
	                System.out.println("Počet študentov na danom obore: "+pocet);
	            }

	            else if (volba == 9) {
	                
	            	System.out.print("Zadaj ID študenta: ");
	                int id = Integer.parseInt(sc.nextLine());
	                System.out.print("Zadaj názov suboru: ");
	                String subor = sc.nextLine();
	                
	                if (databaza.uloz_student_Subor(id, subor)) {
	                    
	                	System.out.println("Študent bol uložený.");
	                
	                } 
	                
	                else {
	                    
	                	System.out.println("Nepodarilo sa uložiť.");
	                
	                }
	            }

	            else if (volba == 10) {
	                
	            	System.out.print("Zadaj názov súboru: ");
	                String subor = sc.nextLine();
	                System.out.print("Je študent z TLI? (ano/ne): ");
	                String odpoved = sc.nextLine();
	                boolean jeTLI = odpoved.equalsIgnoreCase("ano");

	                if (databaza.nacitaj_student_subor(subor, jeTLI)) {
	                    
	                	System.out.println("Študent načítaný.");
	                
	                } 
	                
	                else {
	                    
	                	System.out.println("Nepodarilo sa načítať.");
	                
	                }
	            }

	            else if (volba == 0) {
	                	            	
	            	konec = true;
	            	DB_Helper.uloz_do_databaze(databaza);
	                System.out.println("Program ukončení.");
	            
	            }

	            else {
	                
	            	System.out.println("Nesprávna volba, prosím zvolte 1-10.");
	            
	            }
	        }

	        sc.close();
	    
	}
}


