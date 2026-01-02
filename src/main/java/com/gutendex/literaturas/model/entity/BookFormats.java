package com.gutendex.literaturas.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class BookFormats {

    @Column(name = "text_plain", length = 1000)
    private String textPlain;

    @Column(name = "text_html", length = 1000)
    private String textHtml;

    @Column(name = "text_plain_utf8", length = 1000)
    private String textPlainUtf8;

    @Column(name = "application_epub", length = 1000)
    private String applicationEpub;

    @Column(name = "application_mobi", length = 1000)
    private String applicationMobi;

    @Column(name = "application_rdf", length = 1000)
    private String applicationRdf;

    @Column(name = "application_zip", length = 1000)
    private String applicationZip;

    @Column(name = "image_jpeg", length = 1000)
    private String imageJpeg;

    @Column(name = "text_plain_us", length = 1000)
    private String textPlainUs;

    // Constructores
    public BookFormats() {}

    // Getters y Setters
    public String getTextPlain() { return textPlain; }
    public void setTextPlain(String textPlain) { this.textPlain = textPlain; }

    public String getTextHtml() { return textHtml; }
    public void setTextHtml(String textHtml) { this.textHtml = textHtml; }

    public String getTextPlainUtf8() { return textPlainUtf8; }
    public void setTextPlainUtf8(String textPlainUtf8) { this.textPlainUtf8 = textPlainUtf8; }

    public String getApplicationEpub() { return applicationEpub; }
    public void setApplicationEpub(String applicationEpub) { this.applicationEpub = applicationEpub; }

    public String getApplicationMobi() { return applicationMobi; }
    public void setApplicationMobi(String applicationMobi) { this.applicationMobi = applicationMobi; }

    public String getApplicationRdf() { return applicationRdf; }
    public void setApplicationRdf(String applicationRdf) { this.applicationRdf = applicationRdf; }

    public String getApplicationZip() { return applicationZip; }
    public void setApplicationZip(String applicationZip) { this.applicationZip = applicationZip; }

    public String getImageJpeg() { return imageJpeg; }
    public void setImageJpeg(String imageJpeg) { this.imageJpeg = imageJpeg; }

    public String getTextPlainUs() { return textPlainUs; }
    public void setTextPlainUs(String textPlainUs) { this.textPlainUs = textPlainUs; }
}