package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.model.DataSet;
import ua.com.juja.sqlcmd.model.DataSetImpl;
import ua.com.juja.sqlcmd.model.DatabaseManager;
import ua.com.juja.sqlcmd.view.View;

import java.util.ArrayList;
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
public class FindTest {

    private DatabaseManager manager;
    private View view;
    private Command command;

    @Before
    public void setup() {
        manager = mock(DatabaseManager.class);
        view = mock(View.class);
        command = new Find(manager, view);
    }

    @Test(expected = ConnectionException.class)
    public void tryGetFindWithoutConnection() {
        // given
        // when
        // then
        command.process("find|user");
    }

    @Test
    public void findSuccessfully() {
        //given
        DataSet dataSet1 = new DataSetImpl();
        dataSet1.put("col1", "val11");
        dataSet1.put("col2", "val21");
        DataSet dataSet2 = new DataSetImpl();
        dataSet2.put("col1", "val12");
        dataSet2.put("col2", "val22");

        when(manager.isConnected()).thenReturn(true);
        when(manager.getTableColumns("user")).thenReturn(new LinkedHashSet<>(Arrays.asList("col1", "col2")));
        when(manager.getTableData("user")).thenReturn(new ArrayList<>(Arrays.asList(dataSet1, dataSet2)));

        //when
        command.process("find|user");

        //then
        verify(view).write("--------------------\r\n" +
                "|col1|col2|\r\n" +
                "--------------------\r\n" +
                "|val11|val21|\r\n" +
                "|val12|val22|\r\n" +
                "--------------------");
    }

    @Test
    public void testCanProcessTablesWithoutParametersString() {
        // when
        boolean canProcess = command.canProcess("find|");

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
    public void testValidationErrorWhenCountParametersIsNot2() {
        // when
        try {
            command.process("find|user|wert");
            fail();
        } catch (IllegalArgumentException e) {
            // then
            assertEquals("Неверно количество параметров разделенных знаком '|', ожидается 2, но есть: 3", e.getMessage());
        }
    }

    @Test
    public void testFormat() throws Exception {
        assertEquals("find|tableName", command.format());
    }

    @Test
    public void testDescription() throws Exception {
        assertEquals("для получения содержимого таблицы 'tableName'", command.description());
    }

}
