package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ClassList;
import io.john.amiscaray.stir.annotation.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElement {

    @Id
    @Getter
    @Setter
    protected String id;

    @ClassList
    @Getter
    @Setter
    protected List<String> classList = new ArrayList<>();

}
