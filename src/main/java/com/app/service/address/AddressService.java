package com.app.service.address;

import com.app.model.Address;
import com.app.repository.IAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService implements IAddressService{

    @Autowired
    IAddressRepository addressRepository;

    @Override
    public Iterable<Address> findAll() {
        return addressRepository.findAll();
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void remove(Long id) {
        addressRepository.deleteById(id);
    }
}
