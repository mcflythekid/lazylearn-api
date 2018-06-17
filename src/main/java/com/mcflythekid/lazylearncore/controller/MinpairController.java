package com.mcflythekid.lazylearncore.controller;

import com.mcflythekid.lazylearncore.entity.Minpair;
import com.mcflythekid.lazylearncore.indto.minpair.MinpairCreateIn;
import com.mcflythekid.lazylearncore.indto.SearchIn;
import com.mcflythekid.lazylearncore.outdto.BootstraptableOut;
import com.mcflythekid.lazylearncore.outdto.JSON;
import com.mcflythekid.lazylearncore.service.MinpairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author McFly the Kid
 */
@RestController
@RequestMapping("/minpair")
public class MinpairController extends BaseController{

    @Autowired
    private MinpairService minpairService;

    @PostMapping("/create")
    public Object create(@Valid @RequestBody MinpairCreateIn in) throws Exception{
        return minpairService.create(in, getUserId());
    }

    @PostMapping("/learned/{minpairId}")
    public JSON learned(@PathVariable String minpairId) throws Exception{
        authorizeMinpair(minpairId);
        minpairService.learned(minpairId);
        return JSON.ok("Update success");
    }

    @PostMapping("/delete/{minpairId}")
    public JSON delete(@PathVariable String minpairId) throws Exception{
        authorizeMinpair(minpairId);
        minpairService.delete(minpairId);
        return JSON.ok("Delete success");
    }

    @PostMapping("/search")
    public BootstraptableOut search(@RequestBody SearchIn in) throws Exception {
        return minpairService.search(in, getUserId());
    }

    @GetMapping("/get/{minpairId}")
    public Minpair create(@PathVariable String minpairId) throws Exception{
        return authorizeMinpair(minpairId);
    }
}
