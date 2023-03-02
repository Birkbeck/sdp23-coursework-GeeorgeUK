package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Machine;
import sml.Registers;
import sml.Translator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static sml.Registers.Register.*;

class TranslatorTest {

  private Machine machine;
  private String currentPath;
  private Registers registers;
  private final ByteArrayOutputStream outData = new ByteArrayOutputStream();
  private final PrintStream defaultOut = System.out;

  @BeforeEach
  void setUp() {
    machine = new Machine(new Registers());
    registers = machine.getRegisters();
    currentPath = Paths.get("").toAbsolutePath().toString() + "/test/sml/example/";
    System.setOut(new PrintStream(outData));
  }

  @AfterEach
  void tearDown() {
    machine = null;
    registers = null;
    currentPath = null;
    System.setOut(defaultOut);
  }

  @Test
  void testExampleMovFile() {
    // Test to see if MOV still works with an integer.
    String thisTest = "mov";
    try {
      Translator translator = new Translator(currentPath + thisTest + ".sml");
      translator.readAndTranslate(machine.getLabels(), machine.getProgram());
      machine.execute();
      Assertions.assertEquals(1, machine.getRegisters().get(EAX));
    } catch (IOException error) {
      Assertions.fail("Failed to read the program: " + currentPath + thisTest + ".sml");
    }
  }

  @Test
  void testExampleMov2File() {
    // Test to see if MOV still works with a register.
    String thisTest = "mov2";
    try {
      Translator translator = new Translator(currentPath + thisTest + ".sml");
      translator.readAndTranslate(machine.getLabels(), machine.getProgram());
      machine.execute();
      Assertions.assertEquals(5, machine.getRegisters().get(EBX));
    } catch (IOException error) {
      Assertions.fail("Failed to read the program: " + currentPath + thisTest + ".sml");
    }
  }

  @Test
  void testExampleOutFile() {
    // Test to see if OUT still works, as it's our current instruction with no arguments.
    String thisTest = "out";
    try {
      registers.set(EAX, 5);
      Translator translator = new Translator(currentPath + thisTest + ".sml");
      translator.readAndTranslate(machine.getLabels(), machine.getProgram());
      machine.execute();
      Assertions.assertEquals("5\n", outData.toString());
    } catch (IOException error) {
      Assertions.fail("Failed to read the program: " + currentPath + thisTest + ".sml");
    }
  }
}
