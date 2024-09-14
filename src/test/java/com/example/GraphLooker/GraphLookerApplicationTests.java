package com.example.GraphLooker;

import com.example.GraphLooker.Model.UndirectedGraph;
import com.example.GraphLooker.Model.UndirectedGraphNode;
import com.example.GraphLooker.Util.GraphCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GraphLookerApplicationTests {

	@Test
	void GraphNodeCheck() {
		UndirectedGraphNode node1 = new UndirectedGraphNode("A");
		UndirectedGraphNode node2 = new UndirectedGraphNode("B");

		Assertions.assertEquals("A", node1.getName());
		Assertions.assertEquals("B", node2.getName());

		node1.setVisited(true);

		Assertions.assertTrue( node1.isVisited());
		Assertions.assertFalse( node2.isVisited());

		node1.setWayCost(5);
		node2.setWayCost(10);

		Assertions.assertEquals(5, node1.getWayCost());
		Assertions.assertEquals(10, node2.getWayCost());
	}

	@Test
	void UndirectedGraphCheck() {
		UndirectedGraph testGraph = new UndirectedGraph();

		testGraph.AddVertex(new UndirectedGraphNode("A"));
		testGraph.AddVertex(new UndirectedGraphNode("B"));
		testGraph.AddRib(testGraph.FindVertexByName("A"), testGraph.FindVertexByName("B"), (long)5);

		Assertions.assertEquals(Long.valueOf(5), testGraph.GetShortestWay("A","B"));
		Assertions.assertEquals(Long.valueOf(5), testGraph.GetShortestWay("B","A"));

		testGraph.AddVertex(new UndirectedGraphNode("C"));
		testGraph.AddRib(testGraph.FindVertexByName("B"), testGraph.FindVertexByName("C"), (long)8);

		Assertions.assertEquals(Long.valueOf(13), testGraph.GetShortestWay("A","C"));
		Assertions.assertEquals(Long.valueOf(13), testGraph.GetShortestWay("C","A"));
		Assertions.assertEquals(Long.valueOf(8), testGraph.GetShortestWay("B","C"));
		Assertions.assertEquals(Long.valueOf(8), testGraph.GetShortestWay("C","B"));

		testGraph.AddRib(testGraph.FindVertexByName("A"), testGraph.FindVertexByName("C"), (long)7);

		Assertions.assertEquals(Long.valueOf(7), testGraph.GetShortestWay("A","C"));
		Assertions.assertEquals(Long.valueOf(7), testGraph.GetShortestWay("C","A"));

		testGraph.AddVertex(new UndirectedGraphNode("D"));
		testGraph.AddRib(testGraph.FindVertexByName("A"), testGraph.FindVertexByName("D"), (long)4);

		Assertions.assertEquals(Long.valueOf(4), testGraph.GetShortestWay("D","A"));
		Assertions.assertEquals(Long.valueOf(4), testGraph.GetShortestWay("A","D"));
		Assertions.assertEquals(Long.valueOf(9), testGraph.GetShortestWay("D","B"));
		Assertions.assertEquals(Long.valueOf(11), testGraph.GetShortestWay("D","C"));
		Assertions.assertEquals(Long.valueOf(9), testGraph.GetShortestWay("B","D"));
		Assertions.assertEquals(Long.valueOf(11), testGraph.GetShortestWay("C","D"));

		testGraph.AddVertex(new UndirectedGraphNode("E"));
		testGraph.AddRib(testGraph.FindVertexByName("D"), testGraph.FindVertexByName("E"), (long)3);

		Assertions.assertEquals(Long.valueOf(3), testGraph.GetShortestWay("E","D"));
		Assertions.assertEquals(Long.valueOf(3), testGraph.GetShortestWay("D","E"));
		Assertions.assertEquals(Long.valueOf(7), testGraph.GetShortestWay("E","A"));
		Assertions.assertEquals(Long.valueOf(12), testGraph.GetShortestWay("E","B"));
		Assertions.assertEquals(Long.valueOf(14), testGraph.GetShortestWay("E","C"));

		testGraph.AddRib(testGraph.FindVertexByName("C"), testGraph.FindVertexByName("E"), (long)1);

		Assertions.assertEquals(Long.valueOf(3), testGraph.GetShortestWay("E","D"));
		Assertions.assertEquals(Long.valueOf(3), testGraph.GetShortestWay("D","E"));
		Assertions.assertEquals(Long.valueOf(7), testGraph.GetShortestWay("E","A"));
		Assertions.assertEquals(Long.valueOf(7), testGraph.GetShortestWay("A","E"));
		Assertions.assertEquals(Long.valueOf(9), testGraph.GetShortestWay("E","B"));
		Assertions.assertEquals(Long.valueOf(1), testGraph.GetShortestWay("E","C"));
		Assertions.assertEquals(Long.valueOf(4), testGraph.GetShortestWay("D","C"));
		Assertions.assertEquals(Long.valueOf(4), testGraph.GetShortestWay("C","D"));
		Assertions.assertEquals(Long.valueOf(9), testGraph.GetShortestWay("D","B"));
		Assertions.assertEquals(Long.valueOf(9), testGraph.GetShortestWay("B","D"));

		Assertions.assertEquals(Long.valueOf(-1), testGraph.GetShortestWay("B","Z"));
	}

	@Test
	void GraphCreatorCheck() {
		String test = """
				vertexes {
				A;
				B;C;D;
				E;
				}
				Ribs {
				A:B:18;
				A:D:5;
				B:D:5;
				B:C:12;
				C:E:5;
				D:E:30;
				B:E:5;
				}
				Route {A;E}""";
		UndirectedGraph Graph = GraphCreator.GraphFromString(test);
		String[] route = GraphCreator.GetRoute(test);

		Assertions.assertNotNull(Graph);
		Assertions.assertNotNull(Graph.FindVertexByName("A"));
		Assertions.assertNotNull(Graph.FindVertexByName("B"));
		Assertions.assertNotNull(Graph.FindVertexByName("C"));
		Assertions.assertNotNull(Graph.FindVertexByName("D"));
		Assertions.assertNotNull(Graph.FindVertexByName("E"));
		Assertions.assertNull(Graph.FindVertexByName("F"));

		Assertions.assertNotNull(route);
		Assertions.assertEquals(route.length, 2);
		Assertions.assertEquals(route[0],"A");
		Assertions.assertEquals(route[1],"E");
	}
}
