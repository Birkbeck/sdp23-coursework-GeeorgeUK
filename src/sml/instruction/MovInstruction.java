package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

/**
 * <b>Represents a MOV instruction.</b>
 * A mov instruction inserts a value into a register.
 * This value can either be a raw value or another register,
 * provided this other register has a value inside it.
 * @author gburto03
 */
public class MovInstruction extends Instruction {
    private final RegisterName register;
    private Integer value;
    private RegisterName source;

    public static final String OP_CODE = "mov";

    // Initialises the MOV instruction for adding a value to a register.
    public MovInstruction(String label, RegisterName register, Integer value) {
        super(label, OP_CODE);
        this.register = register;
        this.value = value;
    }

    // Initialises the MOV instruction for copying a register to a register.
    public MovInstruction(String label, RegisterName register, RegisterName source) {
        super(label, OP_CODE);
        this.register = register;
        this.source = source;
    }

    @Override
    public int execute(Machine m) {
        if (source == null && value != null) {
            m.getRegisters().set(register, value);
        } else {
            m.getRegisters().set(register, m.getRegisters().get(source));
        }
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + register + " " + value.toString();
    }
}
