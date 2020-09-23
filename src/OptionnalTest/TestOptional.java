package OptionnalTest;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import model.Person;
import org.junit.Test;

import java.util.Optional;

/**
 * Optional类常用方法
 * Optional.of():创建一个Optional实例
 * Optional.empty():创建一个空的Optional实例
 * Optional.ofNullable(T t)：如果t不是null,则创建Optional实例，否则创建空实例
 * isPresent()：判断是否包含值
 * orElse(T t):如果optional对象包含值，则返回值，否则返回t
 * orElseGet(Supplier sup)：如果optional对象包含值，返回该值，否则返回sup获取的值
 * map(Function func):如果有值则对其处理，并返回处理后的Optional，否则返回Optional.empty()
 * flatMap(Function func):与map类似，要求返回值必须是Optional
 */
public class TestOptional {

    @Test
    public void test1(){
        Optional optional1 = Optional.of(new Person());//也可传入null
        //optional1 = Optional.of(null);  //报错NullPointerException

        System.out.println(optional1.isPresent()); //如果上面传入null 也会报错NullPointerException
        System.out.println(optional1.get());
    }

    @Test
    public void test2(){
        Optional<Person> optional = Optional.empty();

        System.out.println(optional.isPresent()); //这里不会报空指针异常
        //System.out.println(optional.get());  //会报空指针异常

        Person p1 = optional.orElse(new Person(3,"马三"));
        System.out.println(p1);

        optional = Optional.empty();
        Person p2 = optional.orElseGet(Person::new);
        System.out.println(p2);
    }

    @Test
    public void test3(){
        Optional<Person> optional = Optional.empty(); //后面会报错 No value present
        optional = Optional.of(new Person());

        Optional<Integer> integerOptional = optional.map(Person::getAge);
        System.out.println(integerOptional.get());

        Optional<String> op = optional.flatMap((p1)-> Optional.of(p1.getName()));
        System.out.println(op.get());
    }
}
