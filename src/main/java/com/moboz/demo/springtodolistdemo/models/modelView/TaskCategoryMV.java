package com.moboz.demo.springtodolistdemo.models.modelView;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskCategoryMV {
    private String categoryName;
}
