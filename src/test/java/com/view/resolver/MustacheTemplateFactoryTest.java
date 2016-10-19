package com.view.resolver;

import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MustacheTemplateFactoryTest {

    private MustacheTemplateFactory mustacheTemplateFactory;

    @Mock
    private MustacheFactory mustacheFactory;

    @Mock
    private Mustache mustache;

    @Before
    public void setUp() throws Exception {
        when(mustacheFactory.compile("test")).thenReturn(mustache);
        mustacheTemplateFactory = new MustacheTemplateFactory(mustacheFactory);
    }

    @Test
    public void shouldCreateTheMustacheTemplateCorrectly() throws Exception {
        MustacheTemplate mustacheTemplate = mustacheTemplateFactory.getTemplate("test");
        MustacheTemplate expectedMustacheTemplate = new MustacheTemplate();
        expectedMustacheTemplate.setTemplate(mustache);
        assertThat(mustacheTemplate, new ReflectionEquals(expectedMustacheTemplate));
    }
}
