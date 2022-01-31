package com.interopx.fhir.parser.model;

import java.util.Date;

/**
 * This is the MetaData class from which all POJOs will inherit the basic metadata attributes.
 * 
 * @author nbashyam
 *
 */
public abstract class MetaData {

	/** The resource url, populate if known otherwise leave it empty. */
	private String url;
	
	/** 
	 * The resource version based on updates, For each update the version is expected to be incremented only if you can retrieve the older versions. 
	 * If versioning of data is not supported, this should be left empty. 
	 * 
	 */ 
	private String version;
	
	/** The resource modification time stamp */
	private Date lastModifiedTimestamp;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getLastModifiedTimestamp() {
		return lastModifiedTimestamp;
	}

	public void setLastModifiedTimestamp(Date lastModifiedTimestamp) {
		this.lastModifiedTimestamp = lastModifiedTimestamp;
	} 
	
	
}
