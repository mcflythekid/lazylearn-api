package com.lazylearn.api.service;

import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.repo.DetailedUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class AdminService {

    @Autowired
    private DetailedUserRepo detailedUserRepo;

    public BootstraptableOut search(SearchIn in){
        List rows = detailedUserRepo.findAllByEmail(in.getSearch(), in.getPageable());
        Long total = detailedUserRepo.countAllByEmail(in.getSearch());
        return new BootstraptableOut(rows, total);
    }
}
