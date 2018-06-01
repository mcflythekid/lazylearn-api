package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.indto.SearchDeckInDto;
import com.mcflythekid.lazylearncore.indto.SearchUserInDto;
import com.mcflythekid.lazylearncore.outdto.BootstrapTableOutDto;
import com.mcflythekid.lazylearncore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public BootstrapTableOutDto search(@RequestParam(name = "search", defaultValue = "") String search,
                                       @RequestParam("sort") String sort, @RequestParam("order") String order,
                                       @RequestParam("limit") Integer limit, @RequestParam("offset") Integer offset){
        SearchUserInDto searchUserInDto = new SearchUserInDto(order, sort, limit, offset);
        searchUserInDto.setSearch(search);
        return userService.search(searchUserInDto);
    }
}
