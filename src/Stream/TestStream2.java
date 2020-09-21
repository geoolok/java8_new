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
public class TestStream2 {
    List<Person> personList = Arrays.asList(
            new Person(32,"Liming"),
            new Person(18,"王麻子"),
            new Person(45,"马六"),
            new Person(28,"赵四"),
            new Person(28,"刘屋"),
            new Person(28,"赵四")
    );
    //中间操作
    /**
     * 排序
     * 1.自然排序(Comparable) sorted()
     * 2.定制排序(Comparator) sorted(Comparator com)
     */
    @Test
    public void test7(){
        //自然排序
        List<String> stringList = Arrays.asList("ee","dd","bbb","aaa","ccc");
        stringList.stream()
                .sorted().forEach(System.out::println);

        //定制排序
        //先按照年龄排序，再按照名字排序
        personList.stream().sorted((p1,p2) -> {
            if(p1.getAge() == p2.getAge()){
                return p1.getName().compareTo(p2.getName());
            }
            return p1.getAge()-p2.getAge();
        })
        .forEach(System.out::println);
    }


    /**
     * 映射：
     * map--接收Lambda，将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素
     * flastMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连城一个流
     */
    @Test
    public void test5(){
        //map映射
        List<String> stringList = Arrays.asList("aaa","bbbb","cccc");
        stringList.stream()
                .map((x)->x.toUpperCase())
                .forEach(System.out::println);

        System.out.println("-----------------");
        personList.stream()
                .map(Person::getName)
                .forEach(System.out::println);
    }

    @Test
    public void test6(){
        //利用流将多个字符串转换成字符流
        List<String> stringList = Arrays.asList("aaa","bbbb","cccc");
        Stream<Stream<Character>> stream = stringList.stream()
                .map(TestStream2::filterCharacter);

        stream.forEach((x) -> x.forEach(System.out::println));

        //用flastMap实现,简化
        //flastMap--接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连城一个流
        Stream<Character> flatStream = stringList.stream()
                .flatMap(TestStream2::filterCharacter);
        flatStream.forEach(System.out::println);

    }

    //将字符串转换为字符流
    public static Stream<Character> filterCharacter(String str){
        List<Character> characterList = new ArrayList<>();
        for (Character ch:str.toCharArray()) {
            characterList.add(ch);
        }
        return characterList.stream();
    }


    /**
     * 筛选与切片
     * filter--接收Lambda，从流中排除某些元素。
     * limit--截断流，获取元素的个数不超过给定的个数
     * skip(n)--跳过元素，返回一个排除前N个元素的流，若流中本来不足n个，则返回空流。与limit(n)互补
     * distinct--筛选，通过流所生成元素的hanshCode()和equals()去除重复元素
     */

    //内部迭代，迭代操作由Stream Api完成相应操作
    @Test
    public void test1(){
        //中间操作，不会执行任何操作
        //filter
        Stream personStream = personList.stream()
                .filter((person) -> {
                    System.out.println("中间操作，只有在有终止操作才会执行中间操作，所谓的延迟执行,即“惰性求值”");
                    return person.getAge()>30;
                });

        //终止操作，实验时，可以注释下面代码比较下部注释的执行结果
        //一次性执行所有内容，即“惰性求值”
        personStream.forEach(System.out::println);
    }

    @Test
    public void test2(){
        //limit
        Stream stream = personList.stream()
                .limit(3);

        stream.forEach(System.out::println);
        System.out.println("-------分割线------");
        //skip  跳过几个元素
        Stream skipStream = personList.stream()
                .skip(2);
        skipStream.forEach(System.out::println);
    }

    @Test
    public void test3(){
        //演示短路
        Stream stream = personList.stream()
                .filter((person) -> {
                    System.out.println("limit里面是几，找到几个符合数据立即停止迭代，这就是短路");
                    return person.getAge()>21;
                })
                .limit(2);

        stream.forEach(System.out::println);
    }

    //distinct
    @Test
    public void test4(){
        //要去重，必须重写Person里的equals和hashcode
        Stream stream = personList.stream()
                .distinct();

        stream.forEach(System.out::println);
    }
}
