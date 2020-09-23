package ForkJoin;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class TestForkJoin {
    //ForkJoin框架  小数据运算最慢，大数据运算一般般
    @Test
    public void test(){
        Instant startTime = Instant.now();
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkjoinCalculate(1L,100000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant endTime = Instant.now();

        System.out.println("耗费时间：" + Duration.between(startTime,endTime).toMillis());
    }

    //普通for  小数据运算最快
    @Test
    public void test2(){
        Instant startTime = Instant.now();
        long sum = 0L;
        for (long i = 1L ; i<=100000L ;i++){
            sum +=i;
        }
        System.out.println(sum);
        Instant endTime = Instant.now();

        System.out.println("耗费时间：" + Duration.between(startTime,endTime).toMillis());
    }

    //java8并行流，大数据运算最快
    @Test
    public void test3(){
        Instant startTime = Instant.now();
        long sum = LongStream.rangeClosed(0,100000L)
                .parallel()  //并行流
                .reduce(0,Long::sum);

        System.out.println(sum);
        Instant endTime = Instant.now();

        System.out.println("耗费时间：" + Duration.between(startTime,endTime).toMillis());
    }
}
