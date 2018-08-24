package org.something.yellow.vo;

/**
 * Created by huangshizhe on 2018/8/18
 */
public class Point {
    public double x;
    public double y;

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point(String str) {
        String[] op = str.split(",");
        this.x = Double.valueOf(op[0]);
        this.y = Double.valueOf(op[1]);
    }

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public String getResult() {
        return x + "," + y;
    }

    public static double getDistance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(Math.abs(p1.x - p2.x), 2) + Math.pow(Math.abs(p1.y - p2.y), 2));
    }

    public static Point addPoint(Point p1, Point p2) {
        return new Point(p1.x + p2.x, p1.y + p2.y);
    }

    public static Point jianPoint(Point p1, Point p2) {
        return new Point(p1.x - p2.x, p1.y - p2.y);
    }

    public static double pointCheng(Point p1, Point p2) {
        return p1.x * p2.x + p1.y * p2.y;
    }

    public Point getNewPoint(double a, double b) {
        return new Point(x * a / b, y * a / b);
    }
}
