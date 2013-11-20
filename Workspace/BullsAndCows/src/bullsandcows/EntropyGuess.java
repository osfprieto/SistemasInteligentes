/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bullsandcows;

import java.util.LinkedList;

/**
 *
 * @author osfprieto
 */
public class EntropyGuess {
    
    private LinkedList<String> initVocab;
    private double maxEntropy;
    
    public EntropyGuess(LinkedList<String> initVocab){
        this.initVocab = initVocab;
        
        //theoretically max entropy
        maxEntropy = (initVocab.size() * Math.log10(initVocab.size()));
    }
        
    private double computeInformationGain(String word, LinkedList<String> vocab){
        //computes information gain of a word over the set of words in vocab """
        
        //compute the num of elements per partition (as created by w)
        //bucket_freq = [0 for i in xrange(0,41)]
        int bucket_freq[] = new int[41];
        for(int i=0;i<41;i++)
            bucket_freq[i] = 0;
        
        
        for(String w : vocab){
            
            int bc[] = computeMatch(w, word);
            
            int b = bc[0];
            int c = bc[1];
            
            bucket_freq[10*b+c]++;
            
        }
        //compute information gain
        double e = 0;
        for ( int f : bucket_freq){
            if (f > 1)
                e += f * Math.log10(f);
        }
        return e;
    }
        
    public String computeGuess(LinkedList<String> vocab){
        //""" calculates guess that minimizes entropy """
        double min_e = maxEntropy;
        String min_w = null;
        for (String w : initVocab){
            //compute information gain
            double e = computeInformationGain(w, vocab);
            
            //update max
            if (e == 0)
                return w;
            else if (e <= min_e){
                min_e = e;
                min_w = w;
            }
        }
        return min_w;
    }

    private int[] computeMatch(String w, String word) {
        int bc[] = new int[2];
        for(int i=0;i<w.length();i++){
            if(w.charAt(i)==word.charAt(i))
                bc[0]++;
            else if(w.contains(""+word.charAt(i)))
                bc[1]++;
        }
        return bc;
    }
    
}
