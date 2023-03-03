package services.factory;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

public class F {
    public static final String PROPERTY_FILE_KEY = "com.technojeeves.factory";
    protected static F instance;
    protected Map<String, Class> classes = new TreeMap<String, Class>();
    protected Properties keysToClassNames

    private F() {
        try {
            initFromSystemProperties();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getClass().getName() + ": " +
                                       ex.getMessage());
        }
    }

    private F(Map<String, Class> mappings) {
        classes = mappings;
    }

    public static F getFactory() {
        if (instance == null) {
            instance = new F();
        }

        return instance;
    }

    public static F getFactory(Map<String, Class> mappings) {
        if (instance == null) {
            instance = new F(mappings);
        }

        return instance;
    }

    public Object newInstance(String abstractName, Class<?>[] paramTypes,
                              Object[] params) {
        Object obj = null;

        try {
            Class<?> cls = classes.get(abstractName);

            if (cls == null) {
                throw new RuntimeException("No class registered under " +
                                           abstractName);
            }

            Constructor<?> ctor = cls.getConstructor(paramTypes);
            obj = ctor.newInstance(params);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return obj;
    }

    public Object newInstance(String abstractName) {
        return newInstance(abstractName, new Class[] {  }, new Object[] {  });
    }

    protected void initFromSystemProperties() throws Exception {
        String fileName = System.getProperty(PROPERTY_FILE_KEY);

        if (fileName == null) {
            //TODO
            throw new RuntimeException("No system property " +
                                       PROPERTY_FILE_KEY + " found");
        }

        keysToClassNames = new Properties();
        FileInputStream fi = new FileInputStream(fileName);
        keysToClassNames.load(fi);
        fi.close();

        Iterator it = keysToClassNames.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String abstractName = (String) entry.getKey();
            String className = (String) entry.getValue();
            Class cls = Class.forName(className);
            classes.put(abstractName, cls);
        }
    }
}