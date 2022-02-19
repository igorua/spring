package util;

import com.example.spring.dao.Entity.Location;
import com.example.spring.dao.Entity.Region;
import com.example.spring.dao.Entity.User;
import com.example.spring.dto.*;

import java.util.Collections;
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

    public static Location locationForCreateUser() {
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

    public static GetUserDto allUsers() {
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

    public static EditUserDto getEditUserDto() {
        return EditUserDto.builder()
                .name("NewName")
                .surname("NewSurname")
                .age(32)
                .build();
    }

    public static List<LocationInfoDto> allLocationDtoIntegration() {
        return List.of(LocationInfoDto.builder()
                        .id(1L)
                        .name("Lviv")
                        .longitude(49.84)
                        .latitude(24.031)
                        .build(),
                LocationInfoDto.builder()
                        .id(2L)
                        .name("Radechiv")
                        .longitude(50.16)
                        .latitude(24.38)
                        .build(),
                LocationInfoDto.builder()
                        .id(3L)
                        .name("Stryi")
                        .longitude(49.26)
                        .latitude(49.15)
                        .build(),
                LocationInfoDto.builder()
                        .id(4L)
                        .name("Kyiv")
                        .longitude(50.45)
                        .latitude(30.52)
                        .build(),
                LocationInfoDto.builder()
                        .id(5L)
                        .name("Bila Tserkva")
                        .longitude(49.48)
                        .latitude(30.06)
                        .build(),
                LocationInfoDto.builder()
                        .id(6L)
                        .name("Brovary")
                        .longitude(50.31)
                        .latitude(30.48)
                        .build(),
                LocationInfoDto.builder()
                        .id(7L)
                        .name("Lutsk")
                        .longitude(50.45)
                        .latitude(33.44)
                        .build(),
                LocationInfoDto.builder()
                        .id(8L)
                        .name("Kivertsi")
                        .longitude(50.49)
                        .latitude(25.27)
                        .build(),
                LocationInfoDto.builder()
                        .id(9L)
                        .name("Sarny")
                        .longitude(51.33)
                        .latitude(26.60)
                        .build()
        );
    }

    public static List<RegionInfoDto> allRegionsIntegration() {
        return List.of(RegionInfoDto.builder()
                        .id(1L)
                        .name("Lviv region")
                        .locationInfoDtoList(Collections.emptyList())
                        .build(),
                RegionInfoDto.builder()
                        .id(2L)
                        .name("Kyiv region")
                        .locationInfoDtoList(Collections.emptyList())
                        .build(),
                RegionInfoDto.builder()
                        .id(3L)
                        .name("Volyn region")
                        .locationInfoDtoList(Collections.emptyList())
                        .build());
    }

    public static List<GetUserDto> allUsersIntegration(){
        return List.of(GetUserDto.builder()
                .id(1L)
                .name("John")
                .surname("Doe")
                .age(32)
                .locationInfoDto(LocationInfoDto.builder()
                        .id(1L)
                        .name("Lviv")
                        .longitude(49.84)
                        .latitude(24.031)
                        .build())
                .build(),
                GetUserDto.builder()
                        .id(2L)
                        .name("Abu")
                        .surname("Dabi")
                        .age(43)
                        .locationInfoDto(LocationInfoDto.builder()
                                .id(2L)
                                .name("Radechiv")
                                .longitude(50.16)
                                .latitude(24.38)
                                .build())
                        .build(),
                GetUserDto.builder()
                        .id(3L)
                        .name("Haku")
                        .surname("Riko")
                        .age(24)
                        .locationInfoDto(LocationInfoDto.builder()
                                .id(3L)
                                .name("Stryi")
                                .longitude(49.26)
                                .latitude(49.15)
                                .build())
                        .build(),
                GetUserDto.builder()
                        .id(4L)
                        .name("Minato")
                        .surname("Nani")
                        .age(44)
                        .locationInfoDto(LocationInfoDto.builder()
                                .id(4L)
                                .name("Kyiv")
                                .longitude(50.45)
                                .latitude(30.52)
                                .build())
                        .build(),
                GetUserDto.builder()
                        .id(5L)
                        .name("Andriy")
                        .surname("Smotyakov")
                        .age(25)
                        .locationInfoDto(LocationInfoDto.builder()
                                .id(5L)
                                .name("Bila Tserkva")
                                .longitude(49.48)
                                .latitude(30.06)
                                .build())
                        .build());
    }
}
