package twcrone.gitem.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GithubLicense(
    String key,
    String name,
    @JsonProperty("spdx_id") String spdxId,
    String url,
    @JsonProperty("node_id") String nodeId
) {}
