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
public class StudentWithIgnored {

    private Integer studentId;
    private String name;
    @TableIgnore
    private String sin;
    private Float gpa;
    private String major;

}
