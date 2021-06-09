package com.lazylearn.api.indto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.AssertTrue;
import java.util.TimeZone;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author 4nha
 * Date: 2020-05-13
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChangeTimezoneIn {

    @NotBlank
    private String timezone;

    @AssertTrue(message = "Invalid timezone. Valid format: GMT<Zone>")
    public boolean isValidTimezone() {
        if (isBlank(timezone)) {
            return false;
        }

        try {
            TimeZone.getTimeZone(timezone);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
