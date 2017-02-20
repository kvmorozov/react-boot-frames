package ru.sbt.pprbcf.client.base;

import org.springframework.hateoas.ResourceSupport;

/**
 * Created by sbt-morozov-kv on 14.02.2017.
 */
public class PluggableFeature extends ResourceSupport {

    public enum FeatureType {
        BUTTON,
        LINK
    }

    private String caption;
    private FeatureType type;
    private String renderComponent;
    private String requireScript;

    public PluggableFeature(String caption, FeatureType type, String renderComponent, String requireScript) {
        this.caption = caption;
        this.type = type;
        this.renderComponent = renderComponent;
        this.requireScript = requireScript;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public FeatureType getType() {
        return type;
    }

    public void setType(FeatureType type) {
        this.type = type;
    }

    public String getRenderComponent() {
        return renderComponent;
    }

    public void setRenderComponent(String renderComponent) {
        this.renderComponent = renderComponent;
    }

    public String getRequireScript() {
        return requireScript;
    }

    public void setRequireScript(String requireScript) {
        this.requireScript = requireScript;
    }
}
