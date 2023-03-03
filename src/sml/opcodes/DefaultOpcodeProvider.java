package sml.opcodes;

import sml.OpcodeProvider;

public class DefaultOpcodeProvider implements OpcodeProvider {
  @Override
  public String getOpcode(String toConvert) {
    // With the default one we just return what we had originally
    return toConvert;
  }
}
