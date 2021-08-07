package com.ohapon.eshop.web;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

public class PageGenerator {
    private static PageGenerator pageGenerator;
    private final Configuration configuration = new Configuration();

    public static PageGenerator instance() {
        if (pageGenerator == null) {
            pageGenerator = new PageGenerator();
        }
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, ?> data) {
        Writer stream = new StringWriter();

        try {
            configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
            configuration.setDefaultEncoding("UTF-8");

            Template template = configuration.getTemplate(filename);
            template.process(data, stream);

        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();

    }
}