package com.harena.api.unit.objectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.harena.api.conf.FacadeIT;
import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;

public class ObjectMapperTest extends FacadeIT {
  @Autowired ObjectMapper injectedBean;
  ObjectMapper newInstance = new ObjectMapper();

  SomeClassWithDatetimeField someClassWithDatetimeField =
      new SomeClassWithDatetimeField(Instant.now());

  //    @Test
  //    void new_instance_throws_on_java_datetime_module() {
  //        String jsonString = someClassWithDatetimeField.toJsonString();
  //        assertThrows(
  //                InvalidDefinitionException.class,
  //                () -> newInstance.readValue(jsonString, SomeClassWithDatetimeField.class));
  //    }
  //
  //    @Test
  //    void injected_bean_handles_java_datetime_module() {
  //        assertDoesNotThrow(
  //                () ->
  //                        injectedBean.readValue(
  //                                someClassWithDatetimeField.toJsonString(),
  // SomeClassWithDatetimeField.class));
  //    }
}
