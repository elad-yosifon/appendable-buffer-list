import java.util.concurrent.TimeUnit;

public class Runner {

    private static final boolean CSV_MODE = true;

    private static final int NUMBER_OF_ITERATIONS_PER_TEST = 1_000_000;

    private static void _run(char[][] strings) {
        abl(strings);
//        sbuilder(strings);
    }

    private static void sbuilder(char[][] strings) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char[] string : strings) {
            stringBuilder.append(string, 0, string.length);
        }
        String s1 = stringBuilder.toString();
    }

    private static void abl(char[][] strings) {
        IAppendableBufferList abl = AppendableBufferLinkedList.create();
        for (char[] string : strings) {
            abl.add(string, string.length);
        }
        String out = new String(abl.join());
    }

    private static final int[][] setup = {

            // number of buffers, size of buffers
            {2, 1},
            {2, 1 << 1},
            {2, 1 << 2},
            {2, 1 << 3},
            {2, 1 << 4},
            {2, 1 << 1},
            {2, 1 << 5},
            {2, 1 << 6},
            {2, 1 << 7},
            {2, 1 << 8},
            {2, 1 << 9},
            {2, 1 << 10},
            {2, 1 << 11},
            {2, 1 << 12},
            {2, 1 << 13},
            {2, 1 << 14},
            {2, 1 << 15},

            {1<<2, 1},
            {1<<2, 1 << 1},
            {1<<2, 1 << 2},
            {1<<2, 1 << 3},
            {1<<2, 1 << 4},
            {1<<2, 1 << 1},
            {1<<2, 1 << 5},
            {1<<2, 1 << 6},
            {1<<2, 1 << 7},
            {1<<2, 1 << 8},
            {1<<2, 1 << 9},
            {1<<2, 1 << 10},
            {1<<2, 1 << 11},
            {1<<2, 1 << 12},
            {1<<2, 1 << 13},
            {1<<2, 1 << 14},
            {1<<2, 1 << 15},

    };

    public static void main(String[] args) {

        if (CSV_MODE) {
            System.out.println("number_of_buffers,size_of_buffer,avg_execution_time_in_nano");
        }

        for (int[] params : setup) {
            int numberOfBuffers = params[0];
            int sizeOfBuffers = params[1];
            if (CSV_MODE) {
                long avg = _runWithParams(numberOfBuffers, sizeOfBuffers);
                System.out.println(String.format("%d,%d,%d", numberOfBuffers, sizeOfBuffers, avg));
            } else {
                System.out.println(String.format("running %d tests of: %d buffers, each with sizeof %d ...", NUMBER_OF_ITERATIONS_PER_TEST, numberOfBuffers, sizeOfBuffers));
                long avg = _runWithParams(numberOfBuffers, sizeOfBuffers);
                System.out.println(String.format("took: %d \r\n", TimeUnit.NANOSECONDS.toNanos(avg)));
            }

        }
    }

    private static long _runWithParams(int numberOfBuffers, int sizeOfBuffers) {

        char[][] strings = setupBenchmark(numberOfBuffers, sizeOfBuffers);

        if (!CSV_MODE) {
            System.out.println("starting warmup...");
        }
        int warmup = NUMBER_OF_ITERATIONS_PER_TEST;
        while (warmup-- >= 0) {
            _run(strings);
        }
        if (!CSV_MODE) {
            System.out.println("finished warmup.");
        }
        int numberOfExecutions = 0;
        long summedExecutionTime = 0;

        if (!CSV_MODE) {
            System.out.println("starting benchmark...");
        }
        int iter = NUMBER_OF_ITERATIONS_PER_TEST;
        while (iter-- >= 0) {
            long s = System.nanoTime();
            _run(strings);
            long e = System.nanoTime();

            numberOfExecutions++;
            summedExecutionTime += (e - s);
        }
        if (!CSV_MODE) {
            System.out.println("finished benchmark.");
        }
        return summedExecutionTime / numberOfExecutions;
    }

    private static char[][] setupBenchmark(int numberOfBuffers, int sizeOfBuffers) {
        char[][] strings = new char[numberOfBuffers][];
        for (int i = 0; i < numberOfBuffers; i++) {
            strings[i] = newSizedByteArray(sizeOfBuffers);
        }
        return strings;
    }

    private static char[] newSizedByteArray(int i) {
        char[] bytes = new char[i];
        while (--i >= 0) {
            bytes[i] = '.';
        }
        return bytes;
    }
}
