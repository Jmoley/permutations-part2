package tsp;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Set;
import java.util.LinkedHashSet;
public class IntegerPermutation {
    static List<Integer> nextPermutation(List<Integer> sequence) {
        ArrayList<Integer> sequenceR = new ArrayList<Integer>();
        sequenceR.addAll(sequence); //Creates a duplicate of sequence, to avoid editing original list.

        int i = largestI(sequenceR);    //Step 1
        int j = largestJ(sequenceR, i); //Step 2
        swap(sequenceR, i, j);          //Step 3
        reverse(sequenceR, i);          //Step 4

        if (i == -1) { //i=-1 means that no such largest i exists, and this sequence is the last permutation.
            return null;
        } else {
            return(sequenceR);
        }
    }

    public static int largestI(List<Integer> sequenceR) {
        int i = 1;
        int largestI = -1; //If largestI remains unchanged at -1, no such largest index i exists
        while (i < sequenceR.size()) {
            if (sequenceR.get(i - 1) < sequenceR.get(i)) { //Looks for the largest value of i, where the integer 
                largestI = i;                              //at i is preceded by a smaller value.
            }
            i++;
        }
        return (largestI);
    }

    public static int largestJ(List<Integer> sequenceR, int i) {
        int j = 1;
        int largestJ = -1; //Start value irrelevant.
        while (j < sequenceR.size()) {
            if (j >= i & sequenceR.get(j) > sequenceR.get(i - 1)) { //Looks for the largest value of j, where the integer
                largestJ = j;                                       //at j is greater than the integer at i-1.
            }
            j++;
        }
        return (largestJ);
    }

    public static void swap(List<Integer> sequenceR, int i, int j) { //Swaps sequenceR[j] and sequenceR[i-1]
        int temp = sequenceR.get(j); //Stores the integer at j as a temp value to avoid losing it.
        sequenceR.set(j, sequenceR.get(i - 1)); //Sets the int at position j to be the value at i-1
        sequenceR.set(i - 1, temp); //Sets the int at position i-1 to be the value of temp (j). The two are now swapped.
    }

    public static void reverse(List<Integer> sequenceR, int i) {
        ArrayList<Integer> suffix = new ArrayList<Integer>();
        ArrayList<Integer> prefix = new ArrayList<Integer>();

        for (int k = i; k < sequenceR.size(); k++) {
            suffix.add(sequenceR.get(k)); //Adds to the list 'suffix' all values to be reversed (starting from i)
        }

        Collections.reverse(suffix);

        for (int m = 0; m < i; m++) {
            prefix.add(sequenceR.get(m)); //Adds to the list 'prefix' all values before the suffix (values not reversed)
        }
        sequenceR.clear(); //Clears the list sequenceR, to then combine the prefix with the newly reversed suffix.
        sequenceR.addAll(prefix);
        sequenceR.addAll(suffix);
    }

    static Set<List<Integer>> allPermutations(int n){
    
        if (n <= 0) {
            throw new IllegalArgumentException("Error, n is <= 0 (illegal).");
        } else {
            Set<List<Integer>> permutations = new LinkedHashSet<List<Integer>>();
            ArrayList<Integer> list = new ArrayList<Integer>();

            for (int i = 0; i <= n; i++){
                list.add(i); //Creates a list with upper bound n. n=3 would create list=[0,1,2,3]
            }
            
            permutations.add(list); //Adds list to set
            
            for (int j = 0; j < factorial(list.size()-1); j++){ //List size n has n! permutations
            
                permutations.add(nextPermutation(list)); //adds the next permutation to the list
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.addAll(nextPermutation(list)); //creates a 'temp' arraylist containing the next permutation
                list.clear();
                list.addAll(temp); //Updates 'list' with the next permutation.
            }
            return (permutations); //Returns the set
            
        }
    }

    static int factorial(int n){ //recursive function to return n!
        if (n ==0){
            return 1;
        }else{
            return(n * factorial(n-1));
        }
        
    }

    public static void main(String[] args) { //Test code!
        ArrayList<Integer> tempList = new ArrayList<Integer>(4);

        tempList.add(1);
        tempList.add(3);
        tempList.add(2);
        tempList.add(0);
        //System.out.println(tempList);
        //System.out.println(nextPermutation(tempList));

        System.out.println(allPermutations(3));
    }

}

