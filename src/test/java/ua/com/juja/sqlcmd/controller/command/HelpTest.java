package ua.com.juja.sqlcmd.controller.command;

import org.junit.Before;
import org.junit.Test;
import ua.com.juja.sqlcmd.view.View;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by oleksandr.baglai on 01.09.2015.
 */
public class HelpTest {

    private View view;
    private Command command;

    @Before
    public void setup() {
        view = mock(View.class);
        Help help = new Help(view);
        Command[] commands = new Command[]{
                new Connect(null, view),
                new Tables(null, view),
                new Clear(null, view),
                new Create(null, view),
                new Find(null, view),
                help,
                new Exit(view)

        };
        help.setCommands(commands);
        command = help;

    }

    @Test
    public void testCanProcessHelpString() {
        // given

        // when
        boolean canProcess = command.canProcess("help");

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
    public void testProcessHelpCommand_thowsExitException() {
        // given

        // when
        command.process("help");

        // then
        verify(view, times(15)).write(any(String.class));
    }

    @Test
    public void testFormat() throws Exception {
        assertEquals("help", command.format());
    }

    @Test
    public void testDescription() throws Exception {
        assertEquals("для вывода этого списка на экран", command.description());
    }
}
