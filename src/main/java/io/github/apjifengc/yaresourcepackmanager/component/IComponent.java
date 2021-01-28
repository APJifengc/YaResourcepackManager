package io.github.apjifengc.yaresourcepackmanager.component;

import com.google.gson.JsonObject;

import java.io.InputStream;

public interface IComponent {
    String getFilePath();
    ComponentType getType();
    InputStream getFile();
    JsonObject getJson();

    enum ComponentType {
        SINGLE_JSON_FILE, MULTIPLE_FILE
    }
}
