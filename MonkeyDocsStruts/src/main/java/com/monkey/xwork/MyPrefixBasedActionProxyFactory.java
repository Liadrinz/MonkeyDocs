package com.monkey.xwork;

import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.ActionProxyFactory;
import com.opensymphony.xwork2.DefaultActionProxyFactory;
import com.opensymphony.xwork2.inject.Container;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.logging.Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.struts2.StrutsConstants;

import java.util.HashMap;
import java.util.Map;

public class MyPrefixBasedActionProxyFactory extends DefaultActionProxyFactory {

    private static final Logger LOG = (Logger) LogManager.getLogger(MyPrefixBasedActionProxyFactory.class);

    private Map<String, ActionProxyFactory> actionProxyFactories = new HashMap<>();

    @Inject
    public void setContainer(Container container) {
        this.container = container;
    }

    @Inject(StrutsConstants.PREFIX_BASED_MAPPER_CONFIGURATION)
    public void setPrefixBasedActionProxyFactories(String list) {
        if (list != null) {
            String[] factories = list.split(",");
            for (String factory : factories) {
                String[] thisFactory = factory.split(":");
                if (thisFactory.length == 2) {
                    String factoryPrefix = thisFactory[0].trim();
                    String factoryName = thisFactory[1].trim();
                    ActionProxyFactory obj = container.getInstance(ActionProxyFactory.class, factoryName);
                    if (obj != null) {
                        actionProxyFactories.put(factoryPrefix, obj);
                    } else {
                        LOG.warn("Invalid PrefixBasedActionProxyFactory config entry: [{}]", factory);
                    }
                }
            }
        }
    }

    public ActionProxy createActionProxy(String namespace, String actionName, String methodName,
                                         Map<String, Object> extraContext, boolean executeResult, boolean cleanupContext) {

        String uri = namespace + (namespace.endsWith("/") ? actionName : "/" + actionName);
        for (int lastIndex = uri.lastIndexOf('/'); lastIndex > (-1); lastIndex = uri.lastIndexOf('/', lastIndex - 1)) {
            String key = uri.substring(0, lastIndex);
            ActionProxyFactory actionProxyFactory = actionProxyFactories.get(key);
            if (actionProxyFactory != null) {
                LOG.debug("Using ActionProxyFactory [{}] for prefix [{}]", actionProxyFactory, key);
                return actionProxyFactory.createActionProxy(namespace, actionName, methodName, extraContext, executeResult, cleanupContext);
            } else {
                LOG.debug("No ActionProxyFactory defined for [{}]", key);
            }
        }
        LOG.debug("Cannot find any matching ActionProxyFactory, falling back to [{}]", super.getClass().getName());
        return super.createActionProxy(namespace, actionName, methodName, extraContext, executeResult, cleanupContext);
    }

}