package io.github.treech.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class BeanUtils {

    /**
     * 深拷贝对象-需要实现Serializable接口，同时配置serialVersionUID
     *
     * @param origin
     * @return 拷贝对象
     */
    public static Object deepCopy(final Object origin) {
        Object destList = null;
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(origin);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream inStream = new ObjectInputStream(byteIn);
            destList = inStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return destList;
    }


    /**
     * 深拷贝集合-需要实现Serializable接口，同时配置serialVersionUID
     *
     * @param srcList
     * @param <T>
     * @return
     */
    public static <T> List<T> deepCopy(List<T> srcList) {
        List<T> destList = null;
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(srcList);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream inStream = new ObjectInputStream(byteIn);
            destList = (List<T>) inStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return destList;
    }
}
