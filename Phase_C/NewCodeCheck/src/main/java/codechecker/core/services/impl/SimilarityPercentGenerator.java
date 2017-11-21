package codechecker.core.services.impl;

import java.util.HashSet;

//Class to obtain the similarity percent between two submissions
public class SimilarityPercentGenerator {
    
    /*
     * Function to calculate the similarity percent based on the hashcodes of the nodes obtained from the two submissions
     */
    public double getSimilarityPercent(HashCodeVisitor crv1, HashCodeVisitor crv2){
        HashSet<Integer> crvCode1 = crv1.getNodeHashCodes(); //Hashcodes of the nodes from the first submission
        HashSet<Integer> crvCode2 = crv2.getNodeHashCodes(); //Hashcodes of the nodes from the second submission
        HashSet<Integer> crvIntersection = this.intersection(crvCode1, crvCode2); //Common hashcodes from both submissions
        HashSet<Integer> crvUnion = this.union(crvCode1, crvCode2); //Combine all the hashcodes from both the submissions

        double similarityPercent = ((double)crvIntersection.size()/crvUnion.size()) * 100; 
        return similarityPercent;
    }
    
    /*
     * Function to obtain the common hashcodes from the two given hashsets
     */
    private HashSet<Integer> intersection(HashSet<Integer> crvCode1, HashSet<Integer> crvCode2){
        HashSet<Integer> crvIntersection = new HashSet<Integer>(crvCode1);
        crvIntersection.retainAll(crvCode2);
        return crvIntersection;
    }
    
    /*
     * Function to obtain all the hashcodes from the two given hashsets
     */
    private HashSet<Integer> union(HashSet<Integer> crvCode1, HashSet<Integer> crvCode2){
        HashSet<Integer> crvUnion = new HashSet<Integer>(crvCode1);
        crvUnion.addAll(crvCode2);
        return crvUnion;
    }
}
