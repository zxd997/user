package com.zxd.user.utils;

import com.zxd.user.enums.ResultEnum;
import com.zxd.user.vo.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object o){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(o);
        return resultVO;
    }
    public static ResultVO success(){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        return resultVO;
    }
    public static ResultVO error(ResultEnum resultEnum){
        ResultVO<Object> resultVO = new ResultVO<>();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMsg());
        return resultVO;
    }
}
