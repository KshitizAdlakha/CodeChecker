package codechecker;

import codechecker.core.entities.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserTests {
    @Mock
    private User user;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void sampleTest(){
        when(user.login("test", "test")).thenReturn(true);
        assertEquals(true,user.login("test", "test"));
    }

    @Test
    public void sampleTest1(){
        when(user.login("test", "test")).thenReturn(true);
        assertEquals(true,user.login("test", "test"));
    }
}
