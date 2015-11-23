package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.util.Arrays;
import java.util.LinkedHashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by oleksandr.baglai on 01.09.2015.
 */
public class TablesTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Tables(manager, view);
    }

    @Test(expected = ConnectionException.class)
    public void tryGetTablesWithoutConnection() {
        // given
        // when
        // then
        command.process("tables");
    }

    @Test
    public void getTablesSuccessfully() {
        when(manager.isConnected()).thenReturn(true);
        when(manager.getTableNames()).thenReturn(new LinkedHashSet<String>(Arrays.asList("user", "qwe")));

        command.process("tables");

        verify(view).write("[user, qwe]");
    }

    @Test
    public void testCanProcessTablesWithoutParametersString() {
        // when
        boolean canProcess = command.canProcess("tables");

        // then
        assertTrue(canProcess);
    }

    @Test
    public void testCantProcessQweString() {
        // when
        boolean canProcess = command.canProcess("qwe");

        // then
        assertFalse(canProcess);
    }

    @Test
    public void testValidationErrorWhenCountParametersIsMoreThen0() {
        // when
        try {
            command.process("tables|user");
            fail();
        } catch (IllegalArgumentException e) {
            // then
            assertEquals("Формат команды 'tables', а ты ввел: tables|user", e.getMessage());
        }
    }

    @Test
    public void testFormat() throws Exception {
        assertEquals("tables", command.format());
    }

    @Test
    public void testDescription() throws Exception {
        assertEquals("для получения списка всех таблиц базы, к которой подключились", command.description());
    }

}
