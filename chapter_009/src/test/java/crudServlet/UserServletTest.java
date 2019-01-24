package crudServlet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

//import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class UserServletTest {
private  Validate validate = new ValidateStub();
    @Test
    public void add() throws ServletException, IOException {
        PowerMockito.mockStatic(ValidateService.class);
        Mockito.when(ValidateService.getInstance()).thenReturn((ValidateService) this.validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("name")).thenReturn("name");
        when(req.getParameter("login")).thenReturn("name");
        when(req.getParameter("email")).thenReturn("name@mail.ru");
        when(req.getParameter("password")).thenReturn("1");
        when(req.getParameter("role")).thenReturn("admin");
        when(req.getParameter("action")).thenReturn("add");
       new UserServlet().doPost(req, resp);
        assertThat(validate.findAllMap().values().iterator().next().getName(), is("name"));
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void findAllMap() {
    }
}