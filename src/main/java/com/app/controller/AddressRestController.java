package com.app.controller;


import com.app.exception.NotFoundException;
import com.app.model.Address;
import com.app.service.address.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/restAddress")
public class AddressRestController {
    @Autowired
    IAddressService addressService;

    //list với iterial thì vẫn oke
    // còn nếu optinal thì ko lấy đấy dc phải get() sang đối tượng
    @GetMapping("")
    public ResponseEntity<Iterable<Address>> listAddress(){
        return new ResponseEntity<>(addressService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable Long id) throws NotFoundException {

        return new ResponseEntity<>(addressService.findById(id).get(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Address> save(@RequestBody Address address){
        addressService.save(address);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> update(@RequestBody Address address, @PathVariable Long id) throws NotFoundException {
        address.setId(id);
        addressService.save(address);
        return new ResponseEntity<>(address,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Address> delete(@PathVariable Long id){
        addressService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView error(){
        return new ModelAndView("error");
    }
}
