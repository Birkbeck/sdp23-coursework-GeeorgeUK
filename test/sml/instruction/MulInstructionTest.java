package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class MulInstructionTest {
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
  void testMul() {
    registers.set(EAX, 6);
    registers.set(EBX, 4);
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(24, machine.getRegisters().get(EAX));
  }

  @Test
  void testMulWithNegative() {
    registers.set(EAX, -6);
    registers.set(EBX, 8);
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-48, machine.getRegisters().get(EAX));
  }

  @Test
  void testMulWithTwoNegatives() {
    registers.set(EAX, -12);
    registers.set(EBX, -8);
    Instruction instruction = new MulInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(96, machine.getRegisters().get(EAX));
  }
}
