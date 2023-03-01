package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Represents the labels, an optional way to identify an instruction.
 * <p>
 * A label can be referenced by a separate instruction to
 * jump to the associated instruction.
 */
public final class Labels {
  private final Map<String, Integer> labels = new HashMap<>();

  /**
   * Adds a label with the associated address to the map.
   *
   * @param label   the label
   * @param address the address the label refers to
   */
  public void addLabel(String label, int address) {
    Objects.requireNonNull(label);
    // Check to see if the label is already in there.
    // If it is, we have a duplicate.
    // Note that prior to version 1.0-alpha12 duplicates
    //  were ignored by the Jnz Instruction. Now, we throw a runtime exception
    //  with an explanation as to why the program failed.
    if (labels.containsKey(label)) {
      throw new RuntimeException("Labels must be unique, but duplicates were found. Please rename any duplicates.");
    }
    labels.put(label, address);
  }

  /**
   * Returns the address associated with the label.
   *
   * @param label the label
   * @return the address the label refers to
   */
  public int getAddress(String label) {
    // A null pointer exception will occur when the label does not exist.
    // This is because the array does not contain the key with any value.

    return labels.get(label);
  }

  /**
   * representation of this instance,
   * in the form "[label -> address, label -> address, ..., label -> address]"
   *
   * @return the string representation of the labels map
   */
  @Override
  public String toString() {
    // TODO: Implement the method using the Stream API (see also class Registers).
    return "";
  }

  /**
   * The hashCode() method generates a unique integer to be used in comparison.
   * @return A unique integer to be used in comparison.
   */
  public int hashCode() {
    return Objects.hash(labels);
  }

  /**
   * The equals() method compares this labels array with another label array.
   *
   * @param o The labels array we are comparing with.
   * @return A boolean for whether it matches.
   */
  public boolean equals(Object o) {
    if (o instanceof Labels other) {
      return Objects.equals(this.labels, other.labels);
    }
    return false;
  }

  /**
   * Removes the labels
   */
  public void reset() {
    labels.clear();
  }
}
