package com.lazylearn.api.outdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 4nha
 * Date: 2020-05-13
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SettingOut {
    private String timezone;
    private String email;
    private String name;
}
