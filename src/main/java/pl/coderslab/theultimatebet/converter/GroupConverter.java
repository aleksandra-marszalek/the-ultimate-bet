package pl.coderslab.theultimatebet.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.theultimatebet.entity.Group;
import pl.coderslab.theultimatebet.service.GroupService;

public class GroupConverter implements Converter<String, Group> {

        @Autowired
        GroupService groupService;

        @Override
        public Group convert(String s) {
            return groupService.findById(Long.parseLong(s));
        }

}
