package tests;

// (c) Larry Herman, 2016.  You are allowed to use this code yourself, but
// not to provide it to anyone else.

import chess.BoardSquare;
import chess.Chess;
import java.util.NoSuchElementException;
import org.junit.*;
import static org.junit.Assert.*;

public class Project2SecretTests {

  // Tests blackWon() and whiteWon() with multiple kings on the board.
  @Test public void testSecret1() {
    Chess chess= TestCode.standardBoard();

    assertFalse(chess.blackWon());
    assertFalse(chess.whiteWon());

    // adding 3 white kings
    for (int i= 0; i < 3; i++)
      chess.setEntry(BoardSquare.WHITEKING, (char) ('c' + i), 4);

    // adding 4 black kings
    for (int i= 0; i < 4; i++)
      chess.setEntry(BoardSquare.BLACKKING, (char) ('c' + i), 3);

    assertFalse(chess.blackWon());
    assertFalse(chess.whiteWon());
  }

  // Tests additional functionality of the validMove() method, calling it
  // when the starting square has a too-small column letter and a too-small
  // row number, as well as the ending column having a too-small column
  // letter and a too-small row number.
  @Test public void testSecret2() {
    Chess chess= TestCode.standardBoard();

    // the column letter of the starting square is too small (note that 'A'
    // < 'a' in ASCII)
    assertFalse(chess.validMove('A', 5, 'e', 7));

    // the row number of the starting square is too small
    assertFalse(chess.validMove('b', -1, 'e', 7));

    // the column letter of the starting square is too small
    assertFalse(chess.validMove('d', 5, 'Z', 5));

    // the row number of the starting square is too small
    assertFalse(chess.validMove('e', 7, 'b', -10));
  }

  // Tests whether the copy constructor creates a Chess object that is
  // independent of the current object (a deep copy).
  @Test public void testSecret3() {
    Chess chess= TestCode.standardBoard();
    Chess anotherChess= new Chess(chess);

    chess.setEntry(BoardSquare.WHITEKING, 'b', 5);

    // the following change should affect only the object "chess"
    assertEquals(chess.getEntry('b', 5), BoardSquare.WHITEKING);

    // anotherChess at the same position should still be empty
    assertEquals(anotherChess.getEntry('b', 5), BoardSquare.EMPTYSQUARE);
  }

  // Tests calling setEntry() on squares that are off the board
  @Test public void testSecret4() {
    Chess chess= new Chess();

    // the row number is too small, so the move is invalid
    try {
      chess.setEntry(BoardSquare.WHITEBISHOP, 'b', 0);
      // if we reach here, this will cause the test to fail
      fail("NoSuchElementException should be thrown");
    } catch (NoSuchElementException nsee) {
      // do nothing if the expected exception is thrown
    }

    // the column letter is too small, so the move is invalid (note that '0'
    // < 'a' in ASCII)
    try {
      chess.setEntry(BoardSquare.WHITEBISHOP, '0', 5);
      // if we reach here, this will cause the test to fail
      fail("IllegalStateException should be thrown");
    } catch (NoSuchElementException nsee) {
      // do nothing if the expected exception is thrown
    }

    // if we reach here the test passes
  }

  // Tests additional functionality of the validMove() method.  Tests some
  // valid bishop movements where the intermediate squares are unoccupied.
  @Test public void testSecret5() {
    Chess chess= TestCode.stringToBoard("--------" +  // row 8
                                        "--------" +
                                        "----p---" +
                                        "----p---" +
                                        "----B---" +
                                        "---P----" +
                                        "--P-----" +
                                        "--------");  // row 1

    // move bishop diagonally upper left to b7 where all intermediate
    // squares are unoccupied
    assertTrue(chess.validMove('e', 4, 'b', 7));

    // move bishop diagonally upper right to h7 where all intermediate
    // squares are unoccupied
    assertTrue(chess.validMove('e', 4, 'h', 7));
  }

  // Tests additional functionality of the validMove() method.  Tests some
  // invalid bishop movements, including one where the intermediate squares
  // are occupied, making the move invalid.
  @Test public void testSecret6() {
    Chess chess= TestCode.stringToBoard("--------" +  // row 8
                                        "--------" +
                                        "----p---" +
                                        "----p---" +
                                        "----B---" +
                                        "---P----" +
                                        "--P-----" +
                                        "--------");  // row 1

    // move bishop diagonally to the lower left to b1, which is empty, but
    // the intermediate squares are occupied
    assertFalse(chess.validMove('e', 4, 'b', 1));

    // move bishop downward, which is invalid
    assertFalse(chess.validMove('e', 4, 'e', 2));
  }

  // Tests additional functionality of the validMove() method for kings;
  // a valid move, and various invalid moves of different types.
  @Test public void testSecret7() {
    Chess chess= TestCode.stringToBoard("--------" +  // row 8
                                         "--------" +
                                         "----P---" +
                                         "----P---" +
                                         "----K---" +
                                         "---p----" +
                                         "--P-----" +
                                         "--------");  // row 1

    // move king rightward to f4
    assertTrue(chess.validMove('e', 4, 'f', 4));

    // move king diagonally, which is valid because it is occupied by an
    // opposite color piece
    assertTrue(chess.validMove('e', 4, 'd', 3));

    // move king upward to e5 which is invalid because it is occupied by a
    // piece of the same color
    assertFalse(chess.validMove('e', 4, 'e', 5));

    // it is not a valid move for any piece type if the ending square is the
    // same as the starting square
    assertFalse(chess.validMove('e', 4, 'e', 4));

    // a king cannot move more than one square
    assertFalse(chess.validMove('e', 4, 'a', 4));
  }

  // Tests additional functionality of the validMove() method; some valid
  // movements by a rook.
  @Test public void testSecret8() { 
    Chess chess= TestCode.stringToBoard("--------" +  // row 8
                                        "--------" +
                                        "------p-" +
                                        "------p-" +
                                        "------p-" +
                                        "---pppr-" +
                                        "--------" +
                                        "--------");  // row 1

    // movee rook one square rightward to h3
    assertTrue(chess.validMove('g', 3, 'h', 3));

    // move rook 2 squares downward to g1
    assertTrue(chess.validMove('g', 3, 'g', 1));
  }

  // Tests additional functionality of the validMove() method.  Tests some
  // valid queen movements where the intermediate squares are unoccupied.
  @Test public void testSecret9() {
    Chess chess= TestCode.stringToBoard("--------" +  // row 8
                                        "--------" +
                                        "----p---" +
                                        "----p---" +
                                        "----Q---" +
                                        "---P----" +
                                        "--P-----" +
                                        "--------");  // row 1

    // move queen leftward to a4 where all intermediate squares are
    // unoccupied
    assertTrue(chess.validMove('e', 4, 'a', 4));

    // move queen downward to e1 where all intermediate squares are
    // unoccupied
    assertTrue(chess.validMove('e', 4, 'e', 1));
  }

  // Tests all valid and invalid knight and king moves from a particular
  // starting square, using the private helper method testValid() (defined
  // below).
  @Test public void testSecret10() {
    String test1= "--------" +  // row 8
                  "--------" +
                  "--------" +
                  "----x-x-" +
                  "---x---x" +
                  "--------" +
                  "---x---x" +
                  "----x-x-";  // row 1
    String test2= "xxx-----" +  // row 8
                  "x-x-----" +
                  "xxx-----" +
                  "--------" +
                  "--------" +
                  "--------" +
                  "--------" +
                  "--------";  // row 1

    testValid(test1, BoardSquare.BLACKKNIGHT, 'f', 3);
    testValid(test1, BoardSquare.WHITEKNIGHT, 'f', 3);

    testValid(test2, BoardSquare.BLACKKING, 'b', 7);
    testValid(test2, BoardSquare.WHITEKING, 'b', 7);
  }

  // Tests all valid and invalid rook moves from a particular starting
  // square, using the private helper method testValid() (defined below).
  @Test public void testSecret11() {
    String test= "-----x--" +  // row 8
                 "-----x--" +
                 "-----x--" +
                 "-----x--" +
                 "xxxxx-xx" +
                 "-----x--" +
                 "-----x--" +
                 "-----x--";  // row 1

    testValid(test, BoardSquare.BLACKROOK, 'f', 4);
    testValid(test, BoardSquare.WHITEROOK, 'f', 4);
  }

  // Tests additional functionality of the validMove() method; some valid
  // bishop movements.
  @Test public void testSecret12() {
    Chess chess= TestCode.stringToBoard("pppppppp" +  // row 8
                                        "p-pppppp" +
                                        "pp-ppppp" +
                                        "ppp-pppp" +
                                        "pppp-ppp" +
                                        "ppppp-pp" +
                                        "ppppppBp" +
                                        "pppppppp");  // row 1

    // b7 is empty so the move is valid
    assertTrue(chess.validMove('g', 2, 'b', 7));

    // test whether the bishop can take the pawn at a8
    assertTrue(chess.validMove('g', 2, 'a', 8));

    // test whether the bishop can take the pawn at h1
    assertTrue(chess.validMove('g', 2, 'h', 1));
  }

  // Tests additional functionality of the validMove() method; tests some
  // invalid movements by a rook.
  @Test public void testSecret13() {
    Chess chess= TestCode.stringToBoard("--------" +  // row 8
                                        "--------" +
                                        "------p-" +
                                        "------p-" +
                                        "------p-" +
                                        "---pppr-" +
                                        "--------" +
                                        "--------");  // row 1

    // move rook leftward to b3, which is empty, but the intermediate
    // squares are occupied
    assertFalse(chess.validMove('g', 3, 'b', 3));

    // move rook upward to g7, which is empty, but the intermediate
    // squares are occupied
    assertFalse(chess.validMove('g', 3, 'g', 7));
  }

  // Tests additional functionality of the validMove() method.  Tests some
  // invalid queen movements, including one where the intermediate squares
  // are occupied, making the move invalid.
  @Test public void testSecret14() {
    Chess chess= TestCode.stringToBoard("----P---" +  // row 8
                                        "--------" +
                                        "----r---" +
                                        "----r---" +
                                        "----Q---" +
                                        "---r-r--" +
                                        "--r---r-" +
                                        "-------P");  // row 1

    // move queen upward to e8; it can NOT replace a piece of the same color
    assertFalse(chess.validMove('e', 4, 'e', 8));

    // move queen diagonally bottom right to h1; it can NOT replace a piece
    // of the same color
    assertFalse(chess.validMove('e', 4, 'h', 1));

    // move queen upward to e7, which is empty, but the intermediate
    // squares are occupied
    assertFalse(chess.validMove('e', 4, 'e', 7));

    // move queen diagonally bottom left to b1, which is empty, but the
    // intermediate squares are occupied
    assertFalse(chess.validMove('e', 4, 'b', 1));
  }

  // Tests all valid and invalid bishop and pawn moves from a particular
  // starting square, using the private helper method testValid() (defined
  // below).
  @Test public void testSecret15() {
    String test1= "-------x" +  // row 8
                  "x-----x-" +
                  "-x---x--" +
                  "--x-x---" +
                  "--------" +
                  "--x-x---" +
                  "-x---x--" +
                  "x-----x-";  // row 1
    String test2= "--------" +  // row 8
                  "--------" +
                  "--------" +
                  "------x-" +
                  "--------" +
                  "--------" +
                  "--------" +
                  "--------";  // row 1

    testValid(test1, BoardSquare.BLACKBISHOP, 'd', 4);
    testValid(test1, BoardSquare.WHITEBISHOP, 'd', 4);

    testValid(test2, BoardSquare.BLACKPAWN, 'g', 6);
    testValid(test2, BoardSquare.WHITEPAWN, 'g', 4);
  }

  // Tests all valid and invalid queen moves from a particular starting
  // square, using the private helper method testValid() (defined below).
  @Test public void testSecret16() {
    String test= "--x----x" +  // row 8
                 "--x---x-" +
                 "--x--x--" +
                 "x-x-x---" +
                 "-xxx----" +
                 "xx-xxxxx" +
                 "-xxx----" +
                 "x-x-x---";  // row 1

    testValid(test, BoardSquare.BLACKQUEEN, 'c', 3);
    testValid(test, BoardSquare.WHITEQUEEN, 'c', 3);
  }

  // Tests a complete (short) game, by calling move().  The game will end by
  // white taking black's king in five moves.
  @Test public void testSecret17() {
    Chess chess= TestCode.standardBoard();

    // white pawn goes one space upward
    assertTrue(chess.move('e', 2, 'e', 3));

    // black pawn goes one space downward
    assertTrue(chess.move('d', 7, 'd', 6));

    // white bishop goes to b5 (checkmate now)
    assertTrue(chess.move('f', 1, 'b', 5));

    // any dummy move by black
    assertTrue(chess.move('h', 7, 'h', 6));

    // white bishop takes black king
    assertTrue(chess.move('b', 5, 'e', 8));

    assertTrue(chess.whiteWon());
  }

  // private utility method ////////////////////////////////////////////

  // Helper method that allows checking all the valid moves for a particular
  // piece starting from a given starting square.  Its parameters are a
  // string, a piece, and the location of a starting square.  The string
  // should contain and 'x' in the position of every valid move (if the
  // piece was placed at the starting square), and a dash in the position of
  // every invalid move.  validMove() will be called on every square of the
  // board (except the starting square), and if it is correct it should
  // return false for every location where there is a dash, and true for
  // every location containing an 'x'.
  static void testValid(String board, BoardSquare piece, char col, int row) {
    Chess chess= new Chess();
    int r, rNum;
    char c;
    int pos= 0;

    chess.setEntry(piece, col, row);

    for (r= 1; r <= 8; r++) {
      rNum= 8 - r + 1;
      for (c= 'a'; c <= 'h'; c++) {
        if (board.charAt(pos++) == '-')
          assertFalse(chess.validMove(col, row, c, rNum));
        else assertTrue(chess.validMove(col, row, c, rNum));
      }
    }
  }

}
