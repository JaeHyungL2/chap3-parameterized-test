package com.ohgiraffers.parameterized.section01.params;


import com.ohgiraffers.parameterized.section01.param.DateValidator;
import com.ohgiraffers.parameterized.section01.param.NumberValidator;
import com.ohgiraffers.parameterized.section01.param.StringValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.stream.Stream;

//테스트해보자 ㅋ 아까추가한 파람 이용해서, 테스트
public class ParameterizedTests {
    //테스트시, 개수만큼 테스트작성해야하는데, 테스트의 매개변수 선언가능해서,
    //여러번 테스트작성시 횟수를 줄여줄 수 있다. -목표-

    //테스트시 여러 값들을 이용하여 검증을 해야하는 경우 모든 경우의 수 만큼 테스트 메소드를 작성해야한다.
    // 이 경우 테스트 메소드에 매개변수를 선언할 수 있다.
    //왜냐, 일바넞ㄱ인 테스트에선 매개변수 사용할 수 없다.

    //파라미터로 전달할 값의 목만큼 반보적으로 테스트 메소드를 실행하며, 반복 실행시 마다 준비된 값 목록을 매개변수로 전달하여 테스트 코드를 실행하게 된다.

    //given - when = then 패턴에서  사전에 매개변수줄수있으니, given부분 대체가능. 어노테이션으로..


    /* 목차 1번 @ValueSource를 이용한 parameter value목록 지정
        필기.
        @ValueSource를 이용하여 한 개의 파라미터로 전달한 값들의 목록을 지정할 수 있다.
        이 때, 지원하는 타입은 다음과 같은데..
        short, byte, int, long, loat, double, char, java.lang.String, java.lang.Class 다.
     */
    @DisplayName("홀수 짝수 판별 테스트")
    @ParameterizedTest
    @ValueSource(ints={1,3,-1,15,123})
    void testIsOdd(int number) {
        //when
        boolean result= NumberValidator.isOdd(number);

                //then
        Assertions.assertTrue(result);

//방금 세팅 단축키: ctrl+atl+s -file encoding이랑, build-gradle 인텔리j와 utf-8바꿔주기
    }

    /* 목차2 @NullSource와
    기본자료형 타입은 null값 가질 수 없으니,
    문자열이나 클래스 타입인 경우 nullㅣ이나 빈값을 파라미터로 전달하기위해 사용되는 어노테이션이다.
    null값과 빈 값을 모두 전달하기위해 구성된 @NullAndEmptySource도 이용할 수 있다.

     */

    @DisplayName("null값 테스트")
    @ParameterizedTest
    @NullSource   //스트링input에 널값 넣어준다. 이 어노테이션이
    void testIsNull(String input) {

        //when   given은 대체됨 ㅋ
        boolean result = StringValidator.isnull(input); //이것도 클래스만들자.

        //then
        Assertions.assertTrue(result);  //긜고 넣어준 null값이 맞는지 결과확인
    }
    @DisplayName("empty값 테스트")
    @ParameterizedTest
    @EmptySource   //다시 엠프티소스 넣어주고,
    void testIsEmpty(String input) {

        //when
        boolean result = StringValidator.isEmpty(input);
        //then
        Assertions.assertTrue(result);
    }

    @DisplayName("blank값 테스트")
    @ParameterizedTest
    @NullAndEmptySource
    void testIsBlank(String input) {

        //when
        boolean result = StringValidator.isBlank(input);
        //then
        Assertions.assertTrue(result);

    }

    //null소스, 엠프티소스 사용해본건데,    매개변수넣을때, 아~
    //null소스면 null값 넣을수있고, 엠프티는 공백넣을수있다...원래안되는걸 해준다.
    //어노테이션 해줘서 ㅋ
    //  "".equls <--이게 공백일때를 표현한거군..

    /* 목차 3. 열거형을 이용한 @EnumSource 활용해보자.
       필기 열거형에 작성된 타입들을 파라미터로
     */
    @DisplayName("Month에 정의된 타입들이 1~12월 사이의 범위인지 테스트")
    @ParameterizedTest
    @EnumSource(Month.class)  //Month 이미 구현되어있는걸 가져다쓰는것임. enum파일로

    //ctrl 누르고 클래스,메소드 누르면 들어가볼수있다...근본ㄲ"ㅏ지
    //메소드쓰고 객체시 컨트롤 누르고 들어가볼수있다 .소스코드
    void testMonthValueIsCollect(Month month){

        //when
        boolean result = DateValidator.isCollect(month);   //만들어보자. 클래스박스에 담은걸

        //then
        Assertions.assertTrue(result);
    }

    @DisplayName("2월, 4월 6월, 9월, 11월을 제외한 달이 31일인지 확인")
    @ParameterizedTest
    @EnumSource(
            value = Month.class,
            names =  {"FEBRUARY", "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"},
            mode = EnumSource.Mode.INCLUDE  //제외한것 exclude로..
    )

    void testHasThirtyOneDaysLong(Month month){

        //given
        int verifyValue = 31;
        //month는 enum타입이라 수작업으로 뭐 가져올수있어서 파라미터있어도 기븐쓰네
        //꼭 파라미터라이즈한다고 기븐안쓰는게아니라, 기븐엔 필요한데이터 만들어놓는데,
        // 파라미터라이즈는 같은타입이라도 0이었다 3이었다 결과달라지는 메소드있을수있으니 뭐 써도됨 given

        //when
        int actual = DateValidator.getLastDayOf(month);

        //then
        Assertions.assertEquals(verifyValue, actual);

    }

    /* 목차 4 @CsvSource를 이용한 CSV리터럴
        필기, 입력값과 예상값ㅇ르ㅡ 테스트 메소드에 전달하기 위해 사용할 수 있다.
              이 경우, 여러 매개변수에 값을 전달할 여러 인자 묶음이 필요하게된다.
              이때, @CsvSouce를 사용할 수 있다.
     */
    @DisplayName("영문자를 대문자로 변경하는지 확인")
    @ParameterizedTest

    //@CsvSource({"test,TEST", "tEsT,TEST", "JavaScript, JAVASCRIPT"})
        // test:입력값 TEST:결과값 ,verifyValue
    @CsvSource(value = {"test:TEST", "tEsT:TEST", "JavaScript:JAVASCRIPT"},
      delimiter=':'
      )

    void testToUpperCase(String input, String verifyValue) {

        //when
        String actual = input.toUpperCase();
        //대문자로바뀌어서,

        //then
        Assertions.assertEquals(verifyValue, actual);

        //
    }

    //1.먼저 쉼표시 테스트되는지 확인해본거고,
    //2. 방금 푼 두번째csv소스 보면 , 되네 ㅋ
    //같은테스트코드사용시, 입력/결과값만 다른경우,   지정해줘서 여러번 테스트반복이 가능해지도록해본것

    @DisplayName("CSV파일을 읽은 테스트 데이터를 테스트에 활용하는지 확인")
    @ParameterizedTest
    @CsvFileSource(resources = "/parameter-test-data.csv", numLinesToSkip = 1)  //필드명 첫번째는csv에서 지워줌ㅋ  //csv파일없으니 만들어주자.

    void testToUpperCaseWithCSVFileData(String input, String verifyValue) {

        //when
        String actual = input.toUpperCase();


        //then  나눠주고,
        Assertions.assertEquals(verifyValue, actual);

//테스트패키안에 리소스에 파일생성해주자 ㅋ
//csv란, 쉼표로 구분한 파일을 csv라함..
//보통 대용량파일저장시, 용량줄이기위해 사용
    }
    //테스트시, 첫번째가 틀렸다하네? - q방금 정정 ㅋ 첫번째줄 skip으로

    /* 목차5 @MethodSource를 활용한 메소드 인수 활용하기
        v필기 .Stream을 반환하는 메소드를 만들어서 이를 테스트에 활용할 수 있다.

     */

    private static Stream<Arguments> providerStringSource() {
        return Stream.of(
                Arguments.of("hello world", "HELLO WORLD"),
                Arguments.of("JavaScript", "JAVASCRIPT"),
                Arguments.of("tEsT", "TEST")
        );
    }

    @DisplayName("메소드 ㅡ소스를 활용한 대문자 변환 테스트")
    @ParameterizedTest
    @MethodSource("providerStringSource")
    void testToUpperCaseWithMethodSource(String input, String verifyValue) {

        //when
        String actual = input.toUpperCase();

        //then
        Assertions.assertEquals(verifyValue, actual);

    }
    //이 스트림에선 쌍으로 이루는걸 생성한다는거고, 생성한 값을 메소드소스로 가지고와서 똑같이 테스트진행
    //입력햇던 스트림값들이 들어오는걸 확인가능.

    /* 목차 6,  인수 변환기( 암시적변환과 명시적 변환)
        필기 . Junit5는 인수로 지정된 String을 Enum으로 변환한다. 이처럼 기본적으로 제공하는 변환을 암시적 변환이라함.
            UUID, LocalDate, LocalDateTime, Year, Month, 파일 및 경로, URL, 열거형 서브클래스 등을 암시적으로 변환해준다.

     */
    @DisplayName("암시적 변환 테스트")
    @ParameterizedTest(name="[{0}] is 30 days long") //그냥 [ { 0 } ] r규칙이다..
    @CsvSource({"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    void testAutoConverting(Month month) {

        //given
        int verifyValue = 30;

        //when
        int actual = DateValidator.getLastDayOf(month);

        //then
        Assertions.assertEquals(verifyValue,actual);
    }
    //테스트수업은 끝.
    //계산기 하나씩만들어서 테스트로..
}

