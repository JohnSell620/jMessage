package io.johnsell620.jMessage;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import io.johnsell620.jMessage.resources.*;
 
@Component
public class JerseyConfig extends ResourceConfig 
{
  public JerseyConfig() 
  {
    register(CommentResource.class);
    register(LinkResource.class);
    register(MessageResource.class);
    register(ProfileResource.class);
    register(ThreadResource.class);
    register(UserResource.class);
  }
}
