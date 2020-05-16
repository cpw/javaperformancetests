package cpw.mods.performancetests;

import org.openjdk.jmh.annotations.Benchmark;

public class TestStrings {
    public static String TST = new String("teststringteststringteststringteststringteststringteststringteststringteststringteststringteststringteststringteststring1");
    public static String TRG = new String("teststringteststringteststringteststringteststringteststringteststringteststringteststringteststringteststringteststring2");
    public static char[] tstarray = TST.toCharArray();
    public static char[] trgarray = TRG.toCharArray();
    @Benchmark
    public boolean testStringEquals() {
        return TRG.equals(TST);
    }

    @Benchmark
    public boolean testDerpyStringEquals() {
        for (int i = 0; i < trgarray.length; i++) {
            if (tstarray[i] != trgarray[i])
                return false;
        }
        return true;
    }
}
