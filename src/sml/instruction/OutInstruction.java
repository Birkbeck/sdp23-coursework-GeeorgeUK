package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * <h1>OutInstruction</h1>
 * <p>A subclass of <b>Instruction</b>, an <b>output</b> or <b>OUT</b> instruction
 * displays the value stored in the register to the standard console output.</p>
 * @author gburto03
 */
public class OutInstruction extends Instruction {
  private final RegisterName source;

  public static final String OP_CODE = "out";

  /**
   * This method initialises the OutInstruction class.
   *
   * @param label    An optional string label.
   * @param source   The source register.
   *                 This is where the value we want to print is stored.
   * @author gburto03
   */
  public OutInstruction(String label, RegisterName source) {
    super(label, OP_CODE);
    this.source = source;
  }

  /**
   * This method overrides the abstract method for the output instruction.
   *
   * @param machine The machine in which we execute this instruction.
   * @return Returns an integer representing the program counter update.
   * @author gburto03
   */
  @Override
  public int execute(Machine machine) {
    System.out.println(machine.getRegisters().get(source));
    return NORMAL_PROGRAM_COUNTER_UPDATE;
  }

  /**
   * @return A string containing the label (if applicable), the opcode, and the source.
   * @author gburto03
   */
  @Override
  public String toString() {
    return getLabelString() + getOpcode() + " " + source;
  }
}
