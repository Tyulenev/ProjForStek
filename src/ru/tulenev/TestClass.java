package ru.tulenev;
import junit.textui.TestRunner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import ru.tulenev.ex1.Matcher;
import ru.tulenev.ex2.TaskFinder;


public class TestClass {
        public static void main(String[] args) throws Exception {
                //Matcher.testMatch();
                TaskFinder.findTaskHavingMaxPriorityInGroup(TaskFinder.tasks, 1);
        }
}
