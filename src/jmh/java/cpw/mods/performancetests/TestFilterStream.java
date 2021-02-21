package cpw.mods.performancetests;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestFilterStream {
    private static List<String> strings = IntStream.range(1, 1000).mapToObj(String::valueOf).collect(Collectors.toList());

    @Benchmark
    public long testStream() {
        //return strings.stream().takeWhile(s->s.length()<2).count();
        return 1;
    }
    @Benchmark
    public long testLoop() {
        long count = 0L;
        for (String s : strings) {
            if (s.length() < 2) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }
}
