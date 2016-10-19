package com.view.resolver;

import com.github.mustachejava.Mustache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MustacheTemplateTest {

    MustacheTemplate mustacheTemplate;

    @Mock
    Mustache template;

    @Before
    public void setUp() throws Exception {
        mustacheTemplate = new MustacheTemplate();
        mustacheTemplate.setTemplate(template);
    }

    @Test
    public void shouldInvokeTheExecuteOfMustache() throws Exception {
        StringWriter sw = new StringWriter();
        HashMap<String, Object> models = new HashMap();
        mustacheTemplate.execute(sw, models);
        verify(template, times(1)).execute(sw, models);
    }

    @Test
    public void shouldInvokeTheExecuteOfMustacheWithModels() throws Exception {
        StringWriter sw = new StringWriter();
        HashMap<String, Object> models = new HashMap<>();
        mustacheTemplate.execute(sw, models, "here is body", "body");

        models.put("body", "here is body");
        verify(template, times(1)).execute(sw, models);

    }
}
