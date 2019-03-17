package Proxy.services;


import Proxy.cacheproxy.Cache;
import Proxy.cacheproxy.CacheType;

import java.util.List;

public interface ListGenerator {
    @Cache(cacheType = CacheType.IN_MEMORY, listSize = 10)
    List<Integer> generate(int  count);




}
