package com.view.resolver;

import com.github.mustachejava.Mustache;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;
import java.util.Map;

public class MustacheView extends AbstractTemplateView implements View {

    private MustacheView template;
    private Mustache mustache;
    private String placeHolderVariable = "body";

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        if (template != null) {
            StringWriter sw = new StringWriter();
            mustache.execute(sw, model);
            model.put(placeHolderVariable, sw.toString());
            template.renderMergedTemplateModel(model, request, response);
        } else if (mustache != null) {
            mustache.execute(response.getWriter(), model);
        }
    }

    public void setTemplate(MustacheView template) {
        this.template = template;
    }

    public void setPlaceHolderVariable(String placeHolderVariable) {
        this.placeHolderVariable = placeHolderVariable;
    }

    public void setView(Mustache mustache) {
        this.mustache = mustache;
    }
}
