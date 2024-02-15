package com.ohgiraffers.parameterized.section01.param;

public class StringValidator {

    public static boolean isnull(String input) {
        return input == null;

    }
    public static boolean isEmpty(String input) {
        return "".equals(input);
    }

    public static boolean isBlank(String input) {
        return input == null || "".equals(input);
    }

}

//null소스, 엠프티소스 사용해본건데,    매개변수넣을때, 아~
//null소스면 null값 넣을수있고, 엠프티는 공백넣을수있다...원래안되는걸 해준다.
//어노테이션 해줘서 ㅋ
//  "".equls <--이게 공백일때를 표현한거군..