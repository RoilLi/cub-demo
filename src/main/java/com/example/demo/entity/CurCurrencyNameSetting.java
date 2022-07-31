package com.example.demo.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@DynamicUpdate
@Table(name = "CUR_CURRENCY_NAME_SETTING")
public class CurCurrencyNameSetting {

    /** 流水號 **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    /** 幣別代碼 **/
    @Column(name = "CURRENCY_CODE", length = 10)
    private String currencyCode;

    /** 語言 **/
    @Column(name = "LANGUAGE", length = 5)
    private String language;

    /** 名稱 **/
    @Column(name = "NAME", length = 50)
    private String name;

    /** 建立日期 */
    @Column(name = "CREATE_DATE")
    private Date createDate;

    /** 更新日期 */
    @Column(name = "UPDATE_DATE")
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
