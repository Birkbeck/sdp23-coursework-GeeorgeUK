package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

//TODO: write a JavaDoc for this class.

/**
 * @author
 */

public class OutInstruction extends Instruction {
    private final RegisterName register;

    public static final String OP_CODE = "out";

    public OutInstruction(String label, RegisterName register) {
        super(label, OP_CODE);
        this.register = register;
    }

    @Override
    public int execute(Machine m) {
        System.out.println(m.getRegisters().get(register));
        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + register;
    }
}
