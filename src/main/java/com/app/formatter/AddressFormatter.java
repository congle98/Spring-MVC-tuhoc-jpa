package com.app.formatter;

import com.app.exception.NotFoundException;
import com.app.model.Address;
import com.app.service.address.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class AddressFormatter implements Formatter<Address> {

    private IAddressService addressService;

    @Autowired
    public AddressFormatter(IAddressService addressService){
        this.addressService = addressService;

    }

    //phần address thêm mới ở student thì nó field là address nhưng value nhận về là id của address
    // nên nó phải parse cái id đó thành đối tượng để ép vào student luôn

    @Override
    public Address parse(String text, Locale locale) throws ParseException {
        Optional<Address> addressOptional = null;
        try {
            addressOptional = addressService.findById(Long.parseLong(text));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return addressOptional.orElse(null);
    }

    //ngược lại
    @Override
    public String print(Address object, Locale locale) {
        return
                "[" + object.getId() + ", " +object.getName() + "]"
//                dùng trong trường hợp ở view muốn show ra cả 1 đối tượng, giống to string vậy
                ;
    }
}
