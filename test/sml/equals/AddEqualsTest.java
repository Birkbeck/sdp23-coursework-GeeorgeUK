package sml.equals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;
import sml.instruction.AddInstruction;

import static sml.Registers.Register.*;

class AddEqualsTest {
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
  void testAddCompareToNull() {
    registers.set(EAX, 5);
    registers.set(ESI, 6);
    Instruction instruction = new AddInstruction("greeting", EAX, ESI);
    Instruction fakeInstruction = null;
    Assertions.assertNotEquals(instruction, fakeInstruction);
  }

  @Test
  void testAddDifferentRegisters() {
    registers.set(EAX, 5);
    registers.set(EBX, 10);
    registers.set(ECX, 15);
    registers.set(EDX, 20);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    Instruction instruction1 = new AddInstruction(null, ECX, EDX);
    Assertions.assertNotEquals(instruction, instruction1);
  }

  @Test
  void testAddEqualInstructions() {
    registers.set(EAX, 5);
    registers.set(EBX, 10);
    Instruction instruction = new AddInstruction("hello", EAX, EBX);
    Instruction instruction1 = new AddInstruction("hello", EAX, EBX);
    Assertions.assertEquals(instruction, instruction1);
  }
}