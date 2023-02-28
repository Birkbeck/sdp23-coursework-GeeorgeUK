package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class AddInstructionTest {
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
  void testTwoPositives() {
    registers.set(EAX, 5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(11, machine.getRegisters().get(EAX));
  }

  @Test
  void testOneNegative() {
    registers.set(EAX, -5);
    registers.set(EBX, 6);
    Instruction instruction = new AddInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(1, machine.getRegisters().get(EAX));
  }

  @Test
  void testWithLabel() {
    registers.set(EAX, 1);
    registers.set(EBX, 1);
    Instruction instruction = new AddInstruction("labelled", EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(2, machine.getRegisters().get(EAX));
  }

  @Test
  void testTwoNegatives() {
    registers.set(EAX, -10);
    registers.set(EBX, -10);
    Instruction instruction = new AddInstruction("yaya", EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-20, machine.getRegisters().get(EAX));
  }
}
