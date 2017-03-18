package kalkulator;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 * @author Adrian Lukszo
 *
 */
public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Glowne menu z wyborem jakiego kalkulatora chcemy uzyc czy moze skonczyc
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Scanner klaw = new Scanner(System.in);

		String opcja;
		Kalkulator K = new Kalkulator();
		do
		{
			System.out.println("1-Kalkulator z ekranu\n2-Kalkulator z pliku\n0-Zakonczenie programu");
			opcja=klaw.nextLine();
			if(opcja.matches("1"))
				{
				K.liczEkran(klaw);
				}
			else if(opcja.matches("2"))
				{
				System.out.println("Podaj nazwe pliku z obliczeniami");
				String nazwa=klaw.nextLine();
				K.liczPlik(nazwa);
				}
		}while(!opcja.matches("0"));
		klaw.close();
	}

}
