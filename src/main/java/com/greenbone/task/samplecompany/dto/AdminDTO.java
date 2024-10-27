package com.greenbone.task.samplecompany.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {

    private String level;
    private String employeeAbbreviation;
    private String message;

    private AdminDTO(AdminBuilder adminBuilder) {
        this.level=adminBuilder.level;
        this.employeeAbbreviation=adminBuilder.employeeAbbreviation;
        this.message=adminBuilder.message;

    }


    public static class AdminBuilder{

        private String level;
        private String employeeAbbreviation;
        private String message;


        public AdminBuilder setLevel(String level) {
            this.level = level;
            return this;
        }

        public AdminBuilder setEmployeeAbbreviation(String employeeAbbreviation) {
            this.employeeAbbreviation = employeeAbbreviation;
            return this;
        }

        public AdminBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public AdminDTO build(){
            return new AdminDTO(this);
        }

    }

}
