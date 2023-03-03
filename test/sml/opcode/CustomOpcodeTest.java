package sml.opcode;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.PropertiesBeanDefinitionReader;

import java.nio.file.Paths;
import java.util.Properties;


public class CustomOpcodeTest {

  private String currentPath;

  @BeforeEach
  void setUp() {
    currentPath = Paths.get("").toAbsolutePath().toString() + "/test/sml/example/";
  }

  @AfterEach
  void tearDown() {
    currentPath = null;
  }

  @Test
  void testBeanFile() {};
}
