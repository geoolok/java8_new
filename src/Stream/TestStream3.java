package Stream;

import model.Person;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestStream3 {
    List<Person> personList = Arrays.asList(
            new Person(32,"Liming", Person.Status.BUSY),
            new Person(18,"王麻子", Person.Status.FREE),
            new Person(45,"马六", Person.Status.BUSY),
            new Person(28,"赵四", Person.Status.VOCATION),
            new Person(13,"刘屋", Person.Status.FREE),
            new Person(33,"赵本山", Person.Status.FREE),
            new Person(33,"赵四", Person.Status.BUSY)
    );
    /**
     * 查找与匹配
     * allMatch:检查是否匹配所有元素
     * anyMatch:检查是否至少匹配一个元素
     * noneMatch:检查是否没有匹配元素
     * findFirst:返回第一个元素
     * findAny:返回当前流的任意元素
     * count:返回流中元素总个数
     * max:返回流中最大值
     * min:返回流中最小值
     */
    @Test
    public void test2(){
        long count = personList.stream().count();
        System.out.println(count);

        //获取年龄最大的
        Optional<Person> personOptional =  personList.stream()
                .max((p1,p2) -> Integer.compare(p1.getAge(),p2.getAge()));
        System.out.println(personOptional.get().getAge());

        //获取年龄最小的
        Optional<Person> personOptional1 =  personList.stream()
                .min((p1,p2) -> Integer.compare(p1.getAge(),p2.getAge()));
        System.out.println(personOptional1.get().getAge());
    }

    @Test
    public void test1(){
        //allMatch
        boolean status1 = personList.stream()
                .allMatch((x) -> x.getStatus().equals(Person.Status.BUSY));
        System.out.println(status1);

        //anyMatch
        boolean status2 = personList.stream()
                .anyMatch((x) -> x.getStatus().equals(Person.Status.BUSY));
        System.out.println(status2);

        //noneMatch
        boolean status3 = personList.stream()
                .noneMatch((x) -> x.getStatus().equals(Person.Status.BUSY));
        System.out.println(status3);

        //findFirst
        Optional<Person> personOptional = personList.stream().findFirst();
        System.out.println(personOptional.get());

        //findAny 串行(stream)的情况下一般只返回第一个  并行(parallelStream)的情况返回不确定
        Optional<Person> personOptional1 =  personList.parallelStream()
                .filter((p) -> p.getStatus().equals(Person.Status.FREE))
                .findAny();
        System.out.println(personOptional1.get());
    }
}
