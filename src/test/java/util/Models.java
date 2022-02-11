package util;

import com.example.spring.dao.Entity.Location;
import com.example.spring.dao.Entity.Region;
import com.example.spring.dao.Entity.User;
import com.example.spring.dto.*;

import java.util.List;

public class Models {
    public static Location createLocation() {
        return Location.builder()
                .name("Liter")
                .longitude(33.32d)
                .latitude(34.32d)
                .region(Region.builder()
                        .id(1L)
                        .name("Liter")
                        .build())
                .build();
    }

    public static Region getRegion() {
        return Region.builder()
                .id(1L)
                .name("Liter")
                .build();
    }

    public static AddLocationDto createAddLocationDto() {
        return AddLocationDto.builder()
                .regionId(1L)
                .regionName("Name")
                .latitude(34.32d)
                .longitude(33.32d)
                .locationName("Liter")
                .build();
    }

    public static LocationInfoDto getLocationInfoDto() {
        return LocationInfoDto.builder()
                .name("Liter")
                .longitude(33.32)
                .latitude(34.32d)
                .build();
    }

    public static RegionInfoDto getRegionINfoDto() {
        return RegionInfoDto.builder()
                .id(1L)
                .name("Liter")
                .locationInfoDtoList(List.of(getLocationInfoDto()))
                .build();
    }

    public static EditLocationDto editLocationDto() {
        return EditLocationDto.builder()
                .name("new name")
                .latitude(33.32d)
                .longitude(43.23)
                .build();
    }

    public static Region getRegionInf() {
        return Region.builder()
                .name("Liter")
                .locations(List.of(createLocation()))
                .id(1L)
                .build();
    }

    public static AddRegionDto getAddRegionDto() {
        return AddRegionDto.builder()
                .name("Name")
                .build();
    }

    public static EditRegionDto getEditRegionDto() {
        return EditRegionDto.builder()
                .name("new name")
                .build();
    }

    public static CreateUserDto getCreateUserDto() {
        return CreateUserDto.builder()
                .name("Sudo")
                .age(32)
                .locationId(1L)
                .surname("Kuro")
                .build();
    }

    public static User getUser() {
        return User.builder()
                .name("Sudo")
                .surname("Kuro")
                .age(32)
                .location(Location.builder()
                        .id(1L)
                        .region(Region.builder()
                                .id(1L)
                                .name("Liter")
                                .build())
                        .name("Test")
                        .longitude(3.32d)
                        .latitude(3.34d)
                        .build())
                .build();
    }

    public static Location locationForCreateUser(){
        return Location.builder()
                .id(1L)
                .name("Test")
                .longitude(3.32)
                .latitude(3.34)
                .region(Region.builder()
                        .id(1L)
                        .name("Liter")
                        .build())
                .build();
    }

    public static GetUserDto allUsers(){
        return GetUserDto.builder()
                .age(32)
                .locationInfoDto(LocationInfoDto.builder()
                        .id(1L)
                        .name("Test")
                        .longitude(3.32d)
                        .latitude(3.34d)
                        .build())
                .name("Sudo")
                .surname("Kuro")
                .build();
    }

    public static EditUserDto getEditUserDto(){
        return EditUserDto.builder()
                .name("NewName")
                .surname("NewSurname")
                .age(32)
                .build();
    }

}
