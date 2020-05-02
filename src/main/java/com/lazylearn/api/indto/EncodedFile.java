package com.lazylearn.api.indto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author McFly the Kid
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EncodedFile {

    @NotBlank
    private String content;

    @NotBlank
    private String ext;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
