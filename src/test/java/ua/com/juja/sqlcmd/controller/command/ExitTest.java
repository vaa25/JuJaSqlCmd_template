package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ua.com.juja.sqlcmd.view.View;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

/**
 * Created by oleksandr.baglai on 01.09.2015.
 */
public class ExitTest {

    private View view;
    private Command command;

    @Before
    public void setup() {
        view = mock(View.class);
        command = new Exit(view);
    }

    @Test
    public void testCanProcessExitString() {
        // given

        // when
        boolean canProcess = command.canProcess("exit");

        // then
        assertTrue(canProcess);
    }

    @Test
    public void testCantProcessQweString() {
        // given

        // when
        boolean canProcess = command.canProcess("qwe");

        // then
        assertFalse(canProcess);
    }

    @Test
    public void testProcessExitCommand_thowsExitException() {
        // given

        // when
        try {
            command.process("exit");
            fail("Expected ExitException");
        } catch (ExitException e) {
            // do nothing
        }

        // then
        Mockito.verify(view).write("До скорой встречи!");
    }

    @Test
    public void testFormat() throws Exception {
        assertEquals("exit", command.format());
    }

    @Test
    public void testDescription() throws Exception {
        assertEquals("для выхода из программы", command.description());
    }
}
