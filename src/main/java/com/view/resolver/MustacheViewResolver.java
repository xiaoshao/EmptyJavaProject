package com.view.resolver;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

public class MustacheViewResolver extends AbstractTemplateViewResolver implements ViewResolver {

    private String templateName;

    public MustacheViewResolver(String templateName) {
        setViewClass(MustacheTemplate.class);
        this.templateName = templateName;
    }

    @Override
    protected MustacheView buildView(String viewName) throws Exception {
        final MustacheTemplate view = (MustacheTemplate) super.buildView(viewName);

        MustacheView mustacheView = new MustacheView();
        mustacheView.setUrl(getViewUrl(viewName));
        mustacheView.setView(view);

        if(!StringUtils.isEmpty(templateName)){
            final MustacheTemplate template = (MustacheTemplate) super.buildView(templateName);
            mustacheView.setTemplate(template);
        }

        return mustacheView;
    }

    private String getViewUrl(String viewName){
        return getPrefix() + viewName + getSuffix();
    }
}
