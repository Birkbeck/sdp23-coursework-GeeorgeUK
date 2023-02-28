package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.*;

/* For this test we want to check the value of sout is the
correct value. For this we need to use ByteArrayOutputStream and
PrintStream, in order to redirect the output in order to compare it.
 */
class OutInstructionTest {
    private Machine machine;
    private Registers registers;

    // Make a backup of the out stream and system.out
    private final ByteArrayOutputStream outData = new ByteArrayOutputStream();
    private final PrintStream defaultOut = System.out;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();

        // We want to set up a new out stream to compare the output.
        System.setOut(new PrintStream(outData));
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;

        // We want to reset our output back to its original value.
        System.setOut(defaultOut);
    }

    @Test
    void testSout() {
        registers.set(EAX, 5);
        Instruction instruction = new OutInstruction(null, EAX);
        instruction.execute(machine);
        /* Converts the out data to a string. sout automatically
        applies a new line character after each entry,
        so we need to consider this character in our comparison.*/
        Assertions.assertEquals("5\n", outData.toString());
    }

    @Test
    void testSoutWithLabel() {
        registers.set(EAX, 1337);
        Instruction instruction = new OutInstruction("labelled\n", EAX);
        instruction.execute(machine);
        Assertions.assertEquals("1337\n", outData.toString());
    }
}

