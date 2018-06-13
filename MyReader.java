package io;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MyReader extends LineNumberReader {


    private int matches;
    private Pattern regex;
    private String currentLine;

    private int start;
    private int end;


    /**
     * Create a new line-numbering MyReader, using the default input-buffer
     * size.
     *
     * @param in A MyReader object to provide the underlying stream
     */
    public MyReader(java.io.Reader in, String regex) {
        super(in);
        this.regex = Pattern.compile(regex);
        this.matches = 0;
        this.currentLine = null;
    }


    /*
    Methode readLine() alle Zeichen von einem MyReader bis zum nächsten Zeile- numbruch einlesen und
    als einen String zurückgeben kann. Der Zeilenumbruch soll nicht mit zurückgegeben werden.
    Beim Erreichen des Dateiendes soll null zurückgegeben werden.
     */
    @Override
    public String readLine() throws IOException {
        matches = 0;
        if((currentLine = super.readLine()) != null){
            Matcher matcher = this.regex.matcher(currentLine);
            while(matcher.find()){

                start = matcher.start();
                end = matcher.end();
                ++matches;
            }
        }
        return currentLine;
    }

    /*
    die Methode getLineNumber() die Nummer der zuletzt gelesenen Zeile ermitteln kann.
     */
    @Override
    public int getLineNumber() {
        return super.getLineNumber();
    }

    /*
    die Methode getAmountOfMatches() ermitteln kann, wie oft ein dem Konstruktor übergebener regulärer Ausdruck
    in der zuletzt gelesenen Zeile gefunden wurde.
     */
    public int getAmountOfMatches() {
        return this.matches;
    }

    public int[] getSubstringIndices(){
        int[] indices = {start,end};
        return indices;
    }

}
