// src/main/java/com/gutendex/model/dto/FormatDTO.java
package com.gutendex.literaturas.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class FormatDTO {
    
    @JsonProperty("text/plain")
    private String textPlain;
    
    @JsonProperty("text/html")
    private String textHtml;
    
    @JsonProperty("text/plain; charset=utf-8")
    private String textPlainUtf8;
    
    @JsonProperty("application/epub+zip")
    private String applicationEpub;
    
    @JsonProperty("application/x-mobipocket-ebook")
    private String applicationMobi;
    
    @JsonProperty("application/rdf+xml")
    private String applicationRdf;
    
    @JsonProperty("application/zip")
    private String applicationZip;
    
    @JsonProperty("image/jpeg")
    private String imageJpeg;
    
    @JsonProperty("text/plain; charset=us-ascii")
    private String textPlainUs;
    
    // Constructor adicional para mapeo flexible
    public FormatDTO(Map<String, String> formatMap) {
        if (formatMap != null) {
            this.textPlain = formatMap.get("text/plain");
            this.textHtml = formatMap.get("text/html");
            this.textPlainUtf8 = formatMap.get("text/plain; charset=utf-8");
            this.applicationEpub = formatMap.get("application/epub+zip");
            this.applicationMobi = formatMap.get("application/x-mobipocket-ebook");
            this.applicationRdf = formatMap.get("application/rdf+xml");
            this.applicationZip = formatMap.get("application/zip");
            this.imageJpeg = formatMap.get("image/jpeg");
            this.textPlainUs = formatMap.get("text/plain; charset=us-ascii");
        }
    }
    
    public FormatDTO() {}
}