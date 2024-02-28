package modernjavainaction.chapter03.lambda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class lambdaFunction {
    private final static String FILE_URL = "src/modernjavainaction/chapter03/lambda/data.txt";

    public static void main(String[] args) throws IOException{
        String result = processFile1();
        System.out.println("---적용 전---");
        System.out.println(result);

        System.out.println("---적용 후---");

        //String oneLine = processFile2((BufferedReader b) -> b.readLine());
        //System.out.println(oneLine);

        String lines = processFile2((BufferedReader b) -> b.readLine() + "\n" + b.readLine() + "\n람다 활용 : 실행 어라운드 패턴");
        System.out.println(lines);
    }

    public static String processFile1() throws IOException {
        try (BufferedReader br =
                new BufferedReader(new FileReader(FILE_URL))) {
            return br.readLine();
        }
    }

    public static String processFile2(BufferedReaderProcessor b) throws IOException {
        try (
            BufferedReader br =
                    new BufferedReader(new FileReader(FILE_URL))) {
            return b.process(br);
        }
    }


    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;
    }
}

