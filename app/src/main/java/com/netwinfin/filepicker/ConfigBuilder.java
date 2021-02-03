package com.netwinfin.filepicker;

import androidx.annotation.StyleRes;

import com.netwinfin.filepicker.models.Config;

import java.util.ArrayList;
import java.util.Arrays;

public final class ConfigBuilder {
    private String rootDir;
    private boolean showHidden = false;
    private boolean selectMultiple = false;
    private boolean addDivider = false;
    private boolean showOnlyDir = false;

    @StyleRes
    private int themeId = R.style.UnicornFilePicker_Default;

    private final FilePicker filePicker;
    private ArrayList<String> extensionFilters;
    private Config config;

    public ConfigBuilder( FilePicker filePicker) {
        this.filePicker = filePicker;
        this.config = Config.getCleanInstance();
    }

    public com.netwinfin.filepicker.ConfigBuilder setRootDirectory(String dirPath){
        this.rootDir = dirPath;
        return this;
    }

    public com.netwinfin.filepicker.ConfigBuilder showHiddenFiles(boolean value){
        this.showHidden = value;
        return this;
    }

    public com.netwinfin.filepicker.ConfigBuilder selectMultipleFiles(boolean value){
        this.selectMultiple = value;
        return this;
    }

    public com.netwinfin.filepicker.ConfigBuilder setFilters(String[] filters){
        this.extensionFilters = new ArrayList<>(Arrays.asList(filters));
        return this;
    }

    public com.netwinfin.filepicker.ConfigBuilder addItemDivider(boolean value){
        this.addDivider = value;
        return this;
    }

    public com.netwinfin.filepicker.ConfigBuilder theme(@StyleRes int theme){
        this.themeId = theme;
        return this;
    }

    public com.netwinfin.filepicker.ConfigBuilder showOnlyDirectory(boolean value){
        this.showOnlyDir = value;
        return this;
    }

    public com.netwinfin.filepicker.FilePicker build(){
        config.setRootDir(this.rootDir);
        config.setSelectMultiple(this.selectMultiple);
        config.setShowHidden(this.showHidden);
        config.setExtensionFilters(this.extensionFilters);
        config.setAddItemDivider(this.addDivider);
        config.setThemeId(this.themeId);
        config.setShowOnlyDirectory(this.showOnlyDir);
        return filePicker;
    }


}
