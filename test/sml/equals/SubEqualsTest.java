package sml.equals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.instruction.AddInstruction;
import sml.instruction.SubInstruction;

import static sml.Registers.Register.*;

class SubEqualsTest {
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
  void testSubCompareToNull() {
    registers.set(EAX, 10);
    registers.set(ESI, 2);
    Instruction instruction = new SubInstruction(null, EAX, ESI);
    Instruction fakeInstruction = null;
    Assertions.assertNotEquals(instruction, fakeInstruction);
  }

  @Test
  void testSubDifferentRegisters() {
    registers.set(EAX, 20);
    registers.set(EBX, 4);
    registers.set(ECX, 10);
    registers.set(EDX, 2);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    Instruction instruction1 = new SubInstruction(null, ECX, EDX);
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testSubDifferentOpcodes() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    Instruction instruction1 = new SubInstruction(null, EAX, EBX);
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testSubEqualInstructions() {
    registers.set(EAX, 10);
    registers.set(EBX, 5);
    Instruction instruction = new SubInstruction(null, EAX, EBX);
    Instruction instruction1 = new SubInstruction(null, EAX, EBX);
    Assertions.assertEquals(instruction, instruction1);
  }
}
