
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import pt.ualg.fct.aed.HashTable;
import pt.ualg.fct.aed.Quicksort;                            //* * * * * * * * * * * * * * * * * * *
import pt.ualg.fct.aed.lists.immutable.Empty;               // ANTES DE SUBMETER VE A MAIN *
import pt.ualg.fct.aed.lists.immutable.List;                //** * * * * * * **  ** * * * * * * 

import java.text.Collator;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
/**
 * Class Concordance represents objects that implement concordances. A
 * concordance is a table that associates a word to all small pieces of text
 * where that word has been identified.
 */

/**
 * Run at the console from the work directory with 
 * java -ea -Dfile.encoding=UTF-8 -classpath ../bin:../../algs4.jar:../../aed2.jar Concordance args
 */

public class Concordance implements Iterable<String>
{
  public final static Locale myLocale = new Locale("pt", "PT");
  public final static Collator ptCollator = Collator.getInstance(myLocale);
  private static String regex;
  private static HashTable<String, List<String>> ht;

  /**
   * Initializes a concordance object.
   */
  public Concordance()
  {
    ht = new HashTable<>();
  }

  /**
   * Adds a word and the corresponding text where the word has been identified
   * 
   * @param key  the word.
   * @param line the text where the word has been identified.
   */
  public void put(String key, String line)
  {
	  List<String> lin=new Empty<>();
	  if(has(key)) {
		  lin=get(key);
	  }
	  if(!lin.has(line)) {
		  lin=lin.cons(line);
		  ht.put(key, lin);
	  }
  }

  /**
   * Adds each word in the given string together with the given string. Words are
   * substrings of the given string delimited by spaces.
   * 
   * @param line the given string, which contains several words.
   */
  public void putLine(String line){
	 String[] words=line.split(regex);
	 int i=0;
	 while(i<words.length) {
	 	   put(words[i++].toLowerCase(),line);
	    }
  }

  /**
   * Returns the list of strings associated with the given string, representing
   * all texts where the given string has been identified. Words are delimited in
   * each line be spaces.
   * 
   * @param key the given string.
   * @returns the list of strings associated with the given string, as explained.
   */
  public static List<String> get(String key)
  {
    return ht.get(key);
  }

  /**
   * Displays the table on the console, using a simple format
   */
  public void show()
  {
    for (String s : ht)
    {
      StdOut.println(s);
      for (String t : get(s))
        StdOut.println("...." + t);
    }
  }

  /**
   * Displays the table on the console, with the keys sorted
   * and the values for each key also sorted.
   */
  public void showSorted() {
	  String[] s=new String[ht.size()];
	  int i=0;
	  for(String h:ht) {
		  s[i++]=h;
	  }
	  Arrays.sort(s);
	  for(int j=0;j<s.length;j++) {
		 StdOut.println(s[j]);
		 String[] s1=new String[get(s[j]).size()]; 
		 i=0;
		 for(String h:get(s[j])) {
			  s1[i++]=h;
		  }
		  Arrays.sort(s1);
		  for(String t : s1) {
			  StdOut.println("...." + t);
		  }
		}
	  }

  public static void taskA()
  {
    Concordance c = new Concordance();
    regex=" ";
    while (StdIn.hasNextLine())
    {
      String line = StdIn.readLine();
      c.putLine(line);
    }
    c.showSorted();
  }

  public static void taskB(String filename)
  {
	  In f = new In(filename);
	  regex="[^\\p{L}\\-]+";
	  Concordance c = new Concordance();
	  while (f.hasNextLine())
	    {
	      String line = f.readLine();
	      c.putLine(line);
	    }
	  while(StdIn.hasNextLine()) {
		  String word=StdIn.readLine();
			 String[] s1=new String[get(word).size()];
			 int i=0;
			 for(String h:get(word)) {
				  s1[i++]=h;
			  }
			 new Quicksort<String>(ptCollator).sort(s1); 
			  for(String t : s1) {
				  StdOut.println("...." + t);
			  }
	  }
  }
  public static void testA()
  {
    Concordance c = new Concordance();
    while (StdIn.hasNextLine())
    {
      String line = StdIn.readLine();
      c.putLine(line);
    }
    c.show();
  }
 
  public static void main(String[] args)
  {
    char choice = 'A';
    if (args.length > 0)
      choice = args[0].charAt(0);
    if (choice == 'A')
      taskA();
    else if (choice == 'B')
    {
      //assert args.length > 1;
      taskB("http://w3.ualg.pt/~pjguerreiro/sites/26_aed_1516/docs/lusiadas_tudo.txt");
    }
  }

  @Override
  public Iterator<String> iterator()
  {
    return ht.iterator();
  }

  public boolean has(String key)
  {
    return ht.has(key);
  }

  public boolean isEmpty()
  {
    return ht.isEmpty();
  }

  public int size()
  {
    return ht.size();
  }

}