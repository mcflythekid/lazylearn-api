package com.lazylearn.api.crud.subscribe;

import com.lazylearn.api.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author 4nha
 * Date: 2020-05-01
 */
@Entity
@Table(name="subscribe_form")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscribeEntity extends AbstractEntity {
    private String email;
}
