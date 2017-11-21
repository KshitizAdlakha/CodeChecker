package codechecker.core.services.impl;

import java.util.HashSet;

public class SimilarityPercentGenerator {
    public double getSimilarityPercent(HashCodeVisitor crv1, HashCodeVisitor crv2){
        HashSet<Integer> crvCode1 = crv1.getNodeHashCodes();
        HashSet<Integer> crvCode2 = crv2.getNodeHashCodes();
        HashSet<Integer> crvIntersection = this.intersection(crvCode1, crvCode2);
        HashSet<Integer> crvUnion = this.union(crvCode1, crvCode2);

        double similarityPercent = ((double)crvIntersection.size()/crvUnion.size()) * 100;
        return similarityPercent;
    }

    private HashSet<Integer> intersection(HashSet<Integer> crvCode1, HashSet<Integer> crvCode2){
        HashSet<Integer> crvIntersection = new HashSet<Integer>(crvCode1);
        crvIntersection.retainAll(crvCode2);
        return crvIntersection;
    }

    private HashSet<Integer> union(HashSet<Integer> crvCode1, HashSet<Integer> crvCode2){
        HashSet<Integer> crvUnion = new HashSet<Integer>(crvCode1);
        crvUnion.addAll(crvCode2);
        return crvUnion;
    }
}
