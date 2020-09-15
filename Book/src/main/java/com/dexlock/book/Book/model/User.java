package com.dexlock.book.Book.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users",
       uniqueConstraints = {
         @UniqueConstraint(columnNames = "username"),
         @UniqueConstraint(columnNames = "email"),
         @UniqueConstraint(columnNames = "password")
       }
)
public class User {
       @Id
       @GeneratedValue(strategy = GenerationType.AUTO)
       private Long id;

       @NotBlank(message = "Name is mandatory")
       @Size(max = 40)
       @Column
       private  String username;

       @NotBlank(message = "Email is mandatory")
       @Email
       @Column
       private  String email;

       @NotBlank(message = "Password is mandatory")
       @Size(max = 40)
       @Column
       private  String password;

       @ManyToMany(fetch = FetchType.LAZY)
       @JoinTable(	name = "user_roles",
               joinColumns = @JoinColumn(name = "user_id"),
               inverseJoinColumns = @JoinColumn(name = "role_id"))
       private Set<Role> roles = new HashSet<>();

       public User() {
       }

       public User(Long id, String username,  String email, String password) {
              this.id = id;
              this.username = username;
              this.email = email;
              this.password = password;
       }

       public User(Long id, String username, String email, String password, Set<Role> roles) {
              this.id = id;
              this.username = username;
              this.email = email;
              this.password = password;
              this.roles = roles;
       }

       public Long getId() {
              return id;
       }

       public void setId(Long id) {
              this.id = id;
       }

       public String getUsername() {
              return username;
       }

       public void setUsername(String username) {
              this.username = username;
       }

       public String getEmail() {
              return email;
       }

       public void setEmail(String email) {
              this.email = email;
       }

       public String getPassword() {
              return password;
       }

       public void setPassword(String password) {
              this.password = password;
       }

       public Set<Role> getRoles() {
              return roles;
       }

       public void setRoles(Set<Role> roles) {
              this.roles = roles;
       }
}
