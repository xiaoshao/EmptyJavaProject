package com.view.resolver;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractTemplateView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.StringWriter;
import java.util.Map;

public class MustacheView extends AbstractTemplateView implements View {

    private MustacheTemplate template;
    private MustacheTemplate view;
    private String placeHolderVariable = "body";

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType(getContentType());
        if(template == null){
            view.execute(response.getWriter(), model);
        }else{
            StringWriter sw = new StringWriter();
            view.execute(sw, model);
            template.execute(response.getWriter(), model, sw.toString(), placeHolderVariable);
        }
    }

    public void setTemplate(MustacheTemplate template) {
        this.template = template;
    }

    public void setView(MustacheTemplate view) {
        this.view = view;
    }

    public void setPlaceHolderVariable(String placeHolderVariable) {
        this.placeHolderVariable = placeHolderVariable;
    }
}
