# Stir

<img src="./assets/logo.png" alt="logo" width="250"/>

A feature-rich Java framework for server-side rendering. Stir allows you to generate UIs using simple Java objects resembling POJOs, [document templating](#document-templating), and [element descriptors](#element-descriptors). See the [Java Docs](https://john-amiscaray.github.io/Stir/index.html) for more information.

## Table of Contents

- [Installation](#installation)
- [Building a Simple Page Using Java Objects](#building-a-simple-page-using-java-objects)
- [Supported Elements](#supported-elements)
- [Element Descriptors](#element-descriptors)
- [Document Templating](#document-templating)
- [Creating Custom Elements or Components](#creating-custom-elements-or-components)
- [Caching Support](#caching-support)
- [Bootstrap Integration](#bootstrap-integration)
- [Water CSS Integration](#water-css-integration)
- [Document Querying](#document-querying)
- [Examples](#examples)

## Installation

This framework can be added to a project using the following Maven dependency:

```xml
<dependency>
    <groupId>io.john-amiscaray.stir</groupId>
    <artifactId>stir</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Building a Simple Page Using Java Objects

The most basic way to generate pages with Stir would be with the `HTMLDocument` class and the Stir provided element classes. Using simple builder patterns and built-in classes representing HTML elements, you can generate large amounts of reusable markup for your applications:

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

While this example looks slightly verbose, as you'll see later, we provide tools like [element descriptors](#element-descriptors) and [document templating](#document-templating) to reduce the amount of code required.

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

## Element Descriptors

Version 0.5.0 introduced a new compact syntax for generating HTML markup using a language similar to CSS selectors. These are known as **element descriptors**. Element descriptors allow you to initialize an `AbstractUIElement` using a string following Stir's element descriptor language. To do this, simply call the static `ElementDescriptorProcessor.element` method with the element descriptor as a string. As an example, take the following code:

```java
import io.john.amiscaray.stir.domain.elements.Form;

// Import all the static methods of the ElementDescriptorProcessor class so we can use the methods as simple function calls.
import static io.john.amiscaray.stir.util.ElementDescriptorProcessor.*;

class Example {
    
    private Form myForm = (Form) element("form#my-form.red.blue.green[action='/login',method='post']{input[type='text'],input[type='password']}");
    
}
```

This is equivalent to:

```java
import io.john.amiscaray.stir.domain.elements.Form;


class Example {
    
    private Form myForm = Form.builder()
            .cssClasses(new ArrayList<>(List.of("red", "blue", "green")))
            .action("/login")
            .method("post")
            .id("my-form")
            .field(Input.builder()
                    .type("text")
                    .build())
            .field(Input.builder()
                    .type("password")
                    .build());
    
}
```
Here, content enclosed in square brackets describe the HTML attributes for the form and the content enclosed in curly brackets describe the inner children of the form. Element descriptors may also describe the inner text content of an element like so:

```java
element("p.red.blue.green('This is the content!')");
```

which is equivalent to:

```java
Paragraph.builder()
    .cssClass("red")
    .cssClass("blue")
    .cssClass("green")
    .content("This is the content!")
    .build();
```

For additional info on element descriptors, see the [v0.5.0 release notes](https://github.com/john-amiscaray/Stir/releases/tag/v0.5.0).

> Note that v0.5.0 is an unstable pre-release and should not be used. In that version, there is a bug which causes the element descriptors to fail in production. This is fixed in v0.5.1. The release notes, however, give a detailed explanation on element descriptors with content not mentioned here.

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

Below is a list of all the built-in formatting keywords and their meanings:

- `str_content`: All the elements added to the document
- `str_title`: The title of the page
- `str_meta`: The meta tags for the page
- `str_hscripts`: The header scripts for the page
- `str_fscripts`: The footer scripts for the page
- `str_lang`: The language of the page
- `str_styles`: A style tag added to the page (set using the HTMLDocument builder style method
- `str_lstyles`: Linked style sheets for the page

By default, if no format is set for the document, the following layout will be used:

```html
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
</html>
```

### Template Scope

Since version 0.8.0, there is an option to limit the scope of your template to only be for the document body. Simply set the `isFormatForBody` option to true in the `HTMLDocument` builder. The outer content of the template should match the default template shown above.

### Formatting Arguments

From version 0.4.1 onwards, you can now add formatting arguments to your template, allowing you to interpolate custom content into your document format using formatting blocks. To do so, all you need to do is call the `formatArgs` method of the `HTMLDocument`'s builder class with a key and value. The key is what you can use in any formatting block to specify to the `FormatProcessor` that you want the corresponding value to be placed in the given area of the template. For example take the following Java code:

```java
import io.john.amiscaray.stir.domain.HTMLDocument;
import io.john.amiscaray.stir.domain.elements.*;
import java.util.LinkedHashMap;
import java.util.List;

import io.john.amiscaray.stir.stub.StudentWithTableAnnotation;

public class Main {
    
    private final Div sampleContent = Div.builder()
            .child(new Heading(1, "Hello World"))
            .child(new Paragraph("This is the content"))
            .build();
    
    public static void main(String[] args){

        LinkedHashMap<String, String> navMap = new LinkedHashMap<>();
        navMap.put("Home", "/home");
        navMap.put("Products", "/products");
        navMap.put("About", "/about");

        List<StudentWithTableAnnotation> students = List.of(
                new StudentWithTableAnnotation(1, "Susan", 4.0f),
                new StudentWithTableAnnotation(2, "Karen", 0.1f),
                new StudentWithTableAnnotation(3, "George", 3.2f)
        );

        HTMLDocument doc = HTMLDocument.builder()
                .format("""
                        <!DOCTYPE html>
                        <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                <title><& str_title &></title>
                            </head>
                            <body>
                                <& nav &>
                                <h1><& str_title &></h1>
                                <& str_content &>
                            </body>
                        </html>""")
                .title("My Document")
                .formatArg("nav", Nav.fromLabelHrefMap(navMap))
                .element(Div.builder()
                        .id("main-content")
                        .child(new Table(students, StudentWithTableAnnotation.class))
                        .build())
                .build();
        
        System.out.println(doc.generateDocumentString());
        
    }
    
}
```

Where the `StudentWithTableAnnotation` class is defined as so:

```java
package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.TableData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentWithTableAnnotation implements Comparable{

    @TableData(columnName = "Student ID")
    private Integer studentId;
    @TableData(columnName = "Name")
    private String name;
    @TableData(columnName = "GPA")
    private Float gpa;

    @Override
    public int compareTo(Object o) {
        return ((StudentWithTableAnnotation) o).studentId.compareTo(this.studentId);
    }
}
```

The above code outputs the following markup:

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Document</title>
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
                        About
                    </a>
                </li>
            </ul>
        </nav>
        <h1>My Document</h1>
        <div id="main-content">
            <table>
                <thead>
                    <tr>
                        <th>Student ID</th>
                        <th>Name</th>
                        <th>GPA</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Susan</td>
                        <td>4.0</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Karen</td>
                        <td>0.1</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>George</td>
                        <td>3.2</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>
```

Format args with a value of an `AbstractUIElement` will have the corresponding markup of that element be added to the format. Likewise, args of a List of `AbstractUIElement` instances will have the markup of each instance be added sequentially. Otherwise, the format processor will call the `toString` method of whatever value the argument is.

### Element Descriptor Support

[Element Descriptors](https://github.com/john-amiscaray/Stir#element-descriptors) can be added in templating blocks to render the HTML of the corresponding object directly in the template. Simply use the following syntax: `element(<element descriptor>)`. For example:

```java
FormatProcessor formatProcessor = FormatProcessor.getInstance();

HTMLDocument doc = HTMLDocument.builder()
       .format("""
               <!DOCTYPE html>
               <html lang="en">
                   <head>
                       <meta charset="UTF-8">
                       <meta name="viewport" content="width=device-width, initial-scale=1.0">
                       <title>Hello World</title>
                   </head>
                   <body>
                       <&
                       element(ul#my-list.red.blue.green{li('red'),li('blue'),li('green')})
                       element(p#hello-world.red.blue.green('This is the content!'))
                       &>
                   </body>
               </html>
               """)
       .build();

System.out.println(formatProcessor.processDocument(doc));
```

outputs:

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Hello World</title>
    </head>
    <body>
        <ul id="my-list" class="red blue green">
            <li>
                red
            </li>
            <li>
                blue
            </li>
            <li>
                green
            </li>
        </ul>
        <p id="hello-world" class="red blue green">
            This is the content!
        </p>
    </body>
</html>

```

## Creating Custom Elements or Components

This framework provides custom annotations for creating your own elements or components if the need arises. Every class representing HTML elements must be annotated with the `@HTMLElement` annotation so the `ElementProcessor` class knows what to do with it. This annotation has options such as the tag name and whether the element type requires a closing tag. Additionally, every element must extend the `AbstractUIElement` class which contains a list of CSS classes, an element ID, a boolean `hidden` field, and comes with caching support from the `CacheableElement` class.

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

## Bootstrap Integration

The `HTMLDocument` class contains a couple of fields which adds the [Bootstrap](https://getbootstrap.com/) script(s) and stylesheet to your HTML document. The `withBootStrap` field flags that the basic script and stylesheet for Bootstrap should be added to the document, whereas the `withBootStrapPopper` field is used to add the Popper library needed for specialized Bootstrap elements. Each of these fields may be set using the class' builder. 

> Note that if you are using a custom template for your HTMLDocument, the `str_lstyles` and `str_fscripts` keywords must be added to the template for these elements to appear. Refer [here](#document-templating) for more about document templating.

## Water CSS Integration

Similar to the [Bootstrap integration](#bootstrap-integration), Stir comes with support for [Water CSS](https://watercss.kognise.dev/). This can be turned on using the `withWaterCSS` method of the `HTMLDocument` class' builder, along with a color theme using the `waterCSSTheme` method.

## Document Querying

The `HTMLDocument` class provides support for querying its elements using the familiar `querySelector` method which takes a CSS query and returns a list of all the elements added to the document which match that query. At the time of writing this, the `querySelector` method supports every CSS query feature except for pseudo-classes and pseudo-elements.

## Examples

Example uses of this framework can be found [here](https://github.com/john-amiscaray/StirExamples/blob/master/spring-app-example/src/main/java/io/john/amiscaray/springappexample/controller/EmployeeController.java).
