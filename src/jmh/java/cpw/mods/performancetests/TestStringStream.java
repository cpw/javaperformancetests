package cpw.mods.performancetests;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestStringStream {
    private static List<String> strings = IntStream.range(1, 1000).mapToObj(String::valueOf).collect(Collectors.toList());

    private static String getlen(String s) {
        return String.valueOf(s.length());
    }

    @Benchmark
    public String testStream() {
        return strings.stream().map(TestStringStream::getlen).collect(Collectors.joining());
    }

    @Benchmark
    public String testLoop() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, stringsSize = strings.size(); i < stringsSize; i++) {
            sb.append(getlen(strings.get(i)));
        }
        return sb.toString();
    }

}
