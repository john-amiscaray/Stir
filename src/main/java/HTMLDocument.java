import java.util.ArrayList;
import java.util.List;

public class HTMLDocument {

    private List<Object> elements = new ArrayList<>();
    private ElementProcessor processor = new ElementProcessor();

    public static class Builder{

        private HTMLDocument doc;

        public Builder(){
            doc = new HTMLDocument();
        }

        public HTMLDocument.Builder addElement(Object element){
            doc.elements.add(element);
            return this;
        }

        public HTMLDocument build(){
            return doc;
        }

    }

}
