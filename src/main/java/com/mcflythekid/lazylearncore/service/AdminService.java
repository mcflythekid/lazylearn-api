package com.mcflythekid.lazylearncore.service;

import com.mcflythekid.lazylearncore.indto.SearchUserInDto;
import com.mcflythekid.lazylearncore.outdto.BootstrapTableOutDto;
import com.mcflythekid.lazylearncore.repo.VUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class AdminService {

    @Autowired
    private VUserRepo vUserRepo;

    public BootstrapTableOutDto search(SearchUserInDto searchUserInDto){
        List rows = vUserRepo.findAllByEmail(searchUserInDto.getSearch(), searchUserInDto.getPageable());
        Long total = vUserRepo.countAllByEmail(searchUserInDto.getSearch());
        return new BootstrapTableOutDto(rows, total);
    }
}
