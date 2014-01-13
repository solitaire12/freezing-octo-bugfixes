package com.org.tang;

import java.io.Serializable;

public class StoreyImageInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8298622654435942137L;

    private String imageId;
    private String imagePath;
    private String name;

    public String getImageId() {
	return imageId;
    }

    public void setImageId(String imageId) {
	this.imageId = imageId;
    }

    public String getImagePath() {
	return imagePath;
    }

    public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
