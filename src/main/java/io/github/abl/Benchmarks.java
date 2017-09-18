package io.github.abl;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@OperationsPerInvocation(value = 1_000_000)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 1, timeUnit = TimeUnit.NANOSECONDS, batchSize = 1)
@Measurement(iterations = 1, timeUnit = TimeUnit.NANOSECONDS, batchSize = 1)
@Fork(value = 1)
@BenchmarkMode(Mode.AverageTime)
public class Benchmarks {

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + Benchmarks.class.getSimpleName() + ".*")
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public String StringBuilder(Data data) {
        char[][] strings = data.chars;
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] string : strings) {
            stringBuilder.append(string, 0, string.length);
        }
        return stringBuilder.toString();
    }

    @Benchmark
    public String AppendableBufferLinkedList(Data data) {
        char[][] strings = data.chars;
        IAppendableBufferList abl = AppendableBufferLinkedList.create();
        for (char[] string : strings) {
            abl.add(string, string.length);
        }
        return new String(abl.join());
    }

    @State(Scope.Benchmark)
    public static class Data {

        @Param({"2", "4"})
        int numberOfBuffers;

        @Param({"1", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048", "4096", "8192", "16384", "32768"})
        int sizeOfBuffers;

        char[][] chars;

        @Setup
        public void setup() {
            chars = new char[numberOfBuffers][];
            for (int i = 0; i < numberOfBuffers; i++) {
                chars[i] = newArray(sizeOfBuffers);
            }
        }

        private static char[] newArray(int i) {
            char[] chars = new char[i];
            while (--i >= 0) {
                chars[i] = '.';
            }
            return chars;
        }
    }
}


