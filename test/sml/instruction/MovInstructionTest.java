package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class MovInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void testMov() {
        Instruction instruction = new MovInstruction(null, EAX, 5);
        instruction.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void testMovOverwrite() {
        Instruction instruction = new MovInstruction(null, EAX, 3);
        instruction.execute(machine);
        Instruction instruction1 = new MovInstruction(null, EAX,5);
        instruction1.execute(machine);
        Assertions.assertEquals(5, machine.getRegisters().get(EAX));
    }

    @Test
    void testMovWithLabel() {
        Instruction instruction = new MovInstruction("this_is_a_label", EAX, 1);
        instruction.execute(machine);
        Assertions.assertEquals(1, machine.getRegisters().get(EAX));
    }

    @Test
    void testMovFromRegister() {
        machine.getRegisters().set(EBX, 10);
        Instruction instruction = new MovInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(10, machine.getRegisters().get(EAX));
    }

    @Test
    void testMovFromRegisterDoesNotRemoveOldRegister() {
        machine.getRegisters().set(EBX, 10);
        Instruction instruction = new MovInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(10, machine.getRegisters().get(EBX));
    }
}
