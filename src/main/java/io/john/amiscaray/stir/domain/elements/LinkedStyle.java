package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@RequiredArgsConstructor
@AllArgsConstructor
@HTMLElement(tagName="link", hasClosing = false)
public class LinkedStyle extends AbstractUIElement {

    @Attribute(name="href", defaultValue="./styles.css")
    @Getter
    private final String href;
    @Attribute(name="rel")
    @Getter
    private final String rel = "stylesheet";
    @Attribute(name="integrity")
    @Getter
    private String integrity;
    @Attribute(name="crossorigin")
    @Getter
    private String crossOrigin;

    public void setIntegrity(String integrity) {
        propertyChangeSupport.firePropertyChange("integrity", this.integrity, integrity);
        this.integrity = integrity;
    }

    public void setCrossOrigin(String crossOrigin) {
        propertyChangeSupport.firePropertyChange("crossOrigin", this.crossOrigin, crossOrigin);
        this.crossOrigin = crossOrigin;
    }

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

        public LinkedStyle build(){
            LinkedStyle result = new LinkedStyle(href, integrity, crossOrigin);
            result.setId(id);
            result.setCssClasses(classList);
            return result;
        }

    }

}
