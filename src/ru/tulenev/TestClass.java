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
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//                TaskFinder.Node result1 = TaskFinder
//                        .findTaskHavingMaxPriorityInGroup(TaskFinder.tasks, 1)
//                        .orElse(TaskFinder.task(111,"111",111));
//
//                System.out.println("Result with group 1 - "
//                        + "\nid = " + result1.getId()
//                        + "\nname = " + result1.getName()
//                        + "\npriority = " + result1.getPriority());
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                TaskFinder.testFindTaskHavingMaxPriorityInGroup();

        }


//                TaskFinder.findTaskHavingMaxPriorityInGroup(TaskFinder.tasks, 1);
//                TaskFinder.findTaskHavingMaxPriorityInGroup(TaskFinder.tasks, 12);
//                TaskFinder.findTaskHavingMaxPriorityInGroup(TaskFinder.tasks, 13);
}

