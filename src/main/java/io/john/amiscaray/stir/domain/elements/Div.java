package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@HTMLElement(tagName = "div")
@AllArgsConstructor
@NoArgsConstructor
public class Div extends AbstractUIElement{

    @ChildList
    private List<AbstractUIElement> children = new ArrayList<>();

    public static Builder builder(){

        return new Builder();

    }

    public static class Builder {

        private final Div div = new Div();

        public Builder addClass(String clazz){

            div.addClass(clazz);
            return this;

        }

        public Builder id(String id){

            div.id = id;
            return this;

        }

        public Builder addChild(AbstractUIElement child){

            div.children.add(child);
            return this;

        }

        public Div build(){

            return div;

        }

    }

}
