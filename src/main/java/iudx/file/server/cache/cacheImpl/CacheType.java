package iudx.file.server.cache.cacheImpl;

public enum CacheType {
  REVOKED_CLIENT("revoked_client");

  String cacheName;

  CacheType(String name) {
    this.cacheName = name;
  }


}
