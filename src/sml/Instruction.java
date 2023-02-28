package sml;

import java.util.ArrayList;

// TODO: write a JavaDoc for the class

/**
 * Represents an abstract instruction.
 *
 * @author ...
 */
public abstract class Instruction {
  protected final String label;
  protected final String opcode;

  /**
   * Constructor: an instruction with a label and an opcode
   * (opcode must be an operation of the language)
   *
   * @param label  optional label (can be null)
   * @param opcode operation name
   */

  public Instruction(String label, String opcode) {
    this.label = label;
    this.opcode = opcode;
  }

  public String getLabel() {
    return label;
  }

  public String getOpcode() {
    return opcode;
  }

  public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

  /**
   * Executes the instruction in the given machine.
   *
   * @param machine the machine the instruction runs on
   * @return the new program counter (for jump instructions)
   * or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
   * the instruction with the next address is to be executed
   */

  public abstract int execute(Machine machine);

  protected String getLabelString() {
    return (getLabel() == null) ? "" : getLabel() + ": ";
  }

	/* <b>Abstract Class</b><p>
	  An abstract class allows protection of certain attributes and methods in a class.
	  This is done for security reasons, as it can hide attributes we don't want people
	  to see in most situations while still allowing internal use of the data.
 	 */

  @Override
  public abstract String toString();

  // TODO: Make sure that subclasses also implement equals and hashCode (needed in class Machine).
}
