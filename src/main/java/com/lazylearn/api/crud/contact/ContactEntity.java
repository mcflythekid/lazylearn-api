package com.lazylearn.api.crud.contact;

import com.lazylearn.api.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * @author 4nha
 * Date: 2020-05-01
 */
@Entity
@Table(name="contact_form")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactEntity extends AbstractEntity {

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String subject;
    private String email;
    @Lob
    private String content;

    public String format(){
        return new StringBuilder()
                .append("CONTACT").append("\n")
                .append("First name: ").append(firstName).append("\n")
                .append("Last name: ").append(lastName).append("\n")
                .append("Email: ").append(email).append("\n")
                .append("Subject: ").append(subject).append("\n")
                .append(content)
                .toString();
    }
}
