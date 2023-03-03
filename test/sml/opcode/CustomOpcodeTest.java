package sml.opcode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Machine;
import sml.Registers;
import sml.Translator;

import java.nio.file.Paths;

import static sml.Registers.Register.*;

public class CustomOpcodeTest {
  private String currentPath;
  private Machine machine;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    currentPath = Paths.get("").toAbsolutePath() + "/src/sml/example3.sml";
  }

  @AfterEach
  void tearDown() {
    machine = null;
    currentPath = null;
  }

  @Test
  void testCustomOpcodeFile() {
    try {
      Translator translator = new Translator(currentPath);
      translator.readAndTranslate(machine.getLabels(), machine.getProgram());
      machine.execute();
      Assertions.assertEquals(16, machine.getRegisters().get(ECX));
    } catch (Exception error) {
      Assertions.fail("File did not exist");
    }
  }
}
