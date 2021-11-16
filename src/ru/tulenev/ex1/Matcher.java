package ru.tulenev.ex1;

import ru.tulenev.TestRunner;

public class Matcher {
//    public static void main(String[] args) {
//
//        try {
//            System.out.println("Result 1 - " + match("2b2222", "dd** a"));
//            System.out.println("Result 2 - " + match("234", "dd"));
//            System.out.println("Result 3 - " + match("b1", "aa"));
//            System.out.println("Result 4 - " + match("dsa", "aaa"));
//            System.out.println("Result 5 - " + match("dss 33", "aaa d*"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
public static void testMatch() throws Exception{
    TestRunner runner = new TestRunner("match");


        try {
            runner.expectFalse(() -> {
                boolean[] bb = new boolean[10];
                try {
                    bb[0] =  match("x", "d");
                    bb[1] = match("0", "a");
                    bb[2] = match("*", " ");
                    bb[3] = match(" ", "a");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return (bb[0] || bb[1] || bb[2] || bb[3] || bb[4]);
            });

//
//            runner.expectTrue(() -> match("01 xy", "dd aa"));
//            runner.expectTrue(() -> match("1x", "**"));

            runner.expectException(() -> {
                match("x", "w");
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    };





    public static boolean match(String inputStr, String pattern) throws Exception {
        if (pattern.isEmpty()) {
            System.out.println("Шаблон пустой");
            return false;
        } else if (pattern.length() != inputStr.length()) {
            System.out.println("Длины строк в шаблоне и в строке разные");
            return false;
        } else
            {
            int index = 0;
            for (char ch:pattern.toCharArray()) {
                System.out.println(ch);
                char charInInputString = inputStr.charAt(index);
                if (ch=='d') {
                    System.out.println("[" + index +  "] в шаблоне д.б. цифрой");
                     if ((charInInputString)>='0' && (charInInputString)<='9') {
                         System.out.println("+");
                     } else {
                         System.out.println("fail!");
                         return false;
                     }
                } else if (ch=='a') {
                    System.out.println("[" + index +  "] в шаблоне д.б. символом");
                    if ((charInInputString)>='a' && (charInInputString)<='z') {
                        System.out.println("+");
                    } else {
                        System.out.println("fail!");
                        return false;
                    }
                } else if (ch==' ') {
                    System.out.println("[" + index +  "] в шаблоне д.б. пробелом");
                    if (charInInputString == ' ') {System.out.println("+");}
                    else {
                        System.out.println("fail!");
                        return  false;
                    }
                } else if (ch=='*') {
                    System.out.println("[" + index +  "] в шаблоне д.б. любой");
                    if (((charInInputString)>='a' && (charInInputString)<='z') || ((charInInputString)>='0' && (charInInputString)<='9')) {
                        System.out.println("+");
                    } else {
                        System.out.println("fail!");
                        return  false;
                    }
                } else {
                    throw new Exception("Недопустимый символ в шаблоне!!!");
                }
                index++;
            }
        }
    return true;
    }
}
