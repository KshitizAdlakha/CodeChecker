package codechecker.core.services.impl;

import codechecker.core.services.impl.visitors.HashCodeVisitor;

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
        HashSet<Integer> crvDiff1 = this.difference(crvCode1, crvIntersection);//Hashodes of the nodes unique to the first submission
        HashSet<Integer> crvDiff2 = this.difference(crvCode2, crvIntersection);//Hashodes of the nodes unique to the second submission

        double similarityPercent = (((double) 2 * crvIntersection.size()) / ((2 * crvIntersection.size()) + crvDiff1.size()
                + crvDiff2.size())) * 100; //Similarity percent calculation
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
     * Function to obtain the hashcodes that are unique to the first HashSet in the argument.
     */
    private HashSet<Integer> difference(HashSet<Integer> crvCode, HashSet<Integer> crvCommon){
        crvCode.removeAll(crvCommon);
        return crvCode;
    }
}
