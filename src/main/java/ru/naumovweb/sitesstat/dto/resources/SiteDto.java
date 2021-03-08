package ru.naumovweb.sitesstat.dto.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.naumovweb.sitesstat.models.Site;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SiteDto {
    private Long id;
    private String name;
    private String host;
    private Date created_at;

    public static SiteDto fromOrigin(Site origin) {
        return new SiteDto(
                origin.getId(),
                origin.getName(),
                origin.getHost(),
                origin.getCreated_at()
        );
    }
}
