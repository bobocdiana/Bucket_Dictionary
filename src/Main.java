import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
	
	
	public static void main (String[] args) {
		int n,m,q,i;
		Dictionary map = new Dictionary (Integer.valueOf(args[0]));
		String word,definition,last_definition;
		String [] synonyms=new String [Integer.valueOf(args[0])];
		long start=0,stop;
		
		Scanner conf;
		try {
			
			/*
			 * The configuration file is parsed and the dictionary is built (words,
			 * their definitions and their syonyms) 
			*/
			conf = new Scanner(new File(args[1]));
			String line = conf.nextLine();
			String [] arg=line.split(" ");
			n=Integer.valueOf(arg[0]);
			m=Integer.valueOf(arg[1]);
			for (i=1;i<=n;i++) {
				word=conf.nextLine();
				definition=conf.nextLine();
				map.addWord(word, definition);
			}
			for (i=1;i<=m;i++) {
				line=conf.nextLine();
				arg=line.split(" ");
				map.addSynonym(arg[0],arg[1]);
			}
			conf.close();
			
			/*This represents the study of the time's complexity of my implementation.
			 * 
			 */
			if (n==1 || n%10==0) { 
			  start = System.currentTimeMillis();
			  System.out.println(start);
			}
			
			/*This part represents the parsing of the query file and based on the
			 * command given it is written the answer in the output file.
			 * 
			 */
			Scanner inter=new Scanner(new File(args[2]));
			PrintWriter output = new PrintWriter(new File(args[3]));
			line=inter.nextLine();
			q=Integer.valueOf(line);
			for (i=1;i<=q;i++) {
				line=inter.nextLine();
				arg=line.split(" ");
				if (arg[0].equals("0")) {
					output.println(map.get(arg[1]));
				}
				else if (arg[0].equals("1")) {
					output.println(map.getNumberOfDefinitions(arg[1]));
				}
				else if (arg[0].equals("2")) {
					synonyms=map.getSynonyms(arg[1]);
					for (String s:synonyms) {
						output.print(s+" ");
					}
					output.println();
				}
			}
			inter.close();
			output.close();
			/*This part represents the study of the time's complexity and the time of running
			 * is written at stdout.
			 * 
			 */
			if (n==1 || n%10==0) { 
				 stop=System.currentTimeMillis();
				 System.out.println(stop);
				 System.out.println("timp de rulare pentru nr de bucket= "+args[0]+"este "+ (stop-start));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
