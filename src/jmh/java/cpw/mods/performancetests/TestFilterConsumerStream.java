package cpw.mods.performancetests;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestFilterConsumerStream {
    private static List<String> strings = IntStream.range(1, 1000).mapToObj(String::valueOf).collect(Collectors.toList());

    @Benchmark
    public void testStream(Blackhole bh) {
        strings.stream().filter(s->s.length()<2).forEach(bh::consume);
    }
    @Benchmark
    public void testLoop(Blackhole bh) {
        for (String s : strings) {
            if (s.length() < 2) {
                bh.consume(s);
            }
        }
    }
}
