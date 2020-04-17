package com.monkey.entity;

import javax.persistence.*;

@Entity
@Table
public class User
{
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String nickname;
    @Column(unique = true)
    private String tel;
    @Column(unique = true)
    private String email;

    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getId()
    {
        return this.id;
    }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public String getTel() { return tel; }
    public void setTel(String name) {
        this.tel = name;
    }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
