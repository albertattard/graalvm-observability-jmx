package demo;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(final String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        final Demo demo = new Demo();

        final ObjectName objectName = new ObjectName("demo.graalvm:name=Demo");
        final MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(demo, objectName);

        /* Keep running until the exit operation is triggered through JMX */
        System.out.println("This application will keep running until the exit operation is triggered through JMX");
        while (!demo.isExit()) {
            TimeUnit.SECONDS.sleep(1);
            demo.setTime(LocalDateTime.now());
        }

        System.out.println("Done");
    }
}
