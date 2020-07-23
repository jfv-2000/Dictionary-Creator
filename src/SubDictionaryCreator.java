//-------------------------------------------------------------------
//Assignment 4
//Question: Part 1
//Written by: Adrianne Vo (40129948), Jean-Francois Vo (40132554)
//-------------------------------------------------------------------
//This program creates a dictionary, grouping words by alphabetical order from a text input from the user.
/**
 * @author Adrianne Vo(40129948) , Jean-Francois Vo (40132554)
 * COMP 249
 * Assignment 4
 * 19 April 2020
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * SubDictionaryCreator class which creates the dictionary
 */
public class SubDictionaryCreator 
{

	public static void main(String[] args)
	{
		//Welcome message
		System.out.println("Welcome to Adrianne Vo and Jean-Francois Vo's SubDictionaryCreator ! ");
		//Ask user for input
		System.out.print("Please enter the name of the file: ");
		Scanner keyboard = new Scanner(System.in);
		String inputFile = keyboard.nextLine();
		System.out.println();
		
		//Variable Declaration
		PrintWriter pw;
		Scanner sc;
		String line;
		ArrayList<String> text = new ArrayList<String>();
		String temp;
		int letterPresence = 0;
		
		try
		{
			//Read file and create/open dictionary
			sc = new Scanner(new FileInputStream(inputFile));
			pw = new PrintWriter(new FileOutputStream("SubDictionary.txt"));
			//Add to ArrayList
			while(sc.hasNext())
			{
				text.add(sc.next()); 
			}
			//ArrayList to Array
			String[] textArr = new String[text.size()];
			System.out.println(text);
			text.toArray(textArr);
			text.clear();
			
			//Format the text for the conditions
			for (int x = 0; x < textArr.length; x++)
			{
				textArr[x] = textArr[x].toUpperCase();
				//Special character at the end of a word
				if(textArr[x].contains("?") || textArr[x].contains(":") || textArr[x].contains(",") || textArr[x].contains(";") || 
						textArr[x].contains("!") || textArr[x].contains("."))
				{
					textArr[x] = textArr[x].substring(0, textArr[x].length() - 1);
				}
				//Number condition
				if (textArr[x].contains("0") || textArr[x].contains("1") || textArr[x].contains("2") || textArr[x].contains("3") || textArr[x].contains("4") || 
						textArr[x].contains("5") || textArr[x].contains("6") || textArr[x].contains("7") || textArr[x].contains("8") || textArr[x].contains("9") ||
						textArr[x].length() == 1 && !textArr[x].equals("A") && !textArr[x].equals("I"))
				{
					textArr[x] = "";
				}
				//Condition for apostrophe
				if(textArr[x].contains("�"))
				{
					textArr[x] = textArr[x].substring(0, textArr[x].indexOf("�"));
				}
				//Condition for single words
				if(textArr[x].length() == 1 && !textArr[x].equals("A") && !textArr[x].equals("I"))
				{
					textArr[x] = "";
				}

			}
			//Sort dictionary in alphabetical order
			for (int i = 0; i < textArr.length; i++)
			{

				for(int j = i + 1; j < textArr.length ; j++)
				{
					if(textArr[i].equals(textArr[j]))
					{
						textArr[j] = "";
					}
					if (textArr[i].compareTo(textArr[j]) > 0)
					{
						temp = textArr[i];
						textArr[i] = textArr[j];
						textArr[j] = temp;
					}
				}
			}
			
			//Array to ArrayList
			for(int x = 0; x < textArr.length; x++)
			{
				if(!textArr[x].equals(""))
					text.add(textArr[x]);
			}
			text.trimToSize();
			pw.println("The document produced by this sub-dictionary, which includes " + text.size() + " entries.");
			
			//Print to dictionary file
			for(char c = 'A'; c <= 'Z'; c++)
			{
				for(int x = 0; x < text.size(); x++)
				{
					if(text.get(x).startsWith(String.valueOf(c)))
					{
						letterPresence++;
					}
				}
				if (letterPresence != 0)
				{
					pw.print("\n" + c + "\n==\n");
					for(int x = 0; x < text.size(); x++)
					{
						if(text.get(x).startsWith(String.valueOf(c)))
						{
							pw.println(text.get(x));
						}
					}
				}
				letterPresence = 0;
			}
			//Close Scanner and PrintWriter
			pw.close();
			sc.close();
			System.out.println(text);
		}
		catch(FileNotFoundException e)
		{
			System.out.println("Could not open file for reading/writing. System will close.");
			System.exit(0);
		}



	}
}