package twcrone.gitem.api;

import java.io.Serializable;

public record GitemRepo(
        String name,
        String url
) implements Serializable {
}
