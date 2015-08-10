
package org.amirk.games.connectfour.entities.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.*;
import org.amirk.games.connectfour.entities.*;
import java.util.*;
    
public class GameTest{

    protected void setMatrixValues(long[][] matrix, long value){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                matrix[i][j] = value;
            }
        }
    }

    @Test
    public void shouldGetNumRows(){
        int rows = 5;
        int cols = 12;

        long[][] matrix = new long[rows][cols];
        Game game = new Game();
        game.setBoardMatrix(matrix);

        assertEquals(rows, game.getNumRows());
    }

    @Test
    public void shouldGetNumCols(){
        int rows = 5;
        int cols = 12;

        long[][] matrix = new long[rows][cols];
        Game game = new Game();
        game.setBoardMatrix(matrix);

        assertEquals(cols, game.getNumCols());
    }

    @Test
    public void getNumRowsShouldBarfOnNullMatrix(){
        Game game = new Game();
        game.setBoardMatrix(null);

        try{
            game.getNumRows();
            fail("Expected getNumRows to fail for a null matrix but it didn't");
        }catch(NullPointerException e){
            assertThat(e.getMessage(), containsString("row count"));
        }
    }

    @Test
    public void getNumColsShouldBarfOnNullMatrix(){
        Game game = new Game();
        game.setBoardMatrix(null);

        try{
            game.getNumCols();
            fail("Expected getNumCols to fail for a null matrix but it didn't");
        }catch(NullPointerException e){
            assertThat(e.getMessage(), containsString("col count"));
        }
    }

    @Test
    public void isOccupiedShouldBarfOnNullMatrix(){

        Game game = new Game();
        game.setBoardMatrix(null);

        try{
            game.isOccupied(1,1);
            fail("Expected isOccupied to fail for a null matrix but it didn't");
        }catch(NullPointerException e){
            assertThat(e.getMessage(), containsString("null board matrix"));
        }
    }

    @Test
    public void isOccupiedShouldThrowIndexExceptionOnBadRows(){

        int rows = 5;
        int cols = 10;
        long[][] matrix = new long[rows][cols];
        setMatrixValues(matrix,1); // all 1 means all spots are occupied

        Game game = new Game();
        game.setBoardMatrix(matrix);

        try{
            game.isOccupied(-1,0);
            fail("Expected isOccupied to fail on bad row");
        }catch(IndexOutOfBoundsException e){
            assertThat(e.getMessage(), containsString("out of bounds"));
        }

        try{
            game.isOccupied(rows,0);
            fail("Expected isOccupied to fail on bad row");
        }catch(IndexOutOfBoundsException e){
            assertThat(e.getMessage(), containsString("out of bounds"));
        }

        try{
            game.isOccupied(rows+1,0);
            fail("Expected isOccupied to fail on bad row");
        }catch(IndexOutOfBoundsException e){
            assertThat(e.getMessage(), containsString("out of bounds"));
        }
    }

    @Test
    public void isOccupiedShouldThrowIndexExceptionOnBadCols(){
        
        int rows = 5;
        int cols = 10;
        long[][] matrix = new long[rows][cols];
        setMatrixValues(matrix,1); // all 1 means all spots are occupied

        Game game = new Game();
        game.setBoardMatrix(matrix);

        try{
            game.isOccupied(0,-1);
            fail("Expected isOccupied to fail on bad col");
        }catch(IndexOutOfBoundsException e){
            assertThat(e.getMessage(), containsString("out of bounds"));
        }

        try{
            game.isOccupied(0,cols);
            fail("Expected isOccupied to fail on bad col");
        }catch(IndexOutOfBoundsException e){
            assertThat(e.getMessage(), containsString("out of bounds"));
        }

        try{
            game.isOccupied(0,cols+1);
            fail("Expected isOccupied to fail on bad col");
        }catch(IndexOutOfBoundsException e){
            assertThat(e.getMessage(), containsString("out of bounds"));
        }
    }

    @Test
    public void isOccupiedReturnsTrueForOccupiedSpace(){
        int rows = 5;
        int cols = 10;
        long[][] matrix = new long[rows][cols];
        setMatrixValues(matrix,1); // anything greater than 0 means spot is occupied

        Game game = new Game();
        game.setBoardMatrix(matrix);

        assertTrue(game.isOccupied(0,0));
        assertTrue(game.isOccupied(rows-1,0));
        assertTrue(game.isOccupied(rows-2,0));

        assertTrue(game.isOccupied(0,1));
        assertTrue(game.isOccupied(rows-1,1));
        assertTrue(game.isOccupied(rows-2,1));
    }

    @Test 
    public void isOccupiedReturnsFalseForUnoccupiedSpace(){
        int rows = 5;
        int cols = 10;
        long[][] matrix = new long[rows][cols];

        Game game = new Game();
        game.setBoardMatrix(matrix);

        assertFalse(game.isOccupied(0,0));
        assertFalse(game.isOccupied(0,cols-1));
        assertFalse(game.isOccupied(0,cols-2));

        assertFalse(game.isOccupied(1,0));
        assertFalse(game.isOccupied(1,cols-1));
        assertFalse(game.isOccupied(1,cols-2));
    }

    @Test
    public void setBoardMatrixShouldSetBoardMatrixJson() throws Exception{
        Game game = new Game();
        long[][] matrix = new long[4][5];

        assertNull("expected matrix json to be null at first", game.getBoardMatrixJson());

        game.setBoardMatrix(matrix);
        String boardMatrixJson = game.getBoardMatrixJson();
        assertNotNull("Expected board matrix json to be non-null after setting board matrix", boardMatrixJson);
    }

    @Test
    public void settingBoardMatrixShouldSetBoardMatrixJson(){
        long[][] matrix = new long[2][3];
        Game game = new Game();

        assertNull("Expected board matrix json to be null", game.getBoardMatrixJson());
        game.setBoardMatrix(matrix);
        assertNotNull("Expected board matrix json to be set", game.getBoardMatrixJson());
    }

    @Test
    public void settingBoardMatrixShouldJSONSerializeToBoardMatrixJson(){
        long[][] matrix = new long[2][3];
        Game game = new Game();
        game.setBoardMatrix(matrix);

        try{
            ObjectMapper mapper = new ObjectMapper();
            String matrixSerialized = mapper.writeValueAsString(matrix);
            String boardMatrixJson = game.getBoardMatrixJson();
            assertEquals("Expected board matrix json to match serialized matrix json", matrixSerialized, boardMatrixJson);
        }catch(Exception e){
            fail("Testing board matrix json failed with this error: " + e.getMessage());
        }
    }

    @Test
    public void settingBoardMatrixJsonShoulSetBoardMatrix(){
        long[][] matrix = new long[2][3];
        String matrixSerialized = null;

        try{
            ObjectMapper mapper = new ObjectMapper();
            matrixSerialized = mapper.writeValueAsString(matrix);
        }catch(Exception e){
            fail("serializing to/from json gave this error: " + e.getMessage());
            return;
        }

        Game game = new Game();

        assertNull(game.getBoardMatrix());
        game.setBoardMatrixJson(matrixSerialized);
        assertNotNull(game.getBoardMatrix());
        assertEquals("Expected board matrix to be equivalent after json serialization", matrix, game.getBoardMatrix());
    }

    @Test
    public void getHorizontalWinnerShouldThrowForVariousReasons(){
        Game game = new Game();
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        // null board
        try{
            game.setBoardMatrix(null);
            game.getHorizontalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without a board"));
            game.setBoardMatrix(board);
        }

        // not enough players 1
        try{
            game.setPlayers(null);
            game.getHorizontalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // not enough players 2
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getHorizontalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // not enough players 3
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getPlayers().add(one);
            game.getHorizontalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // invalid number-in-row-to-win
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getPlayers().add(one);
            game.getPlayers().add(two);
            game.getHorizontalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("non-positive number-in-row-to-win"));
        }
    }

    @Test
    public void getVerticalWinnerShouldThrowForVariousReasons(){
        Game game = new Game();
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        // null board
        try{
            game.setBoardMatrix(null);
            game.getVerticalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without a board"));
            game.setBoardMatrix(board);
        }

        // not enough players 1
        try{
            game.setPlayers(null);
            game.getVerticalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // not enough players 2
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getVerticalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // not enough players 3
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getPlayers().add(one);
            game.getVerticalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // invalid number-in-row-to-win
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getPlayers().add(one);
            game.getPlayers().add(two);
            game.getHorizontalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("non-positive number-in-row-to-win"));
        }
    }

    @Test
    public void getDiagonalRightWinnerShouldThrowForVariousReasons(){
        Game game = new Game();
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        // null board
        try{
            game.setBoardMatrix(null);
            game.getDiagonalRightWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without a board"));
            game.setBoardMatrix(board);
        }

        // not enough players 1
        try{
            game.setPlayers(null);
            game.getDiagonalRightWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // not enough players 2
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getDiagonalRightWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // not enough players 3
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getPlayers().add(one);
            game.getDiagonalRightWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // invalid number-in-row-to-win
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getPlayers().add(one);
            game.getPlayers().add(two);
            game.getHorizontalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("non-positive number-in-row-to-win"));
        }
    }

    @Test
    public void getDiagonalLeftWinnerShouldThrowForVariousReasons(){
        Game game = new Game();
        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        // null board
        try{
            game.setBoardMatrix(null);
            game.getDiagonalLeftWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without a board"));
            game.setBoardMatrix(board);
        }

        // not enough players 1
        try{
            game.setPlayers(null);
            game.getDiagonalLeftWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // not enough players 2
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getDiagonalLeftWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // not enough players 3
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getPlayers().add(one);
            game.getDiagonalLeftWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("exactly 2 players"));
        }

        // invalid number-in-row-to-win
        try{
            game.setPlayers(new ArrayList<Player>());
            game.getPlayers().add(one);
            game.getPlayers().add(two);
            game.getHorizontalWinnerStartingAt(0,0);
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("non-positive number-in-row-to-win"));
        }
    }

    @Test
    public void getHorizontalWinnerFailsForEdgeOfBoardCases() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(3);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        setMatrixValues(board, one.getId());
        game.setBoardMatrix(board);

        Player winner = game.getHorizontalWinnerStartingAt(1,0);
        assertNull(winner);

        winner = game.getHorizontalWinnerStartingAt(1,1);
        assertNull(winner);

        winner = game.getHorizontalWinnerStartingAt(1,2);
        assertNull(winner);

        winner = game.getHorizontalWinnerStartingAt(2,0);
        assertNull(winner);

        winner = game.getHorizontalWinnerStartingAt(2,1);
        assertNull(winner);

        winner = game.getHorizontalWinnerStartingAt(2,2);
        assertNull(winner);
    }

    @Test
    public void getVerticalWinnerFailsForEdgeOfBoardCases() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(3);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        setMatrixValues(board, one.getId());
        game.setBoardMatrix(board);

        Player winner = game.getVerticalWinnerStartingAt(0,1);
        assertNull(winner);

        winner = game.getVerticalWinnerStartingAt(1,1);
        assertNull(winner);

        winner = game.getVerticalWinnerStartingAt(2,1);
        assertNull(winner);

        winner = game.getVerticalWinnerStartingAt(0,2);
        assertNull(winner);

        winner = game.getVerticalWinnerStartingAt(1,2);
        assertNull(winner);

        winner = game.getVerticalWinnerStartingAt(2,2);
        assertNull(winner);
    }

    @Test
    public void getDiagonalRightWinnerFailsForEdgeOfBoardCases() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(3);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        setMatrixValues(board, one.getId());
        game.setBoardMatrix(board);

        Player winner = game.getDiagonalRightWinnerStartingAt(1,1);
        assertNull(winner);

        winner = game.getDiagonalRightWinnerStartingAt(2,2);
        assertNull(winner);

        winner = game.getDiagonalRightWinnerStartingAt(0,2);
        assertNull(winner);

        winner = game.getDiagonalRightWinnerStartingAt(2,0);
        assertNull(winner);
    }

    @Test
    public void getDiagonalLeftWinnerFailsForEdgeOfBoardCases() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(3);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        setMatrixValues(board, one.getId());
        game.setBoardMatrix(board);

        Player winner = game.getDiagonalLeftWinnerStartingAt(0,0);
        assertNull(winner);

        winner = game.getDiagonalLeftWinnerStartingAt(0,1);
        assertNull(winner);

        winner = game.getDiagonalLeftWinnerStartingAt(0,1);
        assertNull(winner);

        winner = game.getDiagonalLeftWinnerStartingAt(1,1);
        assertNull(winner);

        winner = game.getDiagonalLeftWinnerStartingAt(1,2);
        assertNull(winner);

        winner = game.getDiagonalLeftWinnerStartingAt(0,2);
        assertNull(winner);

        winner = game.getDiagonalLeftWinnerStartingAt(1,2);
        assertNull(winner);

        winner = game.getDiagonalLeftWinnerStartingAt(2,2);
        assertNull(winner);
    }

    @Test
    public void getHorizontalWinnerFailsIfNoWinner() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(2);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                assertNull(game.getHorizontalWinnerStartingAt(i,j));
            }
        }
        
        board[1][0] = two.getId();
        board[0][1] = two.getId();
        board[2][1] = two.getId();
        board[1][2] = two.getId();

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                assertNull(game.getHorizontalWinnerStartingAt(i,j));
            }
        }
    }

    @Test
    public void getVerticalWinnerFailsIfNoWinner() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(2);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                assertNull(game.getVerticalWinnerStartingAt(i,j));
            }
        }
        
        board[1][0] = two.getId();
        board[0][1] = two.getId();
        board[2][1] = two.getId();
        board[1][2] = two.getId();

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                assertNull(game.getVerticalWinnerStartingAt(i,j));
            }
        }
    }

    @Test
    public void getDiagonalRightWinnerFailsIfNoWinner() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(2);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                assertNull(game.getDiagonalRightWinnerStartingAt(i,j));
            }
        }
        
        board[0][0] = two.getId();
        board[2][1] = two.getId();
        board[1][2] = two.getId();
        board[2][2] = two.getId();

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                assertNull("Expected no winner at " + i + ", " + j, game.getDiagonalRightWinnerStartingAt(i,j));
            }
        }
    }

    @Test
    public void getDiagonalLeftWinnerFailsIfNoWinner() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(2);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                assertNull(game.getDiagonalLeftWinnerStartingAt(i,j));
            }
        }
        
        board[0][0] = two.getId();
        board[2][1] = two.getId();
        board[2][0] = two.getId();
        board[0][1] = two.getId();
        board[2][2] = two.getId();
        board[0][2] = two.getId();

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                assertNull("Expected no winner at " + i + ", " + j, game.getDiagonalLeftWinnerStartingAt(i,j));
            }
        }
    }

    @Test
    public void getHorizontalWinnerShouldPassForXInARow() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(2);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        board[0][0] = one.getId();
        board[1][0] = one.getId();
        assertEquals(one, game.getHorizontalWinnerStartingAt(0,0));

        setMatrixValues(board,0);
        board[1][0] = one.getId();
        board[2][0] = one.getId();
        assertEquals(one, game.getHorizontalWinnerStartingAt(1,0));

        setMatrixValues(board,0);
        board[0][1] = one.getId();
        board[1][1] = one.getId();
        assertEquals(one, game.getHorizontalWinnerStartingAt(0,1));

        setMatrixValues(board,0);
        board[1][1] = one.getId();
        board[2][1] = one.getId();
        assertEquals(one, game.getHorizontalWinnerStartingAt(1,1));

        setMatrixValues(board,0);
        board[0][2] = one.getId();
        board[1][2] = one.getId();
        assertEquals(one, game.getHorizontalWinnerStartingAt(0,2));

        setMatrixValues(board,0);
        board[1][2] = one.getId();
        board[2][2] = one.getId();
        assertEquals(one, game.getHorizontalWinnerStartingAt(1,2));  
    }

    @Test
    public void getVerticalWinnerShouldPassForXInARow() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(2);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        setMatrixValues(board,0);
        board[0][0] = one.getId();
        board[0][1] = one.getId();
        assertEquals(one, game.getVerticalWinnerStartingAt(0,0));

        setMatrixValues(board,0);
        board[1][0] = one.getId();
        board[1][1] = one.getId();
        assertEquals(one, game.getVerticalWinnerStartingAt(1,0));

        setMatrixValues(board,0);
        board[2][0] = one.getId();
        board[2][1] = one.getId();
        assertEquals(one, game.getVerticalWinnerStartingAt(2,0));

        setMatrixValues(board,0);
        board[0][1] = one.getId();
        board[0][2] = one.getId();
        assertEquals(one, game.getVerticalWinnerStartingAt(0,1));

        setMatrixValues(board,0);
        board[1][1] = one.getId();
        board[1][2] = one.getId();
        assertEquals(one, game.getVerticalWinnerStartingAt(1,1));

        setMatrixValues(board,0);
        board[2][1] = one.getId();
        board[2][2] = one.getId();
        assertEquals(one, game.getVerticalWinnerStartingAt(2,1));
    }

    @Test
    public void getDiagonalRightWinnerShouldPassForXInARow() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(2);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        setMatrixValues(board,0);
        board[0][0] = one.getId();
        board[1][1] = one.getId();
        assertEquals(one, game.getDiagonalRightWinnerStartingAt(0,0));

        setMatrixValues(board,0);
        board[1][0] = one.getId();
        board[2][1] = one.getId();
        assertEquals(one, game.getDiagonalRightWinnerStartingAt(1,0));

        setMatrixValues(board,0);
        board[0][1] = one.getId();
        board[1][2] = one.getId();
        assertEquals(one, game.getDiagonalRightWinnerStartingAt(0,1));

        setMatrixValues(board,0);
        board[1][1] = one.getId();
        board[2][2] = one.getId();
        assertEquals(one, game.getDiagonalRightWinnerStartingAt(1,1));
    }

    @Test
    public void getDiagonalLeftWinnerShouldPassForXInARow() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(2);

        Player one = new Player();
        one.setId(1);
        Player two = new Player();
        two.setId(2);
        game.setPlayers(new ArrayList<Player>());
        game.getPlayers().add(one);
        game.getPlayers().add(two);

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        setMatrixValues(board,0);
        board[1][0] = one.getId();
        board[0][1] = one.getId();
        assertEquals(one, game.getDiagonalLeftWinnerStartingAt(1,0));

        setMatrixValues(board,0);
        board[2][0] = one.getId();
        board[1][1] = one.getId();
        assertEquals(one, game.getDiagonalLeftWinnerStartingAt(2,0));

        setMatrixValues(board,0);
        board[1][1] = one.getId();
        board[0][2] = one.getId();
        assertEquals(one, game.getDiagonalLeftWinnerStartingAt(1,1));

        setMatrixValues(board,0);
        board[2][1] = one.getId();
        board[1][2] = one.getId();
        assertEquals(one, game.getDiagonalLeftWinnerStartingAt(2,1));
    }

    @Test
    public void findWinningPlayerThrowsForVariousReasons(){
        Game game = new Game();

        try{
            game.setPlayers(new ArrayList<Player>());
            game.getPlayers().add(new Player());
            game.getPlayers().add(new Player());
            game.setBoardMatrix(null);
            game.findWinningPlayerIfPresent();
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without a board"));
        }

        try{
            game.setBoardMatrix(new long[2][2]);
            game.setPlayers(null);
            game.findWinningPlayerIfPresent();
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without exactly 2 players"));
        }

        try{
            game.setPlayers(new ArrayList<Player>());
            game.setBoardMatrix(new long[2][2]);
            game.findWinningPlayerIfPresent();
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without exactly 2 players"));
        }

        try{
            game.setPlayers(new ArrayList<Player>());
            game.getPlayers().add(new Player());
            game.setBoardMatrix(new long[2][2]);
            game.findWinningPlayerIfPresent();
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without exactly 2 players"));
        }
    }

    @Test
    public void findWinningPlayerShouldPassIfBoardHasWinner() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(2);

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        game.setPlayers(new ArrayList<Player>());
        Player one = new Player();
        one.setId(1);
        game.getPlayers().add(one);
        Player two = new Player();
        two.setId(2);
        game.getPlayers().add(two);

        // all winning 2-in-a-row configs originating in the first row
        setMatrixValues(board,0);
        board[0][0] = two.getId();
        board[1][0] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[1][0] = two.getId();
        board[2][0] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[0][0] = two.getId();
        board[1][1] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[1][0] = two.getId();
        board[2][1] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[1][0] = two.getId();
        board[0][1] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[2][0] = two.getId();
        board[1][1] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[0][0] = two.getId();
        board[0][1] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[1][0] = two.getId();
        board[1][1] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[2][0] = two.getId();
        board[2][1] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        // all winning 2-in-a-row configs originating in the second row
        setMatrixValues(board,0);
        board[0][1] = two.getId();
        board[1][1] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[1][1] = two.getId();
        board[2][1] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[0][1] = two.getId();
        board[0][2] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[1][1] = two.getId();
        board[1][2] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[2][1] = two.getId();
        board[2][2] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[0][1] = two.getId();
        board[1][2] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[1][1] = two.getId();
        board[2][2] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[1][1] = two.getId();
        board[0][2] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[2][1] = two.getId();
        board[1][2] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        // all winning 2-in-a-row configs originating in the third row
        setMatrixValues(board,0);
        board[0][2] = two.getId();
        board[1][2] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);

        setMatrixValues(board,0);
        board[1][2] = two.getId();
        board[2][2] = two.getId();
        assertEquals(game.findWinningPlayerIfPresent(), two);
    }

    @Test
    public void findWinningPlayerShouldFailIfNoWinner() throws Exception{
        Game game = new Game();
        game.setNumberInRowToWin(2);

        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        game.setPlayers(new ArrayList<Player>());
        Player one = new Player();
        one.setId(1);
        game.getPlayers().add(one);
        Player two = new Player();
        two.setId(2);
        game.getPlayers().add(two);

        assertNull(game.findWinningPlayerIfPresent());

        setMatrixValues(board,0);
        board[0][0] = one.getId();
        board[2][0] = one.getId();
        board[0][2] = one.getId();
        board[2][2] = one.getId();
        assertNull(game.findWinningPlayerIfPresent());

        setMatrixValues(board,0);
        board[1][1] = one.getId();
        board[0][0] = two.getId();
        assertNull(game.findWinningPlayerIfPresent());

        setMatrixValues(board,0);
        board[0][0] = one.getId();
        board[1][0] = two.getId();
        board[2][0] = one.getId();
        assertNull(game.findWinningPlayerIfPresent());
    }

    @Test
    public void boardIsFullShouldBeTrueWhenBoardIsFull() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        setMatrixValues(board,1);
        game.setBoardMatrix(board);
        assertEquals(true, game.boardIsFull());
    }

    @Test
    public void boardIsFullShouldBeFalseWhenBoardIsNotFull() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        setMatrixValues(board,1);
        board[0][0] = 0;
        game.setBoardMatrix(board);
        assertEquals(false, game.boardIsFull());
    }

    @Test
    public void boardIsFullShouldBarfIfBoardIsNull(){
        Game game = new Game();
        game.setBoardMatrix(null);
        try{
            game.boardIsFull();
            fail("expected boardIsFull to barf but it didn't");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without a board"));
        }
    }

    @Test
    public void outcomeDeterminedShouldBeTrueWhenOutcomeDetermined() {
        Game game = new Game();
        game.setOutcomeDescription("hi");
        assertEquals(game.outcomeAlreadyDetermined(),true);
    }

    @Test
    public void outcomeDeterminedShouldBeFalseWhenNoOutcomeDetermined(){
        Game game = new Game();

        game.setOutcomeDescription(null);
        assertEquals(false, game.outcomeAlreadyDetermined());
    }

    @Test
    public void getLegalMovesShouldBarfForNullBoard(){
        Game game = new Game();
        game.setBoardMatrix(null);
        try{
            game.getLegalMoves();
            fail("Expected getLegalMoves to barf w/o a board");
        }catch(Exception e){
            assertThat(e.getMessage(), containsString("without a board"));
        }
    }

    @Test
    public void getLegalMovesShouldProvideSortedLegalMoves() throws Exception{
        Game game = new Game();
        long[][] board = new long[3][3];
        game.setBoardMatrix(board);

        SortedSet<GameMove> moves = game.getLegalMoves();
        assertEquals(board.length, moves.size());
        assertTrue(moves.contains(new GameMove(0,2)));
        assertTrue(moves.contains(new GameMove(1,2)));
        assertTrue(moves.contains(new GameMove(2,2)));

        moves.clear();
        board[0][2] = 1;
        board[0][1] = 1;
        board[0][0] = 1;
        moves = game.getLegalMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new GameMove(1,2)));
        assertTrue(moves.contains(new GameMove(2,2)));

        moves.clear();
        board[1][2] = 1;
        moves = game.getLegalMoves();
        assertEquals(2, moves.size());
        assertTrue(moves.contains(new GameMove(1,1)));
        assertTrue(moves.contains(new GameMove(2,2)));

        moves.clear();
        setMatrixValues(board,1);
        moves = game.getLegalMoves();
        assertEquals(0, moves.size());
    }
}
