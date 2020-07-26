package cpw.mods.performancetests;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntList;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestStreams {
    public static final List<Integer> boxedIntList = IntStream.range(1, 1000).boxed().collect(Collectors.toList());
    public static final Integer[] boxedIntArray = boxedIntList.toArray(new Integer[0]);
    public static final IntList intList = new IntArrayList(boxedIntList);
    public static final int[] intArray = intList.toIntArray();
    public static final Integer[] tst = IntStream.range(1, 1000).boxed().toArray(Integer[]::new);

    @Benchmark
    public int testBoxedListStream(Blackhole bh) {
        return boxedIntList.stream().mapToInt(Integer::intValue).sum();
    }
    @Benchmark
    public int testBoxedArrayStream(Blackhole bh) {
        return Arrays.stream(boxedIntArray).mapToInt(Integer::intValue).sum();
    }
    @Benchmark
    public int testBoxedListIteratorFor(Blackhole bh) {
        int sum = 0;
        for (Integer i : boxedIntList) {
            sum += i;
        }
        return sum;
    }
    @Benchmark
    public int testBoxedListIndexedFor(Blackhole bh) {
        int sum = 0;
        for (int i1 = 0, testListSize = boxedIntList.size(); i1 < testListSize; i1++) {
            sum += boxedIntList.get(i1);
        }
        return sum;
    }
    @Benchmark
    public int testBoxedArrayWhile(Blackhole bh) {
        int sum = 0;
        int i1 = 0, testListSize = boxedIntArray.length;
        while (i1 < testListSize) {
            sum += boxedIntArray[i1];
            i1++;
        }
        return sum;
    }

    @Benchmark
    public int testPrimitiveListStream(Blackhole bh) {
        return intList.stream().mapToInt(Integer::intValue).sum();
    }
    @Benchmark
    public int testPrimitiveArrayStream(Blackhole bh) {
        return Arrays.stream(intArray).sum();
    }
    @Benchmark
    public int testPrimitiveListIteratorWhile(Blackhole bh) {
        int sum = 0;
        IntIterator it = intList.iterator();
        while (it.hasNext()) {
            sum += it.nextInt();
        }
        return sum;
    }
    @Benchmark
    public int testPrimitiveListIndexedFor(Blackhole bh) {
        int sum = 0;
        for (int i1 = 0, testListSize = intList.size(); i1 < testListSize; i1++) {
            sum += intList.getInt(i1);
        }
        return sum;
    }
    @Benchmark
    public int testPrimitiveArrayIteratorFor(Blackhole bh) {
        int sum = 0;
        for (int i : intArray) {
            sum += i;
        }
        return sum;
    }
}