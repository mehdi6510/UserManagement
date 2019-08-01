package com.afifi.usermng.model.mapper;

import com.afifi.usermng.model.User;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

//    private static DozerBeanMapper apiBeanMapper = new DozerBeanMapper();
//    static {
//        BeanMappingBuilder mappingBuilder = new BeanMappingBuilder() {
//            @Override
//            protected void configure() {
//                String dateFormat = "yyyy/MM/dd HH:mm:ss";
//                mapping(User.class, DTOUser.class,
//                        TypeMappingOptions.wildcard(true),
//                        TypeMappingOptions.dateFormat(dateFormat),
//                        TypeMappingOptions.trimStrings(),
//                        TypeMappingOptions.mapNull(),
//                        TypeMappingOptions.mapEmptyString());
//            }
//        };
//
//        apiBeanMapper = new DozerBeanMapper();
//        apiBeanMapper.addMapping(mappingBuilder);
//    }
//    public DozerBeanMapper getApiBeanMapper() {
//        return apiBeanMapper;
//    }

    public void fillUpdatingDetails(User user, User userDetails) {
        user.setTitle(userDetails.getTitle());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setUsername(userDetails.getUsername());
        user.setPassword(userDetails.getPassword());
        user.setCellPhone(userDetails.getCellPhone());
        user.setEmail(userDetails.getEmail());
        user.setIsAdmin(userDetails.getIsAdmin());
    }

}
