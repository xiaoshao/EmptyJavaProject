package com.view.resolver;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
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
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MustacheViewTest {


    @Mock
    HttpServletResponse response;

    @Mock
    HttpServletRequest request;

    @Test
    public void shouldRenderViewCorrectlyWhenTheViewWithoutTemplate() throws Exception {
        MustacheView mustacheView = createMustacheViewWithoutTemplate();
        Map<String, Object> models = new HashMap<>();
        models.put("view-without-template", "content");

        StringWriter sw = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(sw));
        mustacheView.renderMergedTemplateModel(models, request, response);
        assertThat(sw.toString(), is("<div>content</div>"));
    }

    @Test
    public void shouldRenderTheViewCorrectlyWhenTheViewWith1LayerTemplate() throws Exception{
        HashMap<String, Object> models = new HashMap<>();
        models.put("name", "xiaoshao");
        StringWriter sw = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(sw));

        MustacheView mustacheView = createViewWith1LayerTemplate();

        mustacheView.renderMergedTemplateModel(models, request, response);

        assertThat(sw.toString(), is("<div>xiaoshao</div>Here is view"));
    }

    @Test
    public void shouldRenderTheViewCorrectlyWhenTheViewWith2LayersTemplate() throws Exception {
        HashMap<String, Object> models = new HashMap<>();
        models.put("2LayerKey", "2LayerValue");

        StringWriter sw = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(sw));

        MustacheView mustacheView = createViewWith2LayersTemplate();

        mustacheView.renderMergedTemplateModel(models, request, response);

        assertThat(sw.toString(), is("<2 layer><1 layer>2LayerValue<1layer><2 layer>"));
    }

    private MustacheView createViewWith2LayersTemplate() {
        MustacheView mustacheView = new MustacheView();
        mustacheView.setView(createMustache("{{2LayerKey}}", "content"));

        MustacheView mustacheView1Layer = new MustacheView();
        mustacheView.setView(createMustache("<1 layer>{{body}}<1layer>", "1 layer"));
        mustacheView.setTemplate(mustacheView1Layer);

        MustacheView mustacheView2Layer = new MustacheView();
        mustacheView2Layer.setView(createMustache("<2 layer>{{body}}<2layer>", "2 layer"));
        mustacheView1Layer.setTemplate(mustacheView2Layer);

        return mustacheView;
    }

    private MustacheView createViewWith1LayerTemplate() {
        MustacheView mustacheView = new MustacheView();

        MustacheView template = new MustacheView();
        template.setView(createMustache("<div>{{name}}</div>{{body}}", "template"));

        mustacheView.setView(createMustache("Here is view", "view"));
        mustacheView.setTemplate(template);

        return mustacheView;
    }

    private Mustache createMustache(String template, String templateName) {
        DefaultMustacheFactory defaultMustacheFactory = new DefaultMustacheFactory();
        return defaultMustacheFactory.compile(new StringReader(template), templateName);
    }

    private MustacheView createMustacheViewWithoutTemplate(){
        MustacheView mustacheView = new MustacheView();

        Mustache mustache = createMustache("<div>{{view-without-template}}</div>", "view-without-template");

        mustacheView.setView(mustache);

        return mustacheView;
    }
}
