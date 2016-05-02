package chess;

import java.util.NoSuchElementException;

/*
 * Chess will store a chess board, and will allow for the pieces on the 
 * board to be manipulated.
 */
public class Chess {

   // Private attribute that stores chess board as a 2D array of squares.
   private BoardSquare board[][] = new BoardSquare[8][8];

   // default constructor - creates new Chess object
   // sets all squares in 2D chess board array to empty
   public Chess() {
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[0].length; j++) {
            board[i][j] = BoardSquare.EMPTYSQUARE;
         }
      }
   }

   // copy constructor - deep copy of parameter object
   public Chess(Chess otherGame) {
      // only attribute is deep copied to the new Chess object
      this.board = otherGame.board;
   }

   // places the given piece onto the given position on the 2D chess board
   public void setEntry(BoardSquare piece, char col, int row) 
         throws NoSuchElementException {
      // validates position
      if (validPosition(col, row)) {
         // -1 to adjust for index values
         // -97 to adjust for index and char to int conversion
         board[row - 1][((int) col) - 97] = piece;
      } else {
         // if position is not valid
         throw new NoSuchElementException();
      }
   }

   // retrieves the BoardSquare object at given position
   public BoardSquare getEntry(char col, int row) 
         throws NoSuchElementException {
      // validates position
      if (validPosition(col, row)) {
         // -1 to adjust for index values
         // -97 to adjust for index and char to int conversion
         return board[row - 1][((int) col) - 97];
      } else {
         // if position is not valid
         throw new NoSuchElementException();
      }
   }

   // returns number of squares with given piece (a BoardSquare object)
   public int count(BoardSquare piece) {
      int counter = 0;

      // two for loops for 2D array
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[0].length; j++) {
            if (board[i][j].equals(piece)) {
               // increment counter for every match
               counter++;
            }
         }
      }
      return counter;
   }

   // returns true if black "won the game"
   public boolean blackWon() {
      // return false if there are no black kings, or if a white king exists
      if (!pieceExists(BoardSquare.BLACKKING) || 
            pieceExists(BoardSquare.WHITEKING)) {
         return false;
      } else {
         // return true if a black king exists, and no white kings exist
         return true;
      }
   }

   // returns true if white "won the game"
   public boolean whiteWon() {
      // return false if there are no white kings, or if a black king exists
      if (!pieceExists(BoardSquare.WHITEKING) || 
            pieceExists(BoardSquare.BLACKKING)) {
         return false;
      } else {
         // return true if a white king exists, and no black kings exist
         return true;
      }
   }

   // returns true if a move from start to end position is valid
   public boolean validMove(char startCol, int startRow, char endCol, 
         int endRow) {
      if (!validPosition(startCol, startRow) || 
            !validPosition(endCol, endRow)) {
         return false;
      }

      // retrieves piece from starting position
      BoardSquare piece = getEntry(startCol, startRow);

      // if square is empty, then returns false for invalid,
      // if piece exists on square, checks validity from method for that piece
      if (piece.equals(BoardSquare.EMPTYSQUARE)) {
         // no piece in starting position
         return false;
      } else if (startCol == endCol && startRow == endRow) {
         // starting and ending position is same
         return false;
      } else if ((piece.isBlack() && getEntry(endCol, endRow).isBlack())
            || (piece.isWhite() && getEntry(endCol, endRow).isWhite())) {
         // same color piece on end position
         return false;
      }
      // the following else ifs check to see what type the piece is
      // and check to see if a move is valid for that type of piece
      else if (piece.isPawn() && 
            pawnValidMove(startCol, startRow, endCol, endRow, piece.isBlack())) {
         return true;
      } else if (piece.isBishop() && 
            bishopValidMove(startCol, startRow, endCol, endRow)) {
         return true;
      } else if (piece.isKnight() && 
            knightValidMove(startCol, startRow, endCol, endRow)) {
         return true;
      } else if (piece.isRook() && 
            rookValidMove(startCol, startRow, endCol, endRow)) {
         return true;
      } else if (piece.isQueen() && 
            queenValidMove(startCol, startRow, endCol, endRow)) {
         return true;
      } else if (piece.isKing() && 
            kingValidMove(startCol, startRow, endCol, endRow)) {
         return true;
      }

      // return false by default
      return false;
   }

   // returns true if a move from start to end position is valid
   // and makes that move if it is valid
   public boolean move(char startCol, int startRow, char endCol, int endRow) {
      // utilizes validMove method to check validity of move
      if (validMove(startCol, startRow, endCol, endRow)) {
         // utilizes setEntry to set the BoardSquare object at the end position
         setEntry(getEntry(startCol, startRow), endCol, endRow);
         return true;
      }

      // move is invalid
      return false;
   }

   // checks validity of col,row in 8x8 grid
   private boolean validPosition(char col, int row) {
      // -96 to adjust for char to int conversion
      if (row < 1 || row > 8 || (int) col - 96 < 1 || (int) col - 96 > 8) {
         return false;
      }
      return true;
   }

   // checks to see if given BoardSquare object exists on the 2D chessboard
   private boolean pieceExists(BoardSquare piece) {
      // two for loops for 2D chess board array
      for (int i = 0; i < board.length; i++) {
         for (int j = 0; j < board[0].length; j++) {
            if (board[i][j].equals(piece)) {
               // object match found
               return true;
            }
         }
      }
      // no match found
      return false;
   }

   // validates movement of pawn
   private boolean pawnValidMove(char startCol, int startRow, char endCol, 
         int endRow, boolean isBlack) {
      // pawn column cannot change when moving
      if (startCol != endCol) {
         return false;
      }

      if (isBlack) {
         // black pawn row can only decrease by 1
         if (startRow - endRow == 1) {
            return true;
         }
      } else {
         // white pawn row can only increase by 1
         if (endRow - startRow == 1) {
            return true;
         }
      }

      // return false by default
      return false;
   }

   // validates movement of bishop
   private boolean bishopValidMove(char startCol, int startRow, char endCol, 
         int endRow) {
      // bishop can only move diagonally
      return clearDiagonalPath(startCol, startRow, endCol, endRow);
   }

   // validates movement of knight
   private boolean knightValidMove(char startCol, int startRow, char endCol, 
         int endRow) {
      // validate by checking all 8 possible moves
      if ((endCol - startCol == 1 && endRow - startRow == 2) || 
            (endCol - startCol == 2 && endRow - startRow == 1)
            || (endCol - startCol == 2 && endRow - startRow == -1)
            || (endCol - startCol == 1 && endRow - startRow == -2)
            || (endCol - startCol == -1 && endRow - startRow == -2)
            || (endCol - startCol == -2 && endRow - startRow == -1)
            || (endCol - startCol == -2 && endRow - startRow == 1)
            || (endCol - startCol == -1 && endRow - startRow == 2)) {
         return true;
      }
      return false;
   }

   // validates movement of rook
   private boolean rookValidMove(char startCol, int startRow, char endCol, 
         int endRow) {
      // rook can only move in straight (up,down,left,right)
      return clearStraightPath(startCol, startRow, endCol, endRow);
   }

   // validates movement of queen
   private boolean queenValidMove(char startCol, int startRow, char endCol, 
         int endRow) {
      // queen can move straight or diagonally
      return clearStraightPath(startCol, startRow, endCol, endRow)
            || clearDiagonalPath(startCol, startRow, endCol, endRow);
   }

   // validates movement of king
   private boolean kingValidMove(char startCol, int startRow, char endCol, 
         int endRow) {
      if (startCol == endCol && Math.abs(startRow - endRow) == 1) {
         // moves up 1 or down 1
         return true;
      }
      if (startRow == endRow && Math.abs((char) startCol - endCol) == 1) {
         // moves left 1 or right 1
         return true;
      }
      if (Math.abs(startRow - endRow) == 1 && 
            Math.abs((char) startCol - endCol) == 1) {
         // moves diagonally by 1
         return true;
      }
      return false;
   }

   // checks to see if spaces from start to end position are empty
   // works for up,down,left,right
   private boolean clearStraightPath(char startCol, int startRow, char endCol, 
         int endRow) {

      // note - the 4 if cases check for > 1, since if =1,then return true by
      // default

      if (startCol == endCol) {
         // change in row - vertical move

         // up
         if (endRow - startRow > 1) {
            for (int i = 1; i < (endRow - startRow); i++) {
               if (!(getEntry(startCol, startRow + i).equals(
                     BoardSquare.EMPTYSQUARE))) {
                  // piece in the way
                  return false;
               }
            }
         }

         // down
         if (startRow - endRow > 1) {
            for (int i = 1; i <= (startRow - endRow); i++) {
               if (!(getEntry(startCol, startRow - i).equals(
                     BoardSquare.EMPTYSQUARE))) {
                  // piece in the way
                  return false;
               }
            }
         }

         // if moving by only 1
         return true;

      } else if (startRow == endRow) {
         // change in column - horizontal move

         // right
         if (endRow - startRow > 1) {
            for (int i = 1; i <= (endCol - startCol); i++) {
               if (!(getEntry((char) (startCol + i), startRow).equals(
                     BoardSquare.EMPTYSQUARE))) {
                  // piece in the way
                  return false;
               }
            }
         }

         // left
         if (startRow - endRow > 1) {
            for (int i = 1; i <= (startCol - endCol); i++) {
               if (!(getEntry((char) (startCol - i), startRow).equals(
                     BoardSquare.EMPTYSQUARE))) {
                  // piece in the way
                  return false;
               }
            }
         }
         // if moving by only 1
         return true;
      }
      // not a valid straight move
      return false;
   }

   // checks to see if spaces from start to end position are empty
   // when moving diagonally
   private boolean clearDiagonalPath(char startCol, int startRow, char endCol, 
         int endRow) {
      // validate for valid diagonal move
      if (Math.abs(endCol - startCol) == Math.abs(endRow - startRow)) {

         // up,right
         if (endCol - startCol > 1 && endRow - startRow > 1) {
            for (int i = 1; i <= (endCol - startCol); i++) {
               if (!(getEntry((char) (startCol + i), startRow + i).equals(
                     BoardSquare.EMPTYSQUARE))) {
                  // piece in the way
                  return false;
               }
            }
            return true;
         }

         // up,left
         if (endCol - startCol < 1 && endRow - startRow > 1) {
            for (int i = 1; i <= (endRow - startRow); i++) {
               if (!(getEntry((char) (startCol - i), startRow + i).equals(
                     BoardSquare.EMPTYSQUARE))) {
                  // piece in the way
                  return false;
               }
            }
            return true;
         }

         // down,right
         if (endCol - startCol > 1 && endRow - startRow < 1) {
            for (int i = 1; i <= (endCol - startCol); i++) {
               if (!(getEntry((char) (startCol + i), startRow - i).equals(
                     BoardSquare.EMPTYSQUARE))) {
                  // piece in the way
                  return false;
               }
            }
            return true;
         }

         // down,left
         if (endCol - startCol < 1 && endRow - startRow < 1) {
            for (int i = 1; i <= (startCol - endCol); i++) {
               if (!(getEntry((char) (startCol - i), startRow - i).equals(
                     BoardSquare.EMPTYSQUARE))) {
                  // piece in the way
                  return false;
               }
            }
            return true;
         }

         // return true for when only moving diagonally by 1
         return true;
      }

      // invalid diagonal move
      return false;
   }

}