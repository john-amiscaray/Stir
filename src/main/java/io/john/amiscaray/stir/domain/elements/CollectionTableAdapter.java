package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ObjectTable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;

@Data
@AllArgsConstructor
public class CollectionTableAdapter {

    @ObjectTable
    private Collection<?> collection;

    private Class<?> clazz;

}
