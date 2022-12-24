package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@HTMLElement(tagName = "p")
public class Paragraph extends AbstractUIElement {

    @InnerContent
    @Getter
    private String content;

    public void setContent(String content) {
        propertyChangeSupport.firePropertyChange("content", this.content, content);
        this.content = content;
    }

    public static Builder builder(){

        return new Builder();

    }

    public static class Builder {

        private final Paragraph p = new Paragraph();

        public Builder content(String content){

            p.content = content;
            return this;

        }

        public Builder addClass(String clazz){

            p.addClass(clazz);
            return this;

        }

        public Builder id(String id){

            p.id = id;
            return this;

        }

        public Paragraph build(){

            return p;

        }

    }

}
