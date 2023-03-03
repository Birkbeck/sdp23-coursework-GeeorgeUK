package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class DivInstructionTest {
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
  void testDivisible() {
    registers.set(EAX, 10);
    registers.set(EBX, 2);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(5, machine.getRegisters().get(EAX));
  }

  @Test
  void testIndivisibleIsNotAFloat() {
    registers.set(EAX, 10);
    registers.set(EBX, 3);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(3, machine.getRegisters().get(EAX));
  }

  @Test
  void testNegativeDenominator() {
    registers.set(EAX, 10);
    registers.set(EBX, -2);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-5, machine.getRegisters().get(EAX));
  }

  @Test
  void testNegativeNumerator() {
    registers.set(EAX, -10);
    registers.set(EBX, 2);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(-5, machine.getRegisters().get(EAX));
  }

  @Test
  void testNegativeNumeratorAndDominator() {
    registers.set(EAX, -20);
    registers.set(EBX, -4);
    Instruction instruction = new DivInstruction(null, EAX, EBX);
    instruction.execute(machine);
    Assertions.assertEquals(5, machine.getRegisters().get(EAX));
  }
}
