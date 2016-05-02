package tests;

import chess.BoardSquare;
import chess.Chess;
import java.util.NoSuchElementException;
import org.junit.*;
import static org.junit.Assert.*;

public class StudentTests {


  // tests constructor, and set and get
  @Test public void test1() {
    Chess chess= new Chess();

    chess.setEntry(BoardSquare.BLACKPAWN, 'a', 1);
    chess.setEntry(BoardSquare.WHITEROOK, 'b', 2);
    chess.setEntry(BoardSquare.BLACKROOK, 'c', 3);

    assertEquals(BoardSquare.BLACKPAWN, chess.getEntry('a', 1));
    assertEquals(BoardSquare.WHITEROOK, chess.getEntry('b', 2));
    assertEquals(BoardSquare.BLACKROOK, chess.getEntry('c', 3));

    assertEquals(BoardSquare.EMPTYSQUARE, chess.getEntry('d', 2));
    assertEquals(BoardSquare.EMPTYSQUARE, chess.getEntry('e', 5));
  }

}