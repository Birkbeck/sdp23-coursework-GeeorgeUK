package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * <h1>DivInstruction</h1>
 * <p>A subclass of <b>Instruction</b>, a <b>division</b> or <b>DIV</b> instruction
 * counts the number of times the value of register A can fit into register B.
 * The resulting value is stored back into register A.</p>
 *
 * @author gburto03
 */
public class DivInstruction extends Instruction {

  private final RegisterName source;
  private final RegisterName modifier;

  public static final String OP_CODE = "div";

  /**
   * This method initialises the DivInstruction class.
   *
   * @param label    An optional string label.
   * @param source   The source register. This is where the updated value is stored.
   * @param modifier The register containing the value in which we divide the source.
   * @author gburto03
   */
  public DivInstruction(String label, RegisterName source, RegisterName modifier) {
    super(label, OP_CODE);
    this.source = source;
    this.modifier = modifier;
  }

  /**
   * This method overrides the abstract method for the division instruction.
   *
   * @param machine The machine on which we execute the instruction.
   * @return Returns an integer representing the program counter update.
   * @author gburto03
   */
  @Override
  public int execute(Machine machine) {
    int value1 = machine.getRegisters().get(source);
    int value2 = machine.getRegisters().get(modifier);
    machine.getRegisters().set(source, value1 / value2);
    return NORMAL_PROGRAM_COUNTER_UPDATE;
  }

  /**
   * @return A string containing the label (if applicable), the opcode, the source, and the modifier.
   * @author gburto03
   */
  @Override
  public String toString() {
    return getLabelString() + getOpcode() + " " + source + " " + modifier;
  }

  @Override
  public int hashCode() {
    return Objects.hash(getLabel(), getOpcode(), this.source, this.modifier);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof DivInstruction other) {
      return Objects.equals(this.getLabel(), other.getLabel())
              && Objects.equals(this.getOpcode(), other.getOpcode())
              && Objects.equals(this.source, other.source)
              && Objects.equals(this.modifier, other.modifier);
    }
    return false;
  }
}
