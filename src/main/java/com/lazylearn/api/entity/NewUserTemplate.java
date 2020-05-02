package com.lazylearn.api.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 4nha
 * Date: 2020-05-02
 */
@Entity
@Table(name = "newuser_template")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewUserTemplate implements Serializable {
    @Id
    private String id;
    private String note;
    @Column(name = "type_")
    private String type;
    @Column(name = "record_id")
    private String recordId;

}
