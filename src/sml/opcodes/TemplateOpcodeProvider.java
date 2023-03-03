package sml.opcodes;

import sml.OpcodeProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * <h1>TemplateOpcodeProvider</h1>
 * <p>This class is designed to make it easy to add your own opcode translations
 * in order to allow sml files with non-standard opcodes to function properly. </p>
 * <p>This is done by filling in the key of each entry below with an associated
 * opcode, then updating the beans.xml file to point at your custom implementation
 * of OpcodeProvider.</p>
 * <p>Additional opcodes can be added, provided there is an associated instruction
 * located in the sml.instruction package.</p>
 * @author gburto03
 */
public class TemplateOpcodeProvider implements OpcodeProvider {
  @Override
  public String getOpcode(String toConvert) {
    Map<String, String> converter = new HashMap<>();
    // This is the section you should edit once this file is copied to a new provider file.
    converter.put("myAdd", "add");
    converter.put("mySub", "sub");
    converter.put("myMul", "mul");
    converter.put("myDiv", "div");
    converter.put("myJnz", "jnz");
    converter.put("myOut", "out");
    converter.put("myMov", "mov");

    return converter.get(toConvert);
  }
}
