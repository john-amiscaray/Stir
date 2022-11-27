package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ClassList;
import io.john.amiscaray.stir.annotation.Id;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElement {

    @Id
    protected String id;

    @ClassList
    protected List<String> classList = new ArrayList<>();

}
