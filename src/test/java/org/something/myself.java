package org.something;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by huangshizhe on 2018/8/12
 */

public class myself {

    List<Integer> testData = Lists.newArrayList(1,2,3,4,5);

    @Test
    public void test(){
        Collectors.toList();
      List<Double> a = testData.stream().map(String::valueOf).collect(Collectors.reducing(new ArrayList<Double>(),
              op->Lists.newArrayList(Double.valueOf(op)),(c, b)->{c.addAll(b);return c;}));
      System.out.println(a);
    }

    @Test
    public void behind(){
        System.out.println(Integer.valueOf(""));
    }

    @Test
    public void testAa(){
System.out.println(ttt());
    }

    public String ttt(){
        try{
            throw new Exception();
        }catch (Exception e){
            return "b";
        }finally {
            System.out.println("bbq");
            return "c";
        }
    }
}
