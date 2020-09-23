package Lambda;

import org.junit.Test;

import java.util.function.Consumer;


public class Test_Lambda {
    //Lambda 表达式配合函数式接口使用，接口里只有一个方法
    //左面参数，右面运行内容
    @Test
    public void test(){
        testLambda("面包", (arg) -> System.out.println("您买了一个：" + arg));
    }

    public void testLambda(String name, Consumer<String> consumer){
        consumer.accept(name);
    }
}
