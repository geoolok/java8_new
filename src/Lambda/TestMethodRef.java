package Lambda;

import model.Person;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.*;

/**
 * 一、方法引用
 * 第一种：对象::实例方法名
 * 第二种：类名::静态方法名
 * 第三种：类名：实例方法名
 *
 * 方法引用接口中的方法和实体方法中的参数个数和参数类型相同，并且返回值类型相同。
 *
 * 二、构造器引用
 * 类名::new
 *
 * 三、数组引用
 * type[]::new
 */
public class TestMethodRef {

    //测试一把，真正的实例在后面
    @Test
    public void  test(){
        //Lambda写法
        Consumer<String> con = (x) -> System.out.println("我的名字：" + x);
        con.accept("李元霸");

        //方法引用
        Consumer<Integer> con2 = System.out::println;
        con2.accept(32);
    }

    //第一种
    @Test
    public void test1(){
        //Lambda写法
        Supplier<String> sup = () -> new Person().getName();
        String pName = sup.get();
        System.out.println(pName);

        //方法引用
        Person p = new Person();
        Supplier<Integer> sup2 = p::getAge;
        System.out.println(sup2.get());
    }

    //第二种
    @Test
    public void test2(){
        //Lambda写法
        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        System.out.println(com.compare(18,16));

        //方法引用
        Comparator<Integer> comMethod = Integer::compare;
        System.out.println(comMethod.compare(25,25));
    }

    //第三种

    /**
     * 当Lambda体中 参数列表的的哥参数是实体方法的调用者，第二个参数是实体方法的参数时，可使用类名::方法名
     */
    @Test
    public void test3(){
        //Lambda写法
        BiPredicate<String,String> biPredicate = (x,y) -> x.equals(y);
        System.out.println(biPredicate.test("test","test2"));

        //方法引用
        BiPredicate<String,String> biPredicateMethod = String::equals;
        System.out.println(biPredicateMethod.test("test","test"));

        Comparator<Integer> com = Integer::compareTo;
        System.out.println(com.compare(32,34));
    }

    //构造器（构造函数）引用1
    @Test
    public void test4(){
        //Lambda写法
        Supplier<Person> sup = ()-> new Person();
        Person person = sup.get();
        System.out.println(person);

        //方法引用
        Supplier<Person> supMethod = Person::new;
        System.out.println(supMethod.get());
    }

    //构造器（构造函数）引用2
    @Test
    public void test5(){
        //一个参数的构造函数
        Function<Integer,Person> func = (age) -> new Person(age);
        System.out.println(func.apply(32));

        Function<Integer,Person> funcMethod = Person::new;
        System.out.println(func.apply(28));

        //两个参数的构造函数
        BiFunction<String,Integer,Person> biFunction = (name,age)->new Person(age,name);
        System.out.println(biFunction.apply("王三儿",28));

        //注意参数类型顺序
        BiFunction<Integer,String,Person> biFunctionMethod = Person::new;
        System.out.println(biFunctionMethod.apply(28,"王三儿"));
    }

    //数组引用
    @Test
    public void test6(){
        //String[] strArr = new String[5];
        Function<Integer, String[]> function = (num) -> new String[num];
        String[] strArr = function.apply(5);
        System.out.println(strArr.length);

        //方法引用
        Function<Integer, String[]> functionMethod = String[]::new;
        String[] strArrMethod = functionMethod.apply(15);
        System.out.println(strArrMethod.length);
    }
}
