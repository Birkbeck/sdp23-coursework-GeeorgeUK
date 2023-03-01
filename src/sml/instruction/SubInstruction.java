package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

/**
 * <h1>SubInstruction</h1>
 * <p>A subclass of <b>Instruction</b>, the <b>subtract</b> or <b>SUB</b> instruction
 * subtracts the value of register B from the value stored in register A, storing
 * the resulting value back into register A.</p>
 * @author gburto03
 */
public class SubInstruction extends Instruction {
  private final RegisterName source;
  private final RegisterName modifier;

  public static final String OP_CODE = "sub";

  /**
   * This method initialises the SubInstruction class.
   *
   * @param label    An optional string label.
   * @param source   The source register. This is where the updated value is stored.
   * @param modifier The modifier register. This contains the value we will subtract from
   *                 the source value.
   * @author gburto03
   */
  public SubInstruction(String label, RegisterName source, RegisterName modifier) {
    super(label, OP_CODE);
    this.source = source;
    this.modifier = modifier;
  }

  /**
   * This method overrides the abstract method for the subtraction instruction.
   *
   * @param machine The machine in which we execute this instruction.
   * @return Returns an integer representing the program counter update.
   * @author gburto03
   */
  @Override
  public int execute(Machine machine) {
    int value1 = machine.getRegisters().get(source);
    int value2 = machine.getRegisters().get(modifier);
    machine.getRegisters().set(source, value1 - value2);
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

  /**
   * Overrides the hashcode to include source and modifier.
   * This ensures we can effectively compare two different instructions.
   * @return A unique integer for comparison
   * @author gburto03
   */
  @Override
  public int hashCode() {
    return Objects.hash(getLabel(), getOpcode(), this.source, this.modifier);
  }

  /**
   * Overrides the equals in this subclass.
   * @param o The object to compare
   * @return If this object is equal to o
   * @author gburto03
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof SubInstruction other) {
      return Objects.equals(this.getLabel(), other.getLabel())
              && Objects.equals(this.getOpcode(), other.getOpcode())
              && Objects.equals(this.source, other.source)
              && Objects.equals(this.modifier, other.modifier);
    }
    return false;
  }
}
