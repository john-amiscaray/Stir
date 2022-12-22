package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ObjectTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class CollectionTableAdapter extends AbstractUIElement {

    @ObjectTable
    private Collection<?> collection;

    private Class<?> clazz;

}
