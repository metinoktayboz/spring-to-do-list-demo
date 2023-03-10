package com.moboz.demo.springtodolistdemo.models.modelView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMV {
    private String firstName;
    private String lastName;
    private String email;
}
