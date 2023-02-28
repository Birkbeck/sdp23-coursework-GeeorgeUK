package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * <h1>MovInstruction</h1>
 * <p>A subclass of <b>Instruction</b>, a <b>move</b> or <b>MOV</b> instruction
 * stores the specified integer into register A.</p><p>Another register can be provided in
 * place of an integer to save the value stored in this other register into register A.</p>
 * @author gburto03
 */
public class MovInstruction extends Instruction {
  private final RegisterName source;
  private Integer value;
  private RegisterName modifier;

  public static final String OP_CODE = "mov";

  /**
   * This method initialises the DivInstruction class in cases where an integer is provided.
   * @param label An optional string label.
   * @param source The source register. This is where the value provided will be stored.
   * @param value The value we wish to store in the register.
   * @author gburto03
   */
  public MovInstruction(String label, RegisterName source, Integer value) {
    super(label, OP_CODE);
    this.source = source;
    this.value = value;
  }

  /**
   * This method initialises the DivInstruction class in cases where a second register is provided.
   * @param label An optional string label.
   * @param source The source register. This is where the value contained in the
   *               modifier register will be stored.
   * @param modifier The modifier register. This is where the value we wish to store in the source
   *                 register is currently located.
   * @author gburto03
   */
  public MovInstruction(String label, RegisterName source, RegisterName modifier) {
    super(label, OP_CODE);
    this.source = source;
    this.modifier = modifier;
  }

  /**
   * This method overrides the abstract method for the move instruction.
   * @param machine The machine on which we execute the instruction.
   * @return Returns an integer representing the program counter update.
   * @author gburto03
   */
  @Override
  public int execute(Machine machine) {
    // Are we handling a register or an integer?
    if (modifier == null && value != null) {
      machine.getRegisters().set(source, value);
    } else if (modifier != null && value == null) {
      machine.getRegisters().set(source, machine.getRegisters().get(modifier));
    }
    return NORMAL_PROGRAM_COUNTER_UPDATE;
  }

  /**
   * @return A string containing the label (if applicable), the opcode, the source, and the modifier.
   * The modifier can be either an integer or a register address.
   * @author gburto03
   */
  @Override
  public String toString() {
    if (modifier == null && value != null ) {
      return getLabelString() + getOpcode() + " " + source + " " + value.toString();
    }
    return getLabelString() + getOpcode() + " " + source + " " + modifier.toString();
  }
}