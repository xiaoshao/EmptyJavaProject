package com.view.resolver;

import com.github.mustachejava.DefaultMustacheFactory;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class MustacheViewResolverTest {

    private MustacheViewResolver mustacheViewResolver;

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        mustacheViewResolver = new MustacheViewResolver(null);
        mustacheViewResolver.setSuffix(".md");
        mustacheViewResolver.setPrefix("template/");
    }

    @Test
    public void shouldBuildTheCorrectViewWithoutTemplate() throws Exception {
        MustacheView mustacheView = mustacheViewResolver.buildView("hello");

        String expectedView = "<div>with-out-template</div>\n";
        Map<String, Object> model = new HashMap<>();
        model.put("name", "with-out-template");

        StringWriter sw = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(sw));
        mustacheView.renderMergedTemplateModel(model, request, response);

        assertThat(sw.toString(), is(expectedView));
    }

    @Test
    public void shouldBuildTheCorrectViewWithDefaultTemplate() throws Exception {
        mustacheViewResolver.setDefaultTemplate("template");

        MustacheView mustacheView = mustacheViewResolver.buildView("hello");

        String expectedView = "<body><div>with-out-template</div>\n</body>\n";
        Map<String, Object> model = new HashMap<>();
        model.put("name", "with-out-template");

        StringWriter sw = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(sw));
        mustacheView.renderMergedTemplateModel(model, request, response);

        assertThat(sw.toString(), is(expectedView));
    }
}
