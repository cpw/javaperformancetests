package cpw.mods.performancetests;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestConsumerStream {
    public static final List<Integer> testList = IntStream.range(1, 1000).boxed().collect(Collectors.toList());

    public static final int[] ints = testList.stream().mapToInt(Integer::intValue).toArray();

    @Benchmark
    public int testBoxedListStream() {
        return testList.stream().mapToInt(Integer::intValue).map(TestConsumerStream::multiply).sum();
    }

    @Benchmark
    public int testBoxedListIndexedFor() {
        int sum = 0;
        for (int i = 0, testListSize = testList.size(); i < testListSize; i++) {
            sum += multiply(testList.get(i));
        }
        return sum;
    }

    @Benchmark
    public int testBoxedListIteratorFor() {
        int sum = 0;
        for (Integer anInt : testList) {
            sum += multiply(anInt);
        }
        return sum;
    }

    @Benchmark
    public int testPrimitiveArrayStream() {
        return Arrays.stream(ints).map(TestConsumerStream::multiply).sum();
    }

    @Benchmark
    public int testPrimitiveArrayIndexedFor() {
        int sum = 0;
        for (int i = 0, testListSize = ints.length; i < testListSize; i++) {
            sum += multiply(ints[i]);
        }
        return sum;
    }

    @Benchmark
    public int testPrimitiveArrayIteratorFor() {
        int sum = 0;
        for (int anInt : ints) {
            sum += multiply(anInt);
        }
        return sum;
    }

    public static int multiply(int val) {
        return val * 2;
    }
}
