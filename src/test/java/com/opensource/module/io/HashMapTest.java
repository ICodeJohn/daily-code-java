package com.opensource.module.io;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: ""
 * @Description: ""
 * @Author: ZhaoWei
 * @Date: 2023/6/13 10:20
 * @Version V1.0
 */
public class HashMapTest {

    public static void main(String[] args) {

        /**
         * map key可以为空
         */
        Map map = new  HashMap();
        map.put(null,"123");
        System.out.println(map.get(null));

        /**
         * 泛型上限测试
         */

        List<Cat> list = List.of();
//        list.add(new Animal());
//        list.add(new Cat());
         list.add(new MinCat());
        //test(list);
        Cat cat = new Cat();
    }

    public  Cat  test(List< ? super Cat> e){
      return (Cat) e.get(0);
    }

}
