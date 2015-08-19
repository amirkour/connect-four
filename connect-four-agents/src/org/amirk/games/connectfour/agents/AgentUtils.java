
package org.amirk.games.connectfour.agents;


import org.amirk.games.connectfour.entities.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.lang.NullPointerException;
import java.lang.IndexOutOfBoundsException;

/*
 * Helpers for various agent-related functionality.
 */
public class AgentUtils{
    // public static Logger logger = LoggerFactory.getLogger(AgentUtils.class);
    
    /*
     * Returns a sequence of connect-four game moves (a GameMoveSequence) containing consecutive
     * horizontal moves for playerId starting at (row,col).
     *
     * This helper assumes that the given i,j are at the far-left of a sequene (if there is a
     * sequence.)  In other words: this helper won't traverse to the left of i,j, only to the
     * right.
     */
    public static GameMoveSequence getHorizontalSequence(Game game, long playerId, int row, int col) throws Exception{
        if(game == null){ throw new NullPointerException("Cannot get horizontal sequence for null game"); }

        long[][] board = game.getBoardMatrix();
        if(board == null){ throw new NullPointerException("Cannot get horizontal sequence for null game board"); }
        if(row < 0 || col < 0 || row >= board.length || col >= board[row].length){ throw new IndexOutOfBoundsException("Row/Col combo " + row + ", " + col + " are out of bounds of the current game board"); }

        int startRow = row;
        int startCol = col;
        GameMoveSequence sequence = new GameMoveSequence();

        // advance to the right of the starting row and add game moves to the sequence
        // while they match the caller's given player ID
        while(row < board.length && col < board[row].length && board[row][col] == playerId){
            sequence.add(new GameMove(row,col));
            row++;
        }

        // if there's no actual sequence here, return null to indicate it
        if(sequence.getLength() <= 0){ return null; }

        // if there's an open spot to the right of the 
        // current sequence, increment the open-spot counter
        if(row < board.length && !game.isOccupied(row,col)){ 
            sequence.setOpenAdjacentSpots(sequence.getOpenAdjacentSpots() + 1);
        }

        // likewise, if there's an open spot to the left of the 
        // current sequence, increment the open-spot counter
        int leftOfStart = startRow - 1;
        if(leftOfStart >= 0 && !game.isOccupied(leftOfStart, col)){
            sequence.setOpenAdjacentSpots(sequence.getOpenAdjacentSpots() + 1);
        }

        return sequence;
    }

    /*
     * Returns a sequence of connect-four game moves (a GameMoveSequence) containing consecutive
     * vertical moves for playerId starting at (row,col).
     *
     * This helper assumes that the given i,j are at the top of a sequene (if there is a
     * sequence.)  In other words: this helper won't traverse up above i,j, only down.
     */
    public static GameMoveSequence getVerticalSequence(Game game, long playerId, int row, int col) throws Exception{
        if(game == null){ throw new NullPointerException("Cannot get vertical sequence for null game"); }

        long[][] board = game.getBoardMatrix();
        if(board == null){ throw new NullPointerException("Cannot get vertical sequence for null game board"); }
        if(row < 0 || col < 0 || row >= board.length || col >= board[row].length){ throw new IndexOutOfBoundsException("Row/Col combo " + row + ", " + col + " are out of bounds of the current game board"); }

        int startRow = row;
        int startCol = col;
        GameMoveSequence sequence = new GameMoveSequence();

        // advance to the bottom of the starting col and add game moves to the sequence
        // while they match the caller's given player ID
        while(row < board.length && col < board[row].length && board[row][col] == playerId){
            sequence.add(new GameMove(row,col));
            col++;
        }

        // if there's no actual sequence here, return null to indicate it
        if(sequence.getLength() <= 0){ return null; }

        // if there's an open spot to the bottom of the 
        // current sequence, increment the open-spot counter
        // (and note that in an actual game of connect-four, you will
        // NEVER have an open spot available at the bottom of a vertical
        // sequence of moves.)
        if(col < board[row].length && !game.isOccupied(row,col)){ 
            sequence.setOpenAdjacentSpots(sequence.getOpenAdjacentSpots() + 1);
        }

        // likewise, if there's an open spot on top of the 
        // current sequence, increment the open-spot counter
        int topOfStart = startCol - 1;
        if(topOfStart >= 0 && !game.isOccupied(row, topOfStart)){
            sequence.setOpenAdjacentSpots(sequence.getOpenAdjacentSpots() + 1);
        }

        return sequence;
    }

    /*
     * Returns a sequence of connect-four game moves (a GameMoveSequence) containing consecutive
     * moves that increase down and to the right for playerId starting at (row,col).
     *
     * This helper assumes that the given i,j are at the top-left of a sequene (if there is a
     * sequence.)  In other words: this helper won't traverse up-and-left from i,j, only down-and-right.
     */
    public static GameMoveSequence getDownRightSequence(Game game, long playerId, int row, int col) throws Exception{
        if(game == null){ throw new NullPointerException("Cannot get down-right sequence for null game"); }

        long[][] board = game.getBoardMatrix();
        if(board == null){ throw new NullPointerException("Cannot get down-right sequence for null game board"); }
        if(row < 0 || col < 0 || row >= board.length || col >= board[row].length){ throw new IndexOutOfBoundsException("Row/Col combo " + row + ", " + col + " are out of bounds of the current game board"); }

        int startRow = row;
        int startCol = col;
        GameMoveSequence sequence = new GameMoveSequence();

        // advance down-and-right of the starting (row,col) and add game moves to the sequence
        // while they match the caller's given player ID
        while(row < board.length && col < board[row].length && board[row][col] == playerId){
            sequence.add(new GameMove(row,col));
            col++;
            row++;
        }

        // if there's no actual sequence here, return null to indicate it
        if(sequence.getLength() <= 0){ return null; }

        // if there's an open spot to the bottom-right of the 
        // current sequence, increment the open-spot counter
        if(row < board.length && col < board[row].length && !game.isOccupied(row,col)){ 
            sequence.setOpenAdjacentSpots(sequence.getOpenAdjacentSpots() + 1);
        }

        // likewise, if there's an open spot at the top-left of the 
        // current sequence, increment the open-spot counter
        int topOfStart = startCol - 1;
        int leftOfStart = startRow - 1;
        if(topOfStart >= 0 && leftOfStart >= 0 && !game.isOccupied(leftOfStart, topOfStart)){
            sequence.setOpenAdjacentSpots(sequence.getOpenAdjacentSpots() + 1);
        }

        return sequence;
    }

    /*
     * Returns a sequence of connect-four game moves (a GameMoveSequence) containing consecutive
     * moves that increase down and to the left for playerId starting at (row,col).
     *
     * This helper assumes that the given i,j are at the top-right of a sequene (if there is a
     * sequence.)  In other words: this helper won't traverse up-and-right from i,j, only down-and-left.
     */
    public static GameMoveSequence getDownLeftSequence(Game game, long playerId, int row, int col) throws Exception{
        if(game == null){ throw new NullPointerException("Cannot get down-left sequence for null game"); }

        long[][] board = game.getBoardMatrix();
        if(board == null){ throw new NullPointerException("Cannot get down-left sequence for null game board"); }
        if(row < 0 || col < 0 || row >= board.length || col >= board[row].length){ throw new IndexOutOfBoundsException("Row/Col combo " + row + ", " + col + " are out of bounds of the current game board"); }

        int startRow = row;
        int startCol = col;
        GameMoveSequence sequence = new GameMoveSequence();

        // advance down-and-left of the starting (row,col) and add game moves to the sequence
        // while they match the caller's given player ID
        while(row >= 0 && col < board[row].length && board[row][col] == playerId){
            sequence.add(new GameMove(row,col));
            col++;
            row--;
        }

        // if there's no actual sequence here, return null to indicate it
        if(sequence.getLength() <= 0){ return null; }

        // if there's an open spot to the bottom-left of the 
        // current sequence, increment the open-spot counter
        if(row >= 0 && col < board[row].length && !game.isOccupied(row,col)){ 
            sequence.setOpenAdjacentSpots(sequence.getOpenAdjacentSpots() + 1);
        }

        // likewise, if there's an open spot at the top-right of the 
        // current sequence, increment the open-spot counter
        int topOfStart = startCol - 1;
        int rightOfStart = startRow + 1;
        if(topOfStart >= 0 && rightOfStart < board.length && !game.isOccupied(rightOfStart, topOfStart)){
            sequence.setOpenAdjacentSpots(sequence.getOpenAdjacentSpots() + 1);
        }

        return sequence;
    }

    /*
     * Returns every sequence of pieces on the given game board for the given player, sorted
     * largest-to-smallest, or null if no sequences exist for the given player.
     *
     * For notes on the sort ordering, see class notes for GameMoveSequenceComparator in this package.
     */
    public static List<GameMoveSequence> getSequencesForPlayer(Game game, long playerId) throws Exception{
        if(game == null){ throw new NullPointerException("Cannot get game-move-sequence lists for null game"); }

        long[][] board = game.getBoardMatrix();
        if(board == null){ throw new NullPointerException("Cannot get game-move-sequence lists for null board"); }

        // logger.info("Calculating game move sequences for player " + playerId);
        Set<GameMoveSequence> resultingSequenceSet = new HashSet<GameMoveSequence>();

        // as moves are added to sequences, we need to be able to keep track of which moves
        // we've already seen, so we don't accidentally double-add them to the resulting set.
        // the following hashes will keep track of the moves, by relevant direction.
        Set<GameMove> horizontalMovesSeen = new HashSet<GameMove>();
        Set<GameMove> verticalMovesSeen = new HashSet<GameMove>();
        Set<GameMove> downRightMovesSeen = new HashSet<GameMove>();
        Set<GameMove> downLeftMovesSeen = new HashSet<GameMove>();

        // this loop has to go left-to-right FIRST,
        // then top-to-bottom.  so the i,j are reversed:
        for(int j = 0; j < board[0].length; j++){
            for(int i = 0; i < board.length; i++){
            
                if(board[i][j] != playerId){ continue; }

                GameMove thisMove = new GameMove(i,j);
                // logger.info("This move: " + i + ", " + j);

                // check the horizontal direction first - ignore this (i,j)
                // for the horizontal case if it has already been tallied.
                // otherwise, queue it up in the resulting set, as well as
                // the moves already-encountered
                if(!horizontalMovesSeen.contains(thisMove)){
                    // logger.info("hasn't been seen horizontally!");

                    GameMoveSequence newHorizontalSequence = getHorizontalSequence(game, playerId, i, j);
                    if(newHorizontalSequence != null){
                        // logger.info("Got a horiz seq of length " + newHorizontalSequence.getLength());
                        resultingSequenceSet.add(newHorizontalSequence);
                        horizontalMovesSeen.addAll(newHorizontalSequence.getMoveSequence());
                    }
                }

                // same thing for the vertical case
                if(!verticalMovesSeen.contains(thisMove)){
                    // logger.info("hasn't been see vertically!");
                    GameMoveSequence newVerticalSequence = getVerticalSequence(game, playerId, i, j);
                    if(newVerticalSequence != null){
                        // logger.info("got a vert seq of length " + newVerticalSequence.getLength());
                        resultingSequenceSet.add(newVerticalSequence);
                        verticalMovesSeen.addAll(newVerticalSequence.getMoveSequence());
                    }
                }

                // same for down-right
                if(!downRightMovesSeen.contains(thisMove)){
                    // logger.info("hasn't been see down-right");
                    GameMoveSequence newDownRightSequence = getDownRightSequence(game, playerId, i, j);
                    if(newDownRightSequence != null){
                        // logger.info("got a DR seq of length " + newDownRightSequence.getLength());
                        resultingSequenceSet.add(newDownRightSequence);
                        downRightMovesSeen.addAll(newDownRightSequence.getMoveSequence());
                    }
                }

                // same for down-left
                if(!downLeftMovesSeen.contains(thisMove)){
                    // logger.info("hasn't been see down-left!");
                    GameMoveSequence newDownLeftSequence = getDownLeftSequence(game, playerId, i, j);
                    if(newDownLeftSequence != null){
                        // logger.info("got a new DL seq of length " + newDownLeftSequence.getLength());
                        resultingSequenceSet.add(newDownLeftSequence);
                        downLeftMovesSeen.addAll(newDownLeftSequence.getMoveSequence());
                    }
                }

                // logger.info("After this round, size of set is now: " + resultingSequenceSet.size());
            }
        }

        if(resultingSequenceSet.isEmpty()){ return null; }

        List<GameMoveSequence> resultingList = new ArrayList<GameMoveSequence>();
        resultingList.addAll(resultingSequenceSet);
        Collections.sort(resultingList, new GameMoveSequenceComparator());

        return resultingList;
    }

    /*
     * Helper that copies and returns the given game.  The returned game has the given move
     * (for the given player) applied to it.  The original/given game is not modified by this
     * helper, only the resulting/copied game has the given move applied to it.
     *
     * The only property of the given game that is "deep copied" is the board matrix - 
     * all other properties are just copied by reference (so the copied/returned game is
     * just pointing to all the properties/references of the original.)
     *
     * HIBERNATE NOTICE: the resulting game will have the same id as the original, but will
     * obviously be detached from any hibernate session, so saving/updating the resulting
     * copied game may require a little extra work.
     */
    public static Game getGameCopyAfterApplyingMove(Game gameToCopy, GameMove moveToApply, Player playerToApply) throws Exception{
        if(gameToCopy == null) { throw new Exception("Cannot create game copy, or apply move, without a game object"); }
        if(moveToApply == null){ throw new Exception("Cannot create game copy, or aply move, without a move to apply"); }
        if(playerToApply == null){ throw new Exception("Cannot create game copy, or apply move, without a player"); }

        long[][] originalBoard = gameToCopy.getBoardMatrix();
        if(originalBoard == null){ throw new Exception("Cannot create game copy, ora pply new move, without a board"); }

        long[][] copiedBoard = new long[originalBoard.length][originalBoard[0].length];
        for(int i = 0; i < originalBoard.length; i++){
            for(int j = 0; j < originalBoard[i].length; j++){
                copiedBoard[i][j] = originalBoard[i][j];
            }
        }

        Game copiedGame = new Game();
        copiedGame.setId(gameToCopy.getId());
        copiedGame.setWinningPlayerId(gameToCopy.getWinningPlayerId());
        copiedGame.setOutcomeDescription(gameToCopy.getOutcomeDescription());
        copiedGame.setNumberInRowToWin(gameToCopy.getNumberInRowToWin());
        copiedGame.setPlayers(gameToCopy.getPlayers());
        copiedGame.setBoardMatrix(copiedBoard);

        copiedGame.occupySpot(playerToApply, moveToApply.getRow(), moveToApply.getCol());

        return copiedGame;
    }
}