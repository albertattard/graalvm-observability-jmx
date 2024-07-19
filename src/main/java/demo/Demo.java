package demo;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Demo implements DemoMBean {

    private boolean exit;
    private LocalDateTime time;

    public Demo() {
        this(false, LocalDateTime.now());
    }

    public Demo(final boolean exit, final LocalDateTime time) {
        this.exit = exit;
        this.time = requireNonNull(time);
    }

    @Override
    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public void setTime(final LocalDateTime time) {
        this.time = requireNonNull(time);
    }

    @Override
    public boolean isExit() {
        return exit;
    }

    @Override
    public void exit() {
        this.exit = true;
    }

    @Override
    public String printStatus() {
        return "The time is: %s".formatted(time);
    }

    @Override
    public boolean equals(final Object object) {
        return object instanceof final Demo other
                && exit == other.exit
                && Objects.equals(time, other.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exit, time);
    }

    @Override
    public String toString() {
        return "Demo[exit=%s, time=%s]".formatted(exit, time);
    }
}
