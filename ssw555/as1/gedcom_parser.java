/*
*   Christopher Rudel
*   gedcom_parser.java
*   SSW 555 assignment 2
*   Professor Rowland
*   I pledge my honor that I have abided by the Stevens Honor System
*/

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class gedcom_parser
{
    static boolean checkValid(String str)
    {
        boolean isValid = false;
        String[] words = str.split(" "); // this separates all the words in the string into an array
        if(words[0].equals("0"))
        {
            if(words[1].equals("HEAD") || words[1].equals("TRLR") || words[1].equals("NOTE"))
            {
                isValid = true;
                //System.out.println(str);
            } else if(words[2].equals("INDI") || words[2].equals("FAM"))
            {
                isValid = true;
                //System.out.println(str);
            }
        } else if (words[0].equals("1"))
        {
            if(words[1].equals("NAME") || words[1].equals("SEX") || words[1].equals("BIRT") || words[1].equals("DEAT") || words[1].equals("FAMC") || words[1].equals("FAMS") || words[1].equals("MARR") || words[1].equals("HUSB") || words[1].equals("WIFE") || words[1].equals("CHIL") || words[1].equals("DIV"))
            {
                isValid = true;
                //System.out.println(str);
            }
        } else if (words[0].equals("2"))
        {
            if(words[1].equals("DATE"))
            {
                isValid = true;
            }
        }
        
        return isValid;    
        
    }
    static boolean checkSpecial(String str)
    {
        boolean special = false;
        String[] words = str.split(" ");
        if(words[0].equals("0"))
        {
            if(words[1].equals("HEAD") || words[1].equals("TRLR") || words[1].equals("NOTE"))
        {/* do nothing */} else if(words[2].equals("INDI") || words[2].equals("FAM"))
            {
                special = true;
            }
        }
        return special;
    }
    static void formatAndPrint(String str, boolean valid, boolean special)
    {
        System.out.println("--> " + str);
        String[] words = str.split(" ");
        if(special)
        {
            String outputLine = "<-- " + words[0] + "|" + words[2] + "|Y|" + words[1];
            System.out.println(outputLine);
        }else{
            StringBuilder readLine = new StringBuilder("<-- " + words[0] + "|" + words[1] + "|");
            if(valid)
            {
                readLine.append("Y|"); 
            } else{
                readLine.append("N|");
            }
            for(int i=2; i<words.length; i++)
            {
                readLine.append(words[i] + " ");
            }
            System.out.println(readLine.toString());
        }
        return;
    }

    public static void main(String[] args) throws Exception
    {
        File fp = new File("Christopher_Rudel_GEDCOM_Family.ged");
        Scanner sc = new Scanner(fp);
        ArrayList<String> fileLines = new ArrayList<>();
        while(sc.hasNextLine())
        {
            String str = sc.nextLine();
            fileLines.add(str);
        }
        for(int i=0; i<fileLines.size(); i++)
        {
            boolean isValid = checkValid(fileLines.get(i));
            boolean isSpecial = checkSpecial(fileLines.get(i)); //this checks to see if the line starts with a 0 and has INDI or FAM
            formatAndPrint(fileLines.get(i), isValid, isSpecial);
        }
    }
}