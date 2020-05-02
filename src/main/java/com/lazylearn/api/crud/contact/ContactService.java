package com.lazylearn.api.crud.contact;

import com.lazylearn.api.unit.TelegramUnit;
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

    @Autowired
    private TelegramUnit telegramUnit;

    public ContactEntity create(ContactCreateDto dto){
        ContactEntity contactEntity = new ContactEntity();
        BeanUtils.copyProperties(dto, contactEntity);
        contactEntity = contactRepo.save(contactEntity);

        telegramUnit.sendAsync(contactEntity.format());
        return contactEntity;
    }
}
