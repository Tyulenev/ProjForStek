package ru.tulenev.ex2;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TaskFinder {

    static class Node {
        Node(int id, String name, Integer priority, List<Node> children) {
            this.id = id;
            this.name = name;
            this.priority = priority;
            this.children = children;
        }

        boolean isGroup() {
            return children != null;
        }

        int id;
        String name;
        Integer priority;
        List<Node> children;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Node node = (Node) o;
            return id == node.id
                    && name.equals(node.name)
                    && Objects.equals(priority, node.priority)
                    && Objects.equals(children, node.children);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, priority, children);
        }
    }

    static Node task(int id, String name, int priority) {
        return new Node(id, name, priority, null);
    }

    static  Node group(int id, String name, Node... children) {
        return new Node(id, name, null, Arrays.asList(children));
    }


    public static Node tasks =
            group(0, "Все задачи",
                    group(1, "Разработка",
                            task(2, "Планирование разработок", 1),
                            task(3, "Подготовка релиза", 4),
                            task(4, "Оптимизация", 2)),
                    group(5, "Тестирование",
                            group(6, "Ручное тестирование",
                                    task(7, "Составление тест-планов", 3),
                                    task(8, "Выполнение тестов", 6)),
                            group(9, "Автоматическое тестирование",
                                    task(10, "Составление тест-планов", 3),
                                    task(11, "Написание тестов", 3))),
                    group(12, "Аналитика"));


    static Node maxPriorityNode = task(-1, "-1", -1);
    public static Optional<Node> findTaskHavingMaxPriorityInGroup(Node tasks, int groupId) {
        // ------------------------------------------------------------------------------------------------
        // Решение задачи 2
        // ------------------------------------------------------------------------------------------------
        final int groupIdIntro = -999;
        Optional<Node> returnVal = null;

        if (tasks.isGroup()) {
            System.out.println("Obj with id-" + tasks.id + " have a children");
            if (tasks.id == groupId) {
                //Ищем тогда с максимальным приоритетом
                for (Node n:tasks.children) {
                    System.out.println("ID here! now, name = " + n.name + ", id = " + n.id);
                    findTaskHavingMaxPriorityInGroup(n, groupIdIntro);
                }
            } else {
                for (Node n:tasks.children) {
                    System.out.println("ID here! now, name = " + n.name + ", id = " + n.id);
                    findTaskHavingMaxPriorityInGroup(n, groupId);
                }
            }

        } else { //Если нет наследников - то что нужно!
            System.out.println("CHILDREN! now, name = " + tasks.name + ", id = " + tasks.id);
            if (groupId==groupIdIntro) {
                if (maxPriorityNode.priority < tasks.priority)  {
                    maxPriorityNode = tasks;
                    System.out.println("maxPriotityNode: " + maxPriorityNode.id + ", " + maxPriorityNode.name
                            + ", priority = " + maxPriorityNode.priority);
                }
            }
        }
        System.out.println("maxPriotityNode: " + maxPriorityNode.id + ", " + maxPriorityNode.name
                + ", priority = " + maxPriorityNode.priority);
        return returnVal;
    }


//    static void testFindTaskHavingMaxPriorityInGroup() {
//        TestRunner runner = new TestRunner("findTaskHavingMaxPriorityInGroup");
//
//        runner.expectException(() -> findTaskHavingMaxPriorityInGroup(tasks, 13));
//        runner.expectException(() -> findTaskHavingMaxPriorityInGroup(tasks, 2));
//
//        runner.expectFalse(() -> findTaskHavingMaxPriorityInGroup(tasks, 12).isPresent());
//
//        runner.expectTrue(() -> findTaskHavingMaxPriorityInGroup(tasks, 0).get()
//                .equals(task(8, "Выполнение тестов", 6)));
//        runner.expectTrue(() -> findTaskHavingMaxPriorityInGroup(tasks, 1).get()
//                .equals(task(3, "Подготовка релиза", 4)));
//
//        runner.expectTrue(() -> findTaskHavingMaxPriorityInGroup(tasks, 9).get().priority == 3);
//    }
}
