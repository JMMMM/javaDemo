package com.droolstest.drools;

import com.droolstest.domain.Fire;
import com.droolstest.domain.Room;
import com.droolstest.domain.Sprinkler;
import org.drools.compiler.kie.builder.impl.KieFileSystemImpl;
import org.drools.core.io.impl.ClassPathResource;
import org.drools.core.io.impl.FileSystemResource;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.kie.internal.utils.KieHelper;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * com.droolstest.drools
 *
 * @author jimmy
 * @date 2019-07-11
 */
public class StateFulSessionTestDemo {
    public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        FileSystemResource resource = new FileSystemResource("/Users/jimmy/Work/Jimmy/javaDemo/src/main/resources/rules/rules.drl");
        kieFileSystem.write(resource);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        KieRepository kieRepository = kieServices.getRepository();
        kieBuilder.buildAll();
        KieContainer kieContainer = kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
        KieSession kieSession = kieContainer.newKieSession();

        //step 1 执行后，work memory 里面存在 多个Room 事实
        String[] names = new String[]{"kitchen", "bedroom", "office", "livingroom"};
        Map<String, Room> name2room = new HashMap<>();
        for (String name : names) {
            Room room = new Room(name);
            name2room.put(name, room);
            kieSession.insert(room);
            Sprinkler sprinkler = new Sprinkler(room);
            kieSession.insert(sprinkler);
        }
        kieSession.fireAllRules();

        //step 2 执行后，work memory 存在两个Fire 事实
        Fire kitchenFire = new Fire(name2room.get("kitchen"));
        Fire officeFire = new Fire(name2room.get("office"));
        FactHandle kitchenFireHandle = kieSession.insert(kitchenFire);
        FactHandle officeFireHandle = kieSession.insert(officeFire);
        kieSession.fireAllRules();

        //step 3 work memory 中移除两个Fire事实，此时work memory 还有两个Room事实
        kieSession.delete(kitchenFireHandle);
        kieSession.delete(officeFireHandle);
        kieSession.fireAllRules();

    }
}
