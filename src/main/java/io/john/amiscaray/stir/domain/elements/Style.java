package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@HTMLElement(tagName="link", hasClosing = false)
public class Style extends AbstractElement{

    @Attribute(name="href", defaultValue="./styles.css")
    private final String href;
    @Attribute(name="rel")
    private final String rel = "stylesheet";
    @Attribute(name="integrity")
    private String integrity;
    @Attribute(name="crossorigin")
    private String crossOrigin;

    public static Builder builder(){ return new Builder(); }

    public static class Builder {

        private String href;
        private String integrity;
        private String crossOrigin;
        private String id;
        private final List<String> classList = new ArrayList<>();

        public Builder href(String href){
            this.href = href;
            return this;
        }

        public Builder integrity(String integrity){
            this.integrity = integrity;
            return this;
        }

        public Builder crossOrigin(String crossOrigin){
            this.crossOrigin = crossOrigin;
            return this;
        }

        public Builder id(String id){
            this.id = id;
            return this;
        }

        public Builder addClass(String clazz){
            classList.add(clazz);
            return this;
        }

        public Style build(){
            Style result = new Style(href, integrity, crossOrigin);
            result.setId(id);
            result.setClassList(classList);
            return result;
        }

    }

}
