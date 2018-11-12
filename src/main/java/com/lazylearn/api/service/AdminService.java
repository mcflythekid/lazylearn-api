package com.lazylearn.api.service;

import com.lazylearn.api.config.exception.AppException;
import com.lazylearn.api.entity.Vocab;
import com.lazylearn.api.indto.SearchIn;
import com.lazylearn.api.outdto.BootstraptableOut;
import com.lazylearn.api.outdto.JSON;
import com.lazylearn.api.repo.DetailedUserRepo;
import com.lazylearn.api.repo.VocabRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author McFly the Kid
 */
@Service
public class AdminService {

    @Autowired
    private DetailedUserRepo detailedUserRepo;

    @Autowired
    private VocabService vocabService;

    @Autowired
    private VocabRepo vocabRepo;

    public BootstraptableOut search(SearchIn in){
        List rows = detailedUserRepo.findAllByEmail(in.getSearch(), in.getPageable());
        Long total = detailedUserRepo.countAllByEmail(in.getSearch());
        return new BootstraptableOut(rows, total);
    }

    public JSON refreshAllVocab() throws Exception{
        final int SIZE = 50;
        long count = vocabRepo.count();
        Double totalPage = Math.ceil(count * 1.0 / SIZE);
        for (int page = 0; page < totalPage; page++){
            List<Vocab> vocabs = vocabRepo.findAll(new PageRequest(page, SIZE)).getContent();
            for (Vocab vocab : vocabs){
                vocabService.updateCallback(vocab);
            }
        }

        return JSON.ok(count + " Vocabs refreshed");
    }
}
