package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by oleksandr.baglai on 01.09.2015.
 */
public class ConnectTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Connect(manager, view);
    }

    @Test
    public void testCanProcessConnect() {
        // when
        boolean canProcess = command.canProcess("connect|");

        // then
        assertTrue(canProcess);

    }

    @Test
    public void shouldConnectSuccessfully() {
        //when
        command.process("connect|sqlcmd|postgres|postgres");

        //then
        verify(view).write("Успех!");
    }

    @Test
    public void testCantProcessQweString() {
        // when
        boolean canProcess = command.canProcess("qwe");

        // then
        assertFalse(canProcess);
    }

    @Test
    public void testValidationErrorWhenCountParametersIsNot4() {
        // when
        try {
            command.process("connect|user");
            fail();
        } catch (IllegalArgumentException e) {
            // then
            assertEquals("Неверно количество параметров разделенных знаком '|', ожидается 4, но есть: 2", e.getMessage());
        }
    }

    @Test
    public void testFormat() throws Exception {
        assertEquals("connect|databaseName|userName|password", command.format());
    }

    @Test
    public void testDescription() throws Exception {
        assertEquals("для подключения к базе данных, с которой будем работать", command.description());
    }

}
