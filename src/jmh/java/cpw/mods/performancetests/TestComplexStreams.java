package cpw.mods.performancetests;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestComplexStreams {
    public static List<Integer> testList = IntStream.range(1, 1000).boxed().collect(Collectors.toList());

    @Benchmark
    public int testExplicitStream() {
        int[] constants = { 2, 4 };
        return testList.stream().mapToInt(Integer::intValue).map(i->constants[i&1]*i).sum();
    }
    @Benchmark
    public int testIndexedFor() {
        int[] constants = { 2, 4 };
        int sum = 0;
        for (int i2 = 0, testListSize = testList.size(); i2 < testListSize; i2++) {
            int i = testList.get(i2).intValue();
            int i1 = constants[i&1] * i;
            sum += i1;
        }
        return sum;
    }
}
