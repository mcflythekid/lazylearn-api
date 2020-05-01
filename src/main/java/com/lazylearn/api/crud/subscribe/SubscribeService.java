package com.lazylearn.api.crud.subscribe;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 4nha
 * Date: 2020-05-01
 */
@Service
public class SubscribeService {

    @Autowired
    private SubscribeRepo subscribeRepo;

    public SubscribeEntity create(SubscribeCreateDto dto){
        SubscribeEntity subscribeEntity = new SubscribeEntity();
        BeanUtils.copyProperties(dto, subscribeEntity);
        return subscribeRepo.save(subscribeEntity);
    }
}
