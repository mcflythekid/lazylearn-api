package com.lazylearn.api.crud.contact;

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
@RequestMapping("/crud/contact")
public class ContactCrudController {

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private ContactService contactService;

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping("/list")
    public List<ContactEntity> list() {
        return contactRepo.findAll();
    }

    @PostMapping("/create")
    public ContactEntity create(@RequestBody @Valid ContactCreateDto dto) {
        return contactService.create(dto);
    }

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/delete/{id}")
    public JSON delete(@PathVariable String id) {
        contactRepo.delete(id);
        return JSON.ok("Contact deleted");
    }
}
