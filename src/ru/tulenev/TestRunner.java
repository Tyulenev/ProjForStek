package ru.tulenev;

public class TestRunner {
    public TestRunner(String name) {
        this.name = name;
    }

    public interface BooleanTestCase {
        boolean run();
    }

    public    void expectTrue(BooleanTestCase cond) {
        try {
            if (cond.run()) {
                pass();
            }
            else {
                fail();
            }
        }
        catch (Exception e) {
            fail(e);
        }
    }

    public void expectFalse(BooleanTestCase cond) {
        expectTrue(() -> !cond.run());
    }

    public interface ThrowingTestCase {
        void run() throws Exception;
    }

    public void expectException(ThrowingTestCase block) {
        try {
            block.run();
            fail();
        }
        catch (Exception e) {
            pass();
        }
    }

    private void fail() {
        System.out.printf("FAILED: Test %d of %s\n", testNo++, name);
    }

    private void fail(Exception e) {
        fail();
        e.printStackTrace(System.out);
    }

    private void pass() {
        System.out.printf("PASSED: Test %d of %s\n", testNo++, name);
    }

    private String name;
    private int testNo = 1;
}
