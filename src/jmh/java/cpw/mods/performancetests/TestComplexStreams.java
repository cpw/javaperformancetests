package cpw.mods.performancetests;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntIterator;
import it.unimi.dsi.fastutil.ints.IntList;
import org.openjdk.jmh.annotations.Benchmark;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestComplexStreams {
    public static final List<Integer> testListBoxed = IntStream.range(1, 1000).boxed().collect(Collectors.toList());
    public static final IntList testList = new IntArrayList(testListBoxed);
    public static final int[] testArray = testList.toIntArray();
    public static final int[] constants = { 2, 4 };

    @Benchmark
    public int testBoxedListStream() {
        return testListBoxed.stream().mapToInt(Integer::intValue).map(i->constants[i&1]*i).sum();
    }
    @Benchmark
    public int testBoxedListIndexedFor() {
        int sum = 0;
        for (int i2 = 0, testListSize = testListBoxed.size(); i2 < testListSize; i2++) {
            int i = testListBoxed.get(i2);
            int i1 = constants[i&1] * i;
            sum += i1;
        }
        return sum;
    }
    @Benchmark
    public int testBoxedListIteratorFor() {
        int sum = 0;
        for (Integer integer : testListBoxed) {
            int i = integer;
            int i1 = constants[i & 1] * i;
            sum += i1;
        }
        return sum;
    }
    @Benchmark
    public int testPrimitiveListStream() {
        return testList.stream().mapToInt(Integer::intValue).map(i->constants[i&1]*i).sum();
    }
    @Benchmark
    public int testPrimitiveArrayStream() {
        return Arrays.stream(testArray).map(i->constants[i&1]*i).sum();
    }
    @Benchmark
    public int testPrimitiveArrayIndexed() {
        int sum = 0;
        for (int i : testArray) {
            int i1 = constants[i & 1] * i;
            sum += i1;
        }
        return sum;
    }
    @Benchmark
    public int testPrimitiveListIndexedFor() {
        int sum = 0;
        for (int i2 = 0, testListSize = testList.size(); i2 < testListSize; i2++) {
            int i = testList.getInt(i2);
            int i1 = constants[i&1] * i;
            sum += i1;
        }
        return sum;
    }
    @Benchmark
    public int testPrimitiveListIteratorWhile() {
        int sum = 0;
        IntIterator it = testList.iterator();
        while (it.hasNext()) {
            int i = it.nextInt();
            int i1 = constants[i & 1] * i;
            sum += i1;
        }
        return sum;
    }
}
