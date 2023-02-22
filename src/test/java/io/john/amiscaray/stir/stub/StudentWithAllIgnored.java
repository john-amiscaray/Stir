package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.TableIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentWithAllIgnored {

    @TableIgnore
    private Integer studentId;
    @TableIgnore
    private String name;
    @TableIgnore
    private String sin;
    @TableIgnore
    private Float gpa;
    @TableIgnore
    private String major;

}
