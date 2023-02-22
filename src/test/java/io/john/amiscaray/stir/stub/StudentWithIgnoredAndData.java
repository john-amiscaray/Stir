package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.TableData;
import io.john.amiscaray.stir.annotation.TableIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentWithIgnoredAndData {

    @TableData(columnName="Student ID")
    private Integer studentId;
    @TableData(columnName="Name")
    private String name;
    @TableData(columnName="SIN")
    @TableIgnore
    private String sin;
    @TableData(columnName="GPA")
    private Float gpa;
    @TableData(columnName="Major")
    private String major;

}
