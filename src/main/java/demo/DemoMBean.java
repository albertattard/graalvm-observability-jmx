package demo;

import java.time.LocalDateTime;

public interface DemoMBean {

    LocalDateTime getTime();

    void setTime(LocalDateTime time);

    boolean isExit();

    void exit();

    String printStatus();
}
