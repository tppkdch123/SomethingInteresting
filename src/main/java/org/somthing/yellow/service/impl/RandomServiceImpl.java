package org.somthing.yellow.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.somthing.yellow.service.RandomService;
import org.somthing.yellow.vo.FirstVO;
import org.somthing.yellow.vo.Point;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by huangshizhe on 2018/8/18
 */

@Service
public class RandomServiceImpl implements RandomService {

    public static Logger LOGGER = LoggerFactory.getLogger(RandomServiceImpl.class);

    @Override
    public FirstVO getResult(String params, Double value) {
        List<Point> param = Arrays.stream(params.split(";")).parallel().distinct().map(Point::new).collect(Collectors.toCollection(LinkedList::new));

        Point start = param.get(0);
        List<Point> result = new LinkedList<>();
        Point end = param.get(param.size() - 1);

        double sum = 0;
        int index = -1;
        double max = -1;

        for (int i = 1; i < param.size() - 1; i++) {
            double distance = getDistance(start, end, param.get(i));
            sum += distance;
            if (distance > max) {
                max = distance;
                index = i;
            }
        }

        List<Point> op = getResult(param, value, sum, max, index, result);

        if (CollectionUtils.isEmpty(op)) {
            return new FirstVO();
        }

        FirstVO firstVO = new FirstVO();

        firstVO.setResultPoints(op);

        firstVO.setResultSeparatePoints(result);

        firstVO.setPoints(op.stream().map(Point::getResult).collect(Collectors.joining(";","",";")));

        firstVO.setSeparatePoints(result.stream().map(Point::getResult).collect(Collectors.joining(";","",";")));

        return firstVO;
    }

    @Override
    public String getResultByString(String params, Double value) {

        LOGGER.info("入参 [ {} ]",params);
        List<Point> param = Arrays.stream(params.split(";")).parallel().distinct().map(Point::new).collect(Collectors.toCollection(LinkedList::new));

        Point start = param.get(0);
        List<Point> result = new LinkedList<>();
        Point end = param.get(param.size() - 1);

        double sum = 0;
        int index = -1;
        double max = -1;

        for (int i = 1; i < param.size() - 1; i++) {
            double distance = getDistance(start, end, param.get(i));
            sum += distance;
            if (distance > max) {
                max = distance;
                index = i;
            }
        }

        List<Point> op = getResult(param, value, sum, max, index, result);

        if (CollectionUtils.isEmpty(op)) {
            return "返回结果为空";
        }

        StringBuilder str = new StringBuilder();

        str.append("结果点：").append("\n").append(op.stream().map(Point::getResult).collect(Collectors.joining(";","",";")))
                .append("分割点：\n").append(result.stream().map(Point::getResult).collect(Collectors.joining(";","",";")));
        return str.toString();
    }

    /**
     * @param str   入参
     * @param value 阈值
     */
    public void getR(String str, double value) {
        List<Point> param = Arrays.stream(str.split(";")).parallel().distinct().map(Point::new).collect(Collectors.toCollection(LinkedList::new));
        /**System.out.println("X轴坐标:");
         param.stream().map(Point::getX).forEach((string) -> System.out.println(string));
         System.out.println("Y轴坐标:");
         param.stream().map(Point::getY).forEach((string) -> System.out.println(string));
         **/

        Point start = param.get(0);
        List<Point> result = new LinkedList<>();
        Point end = param.get(param.size() - 1);

        double sum = 0;
        int index = -1;
        double max = -1;

        for (int i = 1; i < param.size() - 1; i++) {
            double distance = getDistance(start, end, param.get(i));
            sum += distance;
            if (distance > max) {
                max = distance;
                index = i;
            }
        }

        List<Point> op = getResult(param, value, sum, max, index, result);
        System.out.println("结果集X轴坐标:");
        op.stream().map(Point::getX).forEach((string) -> System.out.println(string));
        System.out.println("结果集Y轴坐标:");
        op.stream().map(Point::getY).forEach((string) -> System.out.println(string));
        System.out.println("=================================================");
        System.out.println("分隔点X轴坐标");
        result.stream().map(Point::getX).forEach((string) -> System.out.println(string));
        System.out.println("///////////////////");
        System.out.println("分隔点Y轴坐标");
        result.stream().map(Point::getY).forEach((string) -> System.out.println(string));
    }

    public static Double getDistance(Point line1, Point line2, Point point) {

        //线段由点1指向点2的向量
        Point x1 = Point.jianPoint(line2, line1);
        //线段由点1指向目标点的向量
        Point x2 = Point.jianPoint(point, line1);

        //获取x1和x2点积
        double length1 = Point.pointCheng(x1, x2);

        //如果点积小于0,说明两个向量之间的距离大于90度,最短距离为点1到point之间的距离
        if (length1 < 0) {
            return Point.getDistance(line1, point);
        }

        double length = Point.pointCheng(x1, x1);

        if (length1 > length) {
            return Point.getDistance(line2, point);
        }
        Point cut = Point.addPoint(line1, x1.getNewPoint(length1, length));
        return Point.getDistance(cut, point);
    }

    public List<Point> getResult(List<Point> param, double value, double sum, double max, Integer index, List<Point> result) {

        if (param == null || param.size() < 3 || sum <= 0 || max <= 0 || index == null || index <= 0) {
            return new ArrayList<>();
        }

        if (max <= value) {
            result.add(param.get(0));
            result.add(param.get(param.size() - 1));
            return param;
        }

        Point start = param.get(0);

        Point medium = param.get(index);

        Point end = param.get(param.size() - 1);

        int index1 = -1, index2 = -1;
        double max1 = -1, max2 = -1;
        double sum1 = 0, sum2 = 0;

        List<Point> l1 = param.subList(0, index + 1);
        List<Point> l2 = param.subList(index, param.size());

        for (int i = 1; i < l1.size() - 1; i++) {
            double distance = getDistance(l1.get(0), l1.get(l1.size() - 1), l1.get(i));
            sum1 += distance;
            if (distance > max1) {
                max1 = distance;
                index1 = i;
            }
        }

        for (int i = 1; i < l2.size() - 1; i++) {
            double distance = getDistance(l2.get(0), l2.get(l2.size() - 1), l2.get(i));
            sum2 += distance;
            if (distance > max2) {
                max2 = distance;
                index2 = i;
            }
        }

        List<Point> list = new ArrayList<>();
        if (sum1 + sum2 <= sum) {
            list.addAll(getResult(l1, value, sum1, max1, index1, result));
            List<Point> points = getResult(l2, value, sum2, max2, index2, result);
            if (points != null && points.size() != 0) {
                list.addAll(points.subList(1, points.size()));
                if (!list.contains(points.get(0))) {
                    list.add(points.get(0));
                }
            }
            return list;
        } else {
            List<Point> linshi = new ArrayList<>();
            int i = 0;
            double two = 0;
            index = -1;
            for (int x = 0; x < param.size(); x++) {
                Point point = param.get(x);
                if (point != medium) {
                    linshi.add(point);
                    double d = getDistance(start, end, point);
                    if (d > two) {
                        two = d;
                        index = i;
                    }
                    i++;
                } else {
                    sum -= getDistance(start, end, medium);
                }
            }
            return getResult(linshi, value, sum, max, index, result);
        }
    }
}
