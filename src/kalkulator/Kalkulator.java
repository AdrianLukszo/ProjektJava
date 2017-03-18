package kalkulator;
import org.apache.commons.lang3.StringUtils;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * @author Adrian Lukszo
 *
 */
public class Kalkulator {

	public Kalkulator() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Zczytuje wyrazenia z klawiatury a potem wysyla je do funkcji liczacej, aby wyswietlic je na ekranie.
	 * 
	 * @param a przesylam scanner abym nie tracil klawiatury po opuszeniu tej funkcji
	 */
	public void liczEkran(Scanner a)
	{
		
		
		String obliczenia;
		do
		{
			obliczenia=a.nextLine();
			if(!StringUtils.isAlpha(obliczenia))
			{
				System.out.print("= "+licz(obliczenia)+"\n");
			}
		}while(!obliczenia.contains("koniec"));
		
	};
	
	/**
	 * Zczytuje linie tekstu, aby wyslac do metody liczacej wyrazenia, a potem je wyswietlic.
	 * 
	 * @param nazwa nazwa pliku, ktory chcemy otworzyc
	 * @throws FileNotFoundException zabezpieczenie gdyby nie istanial podany przez nas plik
	 */
	public void liczPlik(String nazwa) throws FileNotFoundException
	{
		Scanner a = new Scanner(new File(nazwa));
		String obliczenia;
		while(a.hasNext())
		{
			obliczenia=a.nextLine();
			if(!StringUtils.isAlpha(obliczenia))
			{
				System.out.println(licz(obliczenia));
			}
		}
		a.close();
	};
	
	/**
	 * Metoda oblicza wyrazenia a potem zwraca ich wynik. Pomiedzy liczbami i operatorami musza byc spacje
	 *<p>
	 * Metoda przyjmuje wyrazenie, ktore zamienia na typ scanner aby szybko i latwo zczytac dane do dwoch
	 * list, jednej przechowujacej liczby i drugiej operatory. Kolejnym krokiem jest skopiowanie tych list.
	 * Dalszym krokiem jest wykonanie operacji mnozenia i dzielenia. Po uzyskaniu wyniku usuwamy dany operator
	 * z listy. Sam wynik zapisujemy na miejsu jednej skladowej liczby, usuwaj¹c te druga. Wszystkie operacje
	 * zamiany i usuwania wykonujemy na kopiach list. Kolejnym krokiem jest dodawanie lub odejmowanie od
	 * pierwszej liczby w skopiowanej liscie, za kazdym razem usuwajac operator i dodawana/odejmowana liczbe,
	 * az skoncza sie operatory.  
	 * <p>
	 *  
	 * @param wyrazenie
	 * @return wartosc podanego wyrazenia
	 */
	private double licz(String wyrazenie)
	{
		Scanner b=new Scanner(wyrazenie);
		List<String> operatory=new ArrayList<String>();
		
		List<Double> liczby=new ArrayList<Double>();
		List<Double> zapasL;
		List<String>  zapasO;
		double help;
		do
		{
			
			if(b.hasNextDouble())liczby.add(b.nextDouble());
			else operatory.add(b.next());
		}while(b.hasNext());
		b.close();
		zapasL=liczby;
		zapasO=operatory;
		for(int i=0;i<operatory.size();i++)
		{
			if(operatory.get(i).equals("*" ))
			{
				help=liczby.get(i)*liczby.get(i+1);
				zapasL.set(i,help);
				zapasL.remove(i+1);
				zapasO.remove(i);
			}
			else if(operatory.get(i).equals("/") )
			{
				help=liczby.get(i)/liczby.get(i+1);
				zapasL.set(i,help);
				zapasL.remove(i+1);
				zapasO.remove(i);
			}
		}
		
		for(;zapasL.size()!=1;)
		{
			if(zapasO.get(0).equals("-"))
			{
				help=zapasL.get(0)-zapasL.get(1);
				zapasL.set(0,help);
				zapasL.remove(1);
				zapasO.remove(0);
			}
			else if(zapasO.get(0).equals("+"))
			{
				help=zapasL.get(0)+zapasL.get(1);
				zapasL.set(0,help);
				zapasL.remove(1);
				zapasO.remove(0);
			}
		}
		return (double) zapasL.get(0);
	};
	
		
}
