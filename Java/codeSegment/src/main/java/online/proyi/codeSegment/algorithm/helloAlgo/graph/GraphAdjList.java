package online.proyi.codeSegment.algorithm.helloAlgo.graph;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

// 基于邻接表实现的无向图
public class GraphAdjList {
    // 邻接表，key：顶点，value：该顶点的所有邻接顶点
    Map<Vertex, List<Vertex>> adjList;

    /* 构造方法 */
    public GraphAdjList(Vertex[][] edges) {
        this.adjList = new HashMap<>();
        // 添加所有顶点和边
        for (Vertex[] edge : edges) {
            addVertex(edge[0]);
            addVertex(edge[1]);
            addEdge(edge[0], edge[1]);
        }
    }

    /* 获取顶点数量 */
    public int size() {
        return adjList.size();
    }

    /* 添加边 */
    public void addEdge(Vertex vet1, Vertex vet2) {
        if (!adjList.containsKey(vet1) || !adjList.containsKey(vet2) || vet1 == vet2)
            throw new IllegalArgumentException();
        // 添加边 vet1 - vet2
        adjList.get(vet1).add(vet2);
        adjList.get(vet2).add(vet1);
    }

    /* 删除边 */
    public void removeEdge(Vertex vet1, Vertex vet2) {
        if (!adjList.containsKey(vet1) || !adjList.containsKey(vet2) || vet1 == vet2)
            throw new IllegalArgumentException();
        // 删除边 vet1 - vet2
        adjList.get(vet1).remove(vet2);
        adjList.get(vet2).remove(vet1);
    }

    /* 添加顶点 */
    public void addVertex(Vertex vet) {
        if (adjList.containsKey(vet))
            return;
        // 在邻接表中添加一个新链表
        adjList.put(vet, new ArrayList<>());
    }

    /* 删除顶点 */
    public void removeVertex(Vertex vet) {
        if (!adjList.containsKey(vet))
            throw new IllegalArgumentException();
        // 在邻接表中删除顶点 vet 对应的链表
        adjList.remove(vet);
        // 遍历其他顶点的链表，删除所有包含 vet 的边
        for (List<Vertex> list : adjList.values()) {
            list.remove(vet);
        }
    }

    /* 打印邻接表 */
    public void print() {
        System.out.println(" 邻接表 =");
        for (Map.Entry<Vertex, List<Vertex>> pair : adjList.entrySet()) {
            List<Integer> tmp = new ArrayList<>();
            for (Vertex vertex : pair.getValue())
                tmp.add(vertex.val);
            System.out.println(pair.getKey().val + ": " + tmp + ",");
        }
    }

    static void main() {
        Vertex vertex1 = new Vertex(1);
        Vertex vertex3 = new Vertex(3);
        Vertex vertex2 = new Vertex(2);
        Vertex vertex5 = new Vertex(5);
        Vertex vertex4 = new Vertex(4);

        Vertex[][] edges = new Vertex[][]{
                {vertex1, vertex3}, // 代表 1-3 连通
                {vertex1, vertex5}, // 代表 1-5 连通
                {vertex3, vertex2}, // 代表 3-2 连通
                {vertex2, vertex4}, // 代表 2-4 连通
                {vertex2, vertex5}, // 代表 2-5 连通
                {vertex4, vertex5}  // 代表 4-5 连通
        };
        GraphAdjList graph = new GraphAdjList(edges);
        graph.print();
    }
}

class Vertex {
    // 获取顶点标识
    // 顶点
    final Integer val;

    // 构造函数
    public Vertex(Integer val) {
        this.val = val;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(val, vertex.val);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val);
    }

    // 方便打印调试
    @Override
    public String toString() {
        return val.toString();
    }
}
