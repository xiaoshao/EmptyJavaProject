package com.view.resolver;


import com.github.mustachejava.Mustache;

import java.io.Writer;
import java.util.Map;

public class MustacheTemplate extends MustacheView {

    private Mustache template;

    public void setTemplate(Mustache template) {
        this.template = template;
    }

    public void execute(Writer writer, Map<String, Object> model){
        template.execute(writer, model);
    }


    public void execute(Writer writer, Map<String, Object> model, String body, String placeHolder) {
        model.put(placeHolder, body);
        template.execute(writer, model);
    }
}
