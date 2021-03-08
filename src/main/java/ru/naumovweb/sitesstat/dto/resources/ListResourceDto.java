package ru.naumovweb.sitesstat.dto.resources;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.naumovweb.sitesstat.dto.common.ListItemsDto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class ListResourceDto<T> {

    ListItemsDto<T> itemsDto;
    Class itemResourceDto;
    Class<T> type;

    public Map<String, Object> getResponse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Object> items = new ArrayList<>();
        Method method = itemResourceDto.getMethod("fromOrigin", type);

        for (int i = 0; i < itemsDto.getItems().size(); i++) {
            items.add(
                    method.invoke(null, itemsDto.getItems().get(i))
            );
        }

        Map<String, Object> response = new HashMap<>();
        response.put("count", itemsDto.getCount());
        response.put("items", items);

        return response;
    }
}
