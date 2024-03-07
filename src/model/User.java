package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class User {
    private Integer user_id;
    private  String user_name;
    private  String user_email;
    private  String user_password;
    private  Boolean is_deleted;
    private String user_uuid;
    private   Boolean is_verified;
}
