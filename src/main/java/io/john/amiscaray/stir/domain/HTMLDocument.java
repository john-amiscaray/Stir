package io.john.amiscaray.stir.domain;

import io.john.amiscaray.stir.annotation.Attribute;
import io.john.amiscaray.stir.annotation.ChildList;
import io.john.amiscaray.stir.annotation.HTMLElement;
import io.john.amiscaray.stir.annotation.Nested;
import io.john.amiscaray.stir.annotation.exceptions.IllegalElementException;
import io.john.amiscaray.stir.domain.elements.*;
import io.john.amiscaray.stir.util.ElementProcessor;
import io.john.amiscaray.stir.util.FormatProcessor;
import io.john.amiscaray.stir.util.exceptions.TemplatingException;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A pojo representing an HTML document
 */
public class HTMLDocument {

    /**
     * The elements within the body of the document
     */
    private final List<AbstractUIElement> elements;
    /**
     * The styles linked to the document
     */
    private final List<LinkedStyle> linkedStyles;
    /**
     * A style tag in the header of the document
     */
    private final Style style;
    /**
     * The scripts in the head of the document
     */
    private final List<Script> headerScripts;
    /**
     * The scripts in the bottom of the body
     */
    private final List<Script> footerScripts;
    /**
     * The meta tags of the document
     */
    private final List<Meta> metaTags;
    /**
     * The title of the page
     */
    private String title;

    private final FormatProcessor formatProcessor = FormatProcessor.getInstance();
    /**
     * The language of the page
     */
    private String language = "en";
    /**
     * The template for the document
     */
    private String format =
            """
            <!DOCTYPE html>
            <html lang="<& str_lang &>">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <& str_meta &>
                    <title><& str_title &></title>
                    <& str_hscripts &>
                    <& str_lstyles &>
                    <& str_styles &>
                </head>
                <body>
                    <& str_content &>
                    <& str_fscripts &>
                </body>
            </html>""";

    /**
     * The custom templating keywords/arguments for interpolation into the document format. See the <a href="https://github.com/john-amiscaray/Stir#formatting-arguments">README</a> for more info
     */
    private Map<String, Object> formatArgs = new HashMap<>();

    public HTMLDocument(List<AbstractUIElement> elements, List<LinkedStyle> linkedStyles, Style style,
                        List<Script> headerScripts, List<Script> footerScripts, List<Meta> metaTags,
                        boolean withBootStrap, boolean withBootStrapPopper, boolean withWaterCSS, ColorTheme waterCSSTheme,
                        String title, String language, String format, Map<String, Object> formatArgs) {
        this.elements = elements;
        this.linkedStyles = new ArrayList<>(linkedStyles);
        this.style = style;
        this.headerScripts = new ArrayList<>(headerScripts);
        this.footerScripts = new ArrayList<>(footerScripts);
        this.metaTags = metaTags;
        this.title = title;
        this.language = language;
        if(withBootStrap){
            this.footerScripts.add(Script.builder()
                            .src("https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js")
                            .integrity("sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD")
                            .crossOrigin("anonymous")
                            .build());

            this.linkedStyles.add(LinkedStyle.builder()
                            .href("https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css")
                            .integrity("sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD")
                            .crossOrigin("anonymous")
                            .build());
        }
        if(withBootStrapPopper){
            this.footerScripts.add(Script.builder()
                    .src("https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js")
                    .integrity("sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3")
                    .crossOrigin("anonymous")
                    .build());
        }

        if(withWaterCSS){
            if(waterCSSTheme == null){
                waterCSSTheme = ColorTheme.AUTO;
            }
            switch (waterCSSTheme){
                case LIGHT -> this.linkedStyles.add(LinkedStyle.builder()
                                .href("https://cdn.jsdelivr.net/npm/water.css@2/out/light.css")
                                .build());
                case DARK -> this.linkedStyles.add(LinkedStyle.builder()
                        .href("https://cdn.jsdelivr.net/npm/water.css@2/out/dark.css")
                        .build());
                default -> this.linkedStyles.add(LinkedStyle.builder()
                        .href("https://cdn.jsdelivr.net/npm/water.css@2/out/water.css")
                        .build());
            }
        }

        if(format != null){
            this.format = format;
        }
        if(formatArgs != null){
            long numInvalidKeys = formatArgs.keySet()
                    .stream()
                    .filter(entry -> entry.matches("(.*\\s.*)+") || entry.startsWith("str_") || entry.matches("element(.*)")).count();
            if(numInvalidKeys > 0){
                throw new TemplatingException("Invalid format arg given. Format keys must not have whitespace, start with str_, or match this regex: element(.*)");
            }
            this.formatArgs = formatArgs;
        }
        if(language == null){
            this.language = "en";
        }
        if(title == null){
            this.title = "Title";
        }
    }

    public static HTMLDocumentBuilder builder() {
        return new HTMLDocumentBuilder();
    }

    /**
     * Generates the HTML markup of the document
     * @return The HTML string
     */
    public String generateDocumentString(){

        return formatProcessor.processDocument(this);

    }

    /**
     * Retrieves a list of elements added to the document that match with the given CSS query. Note that this implementation
     * does not currently support pseudo-elements nor pseudo-classes.
     * @param query The CSS selector to select elements with
     * @return All elements that match that query
     */
    public List<AbstractUIElement> querySelector(String query){

        return querySelector(query, this.elements);

    }

    /**
     * Query selector applied to a specific set of elements
     * @param query The CSS selector to select elements with
     * @param elements The elements to test
     * @return All elements that match that query
     */
    private static List<AbstractUIElement> querySelector(String query, List<AbstractUIElement> elements){

        String[] subQueries = query.split(",");
        return Arrays.stream(subQueries)
                .map(subQuery -> processQuery(subQuery, elements))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }

    /**
     * Processes a CSS query/subquery for the query selector method
     * @param query The CSS query
     * @param elements The elements to test
     * @return All elements that match that query
     */
    private static List<AbstractUIElement> processQuery(String query, List<AbstractUIElement> elements){

        if(elements.isEmpty()){
            return elements;
        }

        List<String> tokens = new ArrayList<>();
        Pattern pattern = Pattern.compile("[^.\\\"]*\\[.*\\]|[^ \\[\\]]+");
        Matcher matcher = pattern.matcher(query);
        while (matcher.find()){
            tokens.add(matcher.group());
        }

        List<AbstractUIElement> lastResult = null;
        String lastToken = null;
        for (String token : tokens) {
            if(!isCssSelectorOperator(token)){
                if(lastResult == null){
                    lastResult = processToken(token, elements);
                }else{
                    switch (lastToken) {
                        case ">" -> lastResult = processToken(
                                token,
                                lastResult.stream()
                                        .map(HTMLDocument::getAllDirectDescendents)
                                        .flatMap(Collection::stream)
                                        .collect(Collectors.toList())
                        );
                        case "+" -> lastResult = processToken(
                                token,
                                lastResult.stream()
                                        .map(element -> {
                                            int idx = elements.indexOf(element);
                                            return idx < elements.size() - 1 ? elements.get(idx + 1) : null;
                                        })
                                        .filter(Objects::nonNull)
                                        .collect(Collectors.toList())
                        );
                        case "~" -> {
                            if (lastResult.isEmpty()) {
                                continue;
                            }
                            AbstractUIElement lastElement = lastResult.get(0);
                            int indexOfLastEl = elements.indexOf(lastElement);
                            lastResult = processToken(
                                    token,
                                    elements.stream()
                                            .filter(element -> {
                                                int idx = elements.indexOf(element);
                                                return idx > indexOfLastEl && !processToken(token, List.of(element)).isEmpty();
                                            })
                                            .collect(Collectors.toList())
                            );
                        }
                        default -> lastResult = processToken(
                                token,
                                lastResult.stream()
                                        .map(HTMLDocument::getAllDescendents)
                                        .flatMap(Collection::stream)
                                        .collect(Collectors.toList())
                        );
                    }
                }
            }

            lastToken = token;
        }

        List<AbstractUIElement> finalResult = new ArrayList<>();
        if(lastResult != null){
            finalResult.addAll(lastResult);
        }

        finalResult.addAll(
            elements
                    .stream()
                    .map(HTMLDocument::getAllDirectDescendents)
                    .map(list -> processQuery(query, list))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList())
        );

        return new ArrayList<>(new LinkedHashSet<>(finalResult));

    }

    /**
     * Processes extracted tokens (space separated) within a CSS query
     * @param query The CSS query
     * @param elements The elements to test
     * @return All elements that match that query
     */
    private static List<AbstractUIElement> processToken(String query, List<AbstractUIElement> elements){

        Pattern pattern = Pattern.compile("(\\[[^\\[\\]]+])+$");
        Matcher matcher = pattern.matcher(query);
        if(matcher.find()){
            int idx = matcher.start();
            String attributeSelectors = query.substring(idx);
            elements = processAttributeSelector(attributeSelectors, elements);
            query = query.substring(0, idx);
        }

        if(!query.contains("#") && !query.contains(".")){
            return findAllOfTagName(query, elements);
        }

        List<Character> terminators = List.of('#', '.');
        List<String> names = Arrays.stream(query.split("[#.]"))
                .filter(str -> !str.isEmpty())
                .collect(Collectors.toList());
        List<Character> queryTypes = query.chars()
                .mapToObj(e -> (char) e)
                .filter(terminators::contains)
                .collect(Collectors.toList());

        assert names.size() == queryTypes.size();

        List<AbstractUIElement> currentElements = elements;

        for(int i = 0; i < names.size(); i++){

            if(queryTypes.get(i).equals('#')){
                currentElements = findAllOfID(names.get(i), currentElements);
            }else if(queryTypes.get(i).equals('.')){
                currentElements = findAllOfClass(names.get(i), currentElements);
            }

        }

        return currentElements;

    }

    /**
     * Filters all elements of a tag name
     * @param tagName The tag name
     * @param elements The elements to filter
     * @return The filtered elements
     */
    private static List<AbstractUIElement> findAllOfTagName(String tagName, List<AbstractUIElement> elements){

        if(tagName.equals("*")){
            return elements;
        }

        return elements.stream().filter(element -> {
            Class<?> clazz = element.getClass();
            if(!clazz.isAnnotationPresent(HTMLElement.class) && !(element instanceof Table)){
                throw new IllegalElementException("The class " + clazz.getName() + " is not marked as a valid UI HTML element");
            }
            String elementTag = clazz.isAnnotationPresent(HTMLElement.class) ?
                    clazz.getAnnotation(HTMLElement.class).tagName() :
                    "table";
            // The element must be a table if it does not have the HTMLElement annotation
            return tagName.equals(elementTag);

        }).collect(Collectors.toList());

    }

    /**
     * Filters all elements with an ID
     * @param id The ID of the element
     * @param elements The elements to filter
     * @return The filtered elements
     */
    private static List<AbstractUIElement> findAllOfID(String id, List<AbstractUIElement> elements){

        return elements.stream().filter(element -> element.getId() != null && element.getId().equals(id)).collect(Collectors.toList());

    }

    /**
     * Filters a list of elements with a CSS class
     * @param clazz The CSS class
     * @param elements The elements to filter through
     * @return The filtered elements
     */
    private static List<AbstractUIElement> findAllOfClass(String clazz, List<AbstractUIElement> elements){

        return elements.stream().filter(element -> element.getCssClasses().contains(clazz)).collect(Collectors.toList());

    }

    /**
     * Gets all direct descendents of an HTML element
     * @param ancestor The element to get the children of
     * @return The direct descendents of the element
     */
    public static List<AbstractUIElement> getAllDirectDescendents(AbstractUIElement ancestor){

        Class<?> clazz = ancestor.getClass();
        List<AbstractUIElement> children = new ArrayList<>();
        ElementProcessor processor = ElementProcessor.getInstance();
        try {
            for (Field field : processor.getAllFields(clazz)) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Nested.class)) {
                    children.add((AbstractUIElement) field.get(ancestor));
                }else if(field.isAnnotationPresent(ChildList.class)) {
                    List<AbstractUIElement> childList = (List<AbstractUIElement>) field.get(ancestor);
                    children.addAll(childList);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return children;

    }

    /**
     * Recursively finds all the descendents of an element
     * @param ancestor The element to find the descendents of
     * @return The descendents
     */
    public static List<AbstractUIElement> getAllDescendents(AbstractUIElement ancestor){

        List<AbstractUIElement> directDescendents = getAllDirectDescendents(ancestor);
        return Stream.concat(directDescendents.stream()
                .map(HTMLDocument::getAllDescendents)
                .flatMap(Collection::stream), directDescendents.stream())
                .collect(Collectors.toList());

    }

    /**
     * Processes a <a href="https://www.w3schools.com/css/css_attribute_selectors.asp">CSS attribute selector</a>
     * @param query The CSS query
     * @param elements The elements to filter through
     * @return The filtered elements
     */
    public static List<AbstractUIElement> processAttributeSelector(String query, List<AbstractUIElement> elements){

        // TODO make this pattern not match any spaces in the square brackets and not in quotes
        Pattern pattern = Pattern.compile("\\[[^\\[\\]]+]");
        Matcher matcher = pattern.matcher(query);
        //List<String> operators = List.of("=", "~=", "|=", "^=", "$=", "*=");
        List<AbstractUIElement> currentElements = elements;
        while(matcher.find()){
            String match = matcher.group();
            match = match.substring(1, match.length() - 1);
            String[] keyValue = match.split("=|~=|\\|=|\\^=|\\$=|\\*=", 2);
            assert keyValue.length == 2 || keyValue.length == 1;
            String key = keyValue[0];
            String value = keyValue.length == 2 ? keyValue[1] : null;
            if(value != null && value.charAt(0) == '"' && value.charAt(value.length() - 1) == '"'){
                value = value.substring(1, value.length() - 1);
            }
            if(match.contains("~=")){
                String finalValue = value;
                currentElements = filterForAttributes(key, v -> {
                    if(v.equals(finalValue)){
                        return true;
                    }
                    String pat = "(^" + finalValue + " )|( " + finalValue + " )|( "+ finalValue +"$)";
                    Pattern p = Pattern.compile(pat);
                    Matcher m = p.matcher(v);
                    assert finalValue != null;
                    return m.find() && v.contains(finalValue);
                }, currentElements);
            }else if(match.contains("|=")) {
                String finalValue = value;
                currentElements = filterForAttributes(key, v -> {
                        if(v.equals(finalValue)){
                            return true;
                        }
                        Pattern p = Pattern.compile(".+(-.*)+");
                        Matcher m = p.matcher(v);
                        assert finalValue != null;
                        return m.matches() && v.contains(finalValue);
                    }, currentElements);
            }else if(match.contains("^=")) {
                String finalValue = value;
                assert finalValue != null;
                currentElements = filterForAttributes(key, v -> v.startsWith(finalValue), currentElements);
            }else if(match.contains("$=")){
                String finalValue = value;
                assert finalValue != null;
                currentElements = filterForAttributes(key, v -> v.endsWith(finalValue), currentElements);
            }else if(match.contains("*=")) {
                String finalValue = value;
                assert finalValue != null;
                currentElements = filterForAttributes(key, v -> v.contains(finalValue), currentElements);
            }else if(match.contains("=")) {
                String finalValue = value;
                currentElements = filterForAttributes(key, v -> v.equals(finalValue), currentElements);
            }else{
                currentElements = filterForAttributes(key, v -> true, currentElements);
            }
        }

        return currentElements;

    }

    /**
     * Whether a token is a CSS operator (i.e. >, +, ~)
     * @param token The token to test
     * @return The boolean result
     */
    private static boolean isCssSelectorOperator(String token){

        Pattern pattern = Pattern.compile("^[>+~]$");
        Matcher matcher = pattern.matcher(token);
        return matcher.find();

    }

    /**
     * Filters elements that have some attribute and whose attribute value satisfies some predicate
     * @param attributeName The name of the attribute
     * @param predicate The predicate to test on the attribute value
     * @param elements The elements to filter through
     * @return The filtered elements
     */
    private static List<AbstractUIElement> filterForAttributes(String attributeName, Predicate<String> predicate,
                                                               List<AbstractUIElement> elements){

        ElementProcessor processor = ElementProcessor.getInstance();

        return elements.stream()
                .filter(element -> {

                    if(attributeName.equals("class")){
                        String classAttValue = element.getCssClasses().stream().reduce("", (s1, s2) -> s1 + " " + s2).trim();
                        return element.getCssClasses() != null
                                && !element.getCssClasses().isEmpty()
                                && predicate.test(classAttValue);
                    }else if(attributeName.equals("id")){
                        return element.getId() != null && predicate.test(element.getId());
                    }

                    Field[] fields = processor.getAllFields(element.getClass());
                    for(Field field : fields){
                        field.setAccessible(true);
                        if(!field.isAnnotationPresent(Attribute.class) || !attributeName.equals(field.getName())){
                            continue;
                        }
                        try {
                            return predicate.test(field.get(element).toString());
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    return false;
                }).collect(Collectors.toList());
    }

    public List<AbstractUIElement> getElements() {
        return this.elements;
    }

    public List<LinkedStyle> getLinkedStyles() {
        return linkedStyles;
    }

    public Style getStyle() {
        return style;
    }

    public List<Script> getHeaderScripts() {
        return headerScripts;
    }

    public List<Script> getFooterScripts() {
        return footerScripts;
    }

    public List<Meta> getMetaTags() {
        return metaTags;
    }

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language;
    }

    public String getFormat() {
        return format;
    }

    public Map<String, Object> getFormatArgs() {
        return formatArgs;
    }

    public enum ColorTheme {

        LIGHT,
        DARK,
        AUTO

    }

    public static class HTMLDocumentBuilder {
        private ArrayList<AbstractUIElement> elements;
        private ArrayList<LinkedStyle> linkedStyles;
        private Style style;
        private ArrayList<Script> headerScripts;
        private ArrayList<Script> footerScripts;
        private ArrayList<Meta> metaTags;
        private boolean withBootStrap;
        private boolean withBootStrapPopper;
        private boolean withWaterCSS;
        private ColorTheme waterCSSTheme;
        private String title;
        private String language;
        private String format;
        private ArrayList<String> formatArgs$key;
        private ArrayList<Object> formatArgs$value;

        HTMLDocumentBuilder() {
        }

        public HTMLDocumentBuilder element(AbstractUIElement element) {
            if (this.elements == null) this.elements = new ArrayList<AbstractUIElement>();
            this.elements.add(element);
            return this;
        }

        public HTMLDocumentBuilder elements(Collection<? extends AbstractUIElement> elements) {
            if (this.elements == null) this.elements = new ArrayList<AbstractUIElement>();
            this.elements.addAll(elements);
            return this;
        }

        public HTMLDocumentBuilder clearElements() {
            if (this.elements != null)
                this.elements.clear();
            return this;
        }

        public HTMLDocumentBuilder linkedStyle(LinkedStyle linkedStyle) {
            if (this.linkedStyles == null) this.linkedStyles = new ArrayList<LinkedStyle>();
            this.linkedStyles.add(linkedStyle);
            return this;
        }

        public HTMLDocumentBuilder linkedStyles(Collection<? extends LinkedStyle> linkedStyles) {
            if (this.linkedStyles == null) this.linkedStyles = new ArrayList<LinkedStyle>();
            this.linkedStyles.addAll(linkedStyles);
            return this;
        }

        public HTMLDocumentBuilder clearLinkedStyles() {
            if (this.linkedStyles != null)
                this.linkedStyles.clear();
            return this;
        }

        public HTMLDocumentBuilder style(Style style) {
            this.style = style;
            return this;
        }

        public HTMLDocumentBuilder headerScript(Script headerScript) {
            if (this.headerScripts == null) this.headerScripts = new ArrayList<Script>();
            this.headerScripts.add(headerScript);
            return this;
        }

        public HTMLDocumentBuilder headerScripts(Collection<? extends Script> headerScripts) {
            if (this.headerScripts == null) this.headerScripts = new ArrayList<Script>();
            this.headerScripts.addAll(headerScripts);
            return this;
        }

        public HTMLDocumentBuilder clearHeaderScripts() {
            if (this.headerScripts != null)
                this.headerScripts.clear();
            return this;
        }

        public HTMLDocumentBuilder footerScript(Script footerScript) {
            if (this.footerScripts == null) this.footerScripts = new ArrayList<Script>();
            this.footerScripts.add(footerScript);
            return this;
        }

        public HTMLDocumentBuilder footerScripts(Collection<? extends Script> footerScripts) {
            if (this.footerScripts == null) this.footerScripts = new ArrayList<Script>();
            this.footerScripts.addAll(footerScripts);
            return this;
        }

        public HTMLDocumentBuilder clearFooterScripts() {
            if (this.footerScripts != null)
                this.footerScripts.clear();
            return this;
        }

        public HTMLDocumentBuilder metaTag(Meta metaTag) {
            if (this.metaTags == null) this.metaTags = new ArrayList<Meta>();
            this.metaTags.add(metaTag);
            return this;
        }

        public HTMLDocumentBuilder metaTags(Collection<? extends Meta> metaTags) {
            if (this.metaTags == null) this.metaTags = new ArrayList<Meta>();
            this.metaTags.addAll(metaTags);
            return this;
        }

        public HTMLDocumentBuilder clearMetaTags() {
            if (this.metaTags != null)
                this.metaTags.clear();
            return this;
        }

        public HTMLDocumentBuilder withBootStrap(boolean withBootStrap) {
            this.withBootStrap = withBootStrap;
            return this;
        }

        public HTMLDocumentBuilder withBootStrapPopper(boolean withBootStrapPopper) {
            this.withBootStrapPopper = withBootStrapPopper;
            return this;
        }

        public HTMLDocumentBuilder withWaterCSS(boolean withWaterCSS) {
            this.withWaterCSS = withWaterCSS;
            return this;
        }

        public HTMLDocumentBuilder waterCSSTheme(ColorTheme waterCSSTheme) {
            this.waterCSSTheme = waterCSSTheme;
            return this;
        }

        public HTMLDocumentBuilder title(String title) {
            this.title = title;
            return this;
        }

        public HTMLDocumentBuilder language(String language) {
            this.language = language;
            return this;
        }

        public HTMLDocumentBuilder format(String format) {
            this.format = format;
            return this;
        }

        public HTMLDocumentBuilder formatArg(String formatArgKey, Object formatArgValue) {
            if (this.formatArgs$key == null) {
                this.formatArgs$key = new ArrayList<String>();
                this.formatArgs$value = new ArrayList<Object>();
            }
            this.formatArgs$key.add(formatArgKey);
            this.formatArgs$value.add(formatArgValue);
            return this;
        }

        public HTMLDocumentBuilder formatArgs(Map<? extends String, ?> formatArgs) {
            if (this.formatArgs$key == null) {
                this.formatArgs$key = new ArrayList<String>();
                this.formatArgs$value = new ArrayList<Object>();
            }
            for (final Map.Entry<? extends String, ?> $lombokEntry : formatArgs.entrySet()) {
                this.formatArgs$key.add($lombokEntry.getKey());
                this.formatArgs$value.add($lombokEntry.getValue());
            }
            return this;
        }

        public HTMLDocumentBuilder clearFormatArgs() {
            if (this.formatArgs$key != null) {
                this.formatArgs$key.clear();
                this.formatArgs$value.clear();
            }
            return this;
        }

        public HTMLDocument build() {
            List<AbstractUIElement> elements;
            switch (this.elements == null ? 0 : this.elements.size()) {
                case 0:
                    elements = Collections.emptyList();
                    break;
                case 1:
                    elements = Collections.singletonList(this.elements.get(0));
                    break;
                default:
                    elements = Collections.unmodifiableList(new ArrayList<AbstractUIElement>(this.elements));
            }
            List<LinkedStyle> linkedStyles;
            switch (this.linkedStyles == null ? 0 : this.linkedStyles.size()) {
                case 0:
                    linkedStyles = Collections.emptyList();
                    break;
                case 1:
                    linkedStyles = Collections.singletonList(this.linkedStyles.get(0));
                    break;
                default:
                    linkedStyles = Collections.unmodifiableList(new ArrayList<LinkedStyle>(this.linkedStyles));
            }
            List<Script> headerScripts;
            switch (this.headerScripts == null ? 0 : this.headerScripts.size()) {
                case 0:
                    headerScripts = Collections.emptyList();
                    break;
                case 1:
                    headerScripts = Collections.singletonList(this.headerScripts.get(0));
                    break;
                default:
                    headerScripts = Collections.unmodifiableList(new ArrayList<Script>(this.headerScripts));
            }
            List<Script> footerScripts;
            switch (this.footerScripts == null ? 0 : this.footerScripts.size()) {
                case 0:
                    footerScripts = Collections.emptyList();
                    break;
                case 1:
                    footerScripts = Collections.singletonList(this.footerScripts.get(0));
                    break;
                default:
                    footerScripts = Collections.unmodifiableList(new ArrayList<Script>(this.footerScripts));
            }
            List<Meta> metaTags;
            switch (this.metaTags == null ? 0 : this.metaTags.size()) {
                case 0:
                    metaTags = Collections.emptyList();
                    break;
                case 1:
                    metaTags = Collections.singletonList(this.metaTags.get(0));
                    break;
                default:
                    metaTags = Collections.unmodifiableList(new ArrayList<Meta>(this.metaTags));
            }
            Map<String, Object> formatArgs;
            switch (this.formatArgs$key == null ? 0 : this.formatArgs$key.size()) {
                case 0:
                    formatArgs = Collections.emptyMap();
                    break;
                case 1:
                    formatArgs = Collections.singletonMap(this.formatArgs$key.get(0), this.formatArgs$value.get(0));
                    break;
                default:
                    formatArgs = new LinkedHashMap<String, Object>(this.formatArgs$key.size() < 1073741824 ? 1 + this.formatArgs$key.size() + (this.formatArgs$key.size() - 3) / 3 : Integer.MAX_VALUE);
                    for (int $i = 0; $i < this.formatArgs$key.size(); $i++)
                        formatArgs.put(this.formatArgs$key.get($i), (Object) this.formatArgs$value.get($i));
                    formatArgs = Collections.unmodifiableMap(formatArgs);
            }

            return new HTMLDocument(elements, linkedStyles, style, headerScripts, footerScripts, metaTags, withBootStrap, withBootStrapPopper, withWaterCSS, waterCSSTheme, title, language, format, formatArgs);
        }

        public String toString() {
            return "HTMLDocument.HTMLDocumentBuilder(elements=" + this.elements + ", linkedStyles=" + this.linkedStyles + ", style=" + this.style + ", headerScripts=" + this.headerScripts + ", footerScripts=" + this.footerScripts + ", metaTags=" + this.metaTags + ", withBootStrap=" + this.withBootStrap + ", withBootStrapPopper=" + this.withBootStrapPopper + ", withWaterCSS=" + this.withWaterCSS + ", waterCSSTheme=" + this.waterCSSTheme + ", title=" + this.title + ", language=" + this.language + ", format=" + this.format + ", formatArgs$key=" + this.formatArgs$key + ", formatArgs$value=" + this.formatArgs$value + ")";
        }
    }

}
