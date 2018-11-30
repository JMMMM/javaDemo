package ehcache;

import org.ehcache.*;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.builders.UserManagedCacheBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.core.spi.service.LocalPersistenceService;
import org.ehcache.impl.config.persistence.DefaultPersistenceConfiguration;
import org.ehcache.impl.config.persistence.UserManagedPersistenceContext;
import org.ehcache.impl.persistence.DefaultLocalPersistenceService;

import java.io.File;


public class EhcacheDemo {

    public static void main(String[] args) throws CachePersistenceException {
        LocalPersistenceService persistenceService = new DefaultLocalPersistenceService(new DefaultPersistenceConfiguration(new File("/tmp/a.bin")));
        PersistentUserManagedCache<Long, String> cache = UserManagedCacheBuilder.newUserManagedCacheBuilder(Long.class, String.class)
                .with(new UserManagedPersistenceContext<>("cache-name", persistenceService))
                .withResourcePools(ResourcePoolsBuilder.newResourcePoolsBuilder()
                        .heap(10L, EntryUnit.ENTRIES)
                        .disk(10L, MemoryUnit.MB, true))
                .build(true);
        // Work with the cache
        cache.put(42L, "The Answer!");
        System.out.println(cache.get(42L));

        cache.close();
        cache.destroy();

        persistenceService.stop();
    }

}
