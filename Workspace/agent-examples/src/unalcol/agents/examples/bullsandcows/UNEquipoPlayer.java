/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.bullsandcows;

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
    private String secretNumber;
    private boolean secretNumberFound;
    private NumberIndex ni;

    public UNEquipoPlayer(NumberIndex ni){
        this.ni = ni;
        this.answerSize = ni.positions;
        this.digits = ni.digits;
        init();
    }

    private void init(){

        random = new Random();
        
        digitsArray = new char[digits];
        digitsArray[0] = '0';
        for(int i=1;i<digits;i++)
            digitsArray[i] = (char)(digitsArray[i-1]+1);

        secretNumberFound = false;
        secretNumber = randomNumber();
        //System.out.println(secretNumber);
        
        answers = Permutations(answerSize);
        //System.out.println(answers.size());
        guessesDone = 0;
    }

    private String randomNumber(){
        String ret = "";
        
        LinkedList<Character> availableDigits = new LinkedList<Character>();
        for(int i=0;i<digits;i++)
            availableDigits.add(digitsArray[i]);
        
        for(int i=0;i<answerSize;i++){
            int index = random.nextInt(availableDigits.size());
            ret += ""+availableDigits.get(index);
            availableDigits.remove(index);
        }
        return ret;
    }
    
    private LinkedList<String> Permutations(int size){
        
        if (size > 0){
            LinkedList ret = new LinkedList<String>();

            for (String s : Permutations(size - 1))
                for (char n : digitsArray)
                    if (!s.contains(n+""))
                        ret.add(s + n);
            //System.out.println(size);
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

    public int getBulls(String enemyGuess){
        int tb=0;
        for (int ix = 0; ix < answerSize; ix++)
            if (secretNumber.charAt(ix) == enemyGuess.charAt(ix))
                tb++;
        if(tb==answerSize)
            secretNumberFound = true;
        return tb;
    }
    
    public int getCows(String enemyGuess){
        int tc=0;
        for (int ix = 0; ix < answerSize; ix++)
            if (!(secretNumber.charAt(ix) == enemyGuess.charAt(ix)) && 
               (secretNumber.contains(""+enemyGuess.charAt(ix))))
                tc++;
        return tc;
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
    
    public int[] next(){
        String guess = getGuess();
        int ret[] = new int[guess.length()];
        char chars[] = guess.toCharArray();
        for(int i=0;i<chars.length;i++)
            ret[i] = (int)(chars[i]-'0');
        return ret;
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
    
    public boolean hasBeenBeaten(){
        return secretNumberFound;
    }
    
    /*public UNEquipoPlayer clone(){
        UNEquipoPlayer cloned = new UNEquipoPlayer(null);
        cloned.answerSize = answerSize;
        cloned.answers = (LinkedList<String>) answers.clone();
        cloned.digits = digits;
        cloned.digitsArray = digitsArray.clone();
        cloned.guessesDone = guessesDone;
        cloned.random = new Random();
        cloned.secretNumber = cloned.randomNumber();
        cloned.secretNumberFound = secretNumberFound;
        return cloned;
    }*/

    boolean check(int[] m_opt, int[] bc) {
        int bulls = bc[0];
        int cows = bc[1];
        
        String guess = "";
        for(int i=0;i<m_opt.length;i++)
            guess += ""+m_opt[i];
        
        //System.out.println(guess);
        
        registerBullsAndCows(guess, bulls, cows);
        
        return answers.size()>0;
        
    }
    
}
    
