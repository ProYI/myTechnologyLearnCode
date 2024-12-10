package online.proyi.normal.test.problem.largeFileSort;

import com.google.common.collect.Sets;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class CreateLargeFile {
    public static void main(String[] args) throws IOException {
        // 文件路径
        String fileName = "./largeFile.txt";
        // 文件大小大约为10GB
        long targetSize = 10L * 1024 * 1024 * 1024; // 10GB
        // 每行写入的数字个数
        int numbersPerLine = 100;
        // 随机数的范围
        int min = 1, max = 10000;

        // 计算需要多少行
        // 每个数字占用4字节，计算每行的字节数
        int bytesPerNumber = 4;  // 每个整数占4字节
        int bytesPerLine = numbersPerLine * bytesPerNumber;  // 每行的字节数
        int bytesPerNewline = 100;  // 每行末尾的换行符占1字节 + 99个空格

        // 每行总大小（包括换行符）
        int bytesPerFullLine = bytesPerLine + bytesPerNewline;

        // 计算需要多少行来达到目标文件大小
        long linesNeeded = targetSize / bytesPerFullLine;
        System.out.println("总行数:" + linesNeeded);

        // 创建随机数生成器
        Random rand = new Random();

        // 写文件
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

        // 数字列表，用于存储随机生成的数字
        Set<Integer> numbers = Sets.newHashSetWithExpectedSize(100);

        // 生成随机数并添加到列表中
        for (long i = 0; i < linesNeeded; i++) {
            if (i % 10000 == 0) {
                System.out.println("当前为: " + i + "行");
            }

            // 生成当前批次的随机数
            numbers.clear();  // 清空列表
            for (int j = 0; j < 200; j++) {
                if (numbers.size() == 100) {
                    break;
                }
                numbers.add(rand.nextInt(max - min + 1) + min);
            }

            // 写入每行100个数字
            StringBuilder line = new StringBuilder();
            List list = new ArrayList(numbers);
            for (int k = 0; k < numbersPerLine; k++) {
                line.append(list.get(k)).append(" ");
            }
            writer.write(line.toString().trim());
            writer.newLine();
        }

        writer.close();
        System.out.println("文件生成完成: " + fileName);
    }
}
