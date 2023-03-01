package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.*;
import java.util.stream.IntStream;

/**
 * <h1>JnzInstruction</h1>
 * <p>A subclass of <b>Instruction</b>, the <b>j</b>ump if <b>n</b>ot <b>z</b>ero or <b>JNZ</b>
 * instruction jumps to an instruction if the value stored in a register is not zero.
 * If the resulting value is zero, we continue to the next instruction.</p>
 * @author gburto03
 */
public class JnzInstruction extends Instruction {
  private final RegisterName source;
  private final String destination;

  public static final String OP_CODE = "jnz";

  /**
   * This method initialises the JnzInstruction class.
   *
   * @param label       An optional string label.
   * @param source      The source register. This is what we are checking for a zero.
   * @param destination The label of the destination. This will jump to the earliest found
   *                    instance of a label, so later duplicates will be ignored.
   */
  public JnzInstruction(String label, RegisterName source, String destination) {
    super(label, OP_CODE);
    this.source = source;
    this.destination = destination;
  }

  /**
   * This method overrides the abstract method for the jump instruction.
   *
   * @param machine The machine in which we execute this instruction.
   * @return Returns an integer representing the position in the program.
   *         This will either be an incremented value or a specific index in the program.
   * @author gburto03
   */
  @Override
  public int execute(Machine machine) {
    int value = machine.getRegisters().get(source);
    // We continue as normal if the value is zero.
    if ( value == 0 ) {
      return NORMAL_PROGRAM_COUNTER_UPDATE;
    } else {
      // Here we get the saved list of instructions from the program.
      List<Instruction> program = machine.getProgram();

      // Here we get an integer stream from this list of programs based on its size,
      // filtering out any entries in which the label is not the destination.
      IntStream programIntStream = IntStream.range(0, program.size())
              .filter(index -> Objects.equals(program.get(index).getLabel(), destination));

      // Here we grab the first instruction with this label. We can avoid
      // using complex try brackets by using an OptionalInt.
      OptionalInt newIndex = programIntStream.findFirst();

      // Return the new index of the program head, or an increment if we
      // are not able to find the destination.
      return newIndex.orElse(NORMAL_PROGRAM_COUNTER_UPDATE);
    }
  }

  /**
   * @return A string containing the label (if applicable, the opcode, the source, and the destination.
   * @author gburto03
   */
  @Override
  public String toString() {
    return getLabelString() + getOpcode() + " " + source + " " + destination;
  }

  /**
   * Overrides the hashcode to include source and modifier.
   * This ensures we can effectively compare two different instructions.
   * @return A unique integer for comparison
   * @author gburto03
   */
  @Override
  public int hashCode() {
    return Objects.hash(getLabel(), getOpcode(), this.source, this.destination);
  }

  /**
   * Overrides the equals in this subclass.
   * @param o The object to compare
   * @return If this object is equal to o
   * @author gburto03
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof JnzInstruction other) {
      return Objects.equals(this.getLabel(), other.getLabel())
              && Objects.equals(this.getOpcode(), other.getOpcode())
              && Objects.equals(this.source, other.source)
              && Objects.equals(this.destination, other.destination);
    }
    return false;
  }
}
