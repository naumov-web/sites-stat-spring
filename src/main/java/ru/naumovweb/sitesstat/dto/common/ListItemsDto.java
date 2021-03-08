package ru.naumovweb.sitesstat.dto.common;

import lombok.Data;

import java.util.List;

@Data
public class ListItemsDto<T> {
    private List<T> items;
    private Long count;
}