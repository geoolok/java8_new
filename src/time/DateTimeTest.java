package time;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

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
        Instant instant1 = Instant.now(); //默认获取的是UTC时区，跟北京差八小时
        System.out.println(instant1);

        //添加偏移量,我们跟标准时间差八个时区，所以加八个小时
        OffsetDateTime offsetDateTime = instant1.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        //获取毫秒数
        Long millis1 = instant1.toEpochMilli();
        long millis2 = offsetDateTime.toInstant().toEpochMilli();
        //很奇怪，两个毫秒数一模一样
        System.out.println(millis1);
        System.out.println(millis2);

        //根据时间偏移量创建新的时间，基准是1970年
        Instant instant2 = Instant.ofEpochMilli(millis2);//偏移毫秒
        Instant instant3 = Instant.ofEpochSecond(10); //偏移分钟
        System.out.println(instant2);
        System.out.println(instant3);
    }

    //Duration 计算两个时间之间的差值
    //Period 计算两个日期之间的差值
    @Test
    public void test3() throws InterruptedException {
        Instant instantBegin = Instant.now();
        Thread.sleep(1000);//休眠一秒
        Instant instantEnd = Instant.now();

        Duration duration = Duration.between(instantBegin,instantEnd);
        System.out.println(duration.toMillis()); //获取差值的毫秒数

        //获取两个LocalTime作比较，LocalDateTime等都可作比较
        LocalTime localTimeBegin = LocalTime.now();
        System.out.println(localTimeBegin);
        Thread.sleep(1000);
        LocalTime localTimeEnd = LocalTime.now();

        Duration duration1 = Duration.between(localTimeBegin,localTimeEnd);
        System.out.println(duration1.toMillis());
    }

    @Test
    public void test4(){
        //获取日期差值
        LocalDate localDateBegin = LocalDate.of(2019,3,5);
        LocalDate localDateEnd = LocalDate.now();

        Period period = Period.between(localDateBegin,localDateEnd);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    /**
     * TemporalAdjuster:接口，时间矫正器
     * TemporalAdjusters:TemporalAdjuster的实现类
     * 也可以手动实现TemporalAdjuster接口来提供时间矫正功能
     */
    @Test
    public void test5(){
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        //获取localDate对象年月的20号
        LocalDate localDate1 = localDate.withDayOfMonth(20);
        System.out.println(localDate1);
        //获取localDate对象当前时间为准的下一个礼拜五
        LocalDate localDate2 = localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println(localDate2);
        System.out.println();
    }

    //时间格式化
    @Test
    public void test6(){
        //JDK提供的格式化样式
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime localDateTime = LocalDateTime.now();
        String timeStr = localDateTime.format(dateTimeFormatter);
        System.out.println(timeStr);

        //自定义格式化样式
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timeStr1 = localDateTime.format(dateTimeFormatter1);
        System.out.println(timeStr1);
    }
}
