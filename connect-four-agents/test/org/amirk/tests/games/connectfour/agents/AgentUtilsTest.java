/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.amirk.tests.games.connectfour.agents;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.*;
import org.amirk.games.connectfour.entities.*;
import org.amirk.games.connectfour.agents.*;
import java.util.*;

/**
 *
 * @author foo
 */
public class AgentUtilsTest extends TestParent {
    
    @Test
    public void getHorizontalSequenceBarfsOnBadInputs(){

        try{
            AgentUtils.getHorizontalSequence(null,1,0,0);
            fail("Expected getHorizontalSequence to fail for null game");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("null game"));
        }

        Game game = new Game();
        try{
            game.setBoardMatrix(null);
            AgentUtils.getHorizontalSequence(game,1,0,0);
            fail("Expected getHorizontalSequence to fail for null board");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("null game board"));
        }

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        try{
            AgentUtils.getHorizontalSequence(game,1,-1,0);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getHorizontalSequence(game,1,0,-1);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getHorizontalSequence(game,1,board.length,0);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getHorizontalSequence(game,1,0,board[0].length);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
    }
    
    @Test
    public void getVerticalSequenceBarfsOnBadInputs(){

        try{
            AgentUtils.getVerticalSequence(null,1,0,0);
            fail("Expected getVerticalSequence to fail for null game");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("null game"));
        }

        Game game = new Game();
        try{
            game.setBoardMatrix(null);
            AgentUtils.getVerticalSequence(game,1,0,0);
            fail("Expected getVerticalSequence to fail for null board");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("null game board"));
        }

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        try{
            AgentUtils.getVerticalSequence(game,1,-1,0);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getVerticalSequence(game,1,0,-1);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getVerticalSequence(game,1,board.length,0);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getVerticalSequence(game,1,0,board[0].length);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
    }
    
     @Test
    public void getDownRightSequenceBarfsOnBadInputs(){

        try{
            AgentUtils.getDownRightSequence(null,1,0,0);
            fail("Expected getDownRightSequence to fail for null game");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("null game"));
        }

        Game game = new Game();
        try{
            game.setBoardMatrix(null);
            AgentUtils.getDownRightSequence(game,1,0,0);
            fail("Expected getDownRightSequence to fail for null board");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("null game board"));
        }

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        try{
            AgentUtils.getDownRightSequence(game,1,-1,0);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getDownRightSequence(game,1,0,-1);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getDownRightSequence(game,1,board.length,0);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getDownRightSequence(game,1,0,board[0].length);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
    }
    
    @Test
    public void getDownLeftSequenceBarfsOnBadInputs(){

        try{
            AgentUtils.getDownLeftSequence(null,1,0,0);
            fail("Expected getDownLeftSequence to fail for null game");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("null game"));
        }

        Game game = new Game();
        try{
            game.setBoardMatrix(null);
            AgentUtils.getDownLeftSequence(game,1,0,0);
            fail("Expected getDownLeftSequence to fail for null board");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("null game board"));
        }

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        try{
            AgentUtils.getDownLeftSequence(game,1,-1,0);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getDownLeftSequence(game,1,0,-1);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getDownLeftSequence(game,1,board.length,0);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
        try{
            AgentUtils.getDownLeftSequence(game,1,0,board[0].length);
            fail("Expected out-of-bounds exception for bad indices");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("are out of bounds"));
        }
    }
    
    @Test
    public void getHorizontalSequenceReturnsNullForNoSequence() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        assertNull(AgentUtils.getHorizontalSequence(game,1,0,0));
    }

    @Test
    public void getVerticalSequenceReturnsNullForNoSequence() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        assertNull(AgentUtils.getVerticalSequence(game,1,0,0));
    }

    @Test
    public void getDownRightSequenceReturnsNullForNoSequence() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        assertNull(AgentUtils.getDownRightSequence(game,1,0,0));
    }

    @Test
    public void getDownLeftSequenceReturnsNullForNoSequence() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        assertNull(AgentUtils.getDownLeftSequence(game,1,0,0));
    }

    @Test
    public void getHorizontalSequenceReturnsOneLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;

        setMatrixValues(board,0);
        board[1][1] = playerId;
        seq = AgentUtils.getHorizontalSequence(game,playerId,1,1);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(1,1)));
        assertEquals(seq.getOpenAdjacentSpots(), 2);

        setMatrixValues(board,0);
        board[0][0] = playerId;
        seq = AgentUtils.getHorizontalSequence(game,playerId,0,0);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(0,0)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        board[2][0] = playerId;
        seq = AgentUtils.getHorizontalSequence(game,playerId,2,0);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(2,0)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        board[0][2] = playerId;
        seq = AgentUtils.getHorizontalSequence(game,playerId,0,2);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(0,2)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        board[2][2] = playerId;
        seq = AgentUtils.getHorizontalSequence(game,playerId,2,2);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(2,2)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);
    }
    
    @Test
    public void getVerticalSequenceReturnsOneLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;

        setMatrixValues(board,0);
        board[1][1] = playerId;
        seq = AgentUtils.getVerticalSequence(game,playerId,1,1);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(1,1)));
        assertEquals(seq.getOpenAdjacentSpots(), 2);

        setMatrixValues(board,0);
        board[0][0] = playerId;
        seq = AgentUtils.getVerticalSequence(game,playerId,0,0);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(0,0)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        board[2][0] = playerId;
        seq = AgentUtils.getVerticalSequence(game,playerId,2,0);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(2,0)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        board[0][2] = playerId;
        seq = AgentUtils.getVerticalSequence(game,playerId,0,2);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(0,2)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        board[2][2] = playerId;
        seq = AgentUtils.getVerticalSequence(game,playerId,2,2);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(2,2)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);
    }
    
    @Test
    public void getDownRightSequenceReturnsOneLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;

        setMatrixValues(board,0);
        board[1][1] = playerId;
        seq = AgentUtils.getDownRightSequence(game,playerId,1,1);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(1,1)));
        assertEquals(seq.getOpenAdjacentSpots(), 2);

        setMatrixValues(board,0);
        board[0][0] = playerId;
        seq = AgentUtils.getDownRightSequence(game,playerId,0,0);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(0,0)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        board[2][0] = playerId;
        seq = AgentUtils.getDownRightSequence(game,playerId,2,0);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(2,0)));
        assertEquals(seq.getOpenAdjacentSpots(), 0);

        setMatrixValues(board,0);
        board[0][2] = playerId;
        seq = AgentUtils.getDownRightSequence(game,playerId,0,2);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(0,2)));
        assertEquals(seq.getOpenAdjacentSpots(), 0);

        setMatrixValues(board,0);
        board[2][2] = playerId;
        seq = AgentUtils.getDownRightSequence(game,playerId,2,2);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(2,2)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);
    }
    
    @Test
    public void getDownLeftSequenceReturnsOneLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;

        setMatrixValues(board,0);
        board[1][1] = playerId;
        seq = AgentUtils.getDownLeftSequence(game,playerId,1,1);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(1,1)));
        assertEquals(seq.getOpenAdjacentSpots(), 2);

        setMatrixValues(board,0);
        board[0][0] = playerId;
        seq = AgentUtils.getDownLeftSequence(game,playerId,0,0);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(0,0)));
        assertEquals(seq.getOpenAdjacentSpots(), 0);

        setMatrixValues(board,0);
        board[2][0] = playerId;
        seq = AgentUtils.getDownLeftSequence(game,playerId,2,0);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(2,0)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        board[0][2] = playerId;
        seq = AgentUtils.getDownLeftSequence(game,playerId,0,2);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(0,2)));
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        board[2][2] = playerId;
        seq = AgentUtils.getDownLeftSequence(game,playerId,2,2);
        assertNotNull(seq);
        assertEquals(seq.getLength(), 1);
        assertTrue(seq.contains(new GameMove(2,2)));
        assertEquals(seq.getOpenAdjacentSpots(), 0);
    }
    
    @Test
    public void getHorizontalSequenceReturnsTwoLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;
        GameMove[] moves = null;

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(0,1), new GameMove(1,1) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getHorizontalSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(0,0), new GameMove(1,0) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getHorizontalSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(1,0), new GameMove(2,0) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getHorizontalSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(0,2), new GameMove(1,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getHorizontalSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(1,2), new GameMove(2,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getHorizontalSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);
    }
 
    @Test
    public void getVerticalSequenceReturnsTwoLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;
        GameMove[] moves = null;

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(1,1), new GameMove(1,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getVerticalSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(0,0), new GameMove(0,1) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getVerticalSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(2,0), new GameMove(2,1) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getVerticalSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(0,1), new GameMove(0,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getVerticalSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(2,1), new GameMove(2,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getVerticalSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);
    }
    
    @Test
    public void getDownRightSequenceReturnsTwoLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;
        GameMove[] moves = null;

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(0,0), new GameMove(1,1) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getDownRightSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(1,0), new GameMove(2,1) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getDownRightSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(0,1), new GameMove(1,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getDownRightSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(1,1), new GameMove(2,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getDownRightSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);
    }
    
    @Test
    public void getDownLeftSequenceReturnsTwoLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;
        GameMove[] moves = null;

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(1,0), new GameMove(0,1) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getDownLeftSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(2,0), new GameMove(1,1) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getDownLeftSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(1,1), new GameMove(0,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getDownLeftSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 1);

        setMatrixValues(board,0);
        moves = new GameMove[]{ new GameMove(2,1), new GameMove(1,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getDownLeftSequence(game,playerId, moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);
    }
    
    @Test
    public void getHorizontalSequenceReturnsThreeLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;
        GameMove[] moves = null;

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(0,0), new GameMove(1,0), new GameMove(2,0) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getHorizontalSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(0,1), new GameMove(1,1), new GameMove(2,1) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getHorizontalSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(0,2), new GameMove(1,2), new GameMove(2,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getHorizontalSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);
    }
    
    @Test
    public void getVerticalSequenceReturnsThreeLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;
        GameMove[] moves = null;

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(0,0), new GameMove(0,1), new GameMove(0,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getVerticalSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(1,0), new GameMove(1,1), new GameMove(1,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getVerticalSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(2,0), new GameMove(2,1), new GameMove(2,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getVerticalSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);
    }

    @Test
    public void getDownRightSequenceReturnsThreeLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;
        GameMove[] moves = null;

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(0,0), new GameMove(1,1), new GameMove(2,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getDownRightSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);
    }

    @Test
    public void getDownLeftSequenceReturnsThreeLengthSequences() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        GameMoveSequence seq = null;
        GameMove[] moves = null;

        setMatrixValues(board,0);
        moves = new GameMove[] { new GameMove(2,0), new GameMove(1,1), new GameMove(0,2) };
        for(GameMove m : moves){ board[m.getRow()][m.getCol()] = playerId; }
        seq = AgentUtils.getDownLeftSequence(game,playerId,moves[0].getRow(), moves[0].getCol());
        assertNotNull(seq);
        assertEquals(seq.getLength(), moves.length);
        for(GameMove m : moves){ assertTrue(seq.contains(m)); }
        assertEquals(seq.getOpenAdjacentSpots(), 0);
    }
    
    @Test
    public void getSequencesForPlayerBarfsForBadInputs(){
        Game game = null;
        long[][] board = null;
        try{
            AgentUtils.getSequencesForPlayer(game, 0);
            fail("Expected getSequencesForPlayer to fail for null game");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("null game"));
        }

        game = new Game();
        game.setBoardMatrix(board);
        try{
            AgentUtils.getSequencesForPlayer(game,0);
            fail("Expected getSequencesForPlayer to fail for null board");
        }catch(Exception e){
            assertThat(e.getMessage(),containsString("null board"));
        }
    }

    // test sequences of length 1 in the four corners of the board
    @Test
    public void getSequencesForPlayerReturnsSingleSequences_1() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;

        setMatrixValues(board,0);
        board[0][0] = playerId;
        board[2][0] = playerId;
        board[0][2] = playerId;
        board[2][2] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 0,0, there should be:
        // a horizontal sequence, length 1, w/ 1 open adjacent spot
        // - equivalent to the vertical at the same position
        // - equivalent to the down-right at the same position
        GameMoveSequence seq = new GameMoveSequence();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 0,0, there should be:
        // a down-left sequence, length 1, w/ 0 open adjacent spots
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 2,0, there should be:
        // a horizontal sequence, length 1, w/ 1 open adjacent spot
        // - equivalent to the vertical at the same position
        // - equivalent to the down-left at the same position
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 2,0, there should be:
        // a down-left sequence, length 1, w/ 0 open adjacent spots
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 0,2 there should be:
        // a horizontal sequence, length 1, w/ 1 open adjacent spots
        // - equivalent to the vertical at the same position
        // - equivalent to the down-left at the same position
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,2))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 0,2 there should be:
        // a down-right sequence, length 1, w/ 0 open adjacent spots
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,2))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 2,2 there should be:
        // a horizontal sequence, length 1, w/ 1 open adjacent spots
        // - equivalent to the vertical at the same position
        // - equivalent to the down-right at the same position
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,2))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 2,2 there should be:
        // a down-left sequence, length 1, w/ 0 open adjacent spots
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,2))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));
    }

    // test sequence of length 1 smack in the middle of the board!
    @Test
    public void getSequencesForPlayerReturnsSingleSequences_2() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;

        setMatrixValues(board,0);
        board[1][1] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 1,1 there should be:
        // a horizontal sequence, length 1, w/ 2 open adjacent spots
        // - an equivalent for the vertical at the same position
        // - same with a down-right sequence
        // - same with a down-left sequence
        //
        // so basically: there'll only be the 1 sequence in the resulting list!
        assertEquals(1, sequences.size());

        GameMoveSequence seq = new GameMoveSequence();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));
    }

    // test sequences of length 2, horizontal, in the top-left corner of the board
    @Test
    public void getSequencesForPlayerShouldReturnTwoLengthSequences_horiz_1() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;

        setMatrixValues(board,0);
        board[0][0] = playerId;
        board[1][0] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // ok - there should definitely be a horizontal sequence of lenght 2 - let's see if it's in there!
        GameMoveSequence seq = new GameMoveSequence();
        seq.add(new GameMove(0,0))
           .add(new GameMove(1,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // there should also be 2 vertical sequences, for each of the two moves
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq)); // this is also a down-right w/ 1 open spot

        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq)); // this is also a down-right w/ 1 open spot

        // also, a down-left sequence at 0,0 with no adjacent spots available
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));
    }

    // test sequences of length 2, horizontal, in the middle of the board
    @Test
    public void getSequencesForPlayerShouldReturnTwoLengthSequences_horiz_2() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;

        setMatrixValues(board,0);
        board[1][1] = playerId;
        board[2][1] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // there should be a horizontal sequence, length 2, w/ 1 open adjacent spot
        GameMoveSequence seq = new GameMoveSequence();
        seq.add(new GameMove(1,1))
           .add(new GameMove(2,1))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // there should be 2 vertical sequences, lenght 1, w/ 2 open spots
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq)); // this'll be a down-right and a down-left as well!

        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // there'll be a down-right and a down-left at (2,1), length 1, w/ 1 open adjacent spot
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,1))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));
    }
    
    // test sequences of length 2, vertical, in the top-left of the board
    @Test
    public void getSequencesForPlayerShouldReturnTwoLengthSequences_vert_1() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;

        setMatrixValues(board,0);
        board[0][0] = playerId;
        board[0][1] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // there should be a vertical sequence of length 2 w/ 1 open spot:
        GameMoveSequence seq = new GameMoveSequence();
        seq.add(new GameMove(0,0))
           .add(new GameMove(0,1))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // there should be 2 horizontal sequences of length 1, w/ 1 open spot each
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq)); // this is a down-right sequence too!

        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,1))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq)); // this is a down-right sequence, and a down-left sequence too!

        // there should be a down-left sequence at 0,0 with no adjacent spots
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));
    }

    // test sequences of length 2, vertical, in the middle of the board
    @Test
    public void getSequencesForPlayerShouldReturnTwoLengthSequences_vert_2() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;

        setMatrixValues(board,0);
        board[1][1] = playerId;
        board[1][2] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // there should be a vertical sequence at (1,1), length 2, 1 open adjacent spot
        GameMoveSequence seq = new GameMoveSequence();
        seq.add(new GameMove(1,1))
           .add(new GameMove(1,2))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // there should be a horizontal sequence at (1,1), length 1, 2 open adjcaent spots.
        // this'll also be a down-right sequence, as well as a down-left
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // there should be a horizontal sequence at (1,2), length 1, w/ 2 open adjacent spots.
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,2))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // there should be a down-right sequence at (1,2), lenght 1, w/ 1 open adjacent spot.
        // there'll also be a down-left sequence there.
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,2))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));
    }
    
    // test sequences of length 2, down-right, in the top-left of the board
    @Test
    public void getSequencesForPlayerShouldReturnTwoLengthSequences_downright_1() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;

        setMatrixValues(board,0);
        board[0][0] = playerId;
        board[1][1] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 0,0 there should be
        // a down-right sequence, length 2, 1 open adjacent spot
        GameMoveSequence seq = new GameMoveSequence();
        seq.add(new GameMove(0,0))
           .add(new GameMove(1,1))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 0,0, there should be
        // - a horizontal sequence, lenght 1, w/ 1 open adjacent spot
        // - same for the vertical case
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 0,0, there should be
        // - a down-left sequence, length 1, w/ 0 open adjacent spots
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 1,1 there should be
        // - a horizontal sequence, length 1, w/ 2 open adjacent spots
        // - same for the vertical case
        // - same for the down-right case
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // as a sanity test: ensure there isn't a down-right sequence at 1,1.
        // it should have been captured at 0,0, and should not have been double-reported
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(1);
        assertFalse(sequences.contains(seq));
    }

    // test sequences of length 2, down-right, in the middle of the board
    @Test
    public void getSequencesForPlayerShouldReturnTwoLengthSequences_downright_2() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;

        setMatrixValues(board,0);
        board[1][1] = playerId;
        board[2][2] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 1,1 there should be
        // - a down-right sequence, lenght 2, w/ 1 open adjacent spot
        GameMoveSequence seq = new GameMoveSequence();
        seq.add(new GameMove(1,1))
           .add(new GameMove(2,2))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 1,1 there should be
        // - a horizontal sequence, length 1, w/ 2 open adjacent spots
        // - same for the vertical
        // - same for down-left
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // at 2,2 there should be
        // - a horizontal sequence, length 1, w/ 1 open adjacent spot
        // - same for the vertical
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,2))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 2,2 there should be
        // - a down-left sequence, length 1, w/ 0 open adjacent spots
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,2))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));
    }

    // test sequences of length 2, down-left, in the top-right of the board
    @Test
    public void getSequencesForPlayerShouldReturnTwoLengthSequences_downleft_1() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;

        setMatrixValues(board,0);
        board[2][0] = playerId;
        board[1][1] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 2,0 there should be
        // - a down-left sequence, length 2, w/ 1 open adjacent spot
        GameMoveSequence seq = new GameMoveSequence();
        seq.add(new GameMove(2,0))
           .add(new GameMove(1,1))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 2,0 there should be
        // - a horizontal sequence, length 1, w/ 1 open adjacent spot
        // - same for the vertical
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 2,0 there should be
        // - a down-right sequence, length 1, w/ 0 open adjcaent spots
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 1,1 there should be
        // - a horizontal sequence, length 1, w/ 2 open adjacent spots
        // - same for the vertical
        // - same for the down-right
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // ensure there isn't a down-left at 1,1 w/ 1 open adjacent spot.
        // the first down-left sequence of length 2 should have already tallied this
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(1);
        assertFalse(sequences.contains(seq));
    }

    // test sequences of length 2, down-left, in the middle of the board
    @Test
    public void getSequencesForPlayerShouldReturnTwoLengthSequences_downleft_2() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;

        setMatrixValues(board,0);
        board[1][1] = playerId;
        board[0][2] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 1,1 there should be
        // - a down-left sequence, length 2, w/ 1 open adjacent spot
        GameMoveSequence seq = new GameMoveSequence();
        seq.add(new GameMove(1,1))
           .add(new GameMove(0,2))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 1,1 there should be
        // - a horizontal sequence, length 1, w/ 2 open adjacent spots
        // - same for the vertical
        // - same for the down-right
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // at 0,2 there should be
        // - a horizontal sequence, length 1, w/ 1 open adjacent spot
        // - same for the vertical
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,2))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 0,2 there should be
        // - a down-right sequence, length 1, w/ 0 open adjacent spots
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,2))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));
    }

    // test sequences of length 3, horizontal, at the top of the board
    @Test
    public void getSequencesForPlayerShouldReturnThreeLengthSequences_horizontal_1() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;
        GameMoveSequence seq = new GameMoveSequence();

        board[0][0] = playerId;
        board[1][0] = playerId;
        board[2][0] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 0,0 there should be
        // - a horizontal sequence, length 3, w/ 0 open adjacent
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .add(new GameMove(1,0))
           .add(new GameMove(2,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 0,0 there should be
        // - a vertical sequence, length 1, w/ 1 open adjacent
        // - a down-right that's the same
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 0,0 there should be
        // - a down-left sequence, length 1, w/ 0 open adjacent
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 1,0 there should be
        // - a vertical sequence, length 1, w/ 1 open adjacent
        // - a down-right sequence, same
        // - a down-left sequence, same
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 2,0 there should be
        // - a vertical sequence, length 1, w/ 1 open adjacent
        // - a down-left, same
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 2,0 there should be
        // - a down-left sequence, length 1, w/ 0 open adjacent
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));
    }
    
    // test sequences of length 3, horizontal, at the middle of the board
    @Test
    public void getSequencesForPlayerShouldReturnThreeLengthSequences_horizontal_2() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;
        GameMoveSequence seq = new GameMoveSequence();

        board[0][1] = playerId;
        board[1][1] = playerId;
        board[2][1] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 0,1 there should be
        // - a horizontal sequence, length 3, w/ 0 adjacent
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,1))
           .add(new GameMove(1,1))
           .add(new GameMove(2,1))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 0,1 there should be
        // - a vertical, length 1, w/ 2 adjacent
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // at 0,1 there should be
        // - a down-right, length 1, w/ 1 adjacent
        // - a down-left, lenght 1, w/ 1 adjacent
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,1))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 1,1 there should be
        // - a vertical, length 1, w/ 2 adjacent
        // - same for down-right
        // - same for down-left
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // at 2,1 there should be
        // - a vertical, length 1, w/ 2 adjacent
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // at 2,1 there should be
        // - a down-right, length 1, w/ 1 adjacent
        // - same for down-left
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,1))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));
    }

    // test sequences of length 3, vertical, at the left of the board
    @Test
    public void getSequencesForPlayerShouldReturnThreeLengthSequences_vertical_1() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;
        GameMoveSequence seq = new GameMoveSequence();

        board[0][0] = playerId;
        board[0][1] = playerId;
        board[0][2] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 0,0 there should be
        // - a vertical, length 3, 0 open
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .add(new GameMove(0,1))
           .add(new GameMove(0,2))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 0,0 there should be
        // -a horizontal, length 1, 1 open
        // - same for down-right
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 0,0 there should be
        // - a down-left, length 1, 0 open
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 0,1 there should be
        // - a horizontal, length 1, 1 open
        // - a down-right, same
        // - a down-left, same
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,1))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 0,2 there should be
        // - a horizontal, length 1, 1 open
        // - a down-left, same
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,2))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 0,2 there should be
        // - a down-right, length 1, 0 open
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,2))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));
    }
    
    // test sequences of length 3, vertical, at the middle of the board
    @Test
    public void getSequencesForPlayerShouldReturnThreeLengthSequences_vertical_2() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;
        GameMoveSequence seq = new GameMoveSequence();

        board[1][0] = playerId;
        board[1][1] = playerId;
        board[1][2] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 1,0 there should be
        // - a vertical, length 3, 0 open
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,0))
           .add(new GameMove(1,1))
           .add(new GameMove(1,2))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 1,0 there should be
        // - a horizontal, length 1, 2 open
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,0))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // at 1,0 there should be
        // - a down-right, length 1, 1 open
        // - same for down-left
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 1,1 there should be
        // - a horizontal, length 1, 2 open
        // - same for down-right
        // - same for down-left
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // at 1,2 there should be
        // - a horizontal, length 1, 2 open
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,2))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // at 1,2 there shoudl be
        // - a down-right, length 1, 1 open
        // - a down-left, same
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,2))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));
    }

    // test sequences of length 3, down-right
    @Test
    public void getSequencesForPlayerShouldReturnThreeLengthSequences_downRight() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;
        GameMoveSequence seq = new GameMoveSequence();

        board[0][0] = playerId;
        board[1][1] = playerId;
        board[2][2] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 0,0 there should be
        // - a down-right, length 3, 0 open
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .add(new GameMove(1,1))
           .add(new GameMove(2,2))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 0,0 there should be
        // - a horizontal, length 1, 1 open
        // - a vertical, same
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 0,0 there should be
        // - a down-left, length 1, 0 open
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 1,1 there should be
        // - a horizontal, length 1, 2 open
        // - a vertical, same
        // - a down-left, same
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // 2,2 is covered already! nothing left to check
    }

    // test sequences of length 3, down-left
    @Test
    public void getSequencesForPlayerShouldReturnThreeLengthSequences_downLeft() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);
        int playerId = 1;
        List<GameMoveSequence> sequences = null;
        GameMoveSequence seq = new GameMoveSequence();

        board[2][0] = playerId;
        board[1][1] = playerId;
        board[0][2] = playerId;
        sequences = AgentUtils.getSequencesForPlayer(game,playerId);
        assertNotNull(sequences);

        // at 2,0 there should be
        // - a down-left, length 3, 0 open
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,0))
           .add(new GameMove(1,1))
           .add(new GameMove(0,2))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 2,0 there should be
        // - a horizontal, length 1, 1 open
        // - a vertical, same
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,0))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 2,0 there should be
        // - a down-left, length 1, 0 open
        seq.getMoveSequence().clear();
        seq.add(new GameMove(2,0))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));

        // at 1,1 there should be
        // - a horizontal, length 1, 2 open
        // - a vertical, same
        // - a down-right, same
        seq.getMoveSequence().clear();
        seq.add(new GameMove(1,1))
           .setOpenAdjacentSpots(2);
        assertTrue(sequences.contains(seq));

        // at 0,2 there should be
        // - a horizontal, length 1, 1 open
        // - a vertical, same
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,2))
           .setOpenAdjacentSpots(1);
        assertTrue(sequences.contains(seq));

        // at 0,2 there should be
        // - a down-right, lenght 1, 0 open
        seq.getMoveSequence().clear();
        seq.add(new GameMove(0,2))
           .setOpenAdjacentSpots(0);
        assertTrue(sequences.contains(seq));
    }

    @Test
    public void getGameCopyAfterApplyingMoveBarfsForBadInputs(){
        try{
            AgentUtils.getGameCopyAfterApplyingMove(null, new GameMove(), new Player());
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without a game"));
        }

        Game game = new Game();
        game.setBoardMatrix(new long[3][3]);
        try{
            AgentUtils.getGameCopyAfterApplyingMove(game, null, new Player());
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without a move"));
        }

        try{
            AgentUtils.getGameCopyAfterApplyingMove(game, new GameMove(), null);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without a player"));
        }

        game.setBoardMatrix(null);
        try{
            AgentUtils.getGameCopyAfterApplyingMove(game, new GameMove(), new Player());
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without a board"));
        }
    }

    @Test
    public void getGameCopyAfterApplyingMoveShouldApplyMoveToCopiedGame() throws Exception{
        long[][] board = new long[3][3];
        Player one = new Player();
        one.setId(1);
        Game game = new Game();
        game.setBoardMatrix(board);

        GameMove move = new GameMove(1,1);

        Game copied = AgentUtils.getGameCopyAfterApplyingMove(game, move, one);
        assertTrue(copied.isOccupied(move.getRow(), move.getCol()));
        assertFalse(game.isOccupied(move.getRow(), move.getCol()));
    }

    @Test
    public void getGameCopyAfterApplyingMoveShouldReturnEqualGameExceptBoard() throws Exception{
        int id = 435;

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        List<Player> players = new ArrayList<Player>();
        players.add(one);
        players.add(two);

        String outcomeDescription = "hi";

        short numberInRowToWinGame = 123;
        long winningPlayerId = one.getId();

        long[][] board = new long[3][3];
        board[1][1] = one.getId();

        Game originalGame = new Game();
        originalGame.setId(id);
        originalGame.setWinningPlayerId(winningPlayerId);
        originalGame.setPlayers(players);
        originalGame.setOutcomeDescription(outcomeDescription);
        originalGame.setNumberInRowToWin(numberInRowToWinGame);
        originalGame.setBoardMatrix(board);

        GameMove move = new GameMove(0,0);
        Game copiedGame = AgentUtils.getGameCopyAfterApplyingMove(originalGame, move, two);

        assertEquals(originalGame.getId(), copiedGame.getId());
        assertEquals(originalGame.getPlayers(), copiedGame.getPlayers());
        assertEquals(originalGame.getOutcomeDescription(), copiedGame.getOutcomeDescription());
        assertEquals(originalGame.getWinningPlayerId(), copiedGame.getWinningPlayerId());
        assertEquals(originalGame.getNumberInRowToWin(), copiedGame.getNumberInRowToWin());
        assertThat(originalGame.getBoardMatrix(), not(equalTo(copiedGame.getBoardMatrix())));
    }
}
