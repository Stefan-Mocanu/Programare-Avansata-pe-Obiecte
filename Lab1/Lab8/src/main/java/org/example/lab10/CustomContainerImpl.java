package org.example.lab10;

import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class CustomContainerImpl implements CustomContainer {
    private HashMap<String,Object> container = new HashMap<>();
    private final HashMap<String,Function<CustomContainer, ?>> factories = new HashMap<>();
    @Override
    public <T> boolean addInstance(T instance) {
        if(instance == null)throw new NullNotAllowed();
        return addInstance(instance,instance.getClass().getName());
    }

    @Override
    public <T> boolean addInstance(T instance, String customName) {
        if(instance == null || customName == null)throw new NullNotAllowed();
        if(container.containsKey(customName)){
            throw new CannotRedeclare();
        }else{
            container.put(customName, instance);
        }
        return true;
    }

    @Override
    public <T> T getInstance(Class<T> type) {
        if(type == null)throw new NullNotAllowed();
        return getInstance(type,type.getName());
    }

    @Override
    public <T> T getInstance(Class<T> type, String customName) {
        if(type == null || customName == null)throw new NullNotAllowed();
        if(container.get(customName)==null) {
            if (factories.containsKey(type.getName()))
                addInstance(factories.get(type.getName()).apply(this), customName);
            else{
                throw new NoInstance();
            }
        }
        if(!container.get(customName).getClass().getName().equals(type.getName()))
            throw new InvalidType();
        else{
            return (T) container.get(customName);
        }
    }

    @Override
    public <T> boolean addFactoryMethod(Class<T> type, Function<CustomContainer, T> factoryMethod) {
        if(type == null || factoryMethod == null)throw new NullNotAllowed();
        factories.put(type.getName(), factoryMethod);
        return true;
    }

    @Override
    public <T> T create(Class<T> type) {
        if (factories.containsKey(type.getName()))
            return (T) factories.get(type.getName()).apply(this);
        else{
            throw new NoInstance();
        }
    }

    @Override
    public <T> T create(Class<T> type, Map<String, Object> parameters) {
        HashMap<String,Object> container_auxiliar = container;
        container.putAll(parameters);
        T return_item = create(type);
        container = container_auxiliar;
        return return_item;
    }

    @Override
    public void close() throws Exception {
        for (Map.Entry<String,Object> entry : container.entrySet()){
            if(entry.getValue() instanceof Closeable){
                ((Closeable) entry.getValue()).close();
            }
        }
    }
}
