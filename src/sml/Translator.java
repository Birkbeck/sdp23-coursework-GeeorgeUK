package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
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

  //TODO: Amend the javadoc for this method.

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

    /* Old implementation using switch
    switch (opcode) {
      case AddInstruction.OP_CODE -> {
        String source = scan();
        String modifier = scan();
        return new AddInstruction(label, Register.valueOf(source), Register.valueOf(modifier));
      }
      case OutInstruction.OP_CODE -> {
        String source = scan();
        return new OutInstruction(label, Register.valueOf(source));
      }
      case MovInstruction.OP_CODE -> {
        String source = scan();
        String modifier = scan();
        return new MovInstruction(label, Register.valueOf(source), Integer.valueOf(modifier));
      }
      case SubInstruction.OP_CODE -> {
        String source = scan();
        String modifier = scan();
        return new SubInstruction(label, Register.valueOf(source), Register.valueOf(modifier));
      }
      case MulInstruction.OP_CODE -> {
        String source = scan();
        String modifier = scan();
        return new MulInstruction(label, Register.valueOf(source), Register.valueOf(modifier));
      }
      case DivInstruction.OP_CODE -> {
        String source = scan();
        String modifier = scan();
        return new DivInstruction(label, Register.valueOf(source), Register.valueOf(modifier));
      }
      case JnzInstruction.OP_CODE -> {
        String source = scan();
        String destination = scan();
        return new JnzInstruction(label, Register.valueOf(source), destination);
      }
      default -> {
        System.out.println("Unknown instruction: " + opcode);
      }
       */

    // TODO: Next, use dependency injection to allow this machine class
    //       to work with different sets of opcodes (different CPUs)

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
