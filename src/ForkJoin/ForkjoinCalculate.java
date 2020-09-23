package ForkJoin;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkjoinCalculate extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    public ForkjoinCalculate(Long start, Long end) {
        this.start = start;
        this.end = end;
    }
    private static Long THRESHOLD=50000L;

    @Override
    protected Long compute() {
        Long len = end-start;
        if (len<=THRESHOLD){
            long sum = 0L;
            for(Long i = start;i<=end;i++){
                sum +=1 ;
            }
            return sum;
        }else{
            Long middle = (end-start)/2;

            ForkjoinCalculate left = new ForkjoinCalculate(start,middle);
            left.fork(); //拆分子任务，并压入线程队列

            ForkjoinCalculate right = new ForkjoinCalculate(middle+1,end);
            right.fork();//拆分子任务，并压入线程队列

            return left.join()+right.join();
        }
    }
}
