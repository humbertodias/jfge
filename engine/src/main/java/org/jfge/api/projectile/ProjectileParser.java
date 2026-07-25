package org.jfge.api.projectile;

import java.io.IOException;

public interface ProjectileParser {

  Projectile parseFromXmlFile(String file) throws IOException;
}
