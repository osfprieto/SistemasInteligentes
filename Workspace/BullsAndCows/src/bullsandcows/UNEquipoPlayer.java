/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows;

//Player for Bulls and cows

import java.util.LinkedList;
import java.util.Random;


public class UNEquipoPlayer{
   
        
    private int answerSize;
    private int digits;
    private LinkedList<String> answers;
    private Random random;
    private int guessesDone;
    private char digitsArray[];

    public UNEquipoPlayer(int answerSize, int digits){
        this.answerSize = answerSize;
        this.digits = digits;
        init();
    }

    private void init(){

        digitsArray = new char[digits];
        digitsArray[0] = '0';
        for(int i=1;i<digits;i++)
            digitsArray[i] = (char)(digitsArray[i-1]+1);

        random = new Random();
        answers = Shuffle(Permutations(answerSize));
        guessesDone = 0;

    }

    private LinkedList<String> Permutations(int size){
        if (size > 0){
            LinkedList ret = new LinkedList<String>();

            for (String s : Permutations(size - 1))
                for (char n : digitsArray)
                    if (!s.contains(n+""))
                        ret.add(s + n);
            return ret;
        }
        else{
            LinkedList<String> ret = new LinkedList<String>();
            ret.add("");
            return ret;
        }
    }

    private LinkedList<String> Shuffle(LinkedList<String> source){
        LinkedList<String> ret = new LinkedList<String>();

        while (source.size() > 0){
            int ix = random.nextInt(source.size());
            ret.add(source.get(ix));
            source.remove(ix);
        }
        return ret;
    }

    public void registerBullsAndCows(String guess, int bulls, int cows){

        for (int ans = answers.size() - 1; ans >= 0; ans--){
            int tb = 0, tc = 0;
            for (int ix = 0; ix < answerSize; ix++)
                if (answers.get(ans).charAt(ix) == guess.charAt(ix))
                    tb++;
                else if (answers.get(ans).contains(""+guess.charAt(ix)))
                    tc++;
            if ((tb != bulls) || (tc != cows))
                answers.remove(ans);
        }

    }

    public String getGuess(){
        guessesDone++;
        return answers.get(random.nextInt(answers.size()));
    }

    public boolean hasToAsk(){
        return answers.size()>1;
    }

    public boolean hasBeenCheated(){
        return answers.size()==0;
    }

    public int getGuessesDone(){
        return guessesDone;
    }
}
    
