package com.rookied;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author zhangqiang
 * @date 2021/8/9
 */
public class MyTest {
    @Test
    public void Test1(){
        // String转LocalDate 默认格式
        String time = "2021-08-04";
        LocalDate parse = LocalDate.parse(time);
        System.out.println(parse);
        // String转LocalDate 格式化
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        String time1 = "2021/08/04";
        LocalDate parse1 = LocalDate.parse(time1,df);
        System.out.println(parse1);
        // String转LocalDateTime 默认格式
        String time2 = "2021-08-04T20:00:00";
        LocalDateTime parse2 = LocalDateTime.parse(time2);
        System.out.println(parse2);
        // String转LocalDateTime 格式化
        DateTimeFormatter df1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time3 = "2021-08-04 20:00:00";
        LocalDate parse3 = LocalDate.parse(time3,df1);
        System.out.println(parse3);

    }
}
