package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;

import java.util.ArrayList;
import java.util.List;

@HTMLElement(tagName = "div")
public class Div extends AbstractUIElement {

    @ChildList
    private List<AbstractUIElement> children = new ArrayList<>();

    public static Builder builder(){

        return new Builder();

    }

    public static class Builder{

        private final Div result = new Div();

        public Builder addChild(AbstractUIElement child){

            result.children.add(child);
            return this;

        }

        public Builder setChildren(List<AbstractUIElement> children){

            result.children = children;
            return this;

        }

        public Builder id(String id){

            result.id = id;
            return this;

        }

        public Builder addChild(String clazz){

            result.classes.add(clazz);
            return this;

        }

        public Div build(){

            return result;

        }

    }

    @Override
    public String toString() {
        return "Div{" +
                "id='" + id + '\'' +
                ", classList=" + classes +
                ", children=" + children +
                '}';
    }
}
