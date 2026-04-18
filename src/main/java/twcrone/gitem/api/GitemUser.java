package twcrone.gitem.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record GitemUser(
    @JsonProperty("user_name") String userName,
    @JsonProperty("display_name") String displayName,
    @JsonProperty("avatar") String avatar,
    @JsonProperty("geo_location") String geoLocation,
    String email,
    String url,
    @JsonProperty("created_at") String createdAt,
    List<GitemRepo> repos
) {
}