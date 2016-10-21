package com.view.resolver;

import com.github.mustachejava.Mustache;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class MustacheView extends AbstractTemplateView implements View {

    private MustacheView template;
    private Mustache mustache;
    private String placeHolderVariable = "body";

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        if(template == null){
            mustache.execute(response.getWriter(), model);
        }else{
            String partial = buildView(model);
            model.put(placeHolderVariable, partial);
            template.renderMergedTemplateModel(model, request, response);
        }
    }

    private String buildView(Map<String, Object> model) throws IOException {
        StringWriter sw = new StringWriter();
        mustache.execute(sw, model);
        return sw.toString();
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
