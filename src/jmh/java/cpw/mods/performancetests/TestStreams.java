package cpw.mods.performancetests;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStreams {
    public static List<Integer> testList = IntStream.range(1, 1000).boxed().collect(Collectors.toList());
    public static Integer[] arrayList = testList.toArray(new Integer[0]);
    public static Integer[] tst = IntStream.range(1, 1000).boxed().toArray(Integer[]::new);

    @Benchmark
    public int testExplicitStream(Blackhole bh) {
        return testList.stream().mapToInt(Integer::intValue).sum();
    }
    @Benchmark
    public int testArrayStream(Blackhole bh) {
        return Arrays.stream(arrayList).mapToInt(Integer::intValue).sum();
    }
    @Benchmark
    public int testIteratorFor(Blackhole bh) {
        int sum = 0;
        for (Integer i : testList) {
            sum += i.intValue();
        }
        return sum;
    }
    @Benchmark
    public int testIndexedFor(Blackhole bh) {
        int sum = 0;
        for (int i1 = 0, testListSize = testList.size(); i1 < testListSize; i1++) {
            sum += testList.get(i1).intValue();
        }
        return sum;
    }
    @Benchmark
    public int testWhileArray(Blackhole bh) {
        int sum = 0;
        int i1 = 0, testListSize = arrayList.length;
        while (i1 < testListSize) {
            sum += arrayList[i1].intValue();
            i1++;
        }
        return sum;
    }
}