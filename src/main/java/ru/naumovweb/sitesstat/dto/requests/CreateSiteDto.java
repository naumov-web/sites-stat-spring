package ru.naumovweb.sitesstat.dto.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CreateSiteDto {
    @NotNull(message = "Name of site cannot be null")
    private final String name = null;

    @NotNull(message = "Host of site cannot be null")
    @URL(message = "Host of site must contain of valid URL")
    private final String host = null;
}
