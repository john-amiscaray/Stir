package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import io.john.amiscaray.stir.domain.elements.AbstractUIElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@HTMLElement(tagName = "p")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Paragraph extends AbstractUIElement {

    @InnerContent
    private String content;

    public static Builder builder(){

        return new Builder();

    }

    public static class Builder{

        private Paragraph paragraph = new Paragraph();

        public Builder content(String content){

            paragraph.content = content;
            return this;

        }

        public Builder id(String id){

            paragraph.id = id;
            return this;

        }

        public Builder addClass(String clazz){

            paragraph.addClass(clazz);
            return this;

        }

        public Paragraph build(){

            return paragraph;

        }

    }

}
