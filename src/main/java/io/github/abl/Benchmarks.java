package io.github.abl;

import io.github.abl.impl.AppendableBufferList;
import io.github.ifc.IAppendableBufferList;
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
        String[] strings = data.strings;
        StringBuilder stringBuilder = new StringBuilder();
        for (String string : strings) {
            stringBuilder.append(string);
        }
        return stringBuilder.toString();
    }

    @Benchmark
    public String AppendableBufferLinkedList(Data data) {
        String[] strings = data.strings;
        IAppendableBufferList abl = AppendableBufferList.create();
        for (String string : strings) {
            abl.add(string);
        }
        return abl.joinAsString();
    }

    @Benchmark
    public String AppendableBufferArrayList(Data data) {
        String[] strings = data.strings;
        IAppendableBufferList abl = AppendableBufferList.createArrayList();
        for (String string : strings) {
            abl.add(string);
        }
        return abl.joinAsString();
    }

    @Benchmark
    public String AppendableBufferArrayDeque(Data data) {
        String[] strings = data.strings;
        IAppendableBufferList abl = AppendableBufferList.createArrayDeque();
        for (String string : strings) {
            abl.add(string);
        }
        return abl.joinAsString();
    }

    @State(Scope.Benchmark)
    public static class Data {

        @Param({"2", "4"})
        int numberOfBuffers;

        @Param({"1", "2", "4", "8", "16", "32", "64", "128", "256", "512", "1024", "2048", "4096", "8192", "16384", "32768"})
        int sizeOfBuffers;

        String[] strings;

        private static String newString(int i) {
            byte[] bytes = new byte[i];
            while (--i >= 0) {
                bytes[i] = '.';
            }
            return new String(bytes);
        }

        @Setup
        public void setup() {
            strings = new String[numberOfBuffers];
            for (int i = 0; i < numberOfBuffers; i++) {
                strings[i] = newString(sizeOfBuffers);
            }
        }
    }
}


