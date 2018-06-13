package io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.PatternSyntaxException;

public class SearchLines {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        if( args.length != 1){
            wrong_usage("Regular Expression can't have spaces");
        }

        String regularExpression = args[0];
        MyReader reader = null;
        try{
            try{
                reader = new MyReader(new InputStreamReader(System.in), regularExpression);
            } catch ( PatternSyntaxException pse) {
                wrong_usage("'"+ANSI_RED+regularExpression+ANSI_RESET +"'"+ " --> wrong regular expression syntax \n" +
                        "(https://www.tutorialspoint.com/java/java_regular_expressions.htm)");
            }

            String line;
            System.out.println("Line Number | Matches | Line");
            while((line = reader.readLine()) != null){
              boolean match = (reader.getAmountOfMatches() > 0);
              if (match){
                  String paddedLineNumber = String.format("%12d",reader.getLineNumber()).replace(' ',' ');
                  String paddedMatches = String.format("%9d",reader.getAmountOfMatches()).replace(' ',' ');

                  out(paddedLineNumber,paddedMatches,line,reader.getSubstringIndices());

                  //System.out.println(paddedLineNumber+"|"+paddedMatches+"|"+line);
              }

            }
        } catch(IOException ioEx) {
            ioEx.printStackTrace();
            wrong_usage("An error occured \\_>.<_/");
        } finally {
            if(reader!=null)
                try {
                reader.close();
                } catch (IOException ioEx){
                    ioEx.printStackTrace();
                    wrong_usage("An error occured while shutting down \\_ö.ö_/");
                }
        }





    }

    private static void out(String paddedLineNumber, String paddedMatches, String line, int[] indices) {
        String first = line.substring(0,indices[0]);
        String mid = line.substring(indices[0],indices[1]);
        String last = line.substring(indices[1],line.length());

        System.out.print(paddedLineNumber+"|"+paddedMatches+"|");
        String mySuperCoolNewColoredLine = first+ANSI_RED+mid+ANSI_RESET+last;
        mySuperCoolNewColoredLine = mySuperCoolNewColoredLine.trim();
        System.out.println(mySuperCoolNewColoredLine);
    }


    private static void wrong_usage(String error) {
        System.out.println(error);
        how_to_use();
        System.exit(1);
    }

    private static void how_to_use() {
        System.out.println("    Specific: java io/SearchLines 'pu.*c' < Beispiel.java");
        System.out.println("    General : java io/SearchLines 'regex' < File");
    }
}
