package io.john.amiscaray.stir.domain.elements;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.InnerContent;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

/**
 * A pojo representing an option element for a {@link Select select} element
 */
@HTMLElement(tagName = "option")
@NoArgsConstructor
public class Option extends AbstractUIElement{

    /**
     * The value attribute of the option element
     */
    @Attribute(name = "value")
    private String value;

    /**
     * The inner content of the option element
     */
    @InnerContent
    private String optionName;

    public Option(String optionName) {
        this.optionName = optionName;
        this.value = optionName.toLowerCase(Locale.ROOT);
    }

    public Option(String id, List<String> cssClasses, String style, String value, String optionName, boolean hidden) {
        super(id, cssClasses, style, hidden);
        this.value = value;
        this.optionName = optionName;
    }

    public Option(String value, String optionName) {
        this.value = value;
        this.optionName = optionName;
    }

    public static OptionBuilder builder() {
        return new OptionBuilder();
    }

    public String getValue() {
        return this.value;
    }

    public String getOptionName() {
        return this.optionName;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public String toString() {
        return "Option(value=" + this.getValue() + ", optionName=" + this.getOptionName() + ")";
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Option)) return false;
        final Option other = (Option) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        final Object this$value = this.getValue();
        final Object other$value = other.getValue();
        if (this$value == null ? other$value != null : !this$value.equals(other$value)) return false;
        final Object this$optionName = this.getOptionName();
        final Object other$optionName = other.getOptionName();
        if (this$optionName == null ? other$optionName != null : !this$optionName.equals(other$optionName))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Option;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = super.hashCode();
        final Object $value = this.getValue();
        result = result * PRIME + ($value == null ? 43 : $value.hashCode());
        final Object $optionName = this.getOptionName();
        result = result * PRIME + ($optionName == null ? 43 : $optionName.hashCode());
        return result;
    }

    public static class OptionBuilder {
        private String id;
        private ArrayList<String> cssClasses;
        private String style;
        private String value;
        private String optionName;
        private boolean hidden;

        OptionBuilder() {
        }

        public OptionBuilder id(String id) {
            this.id = id;
            return this;
        }

        public OptionBuilder cssClass(String cssClass) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.add(cssClass);
            return this;
        }

        public OptionBuilder cssClasses(Collection<? extends String> cssClasses) {
            if (this.cssClasses == null) this.cssClasses = new ArrayList<String>();
            this.cssClasses.addAll(cssClasses);
            return this;
        }

        public OptionBuilder clearCssClasses() {
            if (this.cssClasses != null)
                this.cssClasses.clear();
            return this;
        }

        public OptionBuilder style(String style) {
            this.style = style;
            return this;
        }

        public OptionBuilder value(String value) {
            this.value = value;
            return this;
        }

        public OptionBuilder optionName(String optionName) {
            this.optionName = optionName;
            return this;
        }

        public OptionBuilder hidden(boolean hidden) {
            this.hidden = hidden;
            return this;
        }

        public Option build() {
            List<String> cssClasses;
            switch (this.cssClasses == null ? 0 : this.cssClasses.size()) {
                case 0:
                    cssClasses = java.util.Collections.emptyList();
                    break;
                case 1:
                    cssClasses = java.util.Collections.singletonList(this.cssClasses.get(0));
                    break;
                default:
                    cssClasses = java.util.Collections.unmodifiableList(new ArrayList<String>(this.cssClasses));
            }

            return new Option(id, cssClasses, style, value, optionName, hidden);
        }

        public String toString() {
            return "Option.OptionBuilder(id=" + this.id + ", cssClasses=" + this.cssClasses + ", style=" + this.style + ", value=" + this.value + ", optionName=" + this.optionName + ", hidden=" + this.hidden + ")";
        }
    }
}
