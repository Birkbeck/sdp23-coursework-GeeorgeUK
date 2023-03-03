package sml;

import java.util.Objects;

/**
 * <h1>Instruction</h1>
 * <p>Represents an abstract instruction.</p>
 * <p>This should not be instanced directly, but extended for different instructions.</p>
 * @author gburto03
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

  /*
  <h1>Abstract Class</h1>
  <p>An abstract class allows protection of certain attributes and methods in a class.
  This is done for security reasons, as it can hide attributes we don't want people
  to see in most situations while still allowing internal use of the data.</p>
  @author gburto03
  */

  /**
   * @return A formatted version of the instruction
   */
  @Override
  public abstract String toString();

  /**
   * Compares this instruction with another object.
   *
   * @param o The object to compare.
   * @return Whether the object is equal to this host.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null) { return false; }
    if (o instanceof Instruction other) {
      return Objects.equals(this.getLabel(), other.getLabel())
              && Objects.equals(this.getOpcode(), other.getOpcode());
    }
    return false;
  }

  /**
   * Creates a identifier hashcode based on the contents
   * @return An integer based on the passed criteria.
   */
  @Override
  public int hashCode() {
    return Objects.hash(getLabel(), getOpcode());
  }

}
