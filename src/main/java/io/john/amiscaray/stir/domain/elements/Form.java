package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "form")
@NoArgsConstructor
@AllArgsConstructor
public class Form extends AbstractUIElement {

    @ChildList
    private List<Input> fields = new ArrayList<>();

    @Attribute(name = "method", defaultValue = "get")
    @Getter
    private String method;

    @Attribute(name="action", defaultValue = "/")
    @Getter
    private String action;

    public void setMethod(String method) {
        propertyChangeSupport.firePropertyChange("method", this.method, method);
        this.method = method;
    }

    public void setAction(String action) {
        propertyChangeSupport.firePropertyChange("action", this.action, action);
        this.action = action;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{

        private final Form form;

        public Builder(){

            form = new Form();

        }

        public Builder addField(Input input){

            form.fields.add(input);
            return this;

        }

        public Builder method(String method){

            form.method = method;
            return this;

        }

        public Builder action(String action){

            form.action = action;
            return this;

        }

        public Builder id(String id){

            form.id = id;
            return this;

        }

        public Builder addClass(String clazz){

            form.classList.add(clazz);
            return this;

        }

        public Form build(){

            return form;

        }

    }

}
