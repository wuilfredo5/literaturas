// src/main/java/com/gutendex/model/entity/BookFormats.java
package com.gutendex.literaturas.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Embeddable
public class BookFormats {
    
    @Column(name = "text_plain", length = 500)
    private String textPlain;
    
    @Column(name = "text_html", length = 500)
    private String textHtml;
    
    @Column(name = "text_plain_utf8", length = 500)
    private String textPlainUtf8;
    
    @Column(name = "application_epub", length = 500)
    private String applicationEpub;
    
    @Column(name = "application_mobi", length = 500)
    private String applicationMobi;
    
    @Column(name = "application_rdf", length = 500)
    private String applicationRdf;
    
    @Column(name = "application_zip", length = 500)
    private String applicationZip;
    
    @Column(name = "image_jpeg", length = 500)
    private String imageJpeg;
    
    @Column(name = "text_plain_us", length = 500)
    private String textPlainUs;
}