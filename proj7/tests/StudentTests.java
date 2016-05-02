package tests;

import org.junit.*;

import graph.Graph;
import tests.TestCode.StringComparator;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class StudentTests {
   
   //tests constructors
   @Test public void test1() {
      Graph<Integer> graph1 = new Graph<Integer>(TestCode.intComparator);
      Graph<String> graph2 = new Graph<String>(TestCode.strComparator);
      Graph<Character> graph3 = new Graph<Character>(TestCode.charComparator);
      
      
      Graph<Integer> graph4= new Graph<Integer>(graph1);
   }

   //various tests
   @Test public void test2() {
      Graph<Integer> graph1 = new Graph<Integer>(TestCode.intComparator);
      
      assertTrue(graph1.addVertex(1));
      assertTrue(graph1.addVertex(2));
      assertFalse(graph1.addVertex(2));
      assertTrue(graph1.addVertex(3));

      assertTrue(graph1.isVertex(1));
      assertTrue(graph1.isVertex(2));
      assertTrue(graph1.isVertex(3));
      assertFalse(graph1.isVertex(4));

      Set<Integer> set1 = (Set<Integer>) graph1.getVertices();
      Set<Integer> set2 = new HashSet<Integer>();
      set2.add(1);
      set2.add(2);
      set2.add(3);

      assertTrue(set1.equals(set2));
      
      
      assertFalse(graph1.removeVertex(4));
      assertTrue(graph1.removeVertex(3));
      assertFalse(graph1.isVertex(3));

      assertTrue(graph1.addVertex(3));
      assertTrue(graph1.isVertex(3));      
      
      assertFalse(graph1.addEdgeBetweenVertices(1, 2, -1));

      assertTrue(graph1.addEdgeBetweenVertices(1, 2, 5));
      assertTrue(graph1.addEdgeBetweenVertices(3, 2, 2));
      assertTrue(graph1.addEdgeBetweenVertices(3, 2, 3));



      assertEquals(5, graph1.getEdgeCost(1, 2));
      assertEquals(3, graph1.getEdgeCost(3, 2));
      
      assertTrue(graph1.removeEdgeBetweenVertices(3, 2));
      assertEquals(-1, graph1.getEdgeCost(3, 2));

      assertTrue(graph1.addEdgeBetweenVertices(3, 2, 3));

      ArrayList<Integer> setN1 = (ArrayList<Integer>) graph1.getNeighbors(3);
      assertTrue(setN1.contains(2));

      ArrayList<Integer> setP1 = (ArrayList<Integer>) graph1.getPredecessors(2);
      assertTrue(setP1.contains(1));
      assertTrue(setP1.contains(3));

      
      assertFalse(graph1.contractEdgeBetweenVertices(0, 4));
      assertFalse(graph1.contractEdgeBetweenVertices(1, 4));
      assertFalse(graph1.contractEdgeBetweenVertices(0, 2));
      
      assertTrue(graph1.contractEdgeBetweenVertices(1, 3));


   }
   
      
}