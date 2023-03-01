package sml.equals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.instruction.AddInstruction;
import sml.instruction.MovInstruction;

import static sml.Registers.Register.*;

class MovEqualsTest {
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
  void testMovCompareToNull() {
    registers.set(EAX, 10);
    Instruction instruction = new MovInstruction(null, EAX, 0);
    Instruction fakeInstruction = null;
    Assertions.assertNotEquals(instruction, fakeInstruction);
  }

  @Test
  void testMovDifferentRegisters() {
    registers.set(EAX, 20);
    registers.set(ECX, 10);
    Instruction instruction = new MovInstruction(null, EAX, ECX);
    Instruction instruction1 = new MovInstruction(null, ECX, EAX);
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testMovDifferentOpcodes() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    Instruction instruction1 = new MovInstruction(null, EBX, 3);
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testMovDifferentTargets() {
    registers.set(EAX, 1);
    Instruction instruction = new MovInstruction(null, EAX, 56);
    Instruction instruction1 = new MovInstruction(null, EAX, 232);
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testMovDifferentLocationsButSameValue() {
    registers.set(EAX, 5);
    registers.set(EBX, 15);
    Instruction instruction = new MovInstruction(null, EAX, EBX);
    Instruction instruction1 = new MovInstruction(null, EAX, 15);
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testMovEqualInstructions() {
    registers.set(EAX, 10);
    registers.set(EBX, -10);
    Instruction instruction = new MovInstruction(null, EAX, EBX);
    Instruction instruction1 = new MovInstruction(null, EAX, EBX);
    Assertions.assertEquals(instruction, instruction1);
  }
}
