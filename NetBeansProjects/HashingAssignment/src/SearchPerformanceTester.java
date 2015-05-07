
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gmdnko003
 */
public class SearchPerformanceTester {
    public SearchPerformanceTester() throws IOException{
        System.out.println("Search Performance Testing...\n");
        Dictionary dictionary;
        
        dictionary = new LPHashtable(10007);        
        FileUtil.load(dictionary, "lexicon.txt");
        System.out.println("Number of probes in LPHashtable where load factor is 0.5: "+LPHashtable.noOfProbes);
        
        dictionary = new QPHashtable(10007);        
        FileUtil.load(dictionary, "lexicon.txt");
        System.out.println("Number of probes in QPHashtable where load factor is 0.5: "+QPHashtable.noOfProbes);
        
        dictionary = new SCHashtable(10007);        
        FileUtil.load(dictionary, "lexicon.txt");
        System.out.println("Number of probes in SCHashtable where load factor is 0.5: "+SCHashtable.noOfProbes);
        
        dictionary = new LPHashtable(6673);        
        FileUtil.load(dictionary, "lexicon.txt");
        System.out.println("Number of probes in LPHashtable where load factor is 0.75: "+LPHashtable.noOfProbes);
        
        dictionary = new QPHashtable(6673);        
        FileUtil.load(dictionary, "lexicon.txt");
        System.out.println("Number of probes in QPHashtable where load factor is 0.75: "+QPHashtable.noOfProbes);
        
        dictionary = new SCHashtable(6673);        
        FileUtil.load(dictionary, "lexicon.txt");
        System.out.println("Number of probes in SCHashtable where load factor is 0.75: "+SCHashtable.noOfProbes);
        
        dictionary = new LPHashtable(4999);        
        FileUtil.load(dictionary, "lexicon.txt");
        System.out.println("Number of probes in LPHashtable where load factor is 1: "+LPHashtable.noOfProbes);
        
        dictionary = new QPHashtable(4999);        
        FileUtil.load(dictionary, "lexicon.txt");
        System.out.println("Number of probes in QPHashtable where load factor is 1: "+QPHashtable.noOfProbes);
        
        dictionary = new SCHashtable(4999);        
        FileUtil.load(dictionary, "lexicon.txt");
        System.out.println("Number of probes in SCHashtable where load factor is 1: "+SCHashtable.noOfProbes);
    }
}
