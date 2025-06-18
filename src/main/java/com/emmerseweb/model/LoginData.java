package com.emmerseweb.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginData {


        private String userName;
        private String password;
        private String expectedResult;
        private String errorMessage;
        private String toastMessage;
        private String expectedToastMessage;
        private String expectedErrorMessage;


}
