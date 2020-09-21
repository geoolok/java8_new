package Stream;

import model.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Stream三步操作操作：
 * 1.创建Stream
 *
 * 2.中间操作
 *
 * 3.终止操作
 */
public class TestStream1 {

    @Test
    public void test(){
        //1.可以通过Collection集合系列提供的stream()或者parallelStream()
        List<String> list = new ArrayList<String>();
        Stream stream1 = list.stream();

        //2.通过Arrays中的静态方法stream()获取数组流
        Person persons[] = new Person[3];
        Stream<Person> personStream = Arrays.stream(persons);

        //3.通过Stream类中的的静态方法of()
        Stream<String> stringStream = Stream.of("a","b","c");

        //4.创建无限流
        Stream<Integer> integerStream = Stream.iterate(0, (x)->x+5);
        //迭代
        //integerStream.forEach((x) -> System.out.println(x));
        integerStream.limit(10).forEach(System.out::println);

        //生成
        Stream.generate(()->Math.random()).limit(5).forEach(System.out::println);

    }
}
