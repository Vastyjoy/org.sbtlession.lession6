package Proxy.services;

import Proxy.cacheproxy.Cache;
import Proxy.cacheproxy.CacheType;

public interface Calculator {
    @Cache(cacheType = CacheType.FILE, fileNamePrefix = "cache_calc", zip=true, identityBy = {1})
    int calc(String name, int arg1);
}
