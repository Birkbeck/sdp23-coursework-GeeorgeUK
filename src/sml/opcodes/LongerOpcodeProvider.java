package sml.opcodes;

import sml.OpcodeProvider;

import java.util.HashMap;
import java.util.Map;

public class LongerOpcodeProvider implements OpcodeProvider {
  @Override
  public String getOpcode(String toConvert) {
    Map<String, String> converter = new HashMap<>();
    // Map each instruction to its type.
    // The key is the opcode this hypothetical machine reads.
    // The value is the opcode our translator understands.
    converter.put("add", "add");
    converter.put("subtract", "sub");
    converter.put("multiply", "mul");
    converter.put("divide", "div");
    converter.put("notzero", "jnz");
    converter.put("print", "out");
    converter.put("move", "mov");
    // Return just what we need.
    return converter.get(toConvert);
  }
}
