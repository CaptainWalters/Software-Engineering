import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.List;

/**
 *
 * @author Kieran
 */
public class Board {

    // Kieran>>>
     BoardLocation[] board;

     public Board(){
         board = new BoardLocation[40];
     }

     public String getInfo (int position){
         return board[position].getInformation();
     }

     public void populateBoard() throws IOException{
        board[0] = new BoardLocation(0,"Go",0, "", "Collect £200", false);
         board[1] = new BoardLocation(1,"Crapper Street",60,"Brown","", true);
         board[2] = new BoardLocation(2,"Pot Luck",0,"","Pick pot luck card", false);
         board[3] = new BoardLocation(3,"Gangsters Paradise", 60,"brown","", true);
         board[4] = new BoardLocation(4,"Income Tax", 0,"","Pay £200", false);
         board[5] = new BoardLocation(5,"Brighton Station", 200,"station","", true);
         board[6] = new BoardLocation(6,"Weeping Angel", 100,"blue","", true);
         board[7] = new BoardLocation(7,"Opportunity Knocks", 0,"","Pick opportunity knocks card", false);
         board[8] = new BoardLocation(8,"Potts Avenue", 100,"blue","", true);
         board[9] = new BoardLocation(9,"Nardole Drive", 120,"blue","", true);
         board[10] = new BoardLocation(10,"Jail/Just Visiting", 0,"","", false);
         board[11] = new BoardLocation(11,"Skywalker Drive", 140,"purple","", true);
         board[12] = new BoardLocation(12,"Tesla PowerCo", 150,"utilities","", true);
         board[13] = new BoardLocation(13,"Wookie Hole ", 140,"purple","", true);
         board[14] = new BoardLocation(14,"Rey Lane", 160,"purple","", true);
         board[15] = new BoardLocation(15,"Hove Station", 200,"station","", true);
         board[16] = new BoardLocation(16,"Cooper Drive", 180,"orange","", true);
         board[17] = new BoardLocation(17,"Pot Luck", 0,"","Pick pot luck card", false);
         board[18] = new BoardLocation(18,"Wolowitz Street", 180,"orange","", true);
         board[19] = new BoardLocation(19,"Penny Lane", 200,"orange","", true);
         board[20] = new BoardLocation(20,"Free Parking", 0,"","Collect Fines", false);
         board[21] = new BoardLocation(21,"Yue Fei Square", 220,"red","", true);
         board[22] = new BoardLocation(22,"Opportunity Knocks", 0,"","Pick opportunity knocks card", false);
         board[23] = new BoardLocation(23,"Mulan Rouge", 220,"red","", true);
         board[24] = new BoardLocation(24,"Han Xin Gardens", 240,"red","", true);
         board[25] = new BoardLocation(25,"Falmer Station", 200,"station","", true);
         board[26] = new BoardLocation(26,"Kirk Close", 260,"yellow","", true);
         board[27] = new BoardLocation(27,"Picard", 260,"yellow","", true);
         board[28] = new BoardLocation(28,"Edison Water", 150,"utilities","", true);
         board[29] = new BoardLocation(29,"Crusher Creek", 280,"yellow","", true);
         board[30] = new BoardLocation(30," Go to Jail", 0,"","Go to Jail", false);
         board[31] = new BoardLocation(31,"Sirat Mews", 300,"green","", true);
         board[32] = new BoardLocation(32,"Ghengis Crescent", 300,"green","", true);
         board[33] = new BoardLocation(33,"Pot Luck",0,"","Pick pot luck card", false);
         board[34] = new BoardLocation(34,"Ibis Close", 320,"green","", true);
         board[35] = new BoardLocation(35,"Lewes Station", 200,"station","", true);
         board[36] = new BoardLocation(36,"Opportunity Knocks", 0,"","Pick opportunity knocks card", false);
         board[37] = new BoardLocation(37,"Hawking Way", 350,"deep blue","", true);
         board[38] = new BoardLocation(38,"Super Tax", 0,"","Pay £100", false);
         board[39] = new BoardLocation(39,"Turing Heights", 400,"deep blue","", true);

     }

    // Loz>>>>

     // making an immutible list of locations
//       private final List<Tile> gameBoard;
//
//      private Board(Builder builder){
//          this.gameBoard = createGameBoard(builder);
//      }
//
//      public TileLocation getLocation(int tileLocation){
//          return gameBoard.get(tileLocation);
//      }
//
//      private static List<Tile> createGameBoard(final Builder builder){
//          final final Tile[] tiles = newTile[BoardUtils.NUM_TILES);
//          for int i = 0; i < BoardUtils.NUMTILES; i++){
//              tiles[i] = Tile.createTile(i, builder.boardConfig.get(i)); // create a tiler
//          }
//          return ImmutableList.copyOf(tiles);
//
//      }
//
//      public static Board createMonopolyBoard(){
//      //will use the builder class in order to build the MonopolyBoard that we wish to be used.
//          final Builder builder = new Builder();
//          // makings of the new pieces and setting them to the Go Square
//          builder.setPiece[new SmartPhone[Type.SmartPhone,0]];
//          builder.setPiece[new Cat[Type.Cat,0]];
//          builder.setPiece[new Goblet[Type.Goblet,0]];
//          builder.setPiece[new Boot[Type.Boot,0]];
//          builder.setPiece[new HatStand[Type.HatStand,0]];
//          builder.setPiece[new Spoon[Type.Spoon,0]];
//          //^ doesnt yet work not all set up need to make independant classes for all the piece.
//
//      }
//
//      public static class Builder{
//        // creation of a builder in order ot build the board
//      public Builder(){
//      }
//
//      public Builder setPiece(final Piece piece) {
//            this.boardConfig.put[piece.getpieceLocation[], piece);
//            return this;
//      }
//
//      public Builder setMoveMaker(final Type nextPlayerToMove) {
//          this.nextPlayerToMove = nextPlayerToMove;
//          return this;
//      }
//
//      public Board build(){
//          return new Board(this);
//      }


      /*
      ** <<<<piece enum notes>>>>
      **public abstract enum Piece{
      **protected final int pieceLocation;
      **protected final Alliance pieceType;
      **protected final boolean isFirstMove;
      **
      **Piece(final int piecePosition, final Type pieceType){
      **this.pieceType = pieceType;
      **this.pieceLocation = pieceLocation;
      ** ***MORE HERE***
      ** this.isFirstMove = false;
      ** }
      **
      ** public int getPieceLocation(){
      **  return this.pieceLocation;
      ** }
      **
      **public Type getPieceType(){
      **return this.pieceAlliance;
      **}
      **
      **public boolean isFirstMove(){
      **  return this.isFirstMove;
      **}
      **
      **public boolean isFirstLap(){
      **  return this.isFirstLap;
      **}
      **
      **public abstract Collection<Move> calculateLegalMoves(final Board board);
      */

}
