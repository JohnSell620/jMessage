package io.johnsell620.jMessage;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import io.johnsell620.jMessage.resources.UserResource;
 
@Component
public class JerseyConfig extends ResourceConfig 
{
  public JerseyConfig() 
  {
    register(UserResource.class);
  }
}
