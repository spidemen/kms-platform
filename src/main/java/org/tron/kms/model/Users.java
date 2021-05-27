package org.tron.kms.model;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Users {

  private Integer id;

  private String email;

  private String username;

  private String hashed_password;

  private String phone_number;

  private String client_public_key;

  private String role;

  private Boolean is_admin;

  private Date last_login_time;

  private Date created_at;

  private Date updated_at;


}


