package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * jmh
 *
 * @author jimmy
 * @date 2019-09-02
 */
@BenchmarkMode(Mode.AverageTime)//测试方法执行时间
@OutputTimeUnit(TimeUnit.MICROSECONDS)//输出结果的时间粒度为微秒
@State(Scope.Benchmark)//每个测试线程一个实例
public class JMHFirstBenchmark {
    private static Logger log = LoggerFactory.getLogger(JMHFirstBenchmark.class);

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        volatile double x = Math.PI;
    }

    @State(Scope.Thread)
    public static class ThreadState {
        volatile double x = Math.PI;
    }

    @Benchmark
    public void measureUnshared(ThreadState state) {
        state.x++;
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        log.info("measureShared:" + state.x);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JMHFirstBenchmark.class.getSimpleName())
                .warmupIterations(3)
                .measurementIterations(2).measurementTime(TimeValue.valueOf("1s"))
                .threads(10)
                .forks(2)
                .build();
        new Runner(opt).run();
    }
}
