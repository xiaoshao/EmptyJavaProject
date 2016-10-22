package com.view.resolver;


import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

public class MustacheViewResolver extends AbstractTemplateViewResolver implements ViewResolver {

    private MustacheView defaultTemplate = null;
    private DefaultMustacheFactory defaultMustacheFactory;

    public MustacheViewResolver(String templateName) throws Exception {
        setViewClass(MustacheView.class);
        defaultMustacheFactory = new DefaultMustacheFactory();
        buildDefaultTemplate(templateName);
    }

    public MustacheViewResolver() {
        setViewClass(MustacheView.class);
    }

    @Override
    protected MustacheView buildView(String viewName) throws Exception {
        final MustacheView mustacheView = (MustacheView) super.buildView(viewName);

        Mustache mustache = defaultMustacheFactory.compile(mustacheView.getUrl());
        mustacheView.setView(mustache);
        mustacheView.setTemplate(defaultTemplate);

        return mustache == null ? null : mustacheView;
    }

    private void buildDefaultTemplate(String templateName) throws Exception {
        if (!StringUtils.isEmpty(templateName)) {
            defaultTemplate = (MustacheView) super.buildView(templateName);
        }
    }
}
