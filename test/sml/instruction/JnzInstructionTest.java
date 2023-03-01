package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static sml.Registers.Register.*;

class JnzInstructionTest {
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

  private ArrayList<Instruction> loadDemoProgram(int startValue, boolean removeJumpLabel) {
    ArrayList<Instruction> instructionList = new ArrayList<>();
    instructionList.add(new MovInstruction("start", EAX, startValue));
    instructionList.add(new MovInstruction("divider", EBX, 2));
    instructionList.add(new MovInstruction("counter", ECX, -1));
    instructionList.add(new MovInstruction("modifier", EDX, 1));
    // Divide by 2
    if (!removeJumpLabel) {
      instructionList.add(new DivInstruction("retry", EAX, EBX));
    } else {
      instructionList.add(new DivInstruction(null, EAX, EBX));
    }
    // Increment counter
    instructionList.add(new AddInstruction(null, ECX, EDX));
    // If zero, we are done
    instructionList.add(new JnzInstruction(null, EAX, "retry"));
    return instructionList;
  }

  @Test
  void testProgramDoesNotThrowExceptionOnInvalidDestination() {
    ArrayList<Instruction> instructionList = loadDemoProgram(16, true);
    machine.getProgram().addAll(instructionList);
    machine.execute();
    Assertions.assertEquals(0, machine.getRegisters().get(ECX));
  }

  @Test
  void testProgramResultIs16WhenStartingAt66536() {
    ArrayList<Instruction> instructionList = loadDemoProgram(65536, false);
    machine.getProgram().addAll(instructionList);
    machine.execute();
    Assertions.assertEquals(16, machine.getRegisters().get(ECX));
  }

  @Test
  void testProgramResultIsThreeWhenStartingAtEight() {
    ArrayList<Instruction> instructionList = loadDemoProgram(8, false);
    machine.getProgram().addAll(instructionList);
    machine.execute();
    Assertions.assertEquals(3, machine.getRegisters().get(ECX));
  }

  @Test
  void testProgramResultIsZeroWhenNotRepeating() {
    ArrayList<Instruction> instructionList = loadDemoProgram(1, false);
    machine.getProgram().addAll(instructionList);
    machine.execute();
    Assertions.assertEquals(0, machine.getRegisters().get(ECX));
  }
}
