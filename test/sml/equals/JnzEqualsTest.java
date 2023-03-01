package sml.equals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.instruction.AddInstruction;
import sml.instruction.JnzInstruction;

import static sml.Registers.Register.*;

class JnzEqualsTest {
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
  void testJnzCompareToNull() {
    registers.set(EAX, 10);
    Instruction instruction = new JnzInstruction(null, EAX, "here");
    Instruction fakeInstruction = null;
    Assertions.assertNotEquals(instruction, fakeInstruction);
  }

  @Test
  void testJnzDifferentRegisters() {
    registers.set(EAX, 20);
    registers.set(ECX, 10);
    Instruction instruction = new JnzInstruction(null, EAX, "here");
    Instruction instruction1 = new JnzInstruction(null, ECX, "here");
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testJnzDifferentOpcodes() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    Instruction instruction1 = new JnzInstruction(null, EAX, "there");
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testJnzDifferentTargets() {
    registers.set(EAX, 1);
    Instruction instruction = new JnzInstruction(null, EAX, "here");
    Instruction instruction1 = new JnzInstruction(null, EAX, "there");
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testJnzEqualInstructions() {
    registers.set(EAX, 10);
    Instruction instruction = new JnzInstruction(null, EAX, "home");
    Instruction instruction1 = new JnzInstruction(null, EAX, "home");
    Assertions.assertEquals(instruction, instruction1);
  }
}
