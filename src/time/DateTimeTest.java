package time;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;

public class DateTimeTest {
    //LocalDate LocalTime LocalDateTime
    //LocalDateTime包含前两个，这里只测试LocalDateTime
    @Test
    public void test1(){
        //获取当前系统时间
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);

        //给定时间信息，产生对象
        LocalDateTime dateTime1 = LocalDateTime.of(2019,3,15,15,23,30);
        System.out.println(dateTime1);

        //在已有时间对象上加年月日时分秒等操作
        LocalDateTime dateTime2 = dateTime.plusYears(2);//加两年
        System.out.println(dateTime2);

        //在已有时间对象上减去年月日时分秒等操作
        LocalDateTime dateTime3 = dateTime.minusMonths(3);//减去三个月
        System.out.println(dateTime3);

        //获取已有时间对象的时分秒等信息
        System.out.println(dateTime.getYear());
        System.out.println(dateTime.getMonth().getValue());
        System.out.println(dateTime.getMonthValue());//跟上一句一样
        System.out.println(dateTime.getDayOfMonth());
        System.out.println(dateTime.getHour());
    }
    //Instant,时间戳（UTC:unit元年1970年1月1日 0时0分0秒到指定时间的毫秒数）
    @Test
    public void test2(){
        Instant instant1 = Instant.now();
        
    }
}
