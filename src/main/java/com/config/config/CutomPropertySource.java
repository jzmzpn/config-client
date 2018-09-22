package com.config.config;

import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Map;

public class CutomPropertySource extends EnumerablePropertySource<Map<String, String>> {

    public CutomPropertySource(String name, Map<String, String> sourceMap) {
        super(name, sourceMap);
    }

    @Override
    public String[] getPropertyNames() {
        return this.source.keySet().toArray(new String[this.source.size()]);
    }


    @Override
    public Object getProperty(String name) {
        return this.source.get(name);
    }
}
