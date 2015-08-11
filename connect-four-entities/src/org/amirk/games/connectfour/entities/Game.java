package org.amirk.games.connectfour.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.Serializable;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

// TODO - this object has to use field-level access in hibernate.
// if it uses property-level access, hibernate will try and persist
// all the helper functions.
// the solution is:
// a. switch back to property-level and put all the helpers
//    in a separate object (like a GameState or something)
// b. change all the other POJOs to field-level access
@Entity
@Table(name="games")
public class Game implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected long id;
    
    @Column(name="winning_player_id")
    protected Long winningPlayerId;
    
    @Column(name="outcome")
    protected String outcomeDescription;
    
    // in the database this is a tinyint, which is smaller than a short, but 
    // java doesn't go any smaller, so we'll just settle for a short
    @Column(name="number_in_row_to_win")
    @NotNull
    protected short numberInRowToWin;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="game_players",
            joinColumns = @JoinColumn( name="game_id"),
            inverseJoinColumns = @JoinColumn( name="player_id")
    )
    @OrderColumn(name="list_index", nullable = false)
    protected List<Player> players;
    
    // this 2d array of ints is the game board, where each row/col tuple
    // is either 0 for an unoccupied spot on the board, or the id of one
    // of the players of this game (for the player that occupies that
    // row/col in the board.)
    //
    // this matrix is serialized to json and inserted/fetched from the
    // database - that's how the board is persisted and restored.
    // consequently: clients shouldn't interact with the json directly.
    // just interact with this property instead, when you need access
    // to the game board.
    @Transient
    protected long[][] boardMatrix;
    
    // see notes for boardMatrix - this string is just the json representation
    // of the game board, for persisting/restoring to/from database.
    //
    // note that even though this field needs to be persisted, it's marked Transient
    // here because the property's access level is being overriden/used, so that when
    // hibernate tries to persist to/from db, we can insert some logic in the property.
    @Transient
    protected String boardMatrixJson;
    
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    
    public Long getWinningPlayerId(){ return this.winningPlayerId; }
    public void setWinningPlayerId(Long i){ this.winningPlayerId = i; }
    
    public String getOutcomeDescription(){ return this.outcomeDescription; }
    public void setOutcomeDescription(String s){ this.outcomeDescription = s; }
   
    public short getNumberInRowToWin(){ return this.numberInRowToWin; }
    public void setNumberInRowToWin(short i){ this.numberInRowToWin = i; }
    
    // special case for this property - hibernate needs to use the property, not
    // the field, for this particular field, so that we can maintain some state.
    @Column(name="board_matrix_json")
    @NotNull
    @Access(AccessType.PROPERTY)
    public String getBoardMatrixJson(){ return this.boardMatrixJson; }
    public void setBoardMatrixJson(String s){
        this.boardMatrixJson = s;

        try{
            ObjectMapper mapper = new ObjectMapper();
            this.boardMatrix = mapper.readValue(this.boardMatrixJson, long[][].class);
        }catch(Exception e){
            // TODO - how to deal with this ???
        }
    }
    
    // this property isn't persisted - the json equivalent is!
    public long[][] getBoardMatrix(){ return this.boardMatrix; }
    public void setBoardMatrix(long[][] matrix){ 
        this.boardMatrix = matrix;
        this.updateBoardMatrixJsonTo(matrix);
    }
    
    public List<Player> getPlayers(){ return this.players; }
    public void setPlayers(List<Player> list){ this.players = list; }
    
    /*
     * helper that will set the board-json state to match that of the given 2d array
     */
    protected void updateBoardMatrixJsonTo(long[][] matrix){
        if(matrix == null) return;

        try{
            ObjectMapper mapper = new ObjectMapper();
            this.boardMatrixJson = mapper.writeValueAsString(matrix);
        }catch(Exception e){
            // TODO - what to do with this!?
        }
    }
    
    public int getNumRows() throws NullPointerException{
        if(this.boardMatrix == null) throw new NullPointerException("Cannot retrieve row count for null matrix");

        return this.boardMatrix.length;
    }

    public int getNumCols() throws NullPointerException{
        if(this.boardMatrix == null) throw new NullPointerException("Cannot retrieve col count for null matrix");

        return this.boardMatrix[0].length;
    }

    public boolean isOccupied(int row, int col) throws NullPointerException{
        if(this.boardMatrix == null) throw new NullPointerException("Cannot test occupation of null board matrix");
        if(row < 0 || row >= this.boardMatrix.length) throw new IndexOutOfBoundsException("Row number " + row + " is out of bounds (the board has " + this.boardMatrix.length + " rows)");
        if(col < 0 || col >= this.boardMatrix[0].length) throw new IndexOutOfBoundsException("Col number " + col + " is out of bounds (the board has " + this.boardMatrix[0].length + " cols)");

        return this.boardMatrix[row][col] > 0;
    }
    
    /*
     * helper that occupies the given row/col by the given player.
     * throws exceptions if row or col are out of bounds of the board dimensions,
     * if there's no board, or if the given player is null.
     *
     * this helper should always be used to update the game's board, instead of
     * manually getting/updating the board matrix by hand.  by using this helper,
     * you ensure that the json state (also held in this game object) remains in sync
     * with the board.
     */
    public void occupySpot(Player p, int row, int col) throws Exception{
        if(p == null){ throw new NullPointerException("Cannot occupy the board with a null player"); }
        if(this.boardMatrix == null){ throw new NullPointerException("Cannot occupy when there's a null board matrix"); }
        if(row < 0 || row >= this.boardMatrix.length){ throw new IndexOutOfBoundsException("Row number " + row + " is out of bounds (the board has " + this.boardMatrix.length + " rows)"); }
        if(col < 0 || col >= this.boardMatrix[0].length){ throw new IndexOutOfBoundsException("Col number " + col + " is out of bounds (the board has " + this.boardMatrix[0].length + " cols)"); }
        if(this.isOccupied(row,col)){ throw new Exception(row + ", " + col + " is already occupied"); }

        this.boardMatrix[row][col] = p.getId();
        this.updateBoardMatrixJsonTo(this.boardMatrix);
    }
    
    /*
     * same as occupySpot, except this clears the spot
     */
    public void clearSpot(int row, int col) throws Exception {
        if(this.boardMatrix == null){ throw new NullPointerException("Cannot clear spot when there's a null board matrix"); }
        if(row < 0 || row >= this.boardMatrix.length){ throw new IndexOutOfBoundsException("Row number " + row + " is out of bounds (the board has " + this.boardMatrix.length + " rows)"); }
        if(col < 0 || col >= this.boardMatrix[0].length){ throw new IndexOutOfBoundsException("Col number " + col + " is out of bounds (the board has " + this.boardMatrix[0].length + " cols)"); }
        
        this.boardMatrix[row][col] = 0;
        this.updateBoardMatrixJsonTo(this.boardMatrix);
    }
    
    /*
     * Return the player of this game with the given id or null if no such player exists.
     *
     * @param id the id of the player to fetch
     * @return the player with a matching id
     */
    public Player getPlayerWithId(long id){
        if(this.players == null || this.players.size() <= 0){ return null; }

        for(Player next : this.players){
            if(next.getId() == id){ return next; }
        }

        return null;
    }
    
    /*
     * Return the player of this game that has the right number of pieces in-a-row
     * horizontally on the board starting at the given row/col indices, or null if 
     * no such player exists.
     */
    public Player getHorizontalWinnerStartingAt(int i, int j) throws Exception{
        if(this.boardMatrix == null){ throw new NullPointerException("Cannot get horizontal-winners without a board"); }
        if(i < 0 || j < 0){ throw new IndexOutOfBoundsException("Cannot get horizontal-winners for negative row/col indices"); }
        if(this.players == null || this.players.size() != 2){ throw new Exception("Cannot check for horizontal-winners if the game doesn't have exactly 2 players"); }
        if(this.numberInRowToWin < 1){ throw new Exception("Cannot calculate horizontal winners for non-positive number-in-row-to-win!"); }

        // check for numberInRowToWin pieces in-a-row in row i, but
        // not if the last index exceeds the size of the board (cuz then
        // you can't possibly have enough pieces in a row to win for the 
        // given i,j combo.)
        int startIndex = i + this.numberInRowToWin - 1;
        if(startIndex >= this.boardMatrix.length){ return null; }

        long winnerId = this.boardMatrix[startIndex][j];
        if(winnerId <= 0){ return null; }

        // now check consecutive spaces in row i: if even a single piece
        // is not the correct player id, then there isn't a winner for the
        // given i,j tuple
        for(int next = startIndex - 1; next >= i; next--){
            if(this.boardMatrix[next][j] != winnerId){ return null; }
        }

        Player winner = this.getPlayerWithId(winnerId);
        if(winner == null){ throw new Exception("Could not find player with id " + winnerId + " (who was determined to have won the game, horizontally, starting at " + i + ", " + j + ")"); }

        return winner;
    }
    
    /*
     * Return the player of this game that has the right number of pieces in-a-row
     * vertically on the board starting at the given row/col indices, or null if 
     * no such player exists.
     */
    public Player getVerticalWinnerStartingAt(int i, int j) throws Exception{
        if(this.boardMatrix == null){ throw new NullPointerException("Cannot get vertical-winners without a board"); }
        if(i < 0 || j < 0){ throw new IndexOutOfBoundsException("Cannot get vertical-winners for negative row/col indices"); }
        if(this.players == null || this.players.size() != 2){ throw new Exception("Cannot check for vertical-winners if the game doesn't have exactly 2 players"); }
        if(this.numberInRowToWin < 1){ throw new Exception("Cannot calculate vertical winners for non-positive number-in-row-to-win!"); }

        // check for numberInRowToWin pieces in-a-row in col j, but
        // not if the last index exceeds the size of the board (cuz then
        // you can't possibly have enough pieces in a row to win for the 
        // given i,j combo.)
        int startIndex = j + this.numberInRowToWin - 1;
        if(startIndex >= this.boardMatrix[i].length){ return null; }

        long winnerId = this.boardMatrix[i][startIndex];
        if(winnerId <= 0){ return null; }

        // now check consecutive spaces in col j: if even a single piece
        // is not the correct player id, then there isn't a winner for the
        // given i,j tuple
        for(int next = startIndex - 1; next >= j; next--){
            if(this.boardMatrix[i][next] != winnerId){ return null; }
        }

        Player winner = this.getPlayerWithId(winnerId);
        if(winner == null){ throw new Exception("Could not find player with id " + winnerId + " (who was determined to have won the game, vertically, starting at " + i + ", " + j + ")"); }

        return winner;
    }
    
    /*
     * Return the player of this game that has the right number of pieces in-a-row
     * diagonally-going-right on the board starting at the given row/col indices, or 
     * null if no such player exists.
     *
     * Diagonal-right means going down the board in the right-direction:
     * x o o o
     * o x o o
     * o o x o
     * o o o x  <--- 4-in-a-row diagonal-right
     */
    public Player getDiagonalRightWinnerStartingAt(int i, int j) throws Exception{
        if(this.boardMatrix == null){ throw new NullPointerException("Cannot get diagonal-right-winners without a board"); }
        if(i < 0 || j < 0){ throw new IndexOutOfBoundsException("Cannot get diagonal-right-winners for negative row/col indices"); }
        if(this.players == null || this.players.size() != 2){ throw new Exception("Cannot check for diagonal-right-winners if the game doesn't have exactly 2 players"); }
        if(this.numberInRowToWin < 1){ throw new Exception("Cannot calculate diagonal-right winners for non-positive number-in-row-to-win!"); }

        // check for numberInRowToWin pieces in-a-row, but
        // not if the last index of the row OR col exceeds the size of the 
        // board (cuz then you can't possibly have enough pieces in a row 
        // to win for the given i,j combo.)
        int startingRowIndex = i + this.numberInRowToWin - 1;
        if(startingRowIndex >= this.boardMatrix.length){ return null; }

        int startingColIndex = j + this.numberInRowToWin - 1;
        if(startingColIndex >= this.boardMatrix[i].length){ return null; }

        long winnerId = this.boardMatrix[startingRowIndex][startingColIndex];
        if(winnerId <= 0){ return null; }

        // now check consecutive spaces diagonally: if even a single piece
        // is not the correct player id, then there isn't a winner for the
        // given i,j tuple
        int nextR = startingRowIndex - 1;
        int nextC = startingColIndex - 1;
        while(nextR >= i && nextC >= j){
            if(this.boardMatrix[nextR][nextC] != winnerId){ return null; }
            nextR--;
            nextC--;
        }

        Player winner = this.getPlayerWithId(winnerId);
        if(winner == null){ throw new Exception("Could not find player with id " + winnerId + " (who was determined to have won the game, diagonally-right, starting at " + i + ", " + j + ")"); }

        return winner;
    }
    
    /*
     * Return the player of this game that has the right number of pieces in-a-row
     * diagonally-going-left on the board starting at the given row/col indices, or 
     * null if no such player exists.
     *
     * Diagonal-left means going down the board in the left-direction:
     * o o o x
     * o o x o
     * o x o o
     * x o o o  <--- 4-in-a-row diagonal-left
     */
    public Player getDiagonalLeftWinnerStartingAt(int i, int j) throws Exception{
        if(this.boardMatrix == null){ throw new NullPointerException("Cannot get diagonal-left-winners without a board"); }
        if(i < 0 || j < 0){ throw new IndexOutOfBoundsException("Cannot get diagonal-left-winners for negative row/col indices"); }
        if(this.players == null || this.players.size() != 2){ throw new Exception("Cannot check for diagonal-left-winners if the game doesn't have exactly 2 players"); }
        if(this.numberInRowToWin < 1){ throw new Exception("Cannot calculate diagonal-left winners for non-positive number-in-row-to-win!"); }

        // check for numberInRowToWin pieces in-a-row, but
        // not if the first index of the row OR col exceeds the bounds of the 
        // board (cuz then you can't possibly have enough pieces in a row 
        // to win for the given i,j combo.)
        int startingRowIndex = i - this.numberInRowToWin + 1;
        if(startingRowIndex < 0){ return null; }

        int startingColIndex = j + this.numberInRowToWin - 1;
        if(startingColIndex >= this.boardMatrix[i].length){ return null; }

        long winnerId = this.boardMatrix[startingRowIndex][startingColIndex];
        if(winnerId <= 0){ return null; }

        // now check consecutive spaces diagonally: if even a single piece
        // is not the correct player id, then there isn't a winner for the
        // given i,j tuple
        int nextR = startingRowIndex + 1;
        int nextC = startingColIndex - 1;
        while(nextR <= i && nextC >= j){
            if(this.boardMatrix[nextR][nextC] != winnerId){ return null; }
            nextR++;
            nextC--;
        }

        Player winner = this.getPlayerWithId(winnerId);
        if(winner == null){ throw new Exception("Could not find player with id " + winnerId + " (who was determined to have won the game, diagonally-left, starting at " + i + ", " + j + ")"); }

        return winner;
    }
    
    /*
     * Searches the board for a winning player and returns it if one is present.
     * Otherwise, returns null
     */
    public Player findWinningPlayerIfPresent() throws Exception{
        if(this.boardMatrix == null){ throw new NullPointerException("Cannot search for winning players without a board"); }
        if(this.players == null || this.players.size() != 2){ throw new Exception("Cannot search for winning players in a game without exactly 2 players"); }

        // this loop has to traverse left-to-right FIRST, then top-to-bottom.
        // so the loop indices are reversed
        for(int j = 0; j < this.boardMatrix[0].length; j++){
            for(int i = 0; i < this.boardMatrix.length; i++){
                Player winner = null;

                winner = this.getHorizontalWinnerStartingAt(i,j);
                if(winner != null){ return winner; }

                winner = this.getVerticalWinnerStartingAt(i,j);
                if(winner != null){ return winner; }

                winner = this.getDiagonalLeftWinnerStartingAt(i,j);
                if(winner != null){ return winner; }

                winner = this.getDiagonalRightWinnerStartingAt(i,j);
                if(winner != null){ return winner; }
            }
        }

        return null;
    }
    
    /*
     * Returns true if the board is full, false otherwise
     */
    public boolean boardIsFull() throws Exception{
        if(this.boardMatrix == null){ throw new NullPointerException("Cannot check if board is full without a board"); }
        for(int i = 0; i < this.boardMatrix.length; i++){
            for(int j = 0; j < this.boardMatrix[0].length; j++){
                if(this.boardMatrix[i][j] <= 0){ return false; }
            }
        }

        return true;
    }

    /*
     * Returns true if an outcome has already been determined for this game, false otherwise
     */
    public boolean outcomeAlreadyDetermined(){
        return !StringUtils.isBlank(this.outcomeDescription);
    }
    
    /*
     * Helper that lets the caller know if this game is over.
     *
     * Note that this helper will change the state of this game object if it detects
     * game-ending conditions for the first time - the caller will probably want to
     * save this game object after calling this helper, just to ensure that any state
     * set in this helper persists.
     */
    public boolean isGameOver() throws Exception{
        if(this.outcomeAlreadyDetermined()){ return true; }

        // the outcome hasn't been pre-determined - check some game-ending conditions
        // and update the state of this object appropriately where necessary
        Player winningPlayer = this.findWinningPlayerIfPresent();
        if(winningPlayer != null){
            PlayerColor winningColor = winningPlayer.getPlayerColor();
            if(winningColor != null && !StringUtils.isBlank(winningColor.getName())){
                this.setOutcomeDescription(winningColor.getName() + " (" + winningPlayer.getId() + ") wins the game!");
            }else{
                this.setOutcomeDescription("Player " + winningPlayer.getId() + " wins the game!");
            }

            return true;
        }else if(this.boardIsFull()){
            this.setOutcomeDescription("It's a tie!");
            return true;
        }

        return false;
    }
    
     /*
     * Returns a sorted list of legal game moves for the current state of
     * the game board.
     *
     * Legal moves in connect-four are basically the highest-most unoccupied
     * spot of each column.  For example, the X's are legal moves, the dots
     * are unoccupied, and the o's are occupied (and note that the legal moves
     * themselves are unoccupied):
     *
     * . . . . X
     * X . . . o
     * o X . . o
     * o o . X o
     * o o X o o
     */
    public SortedSet<GameMove> getLegalMoves() throws Exception{
        if(this.boardMatrix == null){ throw new NullPointerException("Cannot get legal moves without a board"); }

        SortedSet<GameMove> moves = new TreeSet<GameMove>();
        for(int i = 0; i < this.boardMatrix.length; i++){
            for(int j = this.boardMatrix[i].length - 1; j >= 0; j--){
                if(!this.isOccupied(i,j)){
                    moves.add(new GameMove(i,j));
                    break;
                }
            }
        }

        return moves;
    }
    
    /*
     * Helper that returns the player with the given id, or null if
     * no such player exists.
     */
    public Player getPlayer(long id){
        if(this.players == null || this.players.size() <= 0){ return null; }

        for(Player p : players){
            if(p.getId() == id){ return p; }
        }

        return null;
    }
    
    @Override
    public int hashCode(){
        return new HashCodeBuilder(17,31).append(this.id)
                                         .append(this.winningPlayerId)
                                         .append(this.outcomeDescription)
                                         .append(this.numberInRowToWin)
                                         .append(this.boardMatrix)
                                         .append(this.players)
                                         .toHashCode();
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){ return false; }
        if(obj == this){ return true; }
        if(!(obj instanceof Game)){ return false; }
        
        Game other = (Game)obj;
        return new EqualsBuilder().append(this.id, other.id)
                                  .append(this.winningPlayerId, other.winningPlayerId)
                                  .append(this.outcomeDescription, other.outcomeDescription)
                                  .append(this.numberInRowToWin, other.numberInRowToWin)
                                  .append(this.boardMatrix, other.boardMatrix)
                                  .append(this.players, other.players)
                                  .isEquals();
    }
    
    @Override
    public String toString(){
        return new ToStringBuilder(this).append("id", this.id)
                                        .append("winningPlayerId", this.winningPlayerId)
                                        .append("outcomeDescription", this.outcomeDescription)
                                        .append("numberInRowToWing", this.numberInRowToWin)
                                        .append("boardMatrix", this.boardMatrix)
                                        .append("players", this.players)
                                        .toString();
    }
}
