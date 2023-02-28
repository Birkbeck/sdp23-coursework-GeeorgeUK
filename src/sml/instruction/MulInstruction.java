package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * <h1>MulInstruction</h1>
 * <p>A subclass of <b>Instruction</b>, a <b>multiply</b> or <b>MUL</b> instruction
 * multiples the value stored in register A by the value stored in register B,
 * storing the resulting value back into register A.</p>
 * @author gburto03
 */
public class MulInstruction extends Instruction {
  private final RegisterName source;
  private final RegisterName modifier;

  public static final String OP_CODE = "mul";

  /**
   * This method initialises the MulInstruction class.
   *
   * @param label    An optional string label.
   * @param source   The source register. This is where the updated value is stored.
   * @param modifier The modifier register. This contains the value in which we multiply
   *                 the source value.
   * @author gburto03
   */
  public MulInstruction(String label, RegisterName source, RegisterName modifier) {
    super(label, OP_CODE);
    this.source = source;
    this.modifier = modifier;
  }

  /**
   * This method overrides the abstract method for the multiplication instruction.
   *
   * @param machine The machine in which we execute this instruction.
   * @return Returns an integer representing the program counter update.
   * @author gburto03
   */
  @Override
  public int execute(Machine machine) {
    int value1 = machine.getRegisters().get(source);
    int value2 = machine.getRegisters().get(modifier);
    machine.getRegisters().set(source, value1 * value2);
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
}
