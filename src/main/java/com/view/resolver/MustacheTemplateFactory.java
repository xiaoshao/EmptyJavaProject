package com.view.resolver;


import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

public class MustacheTemplateFactory {

    private MustacheFactory mustacheFactory;

    public MustacheTemplateFactory(MustacheFactory mustacheFactory) {
        this.mustacheFactory = mustacheFactory;
    }

    MustacheTemplate getTemplate(String templateURL) {
        Mustache mustache = mustacheFactory.compile(templateURL);
        MustacheTemplate mustacheTemplate = new MustacheTemplate();
        mustacheTemplate.setTemplate(mustache);
        return mustacheTemplate;
    }
}
