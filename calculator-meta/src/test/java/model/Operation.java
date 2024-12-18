package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class Operation {
    public String id;
    public Double firstArgument;
    public Double secondArgument;
    public Double result;
    public String calculatedAt;

    @JsonCreator
    public Operation(@JsonProperty("id") String id, @JsonProperty("firstArgument") double firstArgument,
                     @JsonProperty("secondArgument") double secondArgument, @JsonProperty("result") double result,
                     @JsonProperty("calculatedAt") String calculatedAt) {
        this.id = id;
        this.firstArgument = firstArgument;
        this.secondArgument = secondArgument;
        this.result = result;
        this.calculatedAt = calculatedAt;
    }
}
