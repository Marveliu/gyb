package cn.wizzer.commons.code.plugin.utils;

import com.intellij.openapi.project.Project;

/**
 * 代码生成配置对象
 * Created by wizzer on 2016/12/22.
 */
public class GenerateConfig {
    private boolean service;
    private boolean conroller;
    private boolean view;
    private boolean locale;
    private String basePath;
    private String baseUri;
    private String basePackage;

    private String controllerPakName = "controllers";
    private String ServicePakName = "services";
    private String modelPakName = "models";
    private boolean force = false;
    private String pages;
    private Project project;

    public boolean isService() {
        return service;
    }

    public void setService(boolean service) {
        this.service = service;
    }

    public boolean isConroller() {
        return conroller;
    }

    public void setConroller(boolean conroller) {
        this.conroller = conroller;
    }

    public boolean isLocale() {
        return locale;
    }

    public void setLocale(boolean locale) {
        this.locale = locale;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public String getControllerPakName() {
        return controllerPakName;
    }

    public void setControllerPakName(String controllerPakName) {
        this.controllerPakName = controllerPakName;
    }

    public String getServicePakName() {
        return ServicePakName;
    }

    public void setServicePakName(String servicePakName) {
        ServicePakName = servicePakName;
    }

    public String getModelPakName() {
        return modelPakName;
    }

    public void setModelPakName(String modelPakName) {
        this.modelPakName = modelPakName;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
