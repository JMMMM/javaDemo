package demo;

import com.github.dapeng.core.SoaException;
import com.isuwang.soa.admin.AdminCacheServiceClient;
import com.isuwang.soa.company.CompanyOnBinlogServiceClient;
import com.isuwang.soa.company.service.CompanyOnBinlogService;
import com.isuwang.soa.crm.company.enums.CrmCompanyType;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Demo {
    public static void main(String[] args) throws SoaException {
//        String[] str = {"广州", "上海", "北京", "四川", "茂名", "佛山", "湖南", "广州快塑", "公司", "常州拓荣塑料有限公司"};
        String[] str = {"海南华塑新型管业有限公司"};
        CompanyOnBinlogServiceClient client = new CompanyOnBinlogServiceClient();
        Random random = new Random();
        long max = 0;
        while (true) {
            int index = random.nextInt(str.length);
            long start = System.currentTimeMillis();
            String value = client.findCompanyByKeyword(str[index], CrmCompanyType.PURCHASER);
            long takes = System.currentTimeMillis() - start;
            if (takes > max) System.out.println(takes + "ms");
            System.out.println(value + " " + takes + "ms");
        }

    }
}
