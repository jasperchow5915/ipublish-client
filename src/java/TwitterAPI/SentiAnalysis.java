/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TwitterAPI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author lingjunqiu
 */
public class SentiAnalysis {

    private int ptweet = 0;
    private int ntweet = 0;
    private int neutweet = 0;

    public void sentimentAnaysis(String text) throws IOException {

        int pword = 0;
        int nword = 0;

        String[] words = text.split(" ");
        String cLine = null;

        for (String word : words) {
            BufferedReader brP = new BufferedReader(new FileReader("/Users/lingjunqiu/PositiveWords.txt"));
            BufferedReader brN = new BufferedReader(new FileReader("/Users/lingjunqiu/NegativeWords.txt"));
            Pattern p = Pattern.compile("[.,\"\\?!:@()#]");// 增加对应的标点  
            Matcher m = p.matcher(word);
            word = m.replaceAll("").trim();
            while ((cLine = brP.readLine()) != null) {
                if (word.toLowerCase().equals(cLine.toLowerCase())) {
                    pword++;
                    break;
                }
                while ((cLine = brN.readLine()) != null) {
                    if (word.toLowerCase().equals(cLine.toLowerCase())) {
                        nword++;
                        break;
                    }
                }
            }
        }

        if (pword > nword) {
            ptweet++;
            setPtweet(ptweet);
        } else if (pword < nword) {
            ntweet++;
            setNeutweet(ntweet);
        } else {
            neutweet++;
            setNeutweet(neutweet);
        }
        pword = 0;
        nword = 0;
    }

    public int getPtweet() {
        return ptweet;
    }

    public void setPtweet(int ptweet) {
        this.ptweet = ptweet;
    }

    public int getNtweet() {
        return ntweet;
    }

    public void setNtweet(int ntweet) {
        this.ntweet = ntweet;
    }

    public int getNeutweet() {
        return neutweet;
    }

    public void setNeutweet(int neutweet) {
        this.neutweet = neutweet;
    }

}
