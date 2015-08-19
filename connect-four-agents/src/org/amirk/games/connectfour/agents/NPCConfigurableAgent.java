
package org.amirk.games.connectfour.agents;

import org.amirk.games.connectfour.entities.*;
import java.util.List;
import java.util.SortedSet;
import java.lang.Integer;
 
/*
 * This implementation of ConnectFourGameAgent employs a configurable, depth-
 * limitable Minimax algorithm to determine which game move to make next for 
 * a given game and player.
 */
public class NPCConfigurableAgent implements ConnectFourGameAgent{
    protected int longestSequenceCoefficient;
    protected int winningSequenceCoefficient;
    protected int adjacentSpotCoefficient;

    protected int opponentsLongestSequenceCoefficient;
    protected int opponentsWinningSequenceCoefficient;
    protected int opponentsAdjacentSpotCoefficient;

    protected int depthOfLookAhead;
    
    public NPCConfigurableAgent(){
        this.longestSequenceCoefficient = 1;
        this.winningSequenceCoefficient = 1;
        this.adjacentSpotCoefficient = 1;

        this.opponentsLongestSequenceCoefficient = 1;
        this.opponentsWinningSequenceCoefficient = 1;
        this.opponentsAdjacentSpotCoefficient = 1;

        this.depthOfLookAhead = 1;
    }

    public NPCConfigurableAgent(int newLongestSequenceCoefficient, 
                                int newWinningSequenceCoefficient,
                                int newAdjacentSpotCoefficient,
                                int newOpponentsLongestSequenceCoefficient,
                                int newOpponentsWinningSequenceCoefficient,
                                int newOpponentsAdjacentSpotCoefficient,
                                int newDepthOfLookAhead){

        this.longestSequenceCoefficient = newLongestSequenceCoefficient;
        this.winningSequenceCoefficient = newWinningSequenceCoefficient;
        this.adjacentSpotCoefficient = newAdjacentSpotCoefficient;
        this.opponentsLongestSequenceCoefficient = newOpponentsLongestSequenceCoefficient;
        this.opponentsWinningSequenceCoefficient = newOpponentsWinningSequenceCoefficient;
        this.opponentsAdjacentSpotCoefficient = newOpponentsAdjacentSpotCoefficient;
        this.depthOfLookAhead = newDepthOfLookAhead;
    }
    
    public int getLongestSequenceCoefficient(){ return this.longestSequenceCoefficient; }
    public void setLongestSequenceCoefficient(int i){ this.longestSequenceCoefficient = i; }

    public int getWinningSequenceCoefficient(){ return this.winningSequenceCoefficient; }
    public void setWinningSequenceCoefficient(int i){ this.winningSequenceCoefficient = i; }

    public int getAdjacentSpotCoefficient(){ return this.adjacentSpotCoefficient; }
    public void setAdjacentSpotCoefficient(int i){ this.adjacentSpotCoefficient = i; }

    public int getOpponentsLongestSequenceCoefficient(){ return this.opponentsLongestSequenceCoefficient; }
    public void setOpponentsLongestSequenceCoefficient(int i){ this.opponentsLongestSequenceCoefficient = i; }

    public int getOpponentsWinningSequenceCoefficient(){ return this.opponentsWinningSequenceCoefficient; }
    public void setOpponentsWinningSequenceCoefficient(int i){ this.opponentsWinningSequenceCoefficient = i; }

    public int getOpponentsAdjacentSpotCoefficient(){ return this.opponentsAdjacentSpotCoefficient; }
    public void setOpponentsAdjacentSpotCoefficient(int i){ this.opponentsAdjacentSpotCoefficient = i; }

    public int getDepthOfLookAhead(){ return this.depthOfLookAhead; }
    public void setDepthOfLookAhead(int i){ this.depthOfLookAhead = i; }

    public GameMove getMoveFor(Game game, Player player) throws Exception{
        if(game == null){ throw new Exception("Cannot get move for null game"); }
        if(player == null){ throw new Exception("Cannot get move for null player"); }

        GameMove moveToReturn = null;
        int maxUtilityOfLegalMoves = Integer.MIN_VALUE;

        SortedSet<GameMove> legalMoves = game.getLegalMoves();

        // if there are no legal moves left, the game is probably over.
        // TODO - is it better to barf here, or just return null!?
        if(legalMoves == null || legalMoves.size() <= 0){ throw new Exception("Could not retrieve a list of legal moves - no move can be calculated"); }

        for(GameMove nextLegalMove : legalMoves){

            Game gameWithMoveApplied = AgentUtils.getGameCopyAfterApplyingMove(game, nextLegalMove, player);
            if(gameWithMoveApplied == null){ throw new Exception("Could not get game copy after applying move - next game move cannot be calculated"); }

            int utilityOfNextMove = this.minValue(gameWithMoveApplied, player, 0, this.depthOfLookAhead);
            if(utilityOfNextMove > maxUtilityOfLegalMoves){
                maxUtilityOfLegalMoves = utilityOfNextMove;
                moveToReturn = nextLegalMove;
            }
        }

        return moveToReturn;
    }
    
    
    public int maxValue(Game game, Player playerMakingMove, int currentDepth, int maxPlyDepth) throws Exception{
        if(game == null){ throw new Exception("Cannot get max-value without a game"); }
        if(playerMakingMove == null){ throw new Exception("Cannot get max-value without a player that's making a move"); }

        // each ply allows for both players to make a move, so the ply-depth
        // is half the current depth (this assumes that the given game has 2 players!)
        int currentPlyDepth = currentDepth / 2;

        // base-case: if the given game is 'over' (someone won, or it's a tie)
        //
        // OR 
        //
        // the recursive stack has reached it's max depth ...
        //
        // ... then return the utility of the given game:
        if(currentPlyDepth >= maxPlyDepth || game.isGameOver()){ return this.evaluateUtility(game, playerMakingMove); }

        // otherwise, continue to recurse down into the look-ahead, for all legal moves
        // of the given game.
        SortedSet<GameMove> legalMoves = game.getLegalMoves();

        // if there aren't any legal moves, that's an error at this point, because
        // the check for "game over" at the top of this function would have returned 'true'
        // if all legal moves had been exhausted (such a game would be a tie at worst.)
        if(legalMoves == null || legalMoves.size() <= 0){ throw new Exception("Encountered a game that isn't over, but has no remaining legal moves!?  No max-value can be calculated for this game!"); }

        int maxUtilityOfLegalMoves = Integer.MIN_VALUE;
        for(GameMove nextLegalMove : legalMoves){

            Game gameWithMoveApplied = AgentUtils.getGameCopyAfterApplyingMove(game, nextLegalMove, playerMakingMove);
            if(gameWithMoveApplied == null){ throw new Exception("Failed to retrieve game copy with move applied at recursive depth " + currentDepth + " out of " + maxPlyDepth); }

            // recurse down to the next legal move and get it's utility.
            // if it's higher than previously recorded, then store it.
            int utilityOfNextMove = this.minValue(gameWithMoveApplied, playerMakingMove, currentDepth + 1, maxPlyDepth);
            if(utilityOfNextMove > maxUtilityOfLegalMoves){ maxUtilityOfLegalMoves = utilityOfNextMove; }
        }

        return maxUtilityOfLegalMoves;
    }

    public int minValue(Game game, Player playerMakingMove, int currentDepth, int maxPlyDepth) throws Exception{
        if(game == null){ throw new Exception("Cannot get min-value without a game"); }
        if(playerMakingMove == null){ throw new Exception("Cannot get min-value without a player that's making a move"); }

        // each ply allows for both players to make a move, so the ply-depth
        // is half the current depth (this assumes that the given game has 2 players!)
        int currentPlyDepth = currentDepth / 2;

        // base-case: if the given game is 'over' (someone won, or it's a tie)
        //
        // OR 
        //
        // the recursive stack has reached it's max depth ...
        //
        // ... then return the utility of the given game:
        if(currentPlyDepth >= maxPlyDepth || game.isGameOver()){ return this.evaluateUtility(game, playerMakingMove); }

        // otherwise, continue to recurse down into the look-ahead, for all legal moves
        // of the given game.
        SortedSet<GameMove> legalMoves = game.getLegalMoves();

        // if there aren't any legal moves, that's an error at this point, because
        // the check for "game over" at the top of this function would have returned 'true'
        // if all legal moves had been exhausted (such a game would be a tie at worst.)
        if(legalMoves == null || legalMoves.size() <= 0){ throw new Exception("Encountered a game that isn't over, but has no remaining legal moves!?  No min-value can be calculated for this game!"); }
        
        // when calculating minValue, the next person to make a move in the game is the opponent
        // of the player making a move.  get that opponent!
        Player opponent = this.getOpponent(game, playerMakingMove);
        if(opponent == null){ throw new Exception("Failed to retrieve game opponent while getting min-value for game at depth " + currentDepth + " out of " + maxPlyDepth); }

        // the opponent of the player making a move is trying to minimize that player's utility!
        // so: start really high - as utility goes down, we'll store it.
        int minUtilityOfLegalMoves = Integer.MAX_VALUE;
        for(GameMove nextLegalMove : legalMoves){

            Game gameWithMoveApplied = AgentUtils.getGameCopyAfterApplyingMove(game, nextLegalMove, opponent);
            if(gameWithMoveApplied == null){ throw new Exception("Failed to retrieve game copy with move applied at recursive depth " + currentDepth + " out of " + maxPlyDepth); }

            // recurse down to the next legal move and get it's utility.
            // if it's higher than previously recorded, then store it.
            int utilityOfNextMove = this.maxValue(gameWithMoveApplied, playerMakingMove, currentDepth + 1, maxPlyDepth);
            if(utilityOfNextMove < minUtilityOfLegalMoves){ minUtilityOfLegalMoves = utilityOfNextMove; }
        }

        return minUtilityOfLegalMoves;
    }

    /*
     * Helper that returns the opponent of the given player in the given game, or null
     * if that opponent could not be determined for any reason.
     */
    public Player getOpponent(Game game, Player thisPlayer){
        if(game == null || thisPlayer == null){ return null; }

        List<Player> players = game.getPlayers();
        if(players == null || players.size() <= 0){ return null; }

        for(Player p : players){
            if(p.getId() != thisPlayer.getId()){ return p; }
        }

        return null;
    }

    /*
     * Evaluate and return the utility of the given game from the pont-of-view of the
     * given player.
     */
    public int evaluateUtility(Game game, Player player) throws Exception{
        if(game == null){ throw new Exception("Cannot evaluate utility of null game"); }
        if(player == null){ throw new Exception("Cannot evaluate utility of null player"); }

        long[][] board = game.getBoardMatrix();
        if(board == null){ throw new Exception("Cannot evaluate utility without a game board"); }

        int resultingUtility = 0;
        Player opponent = this.getOpponent(game,player);
        if(opponent == null){ throw new Exception("Could not determine the opponent of player " + player.getId() + " - no utility can be calculated for this game"); }

        // logger.info("evaluating utility for player " + player.getId() + " (who's opponent is " + opponent.getId() + ") ... ");

        // start wit the utility for the given player - this is the effective offensive
        // utility of the game for this player.
        List<GameMoveSequence> moveSequences = AgentUtils.getSequencesForPlayer(game, player.getId());
        if(moveSequences != null && moveSequences.size() > 0){
        
            // thanks to the sorting of the resulting list, the sequence at the head should be
            // the longest available for this player - grab it and immediately tally up the
            // utility for that sequence (because longer sequences in connect-four are closer to
            // a win than shorter ones!)
            GameMoveSequence longestSequence = moveSequences.get(0);
            resultingUtility = this.longestSequenceCoefficient * longestSequence.getLength();
            // logger.info("longest sequence was " + longestSequence.getLength() + " and utility so far is " + resultingUtility);

            // if the longest sequence is of winning length, the utility of this game should
            // skyrocket for the given player, because winning is (after all) the ultimate goal!
            if(longestSequence.getLength() >= game.getNumberInRowToWin()){
                resultingUtility += this.winningSequenceCoefficient;
                // logger.info("longest sequence was a winner!  utility is now " + resultingUtility);

            // otherwise, tally-up the utility of the rest of this player's sequences.
            }else{

                // every open, adjacent spot for each of the sequences for this player
                // represents an offensive opportunity for this player - those opportunities
                // increase the utility accordingly!
                for(GameMoveSequence nextSequence : moveSequences){
                    if(nextSequence.getOpenAdjacentSpots() > 0){
                        resultingUtility += (this.adjacentSpotCoefficient * nextSequence.getOpenAdjacentSpots());
                    }
                }
                // logger.info("longest sequence was not a winner, open adjacent spots tallied, and utility is now " + resultingUtility);
            }
        }

        // now tally-up the other player's contributions, which represents the defensive utility
        // of the given game for the given player.  in other words: the stronger the oppoonent's
        // offensive position, the less utility this game board has for the current player.
        List<GameMoveSequence> opponentsSequences = AgentUtils.getSequencesForPlayer(game, opponent.getId());
        if(opponentsSequences != null && opponentsSequences.size() > 0){

            // as the opponent's longest sequence extends, they approach a winning move, which is
            // bad for this player.  definitely knock some utility off for long sequences:
            GameMoveSequence opponentsLongestSequence = opponentsSequences.get(0);
            resultingUtility -= (this.opponentsLongestSequenceCoefficient * opponentsLongestSequence.getLength());

            // logger.info("opponents longest sequence has length " + opponentsLongestSequence.getLength() + " and resulting utility is now " + resultingUtility);

            // if the opponent just won w/ their longest sequence, that's very very bad
            // for this player.  dock a bunch of utility for it.
            if(opponentsLongestSequence.getLength() >= game.getNumberInRowToWin()){
                resultingUtility -= this.opponentsWinningSequenceCoefficient;
                // logger.info("opponent won!?  resulting utility is now " + resultingUtility);

            // otherwise, tally up and deduct the opponent's offensive potential, based on
            // the number of open adjacent spots they have available to play
            }else{

                // every open adjacent spot to the opponent's sequences is an offensive opportunity for 
                // the opponent, which is bad for this player.  dock some utility for each one
                for(GameMoveSequence nextOpponentSequence : opponentsSequences){
                    if(nextOpponentSequence.getOpenAdjacentSpots() > 0){
                        resultingUtility -= (this.opponentsAdjacentSpotCoefficient * nextOpponentSequence.getOpenAdjacentSpots());
                    }
                }
                // logger.info("opponent's longest sequence didn't win, open spots tallied, and resulting utility is now " + resultingUtility);
            }
        }

        // logger.info("Here's your final utility: " + resultingUtility);
        return resultingUtility;
    }
}
