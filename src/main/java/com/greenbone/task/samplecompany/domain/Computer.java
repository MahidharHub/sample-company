package com.greenbone.task.samplecompany.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Computer {

    private static final String IP_V4_PATTERN =  "^(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.(?!$)|$)){4}$";
    private static final String MAC_REGEX = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$" ;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "This is id and it is auto generated",example = "1")
    private  Long id;

    @NotBlank(message = "MAC is mandatory")
    @Column(name = "mac_address", nullable = false)
    @Pattern(regexp = MAC_REGEX , message = "Invalid MAC address")
    @Schema(description = "This is MAC address of a computer",example = "00-1B-63-84-55-11")
    private String mac;

    @NotBlank(message = "IPAddress is mandatory")
    @Column(name = "ip_address", nullable = false)
    @Pattern(regexp = IP_V4_PATTERN , message = "Invalid IPV4 address")
    @Schema(description = "This is IP address of a computer",example = "101.122.212.211")
    private String ipAddress;

    @Column(name = "employee", nullable = false)
    @Schema(description = "This is abbreviated employee name",example = "IJS")
    private String employee;

    @Column(name = "description", nullable = false)
    @Schema(description = "This is description",example = "Allotted to IJS 1")
    private String description;

    public Computer(String mac, String ipAddress, String employee, String description) {
        this.mac = mac;
        this.ipAddress = ipAddress;
        this.employee = employee;
        this.description = description;
    }
}
