package com.fengxun.funsun.model.request;

import com.fengxun.funsun.model.bean.HeadlinesBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/27.
 * Holle Android
 */

public class CacheData {

//    public static final int recent_subject_list = 8;
//
//    /**
//     * 获取文件名
//     * @param uid 用户的id
//     * @param studentId 文件唯一标示
//     * @param type :数据类型
//     * @return
//     */
//    public static String getFileNameById(int type, String uid, String studentId) {
//        File file = new File(Config.CACHE_DATA_PATH);
//        if (!file.exists())
//            file.mkdirs();
//        String fileName = "cachedata_" + uid + "_" + type + "_" + studentId + ".dat";
//        return fileName;
//    }
//
//
//    /**
//     * 存储缓存文件：
//     */
//    public static void saveRecentSubList(String uid, String studentId, List<HeadlinesBean> list) {
//        int type = recent_subject_list;
//        String fileName = Config.CACHE_DATA_PATH + File.separator + getFileNameById(type, uid, studentId);
//        File file = new File(fileName);
//        try {
//            if (!file.exists())
//                file.createNewFile();
//            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
//            oos.writeObject(list);
//            oos.flush();
//            oos.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * 读取缓存文件：
//     */
//
//    public static List<HeadlinesBean> getRecentSubList(String uid, String studentId) {
//        int type = recent_subject_list;
//        List<HeadlinesBean> resultList = new ArrayList<>();
//        String fileName = Config.CACHE_DATA_PATH + File.separator + getFileNameById(type, uid, studentId);
//        try {
//            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
//            ArrayList<HeadlinesBean> list_ext = (ArrayList<HeadlinesBean>) ois.readObject();
//            for (HeadlinesBean obj : list_ext) {
//                HeadlinesBean bean = obj;
//                if (bean != null) {
//                    resultList.add(bean);
//                }
//            }
//            ois.close();
//        } catch (Exception e) {
//            return resultList;
//        }
//        return resultList;
//    }
}
