package io.john.amiscaray.stir.stub;

import io.john.amiscaray.stir.annotation.TableData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentWithTableAnnotation {

    @TableData(columnName = "Student ID")
    private Integer studentId;
    @TableData(columnName = "Name")
    private String name;
    @TableData(columnName = "GPA")
    private Float gpa;

}
