package com.lazylearn.api.crud.subscribe;

import com.lazylearn.api.outdto.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author 4nha
 * Date: 2020-05-01
 */
@RestController
@RequestMapping("/crud/subscribe")
public class SubscribeCrudController {

    @Autowired
    private SubscribeRepo subscribeRepo;

    @Autowired
    private SubscribeService subscribeService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/list")
    public List<SubscribeEntity> list() {
        return subscribeRepo.findAll();
    }

    @PostMapping("/create")
    public SubscribeEntity create(@RequestBody @Valid SubscribeCreateDto dto) {
        return subscribeService.create(dto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/delete/{id}")
    public JSON delete(@PathVariable String id) {
        subscribeRepo.delete(id);
        return JSON.ok("Subscribe deleted");
    }
}
