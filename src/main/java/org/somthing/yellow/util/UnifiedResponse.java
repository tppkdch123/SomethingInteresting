package org.somthing.yellow.util;

import lombok.Data;

@Data
public class UnifiedResponse{
    private int status=-1;
    private String message;
    private Object data;

    public UnifiedResponse(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public UnifiedResponse(Exception e){
        this.status=-1;
        this.message="500,服务器内部异常";
        this.data=e.getMessage();
    }

    public UnifiedResponse(Object data){
        this.status=0;
        this.message="成功";
        this.data=data;
    }


    public UnifiedResponse(Integer status,String Content){
        this.status=status;
        this.message=Content;
        this.data=null;
    }
}
