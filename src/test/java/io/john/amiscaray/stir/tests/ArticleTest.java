package io.john.amiscaray.stir.tests;

import io.john.amiscaray.stir.domain.elements.Article;
import io.john.amiscaray.stir.domain.elements.Table;
import io.john.amiscaray.stir.setup.ExpectedHTMLLoader;
import io.john.amiscaray.stir.stub.Student;
import io.john.amiscaray.stir.util.ElementProcessor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ArticleTest {

    private final ElementProcessor processor = ElementProcessor.getInstance();
    private final ExpectedHTMLLoader htmlLoader = ExpectedHTMLLoader.getInstance();

    @Test
    public void testEmptyArticle() throws IOException {

        Article article = Article.builder()
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/emptyArticle.html"), processor.getMarkup(article));

    }

    @Test
    public void testArticleFromBuilderWithAll() throws IOException {

        Article article = Article.builder()
                .id("myArticle")
                .cssClasses(new ArrayList<>(List.of("red", "blue")))
                .cssClass("green")
                .style("color: red;")
                .hidden(true)
                .child(new Table(List.of(new Student(1, "John", 4.0f)), Student.class))
                .build();

        assertEquals(htmlLoader.getHTMLContentOf("html/articleWithEverything.html"), processor.getMarkup(article));

    }

}
