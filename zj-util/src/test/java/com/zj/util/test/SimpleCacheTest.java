package com.zj.util.test;

import com.zj.util.cache.SimpleCache;
import org.junit.Test;

/**
 * Created by Yangxi on 2017/4/17 0017.
 */
public class SimpleCacheTest {
    @Test
    public void testCache(){
        SimpleCache simpleCache = new SimpleCache( 10 );
        for (int i = 0; i < 20; i++) {
            simpleCache.put( "key"+i,"value"+i );
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(simpleCache.get( "key"+i ));
        }
    }
}
