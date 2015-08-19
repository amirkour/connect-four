
package org.amirk.tests.games.connectfour.agents;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import org.junit.*;
import org.amirk.games.connectfour.entities.*;
import org.amirk.games.connectfour.agents.*;
import java.lang.*;
import java.util.*;

public class NPCConfigurableAgentTest extends TestParent{

    @Test
    public void getOpponentShouldReturnOpponent(){
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        List<Player> players = new ArrayList<Player>();
        players.add(one);
        players.add(two);
        
        Game game = new Game();
        game.setPlayers(players);

        NPCConfigurableAgent agent = new NPCConfigurableAgent();

        assertEquals(one, agent.getOpponent(game,two));
        assertEquals(two, agent.getOpponent(game,one));
    }

    @Test
    public void getOpponentShouldReturnNullForBadInputs(){
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        List<Player> players = new ArrayList<Player>();
        players.add(one);
        players.add(two);
        
        Game game = new Game();
        game.setPlayers(players);

        NPCConfigurableAgent agent = new NPCConfigurableAgent();

        assertNull(agent.getOpponent(null,two));
        assertNull(agent.getOpponent(game,null));

        game.setPlayers(null);
        assertNull(agent.getOpponent(game,one));
    }
    
    // in the absence of all other utlity values, the longest sequence coefficient
    // should be the only contributor to a game's utility
    @Test
    public void evaluateLongestSequenceCoefficient() throws Exception{
        int longestSequenceCoefficient = 10;
        int winningSequenceCoefficient = 0;
        int adjacentSpotCoefficient = 0;

        int opponentsLongestSequenceCoefficient = 0;
        int opponentsWinningSequenceCoefficient = 0;
        int opponentsAdjacentSpotCoefficient = 0;

        int depthOfLookAhead = 0;
        NPCConfigurableAgent agent = new NPCConfigurableAgent(longestSequenceCoefficient,
                                                              winningSequenceCoefficient,
                                                              adjacentSpotCoefficient,
                                                              opponentsLongestSequenceCoefficient,
                                                              opponentsWinningSequenceCoefficient,
                                                              opponentsAdjacentSpotCoefficient,
                                                              depthOfLookAhead);

        long[][] board = new long[3][3];
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        List<Player> players = new ArrayList<Player>();
        players.add(one);
        players.add(two);

        Game game = new Game();
        game.setBoardMatrix(board);
        game.setPlayers(players);
        game.setNumberInRowToWin((short)(short)3);

        board[1][2] = one.getId(); // throwing this in just to make sure the longest sequence is
                                   // the only one that matters in the utility calculation

        board[0][0] = one.getId();
        board[1][0] = one.getId(); // a sequence of length 2 - the longest in the board for player one!
        assertEquals(longestSequenceCoefficient * 2, agent.evaluateUtility(game, one));
    }

    // in the absence of all other utility values, the winning-sequence-coefficient
    // should be the only contributor to a game's utility
    @Test
    public void evaluateWinningSequenceCoefficient() throws Exception{
        int longestSequenceCoefficient = 0;
        int winningSequenceCoefficient = 10;
        int adjacentSpotCoefficient = 0;

        int opponentsLongestSequenceCoefficient = 0;
        int opponentsWinningSequenceCoefficient = 0;
        int opponentsAdjacentSpotCoefficient = 0;
        int depthOfLookAhead = 0;
        NPCConfigurableAgent agent = new NPCConfigurableAgent(longestSequenceCoefficient,
                                                              winningSequenceCoefficient,
                                                              adjacentSpotCoefficient,
                                                              opponentsLongestSequenceCoefficient,
                                                              opponentsWinningSequenceCoefficient,
                                                              opponentsAdjacentSpotCoefficient,
                                                              depthOfLookAhead);

        long[][] board = new long[3][3];
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        List<Player> players = new ArrayList<Player>();
        players.add(one);
        players.add(two);

        Game game = new Game();
        game.setBoardMatrix(board);
        game.setPlayers(players);
        game.setNumberInRowToWin((short)3);

        board[0][0] = one.getId();
        board[1][0] = one.getId();
        board[2][0] = one.getId();
        assertEquals(winningSequenceCoefficient, agent.evaluateUtility(game, one));
    }

    // in the absence of all other utility values, the adjacent-spot-coefficient
    // should be the only contributor to a game's utility
    @Test
    public void evaluateAdjacentSpotCoefficient() throws Exception{
        int longestSequenceCoefficient = 0;
        int winningSequenceCoefficient = 0;
        int adjacentSpotCoefficient = 10;

        int opponentsLongestSequenceCoefficient = 0;
        int opponentsWinningSequenceCoefficient = 0;
        int opponentsAdjacentSpotCoefficient = 0;
        int depthOfLookAhead = 0;
        NPCConfigurableAgent agent = new NPCConfigurableAgent(longestSequenceCoefficient,
                                                              winningSequenceCoefficient,
                                                              adjacentSpotCoefficient,
                                                              opponentsLongestSequenceCoefficient,
                                                              opponentsWinningSequenceCoefficient,
                                                              opponentsAdjacentSpotCoefficient,
                                                              depthOfLookAhead);

        long[][] board = new long[3][3];
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        List<Player> players = new ArrayList<Player>();
        players.add(one);
        players.add(two);

        Game game = new Game();
        game.setBoardMatrix(board);
        game.setPlayers(players);
        game.setNumberInRowToWin((short)3);

        board[1][1] = one.getId();

        // let's find out how many adjacent spots there are for player one:
        List<GameMoveSequence> sequences = AgentUtils.getSequencesForPlayer(game, one.getId());

        // now tally up all open adjacent spots - that should determine the utility for this game
        int openSpots = 0;
        for(GameMoveSequence gms : sequences){ openSpots += gms.getOpenAdjacentSpots(); }

        assertEquals(adjacentSpotCoefficient * openSpots, agent.evaluateUtility(game, one));
    }

    // in the absence of all other utility values, the opponent's longest sequence coefficient
    // should be the only contributor to a game's utility
    @Test
    public void evaluateOpponentsLongestSequenceCoefficient() throws Exception{
        int longestSequenceCoefficient = 0;
        int winningSequenceCoefficient = 0;
        int adjacentSpotCoefficient = 0;

        int opponentsLongestSequenceCoefficient = 10;
        int opponentsWinningSequenceCoefficient = 0;
        int opponentsAdjacentSpotCoefficient = 0;
        int depthOfLookAhead = 0;
        NPCConfigurableAgent agent = new NPCConfigurableAgent(longestSequenceCoefficient,
                                                              winningSequenceCoefficient,
                                                              adjacentSpotCoefficient,
                                                              opponentsLongestSequenceCoefficient,
                                                              opponentsWinningSequenceCoefficient,
                                                              opponentsAdjacentSpotCoefficient,
                                                              depthOfLookAhead);

        long[][] board = new long[3][3];
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        List<Player> players = new ArrayList<Player>();
        players.add(one);
        players.add(two);

        Game game = new Game();
        game.setBoardMatrix(board);
        game.setPlayers(players);
        game.setNumberInRowToWin((short)3);

        board[1][0] = two.getId();
        board[1][1] = two.getId(); // opponent has a sequence w/ length 2

        assertEquals(opponentsLongestSequenceCoefficient * 2 * -1, agent.evaluateUtility(game, one));
    }

    // in the absence of all other utility values, the opponent's winning-sequence-coefficient
    // should be the only contributor to a game's utility
    @Test
    public void evaluateOpponentsWinningSequenceCoefficient() throws Exception{
        int longestSequenceCoefficient = 0;
        int winningSequenceCoefficient = 0;
        int adjacentSpotCoefficient = 0;

        int opponentsLongestSequenceCoefficient = 0;
        int opponentsWinningSequenceCoefficient = 10;
        int opponentsAdjacentSpotCoefficient = 0;
        int depthOfLookAhead = 0;
        NPCConfigurableAgent agent = new NPCConfigurableAgent(longestSequenceCoefficient,
                                                              winningSequenceCoefficient,
                                                              adjacentSpotCoefficient,
                                                              opponentsLongestSequenceCoefficient,
                                                              opponentsWinningSequenceCoefficient,
                                                              opponentsAdjacentSpotCoefficient,
                                                              depthOfLookAhead);

        long[][] board = new long[3][3];
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        List<Player> players = new ArrayList<Player>();
        players.add(one);
        players.add(two);

        Game game = new Game();
        game.setBoardMatrix(board);
        game.setPlayers(players);
        game.setNumberInRowToWin((short)3);

        board[0][1] = two.getId();
        board[1][1] = two.getId();
        board[2][1] = two.getId();
        assertEquals(opponentsWinningSequenceCoefficient * -1, agent.evaluateUtility(game,one));
    }

    // in the absence of all other utility values, the opponent's adjacent-spot-coefficient
    // should be the only contributor to a game's utility
    @Test
    public void evaluateOpponentsAdjacentSpotCoefficient() throws Exception{
        int longestSequenceCoefficient = 0;
        int winningSequenceCoefficient = 0;
        int adjacentSpotCoefficient = 0;

        int opponentsLongestSequenceCoefficient = 0;
        int opponentsWinningSequenceCoefficient = 0;
        int opponentsAdjacentSpotCoefficient = 10;
        int depthOfLookAhead = 0;
        NPCConfigurableAgent agent = new NPCConfigurableAgent(longestSequenceCoefficient,
                                                              winningSequenceCoefficient,
                                                              adjacentSpotCoefficient,
                                                              opponentsLongestSequenceCoefficient,
                                                              opponentsWinningSequenceCoefficient,
                                                              opponentsAdjacentSpotCoefficient,
                                                              depthOfLookAhead);

        long[][] board = new long[3][3];
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        List<Player> players = new ArrayList<Player>();
        players.add(one);
        players.add(two);

        Game game = new Game();
        game.setBoardMatrix(board);
        game.setPlayers(players);
        game.setNumberInRowToWin((short)3);

        board[1][1] = two.getId();
        List<GameMoveSequence> sequences = AgentUtils.getSequencesForPlayer(game,two.getId());
        int openSpots = 0;
        for(GameMoveSequence gms : sequences){ openSpots += gms.getOpenAdjacentSpots(); }

        assertEquals(opponentsAdjacentSpotCoefficient * openSpots * -1, agent.evaluateUtility(game,one));
    }

    @Test
    public void getNextMoveDoesStuff() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin((short)4);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        List<Player> players = new ArrayList<Player>();
        players.add(one);
        players.add(two);
        game.setPlayers(players);

        long[][] board = new long[5][5];
        game.setBoardMatrix(board);
        game.occupySpot(one, 2, 4);

        int longestSequenceCoefficient = 1;
        int winningSequenceCoefficient = 1;
        int adjacentSpotCoefficient = 1;

        int opponentsLongestSequenceCoefficient = 1;
        int opponentsWinningSequenceCoefficient = 1;
        int opponentsAdjacentSpotCoefficient = 1;
        int depthOfLookAhead = 2;
        NPCConfigurableAgent agent = new NPCConfigurableAgent(longestSequenceCoefficient,
                                                              winningSequenceCoefficient,
                                                              adjacentSpotCoefficient,
                                                              opponentsLongestSequenceCoefficient,
                                                              opponentsWinningSequenceCoefficient,
                                                              opponentsAdjacentSpotCoefficient,
                                                              depthOfLookAhead);

        GameMove nextMove = agent.getMoveFor(game, two);
    }
}