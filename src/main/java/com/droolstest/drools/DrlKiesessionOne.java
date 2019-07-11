
package com.droolstest.drools;

import com.droolstest.domain.Person;
import com.droolstest.domain.School;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.conf.MultithreadEvaluationOption;
import org.kie.internal.utils.KieHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class DrlKiesessionOne {
    private static int thread_num = 200;//线程数,设置同时并发线程数
    private static int client_num = 10;//访问次数
    /**
     * 测试 并发下 使用
     * 同一个Kiesession有状态
     * 同 KieHelper
     */
    /**
     * 结论1：rule 名是不能重复，这一点，符合kmodule.xml 规范要求
     * 结论2：rule与rule之间的Fact会相互作用  也就是说 规则输出修改后的值 可以是李四 也有可能是王王， 但只有 一个规则文件会执行
     * 结论3：高并发下 有状态会迭代执行， 导致数据信息错误
     * 结论4：helper1.build(MultithreadEvaluationOption.YES).newKieSession(); 并无实际效果
     * 结论5：有状态创建的时间比无状态创建的时间要长
     *
     * @param args
     */
    public static void main(String[] args) {
        //规则文件
        String myRuleFile1 = "package rules " + "import  com.droolstest.domain.Person;" + "import  com.droolstest.domain.School;" +
                "rule \"myRule1\" " +
                "   when " +
                "        $p:Person(name==\"张三\")" +
                "        $s:School(name==\"北大\")" +
                "   then " +
                "      $p.setName(\"李四\");" +
                "      $s.setName(\"清华\");" +
                "      update($p);" +
                "      update($s);" +
                "end                     " +
                "rule \"myRule2\" " +
                "   when " +
                "        $p:Person(name==\"李四\")" +
                "        $s:School(name==\"清华\")" +
                "   then " +
                " System.out.println(\"规则myRuleFile1被调用\");" +
                "end";
        String myRuleFile2 = "package rules " + "import com.droolstest.domain.Person;" + "import  com.droolstest.domain.School;"  +
                "rule \"myRule3\" " +
                "   when " +
                "        $p:Person(name==\"张三\")" +
                "        $s:School(name==\"北大\")" +
                "   then " +
                "                   $p.setName(\"王王\");" +
                "                   $s.setName(\"张大\");" +
                "   update($p);" +
                "   update($s);" +
                "end                     " +
                "rule \"myRule4\" " +
                "   when " +
                "        $p:Person(name==\"王王\")" +
                "        $s:School(name==\"张大\")" +
                "   then " +
                " System.out.println(\"规则myRuleFile2调用\");" +
                "end";
        long start = System.currentTimeMillis();
        KieHelper helper1 = new KieHelper();
        long end = System.currentTimeMillis();
        System.out.println("输出创建KieHelper用的毫秒是=" + (end - start));

        long startAddrule = System.currentTimeMillis();
        //分别将规则myRuleFile2 myRuleFile1 加载到虚拟文件中
        helper1.addContent(myRuleFile1, ResourceType.DRL);
        helper1.addContent(myRuleFile2, ResourceType.DRL);

        long endAddrule = System.currentTimeMillis();
        System.out.println("导入规则所用到的毫秒是=" + (endAddrule - startAddrule));
        long startNewKiesession = System.currentTimeMillis();
        KieSession kieSession = helper1.build(MultithreadEvaluationOption.YES).newKieSession();
        long endNewKiesession = System.currentTimeMillis();
        System.out.println("创建有状态Kiesession所用到的毫秒是=" + (endNewKiesession - startNewKiesession));

        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semp = new Semaphore(thread_num);
        for (int index = 0; index < client_num; index++) {
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        semp.acquire();
                        long startrule = System.currentTimeMillis();
                        Person person = new Person();
                        person.setName("张三");
                        person.setAge("" + (int) (Math.random() * 100));
                        School school = new School();
                        school.setName("北大");
                        school.setCount("40");
                        kieSession.insert(person);
                        kieSession.insert(school);
                        int s1 = kieSession.fireAllRules();
                        System.out.println("kieSession执行规则" + s1);
                        System.out.println(person.getName());
                        //kieSession.dispose();//注释的原因  同一个Kiesession有状态 是线程以外创建的Kiesession 所以必须要注释
                        long endrule = System.currentTimeMillis();
                        System.out.println("规则执行所用到的毫秒是=" + (endrule - startrule));
                        System.out.println("=============================牛逼的分割线=========================================");
                        semp.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            exec.execute(run);
        }
        exec.shutdown();
    }
}