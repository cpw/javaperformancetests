package cpw.mods.performancetests;

import org.openjdk.jmh.annotations.Benchmark;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class TestZipfsExists {
    private static final Path tmpJar;
    private static final FileSystem fs;

    static {
        try {
            tmpJar = Files.createTempFile("fmltest", "mod.jar");
            try (InputStream stream = TestZipfsExists.class.getResourceAsStream("/mod.jar")) {
                Files.copy(stream, tmpJar, StandardCopyOption.REPLACE_EXISTING);
            }
            fs = FileSystems.newFileSystem(tmpJar, TestZipfsExists.class.getClassLoader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Benchmark
    public void benchmarkFileExists() {
        if (!Files.exists(fs.getPath("META-INF/mods.toml")))
            throw new RuntimeException("Expected mods.toml to be present!");
    }

    @Benchmark
    public void benchmarkFileNotExists() {
        if (Files.exists(fs.getPath("META-INF/coremods.json")))
            throw new RuntimeException("Expected coremods.json to be missing!");
    }
}
