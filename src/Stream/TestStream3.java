package Stream;

import model.Person;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
     * 收集
     * collect--将流转换为其他形式，接收一个collector接口的实现，用于将Stream中的元素汇总
     */
    //分组
    @Test
    public void test6(){
        //按照Status分组
        Map<Person.Status,List<Person>> statusListMap = personList.stream().collect(Collectors.groupingBy(Person::getStatus));
        System.out.println(statusListMap);
        System.out.println("---------------------");
        //多级分组
        Map<Person.Status,Map<String,List<Person>>> statusMapMap =  personList.stream().collect(Collectors.groupingBy(Person::getStatus, Collectors.groupingBy((person)->{
            if(person.getAge() <20){
                return "小于20";
            }else {
                return "大于20";
            }
        })));
        System.out.println(statusMapMap);
    }

    @Test
    public void test5(){
        Long count = personList.stream().collect(Collectors.counting());
        System.out.println(count);
        System.out.println("---------------------");
        Integer sumAge = personList.stream().collect(Collectors.summingInt((person->person.getAge())));
        System.out.println(sumAge);
        System.out.println("---------------------");
        Optional op = personList.stream().collect(Collectors.maxBy((p1, p2)->Integer.compare(p1.getAge(),p2.getAge())));
        System.out.println(op.get());
    }

    @Test
    public void test4(){
        //将person list 中的所有姓名提取出来
        List<String> stringList =  personList.stream().map(Person::getName)
                .collect(Collectors.toList());
        stringList.forEach(System.out::println);
        System.out.println("-----------------------");
        //放入Set集合中
        Set<String> stringSet = personList.stream().map(Person::getName)
                .collect(Collectors.toSet());
        stringSet.forEach(System.out::println);
        System.out.println("-----------------------");
        //放入LinkedList
        List<String> linkedList =  personList.stream().map(Person::getName).collect(Collectors.toCollection(LinkedList::new));
        linkedList.forEach(System.out::println);

    }

    /**
     * 归约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator)--可以将流中的元素反复结合，得到一个值
     */
    @Test
    public void test3(){
        List<Integer> integerList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //计算list所有元素的和
        //这个reduce有起始值，不可能为空，所以返回的不是Optional
        Integer integer1 = integerList.stream().reduce(0,(x,y)->x+y);
        System.out.println(integer1);

        System.out.println("------------");
        //这个reduce没有起始值，可能为空，所以返回Optional
        Optional<Integer> optionalInteger = integerList.stream().reduce(Integer::sum);
        System.out.println(optionalInteger.get());

        //计算person列表中所有人的年龄总和
        Optional<Integer> optionalInteger1 = personList.stream()
                .map(Person::getAge)
                .reduce(Integer::sum);
        System.out.println(optionalInteger1.get());
    }


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
