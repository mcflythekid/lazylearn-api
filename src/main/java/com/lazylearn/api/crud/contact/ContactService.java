package com.lazylearn.api.crud.contact;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 4nha
 * Date: 2020-05-01
 */
@Service
public class ContactService {

    @Autowired
    private ContactRepo contactRepo;

    public ContactEntity create(ContactCreateDto dto){
        ContactEntity contactEntity = new ContactEntity();
        BeanUtils.copyProperties(dto, contactEntity);
        return contactRepo.save(contactEntity);
    }
}
