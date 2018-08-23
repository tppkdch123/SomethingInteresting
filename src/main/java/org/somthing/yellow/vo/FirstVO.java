package org.somthing.yellow.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huangshizhe on 2018/8/18
 * @author huangshizhe
 */

@Data
public class FirstVO implements Serializable{

    String introduction = "points/resultPoints点结果集；SeparatePoints/resultSeparatePoints分隔点集";

    String points;

    String separatePoints;

    List<Point> resultPoints;

    List<Point> resultSeparatePoints;
}
