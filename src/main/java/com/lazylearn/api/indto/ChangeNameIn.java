package com.lazylearn.api.indto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author 4nha
 * Date: 2020-05-13
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChangeNameIn {

    @NotBlank
    private String name;
}
