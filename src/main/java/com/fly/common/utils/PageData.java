package com.fly.common.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 *
 * Created by xinshidai on 17/9/26.
 */
@Data
public class PageData implements Serializable{

    private static final long serialVersionUID = 1L;

    //总记录数
    private int totalCount;
    //每页记录数
    private int pageSize;
    //总页数
    private int totalPage;
    //当前页数
    private int currPage;
    //列表数据
    private List<?> list;

    public PageData(List<?> list,int totalCount,int pageSize,int currPage){
        this.list=list;
        this.totalCount=totalCount;
        this.currPage=currPage;
        this.pageSize=pageSize;
        this.totalPage= (int) Math.ceil((double) totalCount/pageSize);
    }

}
