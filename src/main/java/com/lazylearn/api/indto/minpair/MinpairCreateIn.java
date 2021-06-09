package com.lazylearn.api.indto.minpair;

import com.lazylearn.api.indto.EncodedFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * @author McFly the Kid
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MinpairCreateIn {

    @NotNull
    private List<EncodedFile> audioFiles1;

    @NotNull
    private List<EncodedFile> audioFiles2;

    @AssertTrue(message = "Audio files is required")
    public boolean isValidFiles() {
        return audioFiles1 != null && audioFiles2 != null
                && !audioFiles1.isEmpty() && !audioFiles2.isEmpty();
    }

    @NotBlank
    private String word1;

    @NotBlank
    private String word2;

    @NotBlank
    private String phonetic1;

    @NotBlank
    private String phonetic2;

    @NotBlank
    @Pattern(regexp = "^([a-z]{2,10})$", message = "Invalid language name. Allows: a-z")
    private String language;

}
