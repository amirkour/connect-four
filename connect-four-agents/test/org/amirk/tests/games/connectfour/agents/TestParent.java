
package org.amirk.tests.games.connectfour.agents;
/*
 * This class houses helpers for the various test classes in the agent package.
 */
public class TestParent{

    /*
     * Just a heler to set the values of the given 2d matrix to the given value.
     */
    protected void setMatrixValues(long[][] matrix, long value){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = value;
            }
        }
    }
}

