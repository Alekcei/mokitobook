import org.junit.After;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockitobook.SimpleBusinessService;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

// https://www.baeldung.com/mockito-series
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VoidTest {
    @SpyBean
    SimpleBusinessService simpleBusinessService;

    @BeforeEach
    public void beforeEach(){
        simpleBusinessService.resVal.clear();
    }

    @Test()
    @DisplayName("Проверка вызова void, реальнаяфункция не вызывается ")
    public void exampleCallVoid() {

        // Для конкретного параметра  eq("100")
        doNothing().when(simpleBusinessService).callFunc(eq("100"));
        simpleBusinessService.callFunc("100");
        simpleBusinessService.callFunc("101");
        verify(simpleBusinessService).callFunc( "100");
        // Првоеряем что оригинальная функция не вызвалась
        assert  !simpleBusinessService.resVal.contains("100") ;

        // Для любого параметра anyString()
        doNothing().when(simpleBusinessService).callFunc(anyString());
        simpleBusinessService.callFunc("102");
        simpleBusinessService.callFunc("103");
        // atLeast, atMostOnce, atLeastOnce ......
        verify(simpleBusinessService, atLeast(1) ).callFunc( "103");
        // verify(simpleBusinessService).callFunc( "103");
        // Првоеряем что оригинальная функция не вызвалась
        assert  !simpleBusinessService.resVal.isEmpty() ;

    }

    @Test()
    @DisplayName("Подмена ответа, реальнаяфункция не вызывается ")
    public void exampleCallRealVoid() {

        // Для конкретного параметра  eq("100")
        doAnswer(i -> "200").when(simpleBusinessService).runFuncWithParams(eq("100"));

        String resultCalls = simpleBusinessService.runFuncWithParams("100");
        assert simpleBusinessService.resVal.isEmpty() ;
        assert resultCalls.equals("200");
    }

    @Test()
    @DisplayName("Подмена ответа, реальнаяфункция НЕ вызывается ")
    public void exampleCallRealVoid1() {
        System.out.println("2!!!!!!!!!!!!!!!" +simpleBusinessService.resVal.size()  );
        // Для конкретного параметра  eq("100")
        doReturn("500").when(simpleBusinessService).runFuncWithParams(eq("100"));
        String resultCalls = simpleBusinessService.runFuncWithParams("100");
        assert resultCalls.equals("500");

        simpleBusinessService.runFuncWithParams("200");
        assert simpleBusinessService.resVal.get(0) == "200"  ;
    }


    @Test()
    @DisplayName("Подмена ответа, реальная функция ВЫЗЫВАЕТСЯ")
    public void exampleCallReal2() {

        // Для конкретного параметра  eq("100")

        when(simpleBusinessService.getConfigure(eq("100"))).thenReturn("500");
        String resultCalls = simpleBusinessService.getConfigure("100");
        assert resultCalls.equals("500"); // 500 потому что за мокали
        assert !simpleBusinessService.resVal.isEmpty() ; // не пусто - реальная функция вызвалась
        assert simpleBusinessService.resVal.get(0).equals("100"); // реальная функция записала парамтер в контейнер для проверки вызова

    }
    @Test()
    @DisplayName("Проверка вызова, реальная функция ВЫЗЫВАЕТСЯ")
    public void checkCall() {

        // Для конкретного параметра  eq("100")
        doCallRealMethod().when(simpleBusinessService).runFuncWithParams(eq("execute"));
        simpleBusinessService.runFuncWithParams("execute");
        verify(simpleBusinessService).runFuncWithParams( "execute");


    }


}
