package cpw.mods.performancetests;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestConsumerStream {
    public static List<Integer> testList = IntStream.range(1, 1000).boxed().collect(Collectors.toList());
    public static int[] ints = testList.stream().mapToInt(Integer::intValue).toArray();
    @Benchmark
    public int testStream() {
        return Arrays.stream(ints).map(this::multiply).sum();
//        return testList.stream().mapToInt(Integer::intValue).map(this::multiply).sum();
    }

    @Benchmark
    public int testFor() {
        int sum = 0;
        for (int i = 0, testListSize = ints.length; i < testListSize; i++) {
            sum += multiply(ints[i]);
        }
        return sum;
    }

    public int multiply(int val) {
        return val * 2;
    }
}
