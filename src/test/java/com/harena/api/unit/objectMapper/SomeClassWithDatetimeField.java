package com.harena.api.unit.objectMapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
public class SomeClassWithDatetimeField {
    @JsonProperty("datetimeField")
    private Instant datetimeField;

    public String toJsonString() {
        return "{\"datetimeField\": \" " + this.datetimeField + "\"}";
    }
}
