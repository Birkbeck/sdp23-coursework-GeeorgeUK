package sml.equals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import sml.Labels;


class LabelsEqualsTest {

  @Test
  void testLabelsEqual() {
    Labels labels0 = new Labels();
    Labels labels1 = new Labels();
    labels0.addLabel("example", 5);
    labels1.addLabel("example", 5);
  }

  @Test
  void testLabelsWithDifferentAddresses() {
    Labels labels0 = new Labels();
    Labels labels1 = new Labels();
    labels0.addLabel("here", 1);
    labels1.addLabel("here", 2);
    Assertions.assertNotEquals(labels0, labels1);
  }

  @Test
  void testLabelsWithDifferentNames() {
    Labels labels0 = new Labels();
    Labels labels1 = new Labels();
    labels0.addLabel("according", 1);
    labels1.addLabel("to", 1);
    Assertions.assertNotEquals(labels0, labels1);
  }

  @Test
  void testLabelsNWithNoEquals() {
    Labels labels0 = new Labels();
    Labels labels1 = new Labels();
    labels0.addLabel("hello", 1);
    labels1.addLabel("world", 2);
    Assertions.assertNotEquals(labels0, labels1);
  }
}
