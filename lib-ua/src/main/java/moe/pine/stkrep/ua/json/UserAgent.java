package moe.pine.stkrep.ua.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserAgent(
        @JsonProperty("percent") String percent,
        @JsonProperty("useragent") String name,
        @JsonProperty("system") String system
) {
}
