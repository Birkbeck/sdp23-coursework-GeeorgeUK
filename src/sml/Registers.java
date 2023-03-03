package sml;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>Registers</h1>
 * <p>The Registers class is used to create and log different registers.</p>
 * @author gburto03
 */
public final class Registers {
  private final Map<Register, Integer> registers = new HashMap<>();

  // Decoupling the register to the register package.
  public enum Register implements RegisterName {
    EAX, EBX, ECX, EDX, ESP, EBP, ESI, EDI
  }

  public Registers() {
    clear(); // the class is final
  }

  public void clear() {
    for (Register register : Register.values())
      registers.put(register, 0);
  }

  /**
   * Sets the given register to the value.
   *
   * @param register register name
   * @param value    new value
   */
  public void set(RegisterName register, int value) {
    registers.put((Register) register, value);
  }

  /**
   * Returns the value stored in the register.
   *
   * @param register register name
   * @return value
   */
  public int get(RegisterName register) {
    return registers.get((Register) register);
  }

  /**
   * Overrides the equals method to compare with other objects.
   * @param o The other register to compare with.
   * @return A boolean result of whether the two are equal.
   * @author gburto03
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof Registers other) {
      return registers.equals(other.registers);
    }
    return false;
  }

  /**
   * @return A unique integer for this instance.
   * @author gburto03
   */
  @Override
  public int hashCode() {
    return registers.hashCode();
  }

  /**
   * Gets a formatted string representation of this register.
   * @return A string representation of the register.
   */
  @Override
  public String toString() {
    return registers.entrySet().stream()
            .sorted(Map.Entry.comparingByKey())
            .map(e -> e.getKey() + " = " + e.getValue())
            .collect(Collectors.joining(", ", "[", "]"));
  }
}
