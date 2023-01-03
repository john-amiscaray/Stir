# Stir

A Java Framework for generating HTML content and UI development for server-side rendering. Stir allows you to generate UIs using simple Java objects whose fields act as inner content and attributes for the HTML elements they represent. All of these objects can be created using the familiar builder pattern or using simple constructors. See the [Java Docs](https://john-amiscaray.github.io/Stir/index.html) for more information.

## Table of Contents

- [Installation](#installation)
- [Building a Simple Page](#building-a-simple-page)
- [Supported Elements](#supported-elements)
- [Document Templating](#document-templating)
- [Creating Custom Elements or Components](#creating-custom-elements-or-components)
- [Caching Support](#caching-support)
- [Document Querying](#document-querying)
- [Examples](#examples)

## Installation

This project can be added to a project as a Maven dependency. See the package on the right-hand side of the repository home page for installation instructions.

## Building a Simple Page

Pages can be generated using Stir's `HTMLDocument` class. Using simple builder patterns and built-in classes you can generate large amounts of reusable markup for your applications:

```java
import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.*;

public class Main {

   public static void main(String[] args) {

       HTMLDocument document = HTMLDocument
               .builder()
               .title("My Cool Web Page")
               .element(new Nav(NavLinkList.builder()
                       .navLink(NavLink.fromLabelAndHref("Home", "/home"))
                       .navLink(NavLink.fromLabelAndHref("Products", "/products"))
                       .navLink(NavLink.fromLabelAndHref("About Us", "/about"))
                       .build()))
               .element(Article.builder()
                       .child(Heading.builder()
                               .content("Welcome to my web page")
                               .level(1)
                               .build())
                       .child(new Paragraph("Lorem Ipsum something something"))
                       .build())
               .withBootStrap(true)
               .withBootStrapPopper(true)
               .build();

       System.out.println(document.generateDocumentString());

   }

}
```

The above Java code outputs the following markup:

```html
<!DOCTYPE html>
<html lang="en">
   <head>
       <meta charset="UTF-8">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <title>My Cool Web Page</title>
       <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
   </head>
   <body>
       <nav>
           <ul>
               <li>
                   <a href="/home">
                       Home
                   </a>
               </li>
               <li>
                   <a href="/products">
                       Products
                   </a>
               </li>
               <li>
                   <a href="/about">
                       About Us
                   </a>
               </li>
           </ul>
       </nav>
       <article>
           <h1>
               Welcome to my web page
           </h1>
           <p>
               Lorem Ipsum something something
           </p>
       </article>
       <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
       <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
   </body>
</html>
```

The backbone for how all this markup gets generated is the `ElementProcessor` class whose role is to take elements using its `getMarkup` method or styles with its `processStyle` method and generate the appropriate HTML or CSS. Use of this class should be handled within the `HTMLDocument` class but if you need to use it manually, you can get an instance of it using the static `getInstance` method.

## Supported Elements

As of the latest version, Stir has built-in support for the following elements:
- Anchor
- Article
- Button
- Div
- Footer
- Form
- Header
- Heading
- HGroup
- IFrame
- Img
- Input
- Linked CSS styles
- List items
- Meta tags
- Nav
- Nav links
- Ordered List
- Paragraph
- Script
- Section
- Select (with options)
- Style
- Table
- Unordered list

See the [Javadocs](https://john-amiscaray.github.io/Stir/io/john/amiscaray/stir/domain/elements/package-summary.html) in this repository for more info on the classes for each of these elements

## Document Templating

As of version 0.3.0, Stir comes with a simple templating language for your HTML documents, so you can interpolate Stir generated markup into an HTML template. To do this, you can place keywords into a *templating block* (delimited by an opening `<&` tag and a closing `&>` tag). Different keywords can specify different content you set using the `HTMLDocument`'s builder. As an example, see the following code:

```java
import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.*;

public class Main {
    
    private final Div sampleContent = Div.builder()
            .child(new Heading(1, "Hello World"))
            .child(new Paragraph("This is the content"))
            .build();
    
    public static void main(String[] args){

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="<& str_lang &>">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <& str_meta &>
                                <& str_hscripts &>
                                <& str_lstyles &>
                                <& str_styles &>
                                <title><& str_title &></title>
                            </head>
                            <body>
                                <hgroup>
                                    <h1><& str_title &></h1>
                                    <h2>Wowo Much Cool!</h2>
                                </hgroup>
                                <div class="content-wrapper">
                                    <& str_content &>
                                </div>
                                <& str_fscripts &>
                            </body>
                        </html>""")
                .element(sampleContent)
                .title("Hello World")
                .language("fr")
                .footerScript(new Script("./main.js"))
                .headerScript(new Script("./script.js"))
                .linkedStyle(new LinkedStyle("./styles.css"))
                .style(new Style("""
                        div {
                            color: red;
                        }
                        """))
                .metaTag(Meta.builder()
                        .content("thing")
                        .name("sample")
                        .build())
                .build();
        
        System.out.println(doc.generateDocumentString());
        
    }
    
}
```

This prints the following HTML markup

```html
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="sample" content="thing">
        <script src="./script.js"></script>
        <link href="./styles.css" rel="stylesheet">
        <style>
            div {
                color: red;
            }
        </style>
        <title>Hello World</title>
    </head>
    <body>
        <hgroup>
            <h1>Hello World</h1>
            <h2>Wowo Much Cool!</h2>
        </hgroup>
        <div class="content-wrapper">
            <div>
                <h1>
                    Hello World
                </h1>
                <p>
                    This is the content
                </p>
            </div>
        </div>
        <script src="./main.js"></script>
    </body>
</html>
```

Below is a list of all the formatting keywords and their meanings:

- `str_content`: All the elements added to the document
- `str_title`: The title of the page
- `str_meta`: The meta tags for the page
- `str_hscripts`: The header scripts for the page
- `str_fscripts`: The footer scripts for the page
- `str_lang`: The language of the page
- `str_styles`: A style tag added to the page (set using the HTMLDocument builder style method
- `str_lstyles`: Linked style sheets for the page


## Creating Custom Elements or Components

This framework provides custom annotations for creating your own elements or components if the need arises. Every class representing HTML elements must be annotated with the `@HTMLElement` annotation so the `ElementProcessor` class knows what to do with it. This annotation has options such as the tag name and whether the element type requires a closing tag. Additionally, every element must extend the `AbstractUIElement` class which contains a list of CSS classes, an element ID, and comes with caching support from the `CacheableElement` class.

### Adding Attributes

Attributes for your HTML element can be specified using the `@Attribute` annotation on any field you wish to use as an attribute. With this annotation, you can specify the name of the attribute and a default attribute value to use in case of a null field.

### Adding Child Elements

Child elements can be added using the `@ChildList` annotation, the `@Nested` annotation, or a combination of either. `@ChildList` marks any `List` field that contains children of the HTML element, whereas the `@Nested` field marks a single HTML element field as a child of the HTML element representing the class.

### Inner Content

The inner content of the HTML element can be specified with the `@InnerContent` annotation. This marks a field as being the inner content of the HTML element.

### Labels

The `@Label` annotation can be used to mark a property as being the inner content of a label for the given HTML element. From this, the `ElementProcessor` class can generate an HTML label element for the corresponding HTML element. For this label to be generated, however, an ID must be set on the object representing the HTML element.

## Caching Support

As an optimization to the HTML generation, we added support for the caching of HTML content using the `CacheableElement` class. This class gets inherited by all the built-in elements in this framework giving them a `cacheContents` field, a `cacheStatus` field describing the cache freshness, and a `cacheDisabled` field for disabling caching on that element. Upon first generating the HTML content for a given object, the `ElementProcessor` class will fill the element's cache contents for use in the next call. The `CacheableElement` class updates the cache status of the element every time the cache is written to, and when a setter is called on the original object using a callback function. An event can be triggered for the callback function using the `propertyChangeSupport` property of the `CacheableElement` class using its `firePropertyChange` method from the `PropertyChangeSupport` class. Thus, it's important to note that **the `firePropertyChange` method must be called in the setter of an HTMLElement for proper caching.**

### Disabling Caching for an Element

To disable caching for an element, update the `cacheDisabled` field using the appropriate setter.

## Document Querying

The `HTMLDocument` class provides support for querying its elements using the familiar `querySelector` method which takes a CSS query and returns a list of all the elements added to the document which match that query. At the time of writing this, the `querySelector` method supports every CSS query feature except for pseudo-classes and pseudo-elements.

## Examples

Example uses of this framework can be found [here](https://github.com/john-amiscaray/StirExamples/blob/master/spring-app-example/src/main/java/io/john/amiscaray/springappexample/controller/EmployeeController.java).
