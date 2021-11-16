import org.bson.internal.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * @Desctiption <Template>
 * @Author wujiaming
 * @Date 2021/10/21
 */
public class DES {
    public DES() {
    }

    // 测试
    public static void main(String args[]) throws Exception {
//        // 待加密内容
//        String str = "C2L0D2VIC2L0ZS5XZGFTYS5JBI8XMDI0";
////        String str = "C2L0D2VIC2L0ZS5XZGFTYS5JBI8XMDI0";
//        System.out.println(Base64.decode("C2L0D2VIC2L0ZS5XZGFTYS5JBI8XMDI0"));
//        // 密码，长度要是8的倍数
//        String password = "6569836566";
//
//        byte[] result = DES.encrypt(str.getBytes(), password);
//        System.out.println("加密后：" + Base64.encode(result));
//
//        // 直接将如上内容解密
//        try {
//            byte[] decryResult = DES.decrypt(result, password);
//            System.out.println("解密后：" + new String(decryResult));
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
        System.out.println(new String(Base64.decode("C2L0D2VIC2L0ZS5XZGFTYS5JBI8XMDI0")));

        System.out.println(DES.decrypt(Base64.decode("C2L0D2VIC2L0ZS5XZGFTYS5JBI8XMDI0"),"6569836566"));
    }

    /**
     * 加密
     *
     * @param datasource byte[]
     * @param password   String
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource, String password) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(password.getBytes());
            // 创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src      byte[]
     * @param password String
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, String password) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(password.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }
}
