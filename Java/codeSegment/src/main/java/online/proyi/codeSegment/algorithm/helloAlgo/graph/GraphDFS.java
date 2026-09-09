package online.proyi.codeSegment.algorithm.helloAlgo.graph;

import java.util.*;

// 深度优先遍历
public class GraphDFS {

    /**
     * 深度优先遍历(递归实现)
     * @param graph    邻接表表示的图
     * @param startVet 起始顶点
     * @return 遍历序列
     */
    public static List<Vertex> dfs(GraphAdjList graph, Vertex startVet) {
        // 顶点遍历序列
        List<Vertex> res = new ArrayList<>();
        // 哈希集合，用于记录已被访问过的顶点
        Set<Vertex> visited = new HashSet<>();

        dfsHelper(graph, startVet, visited, res);
        return res;
    }

    private static void dfsHelper(GraphAdjList graph, Vertex vet, Set<Vertex> visited, List<Vertex> res) {
        // 记录访问顶点
        res.add(vet);
        // 标记该顶点已被访问
        visited.add(vet);

        for (Vertex adjVet : graph.adjList.getOrDefault(vet, Collections.emptyList())) {
            // 遍历该顶点的所有邻接顶点
            if (!visited.contains(adjVet)) {
                // 递归访问邻接顶点
                dfsHelper(graph, adjVet, visited, res);
            }
        }
    }

    public static void main(String[] args) {
        Vertex v1 = new Vertex(1);
        Vertex v2 = new Vertex(2);
        Vertex v3 = new Vertex(3);
        Vertex v4 = new Vertex(4);
        Vertex v5 = new Vertex(5);

        Vertex[][] edges = {
                {v1, v3}, {v1, v5},
                {v3, v2}, {v2, v4},
                {v2, v5}, {v4, v5}
        };
        GraphAdjList graph = new GraphAdjList(edges);

        List<Vertex> result = dfs(graph, v1);
        System.out.print("DFS 遍历结果: ");
        for (Vertex v : result) {
            System.out.print(v.val + " ");
        }
    }
}
