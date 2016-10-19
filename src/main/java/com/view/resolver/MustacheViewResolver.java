package com.view.resolver;


import com.github.mustachejava.DefaultMustacheFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

public class MustacheViewResolver extends AbstractTemplateViewResolver implements ViewResolver {

    private String templateName;

    private DefaultMustacheFactory defaultMustacheFactory;

    public MustacheViewResolver(String templateName) {
        setViewClass(MustacheView.class);
        defaultMustacheFactory = new DefaultMustacheFactory();
        this.templateName = templateName;
    }

    @Override
    protected MustacheView buildView(String viewName) throws Exception {
        final MustacheView view = (MustacheView) super.buildView(viewName);

        MustacheView mustacheView = new MustacheView();
        mustacheView.setView(defaultMustacheFactory.compile(view.getUrl()));

        if(!StringUtils.isEmpty(templateName)){
            final MustacheView template = (MustacheView) super.buildView(templateName);
            mustacheView.setTemplate(template);
        }

        return mustacheView;
    }
}
