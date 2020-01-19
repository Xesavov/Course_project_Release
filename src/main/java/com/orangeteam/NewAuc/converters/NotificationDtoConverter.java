package com.orangeteam.NewAuc.converters;

import com.orangeteam.NewAuc.dto.NotificationDto;
import com.orangeteam.NewAuc.enums.Activity;
import com.orangeteam.NewAuc.models.Event;
import com.orangeteam.NewAuc.models.Image;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;

@Component
public class NotificationDtoConverter implements Converter<Event, NotificationDto> {
    @Override
    public NotificationDto convert(Event event) {
        NotificationDto dto = new NotificationDto();
        dto.setId(event.getId());
        dto.setProductId(event.getUserProd().getProduct().getId());
        dto.setName(event.getUserProd().getProduct().getName());
        if (event.getDate() != null) {
            dto.setDate(event.getDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        }
        dto.setText(Activity.getText(event.getActivity()));
        List<Image> images = event.getUserProd().getProduct().getImages();
        for (Image i: images){
            if(i.getType()==0){
                dto.setImage("i"+i.getId()+i.getExt());
            }
        }
        return dto;
    }
}
