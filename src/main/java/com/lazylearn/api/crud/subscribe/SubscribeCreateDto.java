package com.lazylearn.api.crud.subscribe;

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
public class SubscribeCreateDto {

    @NotEmpty
    @Length(max = 300, message = "Maximum 300 chars")
    private String email;
}
