package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * <h1>Translator</h1>
 * <p>The translator method converts a text file of written plaintext instructions
 * into a series of machine readable instructions.</p>
 *
 * @author gburto03
 */
public final class Translator {

  private final String fileName; // source file of SML code

  // line contains the characters in the current line that's not been processed yet
  private String line = "";

  public Translator(String fileName) {
    this.fileName = fileName;
  }

  // translate the small program in the file into lab (the labels) and
  // prog (the program)
  // return "no errors were detected"

  public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
    try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
      labels.reset();
      program.clear();

      // Each iteration processes line and reads the next input line into "line"
      while (sc.hasNextLine()) {
        line = sc.nextLine();
        String label = getLabel();

        Instruction instruction = getInstruction(label);
        if (instruction != null) {
          if (label != null)
            labels.addLabel(label, program.size());
          program.add(instruction);
        }
      }
    }
  }

  /**
   * Translates the current line into an instruction with the given label
   *
   * @param label the instruction label
   * @return the new instruction
   * <p>
   * The input line should consist of a single SML instruction,
   * with its label already removed.
   */
  private Instruction getInstruction(String label) {
    if (line.isEmpty())
      return null;
    String opcode = scan();

    // Grab the source
    String source = scan();

    // Grab the argument, if it exists
    String argument;
    try {
      argument = scan();
    } catch (Exception error) {
      error.printStackTrace();
      argument = null;
    }

    // Use the opcode to access the class inside the /instruction/ package
    String capitalisedOpcode = opcode.substring(0,1).toUpperCase() + opcode.substring(1);
    String classLocation = "sml.instruction." + capitalisedOpcode + "Instruction";

    // Check if the class exists. If it does not, we print Unknown instruction, plus the opcode.
    Class<?> thisClass;
    Object builtInstruction;

    try {
      // Here we get the class and constructor.
      thisClass = Class.forName(classLocation);
      Constructor<?>[] constructors = thisClass.getConstructors();

      // This catches instructions with an integer as an argument.
      for (Constructor<?> constructor : constructors) {
        try {
          assert Integer.parseInt(Objects.requireNonNull(argument)) != 0;
          builtInstruction = constructor.newInstance(
                  label,
                  Registers.Register.valueOf(source),
                  Integer.valueOf(argument));
          return (Instruction) builtInstruction;
        } catch (Exception ignored) {}
      }

      // This catches instructions with another register as an argument.
      for (Constructor<?> constructor : constructors) {
        try {
          builtInstruction = constructor.newInstance(
                  label,
                  Registers.Register.valueOf(source),
                  Registers.Register.valueOf(argument));
          return (Instruction) builtInstruction;
        } catch (Exception ignored) {}
      }

      // This catches instructions with an argument that is neither an integer nor a register.
      for (Constructor<?> constructor : constructors ) {
        try {
          builtInstruction = constructor.newInstance(
                  label,
                  Registers.Register.valueOf(source),
                  argument);
          return (Instruction) builtInstruction;
        } catch (Exception ignored) {}
      }

      // This catches instructions with no argument, such as the OUT instruction.
      for (Constructor<?> constructor : constructors ) {
        try {
          builtInstruction = constructor.newInstance(
                  label,
                  Registers.Register.valueOf(source));
          return (Instruction) builtInstruction;
        } catch (Exception ignored) {}
      }

    } catch (Exception error) {
      System.out.println("Unknown instruction: " + opcode);
      //error.printStackTrace();
      return null;
    }

    // TODO: Next, use dependency injection to allow this machine class
    //       to work with different sets of opcodes (different CPUs)
    //       [in progress]

    return null;
  }

  private String getLabel() {
    String word = scan();
    if (word.endsWith(":"))
      return word.substring(0, word.length() - 1);

    // undo scanning the word
    line = word + " " + line;
    return null;
  }

  /*
   * Return the first word of line and remove it from line.
   * If there is no word, return "".
   */
  private String scan() {
    line = line.trim();

    for (int i = 0; i < line.length(); i++)
      if (Character.isWhitespace(line.charAt(i))) {
        String word = line.substring(0, i);
        line = line.substring(i);
        return word;
      }

    return line;
  }
}
