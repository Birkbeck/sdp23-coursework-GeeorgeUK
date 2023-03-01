package sml.equals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.instruction.AddInstruction;
import sml.instruction.OutInstruction;

import static sml.Registers.Register.*;

class OutEqualsTest {
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
  void testOutCompareToNull() {
    registers.set(EAX, 10);
    registers.set(ESI, 2);
    Instruction instruction = new OutInstruction(null, EAX);
    Instruction fakeInstruction = null;
    Assertions.assertNotEquals(instruction, fakeInstruction);
  }

  @Test
  void testOutDifferentRegisters() {
    registers.set(EAX, 20);
    registers.set(ECX, 10);
    Instruction instruction = new OutInstruction(null, EAX);
    Instruction instruction1 = new OutInstruction(null, ECX);
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testOutDifferentOpcodes() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    Instruction instruction1 = new OutInstruction(null, EAX);
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testOutEqualInstructions() {
    registers.set(EAX, 10);
    Instruction instruction = new OutInstruction(null, EAX);
    Instruction instruction1 = new OutInstruction(null, EAX);
    Assertions.assertEquals(instruction, instruction1);
  }
}
