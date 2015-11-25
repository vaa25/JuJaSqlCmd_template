package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Created by oleksandr.baglai on 01.09.2015.
 */
public class CreateTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Create(manager, view);
    }

    @Test(expected = ConnectionException.class)
    public void tryCreateWithoutConnection() {
        // given
        // when
        // then
        command.process("create|user|tab1|val1");
    }

    @Test
    public void createSuccessfully() {
        //given
        when(manager.isConnected()).thenReturn(true);

        //when
        command.process("create|user|tab1|val1");

        //then
        verify(manager).create(eq("user"), any(DataSet.class));
        verify(view).write("Запись {names:[tab1], values:[val1]} была успешно создана в таблице 'user'.");
    }

    @Test
    public void testCanProcessTablesWithoutParametersString() {
        // when
        boolean canProcess = command.canProcess("create|");

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
            command.process("create|user|wert");
            fail();
        } catch (IllegalArgumentException e) {
            // then
            assertEquals("Должно быть четное количество параметров в формате " +
                            "'create|tableName|column1|value1|column2|value2|...|columnN|valueN', а ты прислал: 'create|user|wert'",
                    e.getMessage());
        }
    }

    @Test
    public void testFormat() throws Exception {
        assertEquals("create|tableName|column1|value1|column2|value2|...|columnN|valueN", command.format());
    }

    @Test
    public void testDescription() throws Exception {
        assertEquals("для создания записи в таблице", command.description());
    }

}
