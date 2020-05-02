package com.lazylearn.api.crud.contact;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author 4nha
 * Date: 2020-05-01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactCreateDto {

    @Length(max = 100, message = "Maximum 100 chars")
    private String firstName;

    @Length(max = 100, message = "Maximum 100 chars")
    private String lastName;

    @Length(max = 100, message = "Maximum 100 chars")
    private String subject;

    @Length(max = 300, message = "Maximum 300 chars")
    private String email;

    @NotEmpty
    @Length(max = 10000, message = "Maximum 10000 chars")
    private String content;
}
