import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockitobook.SimpleBusinessService;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;

// https://www.baeldung.com/mockito-series
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WithClearTest {
    @SpyBean
    SimpleBusinessService simpleBusinessService;

    @BeforeEach
    public void beforeEach(){
        simpleBusinessService.resVal.clear();
    }
    @Test()
    @DisplayName("Проверка вызова void, реальнаяфункция не вызывается ")
    public void exampleCallVoid2() {
        when(simpleBusinessService.runFuncWithParams("execute")).thenReturn("300");
        var res = simpleBusinessService.runFuncWithParams("execute");
        assert res.equals("300");

        when(simpleBusinessService.runFuncWithParams("execute")).thenReturn("400");
        res = simpleBusinessService.runFuncWithParams("execute");
        assert res.equals("400");
    }

    @Test()
    @DisplayName("Проверка вызова void, реальнаяфункция не вызывается ")
    public void exampleCallVoid() {
        when(simpleBusinessService.runFuncWithParams("execute")).thenReturn("100");
        var res = simpleBusinessService.runFuncWithParams("execute");
        assert res.equals("100");

        when(simpleBusinessService.runFuncWithParams("execute")).thenReturn("200");
        res = simpleBusinessService.runFuncWithParams("execute");
        assert res.equals("200");
    }



}
