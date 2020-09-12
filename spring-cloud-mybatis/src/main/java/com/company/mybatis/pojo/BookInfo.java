package com.company.mybatis.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * book_info
 * @author 
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookInfo implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 图书描述
     */
    private String bookDesc;

    /**
     * 图书作者
     */
    private String bookAuthor;

    /**
     * 图书价格
     */
    private BigDecimal bookPrice;

    /**
     * 图书数量
     */
    private Integer bookNum;

    /**
     * 是否上架0否1是
     */
    private Integer onLine;

    private Integer bookType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}