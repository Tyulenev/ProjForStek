package ru.tulenev.ex2;

import ru.tulenev.TestRunner;

import java.util.*;

public class TaskFinderV2 {

    public static class Node {
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

        //Getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getPriority() {
            return priority;
        }

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

    public static Node task(int id, String name, int priority) {
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


    static Node maxPriorityNode = null;
    public static Optional<Node> findTaskHavingMaxPriorityInGroup(Node tasks, int groupId) throws Exception {
        // ------------------------------------------------------------------------------------------------
        // Решение задачи 2
        // ------------------------------------------------------------------------------------------------
        scanTasks(tasks, groupId);
        //Печать
        for (Node nn1:fulNodeList) {
            System.out.println("id - " + nn1.id + ", Node name - " + nn1.name);
        }
        //Поиск максимума
        for (Node nn1:fulNodeList) {
            if (!nn1.isGroup()) {
                if (maxPriorityNode == null) {
                    maxPriorityNode = nn1;
                } else if (maxPriorityNode.priority < nn1.priority) {
                    maxPriorityNode = nn1;
                }
            }
        }

        if (fulNodeList.size() ==0) {
            throw new Exception("Нет группы с таким номером!!!!");
        }

        return Optional.ofNullable(maxPriorityNode);

    }

    static ArrayList<Node> fulNodeList = new ArrayList<>();

    public static void scanTasks(Node tasks, int groupId) {
        final int groupIdIntro = -999;
        if (tasks.isGroup()) {
            if (tasks.id == groupId) {
                fulNodeList.add(tasks);
            }

            if ((tasks.id == groupId) || (groupId == groupIdIntro)) {
                if (tasks.children.size()>0) {
                    for (Node n:tasks.children) {
                        if (n!= null) {
                            fulNodeList.add(n);
                        }
                        scanTasks(n, groupIdIntro);
                    }
                }
            }

            else {
                if (tasks.children.size() > 0) {
                    for (Node n : tasks.children) {
                        scanTasks(n, groupId);
                    }
                }
            }
        }
    }


    public static void testFindTaskHavingMaxPriorityInGroup() {
        TestRunner runner = new TestRunner("findTaskHavingMaxPriorityInGroup");

        runner.expectException(() -> findTaskHavingMaxPriorityInGroup(tasks, 13));
        runner.expectException(() -> findTaskHavingMaxPriorityInGroup(tasks, 2));

        runner.expectFalse(() -> findTaskHavingMaxPriorityInGroup(tasks, 12).isPresent());

        runner.expectTrue(() -> findTaskHavingMaxPriorityInGroup(tasks, 0).get()
                .equals(task(8, "Выполнение тестов", 6)));
        runner.expectTrue(() -> findTaskHavingMaxPriorityInGroup(tasks, 1).get()
                .equals(task(3, "Подготовка релиза", 4)));

        runner.expectTrue(() -> findTaskHavingMaxPriorityInGroup(tasks, 9).get().priority == 3);
    }
}
