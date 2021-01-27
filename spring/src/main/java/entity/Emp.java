package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {
    private Integer id;
    private String name;
    private String sex;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date birthday;
    private Double salary;
    private List<String> hobbies;
    private Car cars;

}
