package online.proyi.normal.test.problem.largeFileSort;

import com.google.common.base.Stopwatch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 外部扩展 排序
 */
public class ExternalSort {
    private static final int CHUNK_SIZE = 1000000; // 每块处理的数字量（控制内存占用）
    private static final int NUMBERS_PER_LINE = 100; // 每行写100个数字

    public static void main(String[] args) throws IOException {
        Stopwatch stopwatch = Stopwatch.createStarted();
        String inputFile = "largeFile.txt";
        String outputFile = "sorted_numbers.txt";

        // Step 1: 分块读取和排序
        List<File> sortedChunks = splitAndSortChunks(inputFile);

        // Step 2: 多路归并排序临时文件
        mergeSortedChunks(sortedChunks, outputFile);

        System.out.println("排序完成，结果文件：" + outputFile + ", 耗时:" + stopwatch.elapsed());
    }

    // Step 1: 分块读取并排序生成临时文件
    private static List<File> splitAndSortChunks(String inputFile) throws IOException {
        List<File> chunkFiles = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        List<Integer> buffer = new ArrayList<>(CHUNK_SIZE);
        String line;
        int chunkIndex = 0;

        while ((line = reader.readLine()) != null) {
            String[] numbers = line.split(" ");
            for (String num : numbers) {
                buffer.add(Integer.parseInt(num));
                if (buffer.size() >= CHUNK_SIZE) {
                    // 排序并写入临时文件
                    chunkFiles.add(writeSortedChunk(buffer, chunkIndex++));
                    buffer.clear();
                }
            }
        }
        // 处理剩余数据
        if (!buffer.isEmpty()) {
            chunkFiles.add(writeSortedChunk(buffer, chunkIndex++));
        }
        reader.close();
        return chunkFiles;
    }

    // 将排序好的块写入临时文件
    private static File writeSortedChunk(List<Integer> buffer, int chunkIndex) throws IOException {
        Collections.sort(buffer); // 对当前块排序
        File tempFile = File.createTempFile("chunk_" + chunkIndex + "_", ".txt");
        tempFile.deleteOnExit(); // JVM 退出后删除临时文件

        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        for (int num : buffer) {
            writer.write(num + "\n");
        }
        writer.close();
        System.out.println("生成临时文件: " + tempFile.getAbsolutePath());
        return tempFile;
    }

    // Step 2: 多路归并排序临时文件
    private static void mergeSortedChunks(List<File> sortedChunks, String outputFile) throws IOException {
        PriorityQueue<ChunkReader> pq = new PriorityQueue<>(Comparator.comparingInt(ChunkReader::peek));
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

        // 初始化优先队列
        for (File chunk : sortedChunks) {
            ChunkReader reader = new ChunkReader(chunk);
            if (reader.hasNext()) {
                pq.add(reader);
            }
        }

        // 计数器，记录当前行写入了多少个数字
        int count = 0;
        // 当前行的数字集合
        StringBuilder lineBuilder = new StringBuilder();

        // 开始归并
        while (!pq.isEmpty()) {
            ChunkReader smallest = pq.poll();
            int num = smallest.pop();

            // 将当前数字添加到当前行
            lineBuilder.append(num).append(" ");
            count++;

            // 每100个数字写入一行
            if (count == NUMBERS_PER_LINE) {
                writer.write(lineBuilder.toString().trim());
                writer.newLine();
                lineBuilder.setLength(0);  // 清空当前行
                count = 0;
            }

            if (smallest.hasNext()) {
                pq.add(smallest);
            } else {
                smallest.close(); // 关闭已完成的文件
            }
        }

        // 写入剩余未满100个数字的部分
        if (count > 0) {
            writer.write(lineBuilder.toString().trim());
            writer.newLine();
        }

        writer.close();
        System.out.println("多路归并完成，生成文件: " + outputFile);
    }

    // 辅助类：用于管理临时文件的读取
    private static class ChunkReader {
        private BufferedReader reader;
        private Integer next;

        public ChunkReader(File file) throws IOException {
            this.reader = new BufferedReader(new FileReader(file));
            advance();
        }

        public boolean hasNext() {
            return next != null;
        }

        public int peek() {
            return next;
        }

        public int pop() throws IOException {
            int result = next;
            advance();
            return result;
        }

        public void close() throws IOException {
            reader.close();
        }

        private void advance() throws IOException {
            String line = reader.readLine();
            next = (line != null) ? Integer.parseInt(line) : null;
        }
    }
}
