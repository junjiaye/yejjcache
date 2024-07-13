package io.github.junjiaye.yejj.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: yejjcache
 * @ClassName: YeJJApplicationListener
 * @description: plugins的集合点
 * @author: yejj
 * @create: 2024-06-16 10:41
 */
@Component
public class YeJJApplicationListener implements ApplicationListener<ApplicationEvent> {

    @Autowired
    List<YeJJPlugin> plugins;

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ApplicationReadyEvent are){
            plugins.forEach(plugin->{
                plugin.init();
                plugin.startup();
            });
        }else if (event instanceof ContextClosedEvent cce){
            plugins.forEach(YeJJPlugin::shutdown);
        }
    }
}
