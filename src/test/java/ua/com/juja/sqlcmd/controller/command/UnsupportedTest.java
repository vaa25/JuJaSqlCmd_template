package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Created by oleksandr.baglai on 01.09.2015.
 */
public class UnsupportedTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Unsupported(manager, view);
    }

    @Test(expected = ConnectionException.class)
    public void tryGetUnsupportedCommandWithoutConnection() {
        // given
        // when
        // then
        command.process("unsupported");
    }

    @Test
    public void getTablesSuccessfully() {
        when(manager.isConnected()).thenReturn(true);
        String commandString = "unsupported";

        command.process(commandString);

        verify(view).write("Несуществующая команда: " + commandString);
    }

    @Test
    public void testCanProcessWithAnyString() {
        // when
        boolean canProcess = command.canProcess("tables");

        // then
        assertTrue(canProcess);
    }

    @Test
    public void testCanProcessQweString() {
        // when
        boolean canProcess = command.canProcess("qwe");

        // then
        assertTrue(canProcess);
    }

    @Test
    public void testFormat() throws Exception {
        assertEquals("", command.format());
    }

    @Test
    public void testDescription() throws Exception {
        assertEquals("для неподдерживаемых команд", command.description());
    }

}
