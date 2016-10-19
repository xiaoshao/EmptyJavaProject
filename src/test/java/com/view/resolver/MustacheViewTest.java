package com.view.resolver;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MustacheViewTest {

    private MustacheView mustacheView;

    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;

    private DefaultMustacheFactory defaultMustacheFactory;

    @Before
    public void setUp() throws Exception {
        defaultMustacheFactory = new DefaultMustacheFactory();
    }

    @Test
    public void shouldRenderViewCorrectly() throws Exception {
        HashMap<String, Object> models = new HashMap<>();
        models.put("name", "xiaoshao");

        StringWriter sw = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(sw));

        mustacheView = new MustacheView();
        mustacheView.setUrl("test");

        Mustache template = defaultMustacheFactory.compile(new StringReader("<div>{{name}}</div>{{key}}"), "test");
        MustacheTemplate mustacheTemplate = new MustacheTemplate();
        mustacheTemplate.setTemplate(template);
        mustacheView.setView(mustacheTemplate);

        mustacheView.renderMergedTemplateModel(models, request, response);

        assertThat(sw.toString(), is("<div>xiaoshao</div>"));
    }

    @Test
    public void shouldRenderTheViewWithTemplateCorrectly() throws Exception{
        HashMap<String, Object> models = new HashMap<>();
        models.put("name", "xiaoshao");
        StringWriter sw = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(sw));

        mustacheView = new MustacheView();
        mustacheView.setUrl("test");
        mustacheView.setPlaceHolderVariable("body");
        Mustache template = defaultMustacheFactory.compile(new StringReader("<div>{{name}}</div>{{body}}"), "template");
        MustacheTemplate mustacheTemplate = new MustacheTemplate();
        mustacheTemplate.setTemplate(template);
        mustacheView.setTemplate(mustacheTemplate);

        Mustache view = defaultMustacheFactory.compile(new StringReader("Here is view"), "view");
        MustacheTemplate viewTemplate = new MustacheTemplate();
        viewTemplate.setTemplate(view);
        mustacheView.setView(viewTemplate);

        mustacheView.renderMergedTemplateModel(models, request, response);

        assertThat(sw.toString(), is("<div>xiaoshao</div>Here is view"));

    }
}
