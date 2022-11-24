package io.john.amiscaray.stub;

import io.john.amiscaray.annotation.ChildList;
import io.john.amiscaray.annotation.HTMLElement;
import io.john.amiscaray.domain.elements.Input;

import java.util.ArrayList;
import java.util.List;

@HTMLElement(tagName = "form")
public class FormWithChildList {

    @ChildList
    private List<Input> fields = new ArrayList<>();

    public static class Builder{

        private final FormWithChildList form;

        public Builder(){

            form = new FormWithChildList();

        }

        public Builder addInput(Input field){

            form.fields.add(field);
            return this;

        }

        public FormWithChildList build(){

            return form;

        }

    }

}
