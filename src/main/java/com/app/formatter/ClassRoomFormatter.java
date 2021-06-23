package com.app.formatter;

import com.app.exception.NotFoundException;
import com.app.model.Address;
import com.app.model.ClassRoom;
import com.app.service.address.IAddressService;
import com.app.service.classroom.IClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class ClassRoomFormatter implements Formatter<ClassRoom> {
    @Autowired
    private IClassRoomService classRoomService;

    @Autowired
    public ClassRoomFormatter(IClassRoomService classRoomService){
        this.classRoomService = classRoomService;

    }

    @Override
    public ClassRoom parse(String text, Locale locale) throws ParseException {
        Optional<ClassRoom> classRoomOptional = null;
        try {
            classRoomOptional = classRoomService.findById(Long.parseLong(text));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return classRoomOptional.orElse(null);
        //nếu ko có thì trả về null còn nếu ko có null ở đây thì optional nó sẽ trả về rỗng
    }

    @Override
    public String print(ClassRoom object, Locale locale) {
        return "[" + object.getId() + ", " +object.getName() + "]";
    }
}
