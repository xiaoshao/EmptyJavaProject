package com.view.resolver;

import com.github.mustachejava.DefaultMustacheFactory;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class MustacheViewResolverTest {

    private MustacheViewResolver mustacheViewResolver;

    @Before
    public void setUp() throws Exception {
        mustacheViewResolver = new MustacheViewResolver(null);
        mustacheViewResolver.setSuffix(".md");
        mustacheViewResolver.setPrefix("template/");
    }

    @Test
    public void shouldBuildTheCorrectView() throws Exception {
//        MustacheView mustacheView = mustacheViewResolver.buildView("hello");
//        MustacheView expected = new MustacheView();
//        expected.setUrl("template/hello.md");
//        DefaultMustacheFactory defaultMustacheFactory = new DefaultMustacheFactory();
//        MustacheTemplate mustacheTemplate = new MustacheTemplate();
//        mustacheTemplate.setTemplate(defaultMustacheFactory.compile("template/hello.md"));
//        expected.setView(mustacheTemplate);
//
//        assertThat(mustacheView, new ReflectionEquals(expected, "view"));
    }
}
