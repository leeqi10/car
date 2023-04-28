package com.xhu.utils;
import com.xhu.entity.Orderplus;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;
import java.math.BigDecimal;
import java.util.*;

/**
 *@ClassName: DistanceUtils
 *@Description: 根据经纬度计算距离
 *@Author: leeqi10
 *@Date: 2023-04-027 16:56
 */
public class DistanceUtils {

    /**
     * 根据经纬度，计算两点间的距离
     * @param longitudeFrom  第一个点的经度
     * @param latitudeFrom  第一个点的纬度
     * @param longitudeTo 第二个点的经度
     * @param latitudeTo  第二个点的纬度
     * @return 返回距离 单位米
     */
    public static double getDistance(double longitudeFrom, double latitudeFrom, double longitudeTo, double latitudeTo) {
        GlobalCoordinates source = new GlobalCoordinates(latitudeFrom, longitudeFrom);
        GlobalCoordinates target = new GlobalCoordinates(latitudeTo, longitudeTo);
        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.Sphere, source, target).getEllipsoidalDistance();
    }

    /**
     * 根据经纬度，计算两点间的距离
     * @param longitudeFrom  第一个点的经度
     * @param latitudeFrom  第一个点的纬度
     * @param longitudeTo 第二个点的经度
     * @param latitudeTo  第二个点的纬度
     * @param accurate  保留小数点几位
     * @return 返回距离 单位千米
     */
    public static double getDistance(double longitudeFrom, double latitudeFrom, double longitudeTo, double latitudeTo,int accurate) {
        double distance = getDistance(longitudeFrom, latitudeFrom, longitudeTo, latitudeTo);
        if (accurate < 0) {
            throw new RuntimeException("精确度必须是正整数或零");
        }
        return new BigDecimal(distance).divide(new BigDecimal(1000),accurate, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 根据值来进行排序
     * @param map
     * @return
     */
    public static Map<Orderplus, Integer> sortMap(Map<Orderplus, Integer> map) {
        //利用Map的entrySet方法，转化为list进行排序
        List<Map.Entry<Orderplus, Integer>> entryList = new ArrayList<>(map.entrySet());
        //利用Collections的sort方法对list排序
        Collections.sort(entryList, new Comparator<Map.Entry<Orderplus, Integer>>() {
            @Override
            public int compare(Map.Entry<Orderplus, Integer> o1, Map.Entry<Orderplus, Integer> o2) {
                //正序排列，倒序反过来
                return o1.getValue() - o2.getValue();
            }
        });
        //遍历排序好的list，一定要放进LinkedHashMap，因为只有LinkedHashMap是根据插入顺序进行存储
        LinkedHashMap<Orderplus, Integer> linkedHashMap = new LinkedHashMap<Orderplus, Integer>();
        for (Map.Entry<Orderplus,Integer> e : entryList
        ) {
            linkedHashMap.put(e.getKey(),e.getValue());
        }
        return linkedHashMap;
    }
    /**
     * 根据值来进行排序
     * @param map
     * @return
     */
    public static Map<String, Integer> sortMapString(Map<String, Integer> map) {
        //利用Map的entrySet方法，转化为list进行排序
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());
        //利用Collections的sort方法对list排序
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //正序排列，倒序反过来
                return o1.getValue() - o2.getValue();
            }
        });
        //遍历排序好的list，一定要放进LinkedHashMap，因为只有LinkedHashMap是根据插入顺序进行存储
        LinkedHashMap<String, Integer> linkedHashMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String,Integer> e : entryList
        ) {
            linkedHashMap.put(e.getKey(),e.getValue());
        }
        return linkedHashMap;
    }

    public static void main(String[] args) {
        double result = getDistance(116.336116, 40.0708, 116.41235, 40.053032,0);
        System.out.println("经纬度距离计算结果：" + result+ "千米");
    }



}
